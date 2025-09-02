package com.github.henrikac.logparser.core;

public enum LogLevel {
    TRACE,
    DEBUG,
    INFO,
    WARN,
    ERROR,
    CRITICAL;

    public static LogLevel fromString(String value) {
        return LogLevel.valueOf(value.toUpperCase());
    }
}
