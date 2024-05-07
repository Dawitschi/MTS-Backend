package main.java.services;

import main.java.databank.game.game.Game;
import main.java.databank.game.gameplayer.GamePlayer;
import main.java.databank.game.player.Player;
import main.java.databank.game.score.Score;
import main.java.databank.game.team.Team;
import main.java.services.DBServices.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * This class is a helper class.
 * It does all the elo adjustments in the system.
 * Most algorithms in the class are loosely based on the following sources:
 * Source: <a href="https://de.wikipedia.org/wiki/Elo-Zahl">wikipedia</a>
 */
@Service
public class EloCalculatorService {
    /**
     * The maximum elo change per game
     */
    final double k = 30;

    /**
     * This variable changes the gradiation of the scale.
     * It means that with an elo difference of scaleGradation the chance of victory for the stronger team is about 90,9%.
     */
    final double scaleGradation = 400;

    @Autowired
    private GameService gameService;

    /**
     * This method reevaluates the players Elos after a game. And saves all participating Entitys of the game
     */
     public void reevaluateElos(Game game) {

        //Create the score
        HashMap<Team,Integer> score = new HashMap<>();
        score.put(game.getTeam1(),game.getTeam1Score());
        score.put(game.getTeam2(),game.getTeam2Score());

        //Get the teams average elo of each team
        HashMap<Team, Double> teamElos = new HashMap<>();
        for(Team team : score.keySet()) teamElos.put(team,team.getTotalElo());

        //Get the result S of the game
        HashMap<Team, Double> results = calculateResultWithGoals(score);

        //Calculate the expected result E of the Game
        HashMap<Team, Double> expectedResults = calculateExpectedValue(teamElos);

        //Adjusting the players elos
         HashMap<Player, Double> playerEloIncrease = adjustPlayerElos(results,expectedResults,teamElos);
         //Save all Entitys
         save(game, playerEloIncrease);
    }

    private void save(Game game, HashMap<Player, Double> playerEloIncrease) {
         for(GamePlayer gamePlayer : game.getTeam1().getPlayers()) {
             Score score = new Score(
                     gamePlayer.getPlayer(),
                     game,
                     game.getTimeOfGame(),
                     gameService.isTeamWinningTeam(game.getTeam1()),
                     playerEloIncrease.get(gamePlayer.getPlayer()),
                     game.getTeam1Score(),
                     game.getTeam2Score()
             );
             gameService.addScore(game, score);
         }
         for(GamePlayer gamePlayer : game.getTeam2().getPlayers()) {
             Score score = new Score(
                     gamePlayer.getPlayer(),
                     game,
                     game.getTimeOfGame(),
                     gameService.isTeamWinningTeam(game.getTeam2()),
                     playerEloIncrease.get(gamePlayer.getPlayer()),
                     game.getTeam2Score(),
                     game.getTeam1Score()
             );
             gameService.addScore(game, score);
         }
         gameService.saveGame(game);
    }

    /**
     * Adjusts the elos of the participating players.
     * First according to the expected result and then with respected to the elo distribution in the team.
     *
     * If there is only one player per team this is easy, because we can just use the normal formula Elo = Elo + k * (S - E)
     * But if there are multiple players per team, especially with a large elo difference this can cause problems.
     * P1 Elo = 1000, P2 Elo = 2000   -> Team average Elo of 1500
     * P3 Elo = 1500, P4 Elo = 1500   -> Team average Elo of 1500
     * Should P2 get the same reward as P1?
     * If we treat it the same as one player per team then if team P1,P2 wins they get an increase of the following:
     * k(= 30, for example) * (S(= 1, because they won (according to the simple formula)) - E(= 0.5, because they have the same average elo))
     * which means each player on the team would get 30 * (1 - 0.5) = 15 Elo points increase.
     * However, a distribution like P1 gets 20 points and P2 only 10 might be more expected.
     * This might also cause some problems, (atleast I think so even though I cant pinpoint them at the moment, lol)
     * but we are going with it for now
     * For this implementation we use the following formula:
     * TeamEloIncrease = The total elo increase of the team, calculated by the above formula for 1 player teams
     * Elo = Elo + TeamEloIncrease * (1 - (PlayerElo / TeamElo))
     *
     * This ensures a distribution where the lower elo player in the team gets more points than the higher elo player.
     * For example, in our above example P1 would get the elo increase mentioned above.
     * The calculation for P1 would go as follows:
     * TeamEloIncrease(=30, see above) * (1 - (PlayerElo(=1000) / TeamElo(=3000)))
     * 30 * (1 - (1/3)) = 30 * 2/3 = 20
     */
    private HashMap<Player, Double> adjustPlayerElos(HashMap<Team, Double> results, HashMap<Team, Double> expectedResults, HashMap<Team, Double> teamElos) {
        HashMap<Player, Double> playerEloIncrease = new HashMap<>();

        for(Team team : results.keySet()) {
            double s = results.get(team);
            double e = expectedResults.get(team);

            //The average elo increase/decrease per Player (That's why we multiply by team.size(), to get the elo increase/decrease of the whole team)
            double averagePlayerEloIncrease = k * (s - e);
            double teamEloIncrease = averagePlayerEloIncrease  * team.size();

            for(GamePlayer gamePlayer : team.getPlayers()) {
                //Sets every player of the teams elo to the increased value
                Player player = gamePlayer.getPlayer();
                player.setElo(player.getElo() + teamEloIncrease * (1 - gamePlayer.getElo()/teamElos.get(team)));
                playerEloIncrease.put(player, teamEloIncrease * (1 - gamePlayer.getElo()/teamElos.get(team)));
            }
        }
        return playerEloIncrease;
    }

    /**
     * Calculates the result on a scale from 0 to 1
     * In this Implementation 0 means loss and 1 means win.
     * No other factors such as goal difference play a part in the calculation.
     * @param score The final score of the game
     * @return Returns a Hashmap where every Team is mapped to their result
     */
    private HashMap<Team, Double> calculateResult(HashMap<Team, Integer> score) {
        HashMap<Team, Integer> copiedScore = score;
        HashMap<Team, Double> results = new HashMap<>();

        //Find the winning team
        int maxGoals = 0;
        Team winningTeam = null;
        for(Team team : copiedScore.keySet()) {
            int scoredGoals = copiedScore.get(team);
            winningTeam = (scoredGoals > maxGoals) ? team : winningTeam;
        }

        //Set winning teams score to 1 and everyone elses to 0
        copiedScore.remove(winningTeam);
        results.put(winningTeam, 1d);
        for(Team team : copiedScore.keySet()) {
            results.put(team, 0d);
        }

        return results;
    }

    /**
     * Calculates the result on a scale from 0 to 1
     * In this implementation the result is the teams goals divided by the highest amount of goals present in the game
     * @param score The final score of the game
     * @return Returns a Hashmap where every Team is mapped to their result
     */
    private HashMap<Team, Double> calculateResultWithGoals(HashMap<Team, Integer> score) {
        HashMap<Team, Integer> copiedScore = score;
        HashMap<Team, Double> results = new HashMap<>();

        //Find the highest goal count
        int maxGoals = 0;
        for(Team team : copiedScore.keySet()) {
            int scoredGoals = copiedScore.get(team);
            if(scoredGoals > maxGoals) {
                maxGoals = scoredGoals;
            }
        }

        for(Team team : copiedScore.keySet()) {
            double result = (copiedScore.get(team)/maxGoals);
            results.put(team, result);
        }

        return results;
    }

    /**
     * Calculates the expected result on a scale from 0 to 1
     * @return Returns a Hashmap where every Team is mapped to their expected result
     */
    private HashMap<Team, Double> calculateExpectedValue(HashMap<Team, Double> teamElos) {
        HashMap<Team, Double> expectedResults = new HashMap<>();
        HashMap<Team, Double> exponent = calculateExponent(teamElos);

        double sumOfExponents = exponent.values().stream().mapToDouble(i -> i).sum();
        for(Team team : teamElos.keySet()) {
            expectedResults.put(team,exponent.get(team)/(sumOfExponents));
        }

        return expectedResults;
    }

    /**
     * Calculates the exponents for the formula to calculate the expected result.
     * The calculated exponent is: 10^(teamAverageElo / scaleGradation)
     * @param teamElos The average elo of each team.
     * @return The exponent for each team.
     */
    private HashMap<Team, Double> calculateExponent(HashMap<Team, Double> teamElos) {
        HashMap<Team, Double> exponent = new HashMap<>();
        for(Team team : teamElos.keySet()) {
            exponent.put(team,Math.pow(10d,(teamElos.get(team)/scaleGradation)));
        }
        return exponent;
    }
}
