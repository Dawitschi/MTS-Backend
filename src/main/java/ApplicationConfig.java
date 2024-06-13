package main.java;

import main.java.controllers.converters.account.TwoWayAccountConverter;
import main.java.controllers.converters.game.TwoWayGameConverter;
import main.java.controllers.converters.game.TwoWayGameplayerConverter;
import main.java.controllers.converters.game.TwoWayPlayerConverter;
import main.java.controllers.converters.game.TwoWayTeamConverter;
import main.java.databank.accounts.Role;
import main.java.services.DBServices.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;


@Configuration
@ComponentScan
public class ApplicationConfig {
    @Bean
    public DefaultConversionService getConversionService(
            TwoWayAccountConverter twoWayAccountConverter,
            TwoWayGameConverter twoWayGameConverter,
            TwoWayGameplayerConverter twoWayGameplayerConverter,
            TwoWayTeamConverter twoWayTeamConverter,
            TwoWayPlayerConverter twoWayPlayerConverter
    ) {
        DefaultConversionService service = new DefaultConversionService();
        service.addConverter(twoWayAccountConverter);
        service.addConverter(twoWayGameConverter);
        service.addConverter(twoWayGameplayerConverter);
        service.addConverter(twoWayTeamConverter);
        service.addConverter(twoWayPlayerConverter);
        return service;
    }

    @Autowired
    private RoleService roleService;
    @Bean
    @Qualifier(value = "USER")
    public Role getUser() {
        return roleService.findByName("USER");
    }
}
