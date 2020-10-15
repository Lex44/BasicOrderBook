package com.example;

import com.example.commands.CommandFactory;
import com.example.inputstreams.FileStringStream;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
public class BasicOrderBook {
    public static void main(String[] args) {
        if (args.length == 0) {
            log.error("Input file name expected!");
            return;
        }

        OrderBook orderBook = new OrderBook();

        Stream<String> commandStringStream = new FileStringStream(args[0]).toStream();
        if(commandStringStream == null)
            return;

        commandStringStream
                .map(CommandFactory::getCommand)
                .map(e -> e.execute(orderBook))
                .filter(Objects::nonNull)
                .forEach(e -> log.info("result: {}", e));
    }
}
