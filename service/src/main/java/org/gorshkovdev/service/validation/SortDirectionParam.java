package org.gorshkovdev.service.validation;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.validation.*;
import java.lang.annotation.*;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
@Constraint(validatedBy = SortDirectionParamValidator.class)
@Target({PARAMETER})
@Retention(RUNTIME)
@Documented
public @interface SortDirectionParam {

  @SuppressWarnings("unused") String message() default "";

  @SuppressWarnings("unused") Class<?>[] groups() default {};

  @SuppressWarnings("unused") Class<? extends Payload>[] payload() default {};
}
