package main.java;

import main.java.controllers.GameController;
import main.java.services.DBServices.AccountService;
import main.java.services.DBServices.GameService;
import main.java.services.DBServices.PlayerService;
import main.java.services.DBServices.TableService;
import main.java.services.EloCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

    public static void main(String[] args){
        SpringApplication.run(Main.class,args);
    }

    @Autowired
    private AccountService accountService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private EloCalculatorService eloCalculatorService;

    @Autowired
    private GameService gameService;

    @Autowired
    private TableService tableService;

    @Autowired
    private GameController gameController;

    /**
     * Basically the main method of our spring application
     * @param args incoming main method arguments
     * @throws Exception Many
     */
    @Override
    public void run(String... args) throws Exception {
        //Load ApplicationContext
        loadApplicationContext();

        //Do random bs
        //Generate random Players with their accounts
        /*

        //Careful this might create multiple current Games on a certain table
        for (int i = 0; i < 300; i++) {
            Account account = new Account(new ArrayList<>(), "password" + i, "token" + i, "username" + i, new ArrayList<Account>());
            Player player = new Player(account, "name" + i, "nickname" + i);
            accountService.addPlayer(player, account);
            accountService.saveAccount(account);
        }
        for (int i = 0; i<20; i++) {
            Table table = new Table("test" + i, i, i );
            tableService.saveTable(table);
        }
        for (int i = 0; i < 300; i++) {
            Random random = new Random();

            LocalDateTime now = LocalDateTime.now();
            int year = 60 * 60 * 24 * 365;
            LocalDateTime localDateTime = now.plusSeconds((long) random.nextInt( 2 * year) - year);

            List<Player> players = playerService.getAllPlayers();
            Player player1 = players.get(random.nextInt(players.size() - 1));
            players.remove(player1);
            Player player2 = players.get(random.nextInt(players.size() - 1));
            players.remove(player2);
            Player player3 = players.get(random.nextInt(players.size() - 1));
            players.remove(player3);
            Player player4 = players.get(random.nextInt(players.size() - 1));
            players.remove(player4);

            int score1;
            int score2;
            do {
                score1 = random.nextInt(11);
                score2 = random.nextInt(11);
            } while (score2 == score1);

            Table table = tableService.getAllTables().get(random.nextInt(tableService.getAllTables().size()));

            Game game = gameService.createGame(localDateTime, player1, player2, player3, player4,score1, score2, random.nextBoolean(), table);
            eloCalculatorService.reevaluateElos(game);

        }
        */





    }

    private void loadApplicationContext() {

    }
}

