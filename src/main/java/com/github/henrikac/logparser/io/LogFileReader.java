package com.github.henrikac.logparser.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LogFileReader {
    public static List<String> readFile(String fileName) throws IOException {
        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Path.of(fileName), StandardCharsets.UTF_8)) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isBlank()) {
                    continue;
                }

                lines.add(line);
            }
        } catch (IOException e) {
            throw e;
        }

        return lines;
    }
}
