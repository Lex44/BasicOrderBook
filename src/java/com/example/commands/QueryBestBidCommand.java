package com.example.commands;

import com.example.OrderBook;

public class QueryBestBidCommand implements Command {
    @Override
    public String execute(OrderBook orderBook) {
        return orderBook.queryBestBid();
    }
}
