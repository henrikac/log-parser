package com.github.henrikac.logparser.core;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {
    public LogParser() {}

    public List<LogRecord> parse(List<String> lines) throws EmptyLogFileException {
        if (lines.isEmpty()) {
            throw new EmptyLogFileException("Log file is empty");
        }

        ArrayList<LogRecord> records = new ArrayList<>();

        for (String line : lines) {
            records.add(this.parseLine(line));
        }

        return records;
    }

    public LogRecord parseLine(String line) {
        String regex = "^\\d{4}-\\d{2}-\\d{2}[ T]\\d{2}:\\d{2}:\\d{2}(?:\\.\\d{3})?(?:Z|[+\\-]\\d{2}:?\\d{2})?\\s+(TRACE|DEBUG|INFO|WARN|ERROR|CRITICAL)\\s+.*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);

        if (!matcher.matches()) {
            return null;
        }

        LogLevel level = LogLevel.fromString(matcher.group(1).trim());

        return new LogRecord(level, line);
    }
}
