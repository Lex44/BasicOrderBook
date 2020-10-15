package com.example.commands;

import com.example.OrderBook;

public class QueryBestAskCommand implements Command {
    @Override
    public String execute(OrderBook orderBook) {
        return orderBook.queryBestAsk();
    }
}