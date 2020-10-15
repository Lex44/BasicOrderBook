package com.example.inputstreams;


import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Slf4j
public class FileStringStream {
    private String path;

    public FileStringStream(@NotNull String path) {
        this.path = path;
    }

    public @Nullable Stream<String> toStream() {
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            return stream;
        } catch (FileNotFoundException fileNotFoundException) {
            log.error("Can't find file=["+path+"]!");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Read error for file=["+path+"]!");
        }

        return null;
    }
}
