package com.events.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PhoneValidateImpl implements ConstraintValidator<PhoneValidate, String> {

    public static final Logger LOG = LoggerFactory.getLogger(PhoneValidateImpl.class);


    @Override
    public void initialize(PhoneValidate constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // a custom validation should validate 1 and only 1 thing.
        // since a check for not null or not empty already exists
        // we want this function to return true in that case which will
        // prevent the error message for this validation from displaying
        // when the incoming value is null or empty.
        // this is a good design pattern and should be implemented as the first
        // thing in all custom validations.
        if ( StringUtils.isEmpty(value) ) {
            return true;
        }

        //source from https://www.baeldung.com/java-regex-validate-phone-numbers
        String patterns 
        = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$" 
        + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$" 
        + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";

        Pattern pattern = Pattern.compile(patterns);
        Matcher matcher = pattern.matcher(value);
        return ( matcher.matches() );
    }

}
