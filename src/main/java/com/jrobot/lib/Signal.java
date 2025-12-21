package com.jrobot.lib;


import com.google.gson.Gson;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

public class Signal implements Serializable {
    @Serial
    private static final long serialVersionUID = 8274132466435110L;
    private static final Gson GSON = new Gson();

    String base;
    String counter;
    String interval;
    String strategy;
    BigDecimal takeProfit;
    BigDecimal stopLoss;

    String data;

    public Signal(String base, String counter, String interval, String strategy, BigDecimal takeProfit, BigDecimal stopLoss, String data) {
        this.base = base;
        this.counter = counter;
        this.interval = interval;
        this.strategy = strategy;
        this.takeProfit = takeProfit;
        this.stopLoss = stopLoss;
        this.data = data;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public BigDecimal getTakeProfit() {
        return takeProfit;
    }

    public void setTakeProfit(BigDecimal takeProfit) {
        this.takeProfit = takeProfit;
    }

    public BigDecimal getStopLoss() {
        return stopLoss;
    }

    public void setStopLoss(BigDecimal stopLoss) {
        this.stopLoss = stopLoss;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("%S/%S %S", base, counter, strategy);
    }

    public String toJson() {
        return GSON.toJson(this);
    }

    public static Signal fromJson(String json) {
        return GSON.fromJson(json, Signal.class);
    }
}
