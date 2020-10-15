package com.example.commands.impl;

import com.example.service.OrderBook;
import com.example.commands.Command;

public class BuyOrderCommand implements Command {
    private final int size;

    public BuyOrderCommand(int size) {
        this.size = size;
    }

    @Override
    public String execute(OrderBook orderBook) {
        orderBook.buyOrder(size);
        return null;
    }

    @Override
    public String toString() {
        return "command:buy_order:size=" + size;
    }
}
