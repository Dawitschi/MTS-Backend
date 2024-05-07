package main.java;

import main.java.controllers.http.converters.account.TwoWayAccountConverter;
import main.java.controllers.http.converters.game.TwoWayGameConverter;
import main.java.controllers.http.converters.game.TwoWayGameplayerConverter;
import main.java.controllers.http.converters.game.TwoWayPlayerConverter;
import main.java.controllers.http.converters.game.TwoWayTeamConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
@ComponentScan
public class ApplicationConfig {
    @Bean
    public DefaultConversionService getConversionService() {
        DefaultConversionService service = new DefaultConversionService();
        service.addConverter(new TwoWayAccountConverter());
        service.addConverter(new TwoWayGameConverter());
        service.addConverter(new TwoWayGameplayerConverter());
        service.addConverter(new TwoWayTeamConverter());
        service.addConverter(new TwoWayPlayerConverter());
        return service;
    }
}
