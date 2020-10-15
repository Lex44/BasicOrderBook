package com.example.commands.impl;

import com.example.service.OrderBook;
import com.example.commands.Command;

public class GetSizeAtPriceCommand implements Command {
    private final int price;

    public GetSizeAtPriceCommand(int price) {
        this.price = price;
    }

    @Override
    public String execute(OrderBook orderBook) {
        return orderBook.getSizeAtPrice(price);
    }

    @Override
    public String toString() {
        return "command:get_size_at_price:price=" + price;
    }

}
