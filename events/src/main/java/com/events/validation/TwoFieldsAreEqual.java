package com.events.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = TwoFieldsAreEqualImpl.class)
//@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface TwoFieldsAreEqual {

	String fieldOneName();

	String fieldTwoName();

	String message() default "{TwoFieldsAreEqual}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}