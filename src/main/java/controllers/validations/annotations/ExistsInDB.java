package main.java.controllers.validations.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistsInDBValidator.class)
public @interface ExistsInDB {

    Class<? extends JpaRepository<?,?>> repo();
    String message() default "Entity must found in the repository";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
