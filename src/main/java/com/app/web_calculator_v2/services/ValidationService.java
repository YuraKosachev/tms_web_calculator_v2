package com.app.web_calculator_v2.services;

import com.app.web_calculator_v2.interfaces.Validator;
import com.app.web_calculator_v2.models.ValidationResult;
import com.app.web_calculator_v2.models.ValidatorResult;

public class ValidationService {

    public <T> ValidatorResult validate(T input, Validator<T>... validators) {
        ValidatorResult validatorResult = ValidatorResult.getInstance(true);
        for (Validator validator : validators) {
            ValidationResult result = validator.validate(input);
            validatorResult.setSuccess(result.isValid() && validatorResult.isSuccess());

            if (!result.isValid())
                validatorResult.addError(result.getMessage());
        }
        return validatorResult;
    }

    public static ValidationService getValidationService() {
        return new ValidationService();
    }
}
