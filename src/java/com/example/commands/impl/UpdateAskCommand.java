package com.example.commands.impl;

import com.example.service.OrderBook;
import com.example.commands.Command;

public class UpdateAskCommand implements Command {
    private final int price;
    private final int size;

    public UpdateAskCommand(int price, int size) {
        this.price = price;
        this.size = size;
    }

    @Override
    public String execute(OrderBook orderBook) {
        orderBook.updateAskForPrice(price, size);
        return null;
    }

    @Override
    public String toString() {
        return "command:ask_update:price=" + price + ":size=" + size;
    }
}
