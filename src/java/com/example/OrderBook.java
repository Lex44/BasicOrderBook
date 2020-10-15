package com.example;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

public class OrderBook {
    public static final String NA = "N/A";

    private final SortedMap<Integer, Integer> asksMap = new TreeMap<>();
    private final SortedMap<Integer, Integer> bidsMap = new TreeMap<>(Comparator.reverseOrder());

    public String queryBestAsk() {
        return asksMap.isEmpty() ? NA : String.valueOf(asksMap.firstKey());
    }

    public String queryBestBid() {
        return bidsMap.isEmpty() ? NA : String.valueOf(bidsMap.firstKey());
    }

    public String getSizeAtPrice(int price) {
        if (bidsMap.containsKey(price)) {
            return String.valueOf(bidsMap.get(price));
        }

        if (asksMap.containsKey(price)) {
            return String.valueOf(asksMap.get(price));
        }

        return String.valueOf(0);
    }

    private int processOrderInstantly(SortedMap<Integer,Integer> map,
                                      int price, int size, boolean isMapOrderAscend) {
        if(size == 0 || map.isEmpty()) {
            return size;
        }

        int firstPrice = map.firstKey();
        if ((!isMapOrderAscend && price > firstPrice)
                || (isMapOrderAscend && price < firstPrice)) {
            return size;
        }

        int firstSize = map.get(firstPrice);
        if (firstSize >= size) {
            map.put(price, firstSize - size);
            return 0;
        }

        map.remove(firstPrice);
        return processOrderInstantly(map, price, size - firstSize, isMapOrderAscend);
    }

    public void updateBidForPrice(int price, int size) {
        int notProcessedSize = size;
        if (notProcessedSize != 0 && !bidsMap.isEmpty()) {
            notProcessedSize = processOrderInstantly(bidsMap, price, size, true);
        }

        if (notProcessedSize != 0) {
            asksMap.put(price, notProcessedSize);
        }
    }

    public void updateAskForPrice(int price, int size) {
        int notProcessedSize = size;
        if (notProcessedSize != 0 && !bidsMap.isEmpty()) {
            notProcessedSize = processOrderInstantly(bidsMap, price, size, false);
        }

        if (notProcessedSize != 0) {
            asksMap.put(price, notProcessedSize);
        }
    }

    private int processSizeBest(SortedMap<Integer,Integer> map, int size) {
        int firstOrderSize = map.get(map.firstKey());
        if (firstOrderSize >= size) {
            map.put(map.firstKey(), firstOrderSize - size);
            return 0;
        }

        map.remove(map.firstKey());
        return size - firstOrderSize;
    }

    public void buyOrder(int size) {
        if(size == 0 || asksMap.isEmpty()) {
            return;
        }

        buyOrder(processSizeBest(asksMap, size));
    }

    public void sellOrder(int size) {
        if(size == 0 || bidsMap.isEmpty()) {
            return;
        }

        sellOrder(processSizeBest(bidsMap, size));
    }
}
