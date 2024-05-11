package main.java.controllers.validations.annotations.existsindb;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistsInDBValidator.class)
@Repeatable(value = WrapperExistsInDB.class)
public @interface ExistsInDB {

    Class<? extends JpaRepository<?,?>> repo();
    String message() default "Entity must found in the repository";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
