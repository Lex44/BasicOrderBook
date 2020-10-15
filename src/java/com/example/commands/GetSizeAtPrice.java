package com.example.commands;

import com.example.OrderBook;

public class GetSizeAtPrice implements Command {
    private final int price;

    public GetSizeAtPrice(int price) {
        this.price = price;
    }

    @Override
    public String execute(OrderBook orderBook) {
        return orderBook.getSizeAtPrice(price);
    }
}
