package com.urijoel.curling.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Level {
    BASIC,
    MEDIUM,
    HIGH;

    @JsonCreator
    public static Level fromString(String value) {
        if (value == null) return null;
        switch (value.toUpperCase().trim()) {
            case "BASIC": case "BASICO": case "BÁSICO": case "BAJO": return BASIC;
            case "MEDIUM": case "MEDIO": return MEDIUM;
            case "HIGH": case "ALTO": return HIGH;
            default: return BASIC;
        }
    }
}
