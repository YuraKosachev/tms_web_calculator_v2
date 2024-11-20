package com.app.web_calculator_v2.interfaces;

import com.app.web_calculator_v2.models.ValidationResult;

public interface Validator<T> {
    ValidationResult validate(T input);
}
