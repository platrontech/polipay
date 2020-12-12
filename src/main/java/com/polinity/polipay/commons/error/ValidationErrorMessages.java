package com.polinity.polipay.commons.error;

import java.util.Arrays;

public enum ValidationErrorMessages {
    // Default Error
    INVALID_VALUE_ERROR("", "Girilen değer uygun değil"),
    INVALID_VALUES_ERROR("", "Girilen değerler uygun değil");

    private final String field;
    private final String message;

    ValidationErrorMessages(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public static String getMessageByField(String field) {
        ValidationErrorMessages validationError = Arrays.stream(values())
                .filter(error -> error.field.equals(field))
                .findFirst()
                .orElse(INVALID_VALUE_ERROR);

        return validationError.getMessage();
    }

    public String getMessage() {
        return message;
    }

    public String getField() {
        return field;
    }
}
