package com.example.commands;

import com.example.OrderBook;

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
