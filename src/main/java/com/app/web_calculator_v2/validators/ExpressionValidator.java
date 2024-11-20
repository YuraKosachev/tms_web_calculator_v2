package com.app.web_calculator_v2.validators;

import com.app.web_calculator_v2.interfaces.Validator;
import com.app.web_calculator_v2.models.ValidationResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionValidator implements Validator<String> {
    private final String pattern;
    private final String message;
    public ExpressionValidator(String pattern, String message) {
        this.pattern = pattern;
        this.message = message;
    }
    @Override
    public ValidationResult validate(String input) {
        Pattern regPattern = Pattern.compile(pattern);
        Matcher matcher = regPattern.matcher(input);
        boolean result = matcher.find();
        return new ValidationResult(!result, message);
    }

    public static ExpressionValidator getInstance(String pattern, String message) {
        return new ExpressionValidator(pattern,message);
    }
}
