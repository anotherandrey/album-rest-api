package org.album.service.validation;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.validation.*;
import java.lang.annotation.*;

/**
 * @since 1.0-SNAPSHOT
 */
@Constraint(validatedBy = SortDirectionParameterValidator.class)
@Target({ PARAMETER })
@Retention(RUNTIME)
@Documented
public @interface SortDirectionParameter {

  @SuppressWarnings("unused")
  String message() default "sort direction allowed only asc or desc value";

  @SuppressWarnings("unused")
  Class<?>[] groups() default {};

  @SuppressWarnings("unused")
  Class<? extends Payload>[] payload() default {};
}
