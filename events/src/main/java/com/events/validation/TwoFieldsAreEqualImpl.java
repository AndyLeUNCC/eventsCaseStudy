package com.events.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;


public class TwoFieldsAreEqualImpl implements ConstraintValidator<TwoFieldsAreEqual, Object> {

	private String fieldOneName;
	private String fieldTwoName;

	@Override
	public void initialize(TwoFieldsAreEqual constraintAnnotation) {
		fieldOneName = constraintAnnotation.fieldOneName();
		fieldTwoName = constraintAnnotation.fieldTwoName();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		try {
			final String fieldOneValue = BeanUtils.getProperty(value, fieldOneName);
			final String fieldTwoValue = BeanUtils.getProperty(value, fieldTwoName);
			
			// both are null
			if ( fieldOneValue == null && fieldTwoValue == null ) {
				return true;
			}
			
			// if the are equal then return true
			if (fieldOneValue.equals(fieldTwoValue)) {
				return true;
			}

		} catch (Exception e) {
			// do nothing
		}

		context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                .addPropertyNode(fieldOneName)
                .addConstraintViolation();
               
		return false;
	}

}
