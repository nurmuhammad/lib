package com.jrobot.lib;


import java.io.Serial;
import java.io.Serializable;

public class Signal implements Serializable {
    @Serial
    private static final long serialVersionUID = 8274132466435110L;

    String base;
    String counter;
    String interval;
    String strategy;
    String data;

    public Signal(String base, String counter, String interval, String strategy, String data) {
        this.base = base;
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
}
