package com.polinity.polipay.commons.api.model;

public enum IparaEnvironment {
    PRODUCTION("P"),
    T("TEST");

    private final String value;

    IparaEnvironment(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
