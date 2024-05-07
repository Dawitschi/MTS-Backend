package main.java.controllers.validators;

import main.java.controllers.http.dtos.GameDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class GameCreationInfoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return GameDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        GameDTO gameCreationInfo = (GameDTO) target;

    }
}
