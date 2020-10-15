package com.example.commands.impl;

import com.example.service.OrderBook;
import com.example.commands.Command;

public class QueryBestBidCommand implements Command {
    @Override
    public String execute(OrderBook orderBook) {
        return orderBook.queryBestBid();
    }

    @Override
    public String toString() {
        return "command:best_bid";
    }
}
