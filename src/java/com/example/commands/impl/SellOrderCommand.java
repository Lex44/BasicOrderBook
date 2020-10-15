package com.example.commands.impl;

import com.example.service.OrderBook;
import com.example.commands.Command;

public class SellOrderCommand implements Command {
    private final int size;

    public SellOrderCommand(int size) {
        this.size = size;
    }

    @Override
    public String execute(OrderBook orderBook) {
        orderBook.sellOrder(size);
        return null;
    }

    @Override
    public String toString() {
        return "command:sell_order:size=" + size;
    }
}
