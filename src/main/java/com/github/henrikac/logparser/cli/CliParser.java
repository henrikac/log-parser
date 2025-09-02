package com.github.henrikac.logparser.cli;

import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CliParser {
    public static List<CommandLineOption> parse(String[] args) throws InvalidOptionException, InvalidOptionValueException {
        ArrayList<CommandLineOption> options = new ArrayList<>();
        int i = 0;

        while (i < args.length - 1) {
            String arg = args[i];

            if (!arg.startsWith("--")) {
                throw new InvalidOptionException("Invalid option: " + arg);
            }

            switch (arg) {
                case "--file":
                    if (i == args.length - 1) {
                        throw new InvalidOptionValueException("Option --file requires a value (missing path)");
                    }

                    String original = args[i + 1];
                    String value = original;

                    if (value.isBlank()) {
                        throw new InvalidOptionValueException("Option --file requires a value (missing path)");
                    } else if (value.startsWith("--")) {
                        throw new InvalidOptionValueException("Option --file requires a value, got flag: " + value);
                    }

                    if ("~".equals(value)) {
                        value = System.getProperty("user.home");
                    } else if (value.startsWith("~/")) {
                        value = System.getProperty("user.home") + value.substring(1);
                    }

                    Path path;
                    try {
                        path = Path.of(value).toAbsolutePath().normalize();
                    } catch (InvalidPathException e) {
                        throw new InvalidOptionValueException("Invalid path syntax for --file: " + original);
                    }

                    if (!Files.exists(path)) {
                        throw new InvalidOptionValueException("Invalid path for --file: " + original + " (file not found)");
                    } else if (!Files.isRegularFile(path, LinkOption.NOFOLLOW_LINKS)) {
                        throw new InvalidOptionValueException("Invalid path for --file: " + original + " (not a regular file)");
                    } else if (!Files.isReadable(path)) {
                        throw new InvalidOptionValueException("Invalid path for --file: " + original + " (not readable)");
                    }

                    i += 2;

                    options.add(new CommandLineOption(arg, path.toString()));
                    break;
                case "--level":
                    break;
                case "--format":
                    break;
                default:
                    throw new InvalidOptionException("Unknown option: " + arg);
            };

            i++;
        }

        return options;
    }
}
