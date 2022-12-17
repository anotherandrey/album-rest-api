package org.gorshkovdev.service.validation;

import jakarta.validation.*;
import jakarta.validation.constraintvalidation.*;
import org.apache.commons.lang3.StringUtils;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
@SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT)
public class SortDirectionParamValidator implements
    ConstraintValidator<SortDirectionParam, String> {

  @Override
  public void initialize(SortDirectionParam constraintAnnotation) {
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return StringUtils.equalsAny(value, new String[]{"asc", "ASC", "desc", "DESC"});
  }
}
