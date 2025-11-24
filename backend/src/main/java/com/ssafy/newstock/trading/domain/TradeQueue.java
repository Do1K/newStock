package com.ssafy.newstock.trading.domain;

import com.ssafy.newstock.kis.domain.TradeItem;
import com.ssafy.newstock.trading.service.TradingHandleService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

@Getter
public class TradeQueue {


    private final Map<String, Queue<TradeItem>> buyQueue=new ConcurrentHashMap<>();
    private final Map<String, Queue<TradeItem>> sellQueue=new ConcurrentHashMap<>();
    private static TradeQueue instance=new TradeQueue();


    public static TradeQueue getInstance() {
        return instance;
    }

    public void addSell(String stockCode, TradeItem tradeItem) {
        Queue<TradeItem> queue = sellQueue.computeIfAbsent(stockCode, k -> new PriorityQueue<>());

        synchronized (queue) {
            queue.add(tradeItem);
        }
    }

    public void addBuy(String stockCode, TradeItem tradeItem) {

        Queue<TradeItem> queue = buyQueue.computeIfAbsent(stockCode, k -> new PriorityQueue<>());

        synchronized (queue) {
            queue.add(tradeItem);
        }
    }


}
