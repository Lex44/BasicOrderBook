package com.example.commands;

import com.example.OrderBook;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class UnknownCommand implements Command {
    private final String command;

    public UnknownCommand(String command) {
        this.command = command != null ? command : CommandFactory.COMMAND_NULL;
    }

    public UnknownCommand(List<String> commandParts) {
        command = commandParts != null
                ? String.join(",", commandParts) : CommandFactory.COMMAND_NULL;
    }

    @Override
    public String execute(OrderBook orderBook) {
        log.error("Unknown command=[" + command + "]");
        return null;
    }

    @Override
    public String toString() {
        return "command:unknown:[" + command + "]";
    }
}
