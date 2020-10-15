package com.example;

import com.example.service.OrderBook;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderBookTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 3, 5, 7})
    void getSizeAtPrice_ShouldBeZero(int price) {
        OrderBook orderBook = new OrderBook();
        orderBook.updateBidForPrice(4, 1);
        orderBook.updateAskForPrice(6, 1);
        Assert.assertEquals("0", orderBook.getSizeAtPrice(price));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6})
    void getSizeAtPrice_ShouldBeNotZero(int price) {
        OrderBook orderBook = new OrderBook();
        orderBook.updateBidForPrice(4, 1);
        orderBook.updateAskForPrice(6, 1);
        Assert.assertNotEquals("0", orderBook.getSizeAtPrice(price));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6})
    void getSizeAtPrice_ShouldBeNotZeroAndSame(int price) {
        OrderBook orderBook = new OrderBook();
        orderBook.updateBidForPrice(4, 2);
        orderBook.updateAskForPrice(6, 2);
        Assert.assertEquals("2", orderBook.getSizeAtPrice(price));
    }

    @Test
    void queryBestAsk_CheckAfterSet() {
        OrderBook orderBook = new OrderBook();
        orderBook.updateAskForPrice(6, 2);
        Assert.assertEquals("6,2", orderBook.queryBestAsk());
    }

    @Test
    void queryBestAsk_CheckAfterChange() {
        OrderBook orderBook = new OrderBook();
        orderBook.updateAskForPrice(6, 10);
        orderBook.buyOrder(2);
        Assert.assertEquals("6,8", orderBook.queryBestAsk());
    }

    @Test
    void queryBestBid_CheckAfterSet() {
        OrderBook orderBook = new OrderBook();
        orderBook.updateBidForPrice(6, 2);
        Assert.assertEquals("6,2", orderBook.queryBestBid());
    }

    @Test
    void queryBestBid_CheckAfterChange() {
        OrderBook orderBook = new OrderBook();
        orderBook.updateBidForPrice(6, 2);
        orderBook.sellOrder(1);
        Assert.assertEquals("6,1", orderBook.queryBestBid());
    }

    @Test
    void sellOrder_SellSome() {
        OrderBook orderBook = new OrderBook();
        orderBook.updateBidForPrice(4, 20);
        orderBook.sellOrder(5);
        Assert.assertEquals("4,15", orderBook.queryBestBid());
    }

    @Test
    void buyOrder_BuySome() {
        OrderBook orderBook = new OrderBook();
        orderBook.updateAskForPrice(40, 5);
        orderBook.buyOrder(2);
        Assert.assertEquals("40,3", orderBook.queryBestAsk());
    }
}