package main.java;

import main.java.controllers.http.converters.account.AccountConverter;
import main.java.controllers.http.converters.account.AccountRepresentationConverter;
import main.java.controllers.http.converters.game.game.GameConverter;
import main.java.controllers.http.converters.game.game.GameRepresentationConverter;
import main.java.controllers.http.converters.game.gameplayer.GameplayerConverter;
import main.java.controllers.http.converters.game.gameplayer.GameplayerRepresentationConverter;
import main.java.controllers.http.converters.game.player.PlayerConverter;
import main.java.controllers.http.converters.game.player.PlayerRepresentationConverter;
import main.java.controllers.http.converters.game.team.TeamConverter;
import main.java.controllers.http.converters.game.team.TeamRepresentationConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;

/**
 * The main configuration of the application
 */
@Configuration
@ComponentScan
public class ApplicationConfig {

    @Bean
    public DefaultConversionService getConversionService() {
        DefaultConversionService service = new DefaultConversionService();
        service.addConverter(new AccountRepresentationConverter());
        service.addConverter(new AccountConverter());
        service.addConverter(new GameRepresentationConverter());
        service.addConverter(new GameConverter());
        service.addConverter(new GameplayerRepresentationConverter());
        service.addConverter(new GameplayerConverter());
        service.addConverter(new TeamRepresentationConverter());
        service.addConverter(new TeamConverter());
        service.addConverter(new PlayerRepresentationConverter());
        service.addConverter(new PlayerConverter());
        return service;
    }
}
