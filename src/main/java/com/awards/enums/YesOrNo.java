package com.awards.enums;

/**
 * Enum de sim ou não para padronizar valores.
 *
 * @author Osiel
 */
public enum YesOrNo {
    YES("yes"),
    NO("no");

    private final String value;

    YesOrNo(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
