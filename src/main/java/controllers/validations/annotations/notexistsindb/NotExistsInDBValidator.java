package main.java.controllers.validations.annotations.notexistsindb;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class NotExistsInDBValidator implements ConstraintValidator<NotExistsInDB, Object> {

    private NotExistsInDB constraintAnnotation;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void initialize(NotExistsInDB constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.constraintAnnotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(Object id, ConstraintValidatorContext constraintValidatorContext) {
        if (constraintAnnotation == null) return false;
        applicationContext.getBean(constraintAnnotation.repo());
        JpaRepository rep = applicationContext.getBean(constraintAnnotation.repo());
        return rep.existsById(id);
    }
}
