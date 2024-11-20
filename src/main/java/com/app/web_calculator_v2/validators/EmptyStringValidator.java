package com.app.web_calculator_v2.validators;

import com.app.web_calculator_v2.interfaces.Validator;
import com.app.web_calculator_v2.models.ValidationResult;

public class EmptyStringValidator implements Validator<String> {

    @Override
    public  ValidationResult validate(String input) {
        boolean isEmpty = input == null || input.isBlank();
        String message = isEmpty ? "input parameter is empty" : null;
        return new ValidationResult(!isEmpty, message);
    }

    public static EmptyStringValidator getInstance(){
        return new EmptyStringValidator();
    }
}
