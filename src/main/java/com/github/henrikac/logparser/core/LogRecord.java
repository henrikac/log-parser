package com.github.henrikac.logparser.core;

import java.time.LocalDateTime;

public record LogRecord(
    LogLevel level,
    String line
) {}
