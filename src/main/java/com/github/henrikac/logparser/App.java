package com.github.henrikac.logparser;

import com.github.henrikac.logparser.cli.CliParser;
import com.github.henrikac.logparser.cli.CommandLineOption;
import com.github.henrikac.logparser.cli.InvalidOptionException;
import com.github.henrikac.logparser.core.LogLevel;
import com.github.henrikac.logparser.core.LogParser;
import com.github.henrikac.logparser.core.LogRecord;
import com.github.henrikac.logparser.io.LogFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        try {
            List<CommandLineOption> options = CliParser.parse(args);

            ArrayList<String> lines = new ArrayList<>();
            ArrayList<LogLevel> levels = new ArrayList<>();

            for (CommandLineOption option : options) {
                if (option.getArg().equals("--file")) {
                    try {
                        lines.addAll(LogFileReader.readFile(option.getValue()));
                    } catch (IOException e) {
                        System.err.println("An error occurred while reading the file: " + e.getMessage());

                        System.exit(1);
                    }
                } else if (option.getArg().equals("--level")) {
                    levels.add(LogLevel.fromString(option.getValue()));
                }
            }

            try {
                LogParser parser = new LogParser();
                List<LogRecord> records = parser.parse(lines);

                for (LogRecord record : records) {
                    if (levels.contains(record.level())) {
                        System.out.println(record.line());
                    }
                }
            } catch (Exception e) {
                System.err.println("Something went wrong: " + e.getMessage());

                System.exit(1);
            }
        } catch (InvalidOptionException e) {
            System.out.println(e.getMessage());

            System.exit(1);
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());

            System.exit(1);
        }
    }
}
