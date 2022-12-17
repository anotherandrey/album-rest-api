package org.gorshkovdev.service.validation;

import jakarta.validation.*;
import jakarta.validation.constraintvalidation.*;
import org.apache.commons.lang3.StringUtils;

/**
 * @author gorshkovdev
 * @version 1.0-SNAPSHOT
 */
@SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT)
public class SortByParamValidator implements ConstraintValidator<SortByParam, String> {

  @Override
  public void initialize(SortByParam constraintAnnotation) {
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    SortByParam unwrap = context.unwrap(SortByParam.class);
    String sortByProperty = unwrap.sortByProperty();
    return StringUtils.equalsIgnoreCase(sortByProperty, value);
  }
}
