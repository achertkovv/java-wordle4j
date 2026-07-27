package ru.yandex.practicum;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import static java.nio.charset.StandardCharsets.UTF_8;

public class PrintWriter {
    String logFileName;

    public PrintWriter(String logFileName) {
        this.logFileName = logFileName;
    }

    public void write(Exception ex) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(logFileName, UTF_8, true))) {
            bw.write(LocalDateTime.now() + ": " + ex.getMessage());
            bw.newLine();
        }
    }
}
