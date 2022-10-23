package org.album.service.validation;

import jakarta.validation.*;
import jakarta.validation.constraintvalidation.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

/**
 * @since 1.0-SNAPSHOT
 */
@SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT)
public class SortDirectionParameterValidator implements ConstraintValidator<SortDirectionParameter, String> {

  @Override
  public void initialize(SortDirectionParameter constraintAnnotation) {
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return StringUtils.equalsIgnoreCase(value, Sort.Direction.ASC.toString())
        || StringUtils.equalsIgnoreCase(value, Sort.Direction.DESC.toString());
  }
}
