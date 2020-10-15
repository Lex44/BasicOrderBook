package com.example.commands;

import java.util.Arrays;
import java.util.List;

public final class CommandFactory {
    public static final String COMMAND_NULL = "null";

    private static final String COMMAND_QUERY = "q";
    private static final String COMMAND_QUERY_PARAM_SIZE = "size";
    private static final String COMMAND_QUERY_PARAM_BEST_ASK = "best_ask";
    private static final String COMMAND_QUERY_PARAM_BEST_BID = "best_bid";
    public static final String COMMAND_FORMAT_QUERY_BEST_ASK = "q,best_ask";
    public static final String COMMAND_FORMAT_QUERY_BEST_BID = "q,best_bid";
    public static final String COMMAND_FORMAT_QUERY_SIZE = "q,size,%d";

    private static final String COMMAND_ORDER = "o";
    private static final String COMMAND_ORDER_PARAM_BUY = "buy";
    private static final String COMMAND_ORDER_PARAM_SELL = "sell";
    public static final String COMMAND_FORMAT_ORDER_BUY = "o,buy,%d";
    public static final String COMMAND_FORMAT_ORDER_SELL = "o,sell,%d";

    private static final String COMMAND_UPDATE = "u";
    private static final String COMMAND_UPDATE_PARAM_BID = "bid";
    private static final String COMMAND_UPDATE_PARAM_ASK = "ask";
    public static final String COMMAND_FORMAT_UPDATE_BID = "u,%d,%d,bid";
    public static final String COMMAND_FORMAT_UPDATE_ASK = "u,%d,%d,ask";

    private CommandFactory() {
    }

    private static Command getQueryCommand(List<String> commandParts) {
        String subCommand = commandParts.size() > 1 ? commandParts.get(1) : null;

        if (COMMAND_QUERY_PARAM_BEST_BID.equals(subCommand)) {
            return new QueryBestBidCommand();
        }

        if (COMMAND_QUERY_PARAM_BEST_ASK.equals(subCommand)) {
            return new QueryBestAskCommand();
        }

        if (commandParts.size() == 3
                && COMMAND_QUERY_PARAM_SIZE.equals(subCommand)) {
            try {
                int price = Integer.parseInt(commandParts.get(2));
                return new GetSizeAtPrice(price);
            } catch (NumberFormatException ignored) {
                return new UnknownCommand(commandParts);
            }
        }

        return new UnknownCommand(commandParts);
    }

    private static Command getOrderCommand(List<String> commandParts) {
        if(commandParts.size() < 3) {
            return new UnknownCommand(commandParts);
        }

        int size;
        try {
            size = Integer.parseInt(commandParts.get(2));
        } catch (NumberFormatException e){
            return new UnknownCommand(commandParts);
        }

        String subCommand = commandParts.size() > 1 ? commandParts.get(1) : null;
        if(COMMAND_ORDER_PARAM_BUY.equals(subCommand)){
            return new BuyOrderCommand(size);
        }

        if(COMMAND_ORDER_PARAM_SELL.equals(subCommand)){
            return new SellOrderCommand(size);
        }

        return new UnknownCommand(commandParts);
    }

    private static Command getUpdateCommand(List<String> commandParts) {
        if(commandParts.size() != 4) {
            return new UnknownCommand(commandParts);
        }

        int price;
        int size;
        try {
            price = Integer.parseInt(commandParts.get(1));
            size = Integer.parseInt(commandParts.get(2));
        } catch (NumberFormatException e){
            return new UnknownCommand(commandParts);
        }

        String subCommand = commandParts.get(3);
        if (COMMAND_UPDATE_PARAM_BID.equals(subCommand)) {
            return new UpdateBidCommand(price, size);
        }

        if (COMMAND_UPDATE_PARAM_ASK.equals(subCommand)) {
            return new UpdateAskCommand(price, size);
        }

        return new UnknownCommand(commandParts);
    }

    public static Command getCommand(String commandString) {
        if (commandString == null || commandString.length() == 0) {
            return new UnknownCommand(commandString);
        }

        List<String> commandParts = Arrays.asList(commandString.split(","));
        if (commandParts.size() == 1) {
            return new UnknownCommand(commandString);
        }

        String command = commandParts.get(0);
        if (COMMAND_QUERY.equals(command)){
            return getQueryCommand(commandParts);
        }

        if (COMMAND_ORDER.equals(command)) {
            return getOrderCommand(commandParts);
        }

        if (COMMAND_UPDATE.equals(command)) {
            return getUpdateCommand(commandParts);
        }

        return new UnknownCommand(commandString);
    }
}
