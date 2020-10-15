package com.example.commands;

import com.example.OrderBook;

public interface Command {
    String execute(OrderBook orderBook);
}
