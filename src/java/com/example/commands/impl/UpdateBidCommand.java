package com.example.commands.impl;

import com.example.service.OrderBook;
import com.example.commands.Command;

public class UpdateBidCommand implements Command {
    private final int price;
    private final int size;

    public UpdateBidCommand(int price, int size) {
        this.price = price;
        this.size = size;
    }

    @Override
    public String execute(OrderBook orderBook) {
        orderBook.updateBidForPrice(price, size);
        return null;
    }

    @Override
    public String toString() {
        return "command:bid_update:price=" + price + ":size=" + size;
    }
}
