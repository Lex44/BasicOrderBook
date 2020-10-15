package com.example;

import com.example.service.CommandFactory;
import com.example.service.OrderBook;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class BasicOrderBook {
    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";

    public static void main(String[] args) {
        OrderBook orderBook = new OrderBook();

        String newLine = System.getProperty("line.separator");
        try (Stream<String> commandStringStream = Files.lines(Paths.get(INPUT_FILE));
             FileWriter writeFile = new FileWriter(OUTPUT_FILE)) {
            commandStringStream
                    .map(CommandFactory::getCommand)
                    .map(e -> e.execute(orderBook))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList())
                    .forEach(e -> {
                        try {
                            writeFile.write(e + newLine);
                        } catch (IOException ioException) {
                            log.error("Cant write [" + e + "] to output.txt");
                        }
                    });
        } catch (IOException e) {
            log.error("Cant write to output.txt");
        }
    }
}
