package main.java.controllers.validations.annotations.notexistsindb;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotExistsInDBValidator.class)
@Repeatable(value = WrapperNotExistsInDB.class)
public @interface NotExistsInDB {

    Class<? extends JpaRepository<?,?>> repo();
    String message() default "Entity must found in the repository";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
