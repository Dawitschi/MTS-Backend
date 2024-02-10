package main.java.controllers.validators;

import main.java.controllers.http.objects.GameRepresentation;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class GameCreationInfoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return GameRepresentation.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        GameRepresentation gameCreationInfo = (GameRepresentation) target;



    }
}
