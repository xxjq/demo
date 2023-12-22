package com.example.demo.validation;

import com.example.demo.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State, String> {

    /**
     * @param s                          checked value
     * @param constraintValidatorContext context
     * @return result
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return false;
        }
        return s.equals("已发布") || s.equals("草稿");
    }
}
