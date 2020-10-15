package com.example.commands;

import com.example.service.OrderBook;

public interface Command {
    String execute(OrderBook orderBook);
}
