package com.jrobot.lib;

public interface IPair {
    String getBase();
    String getCounter();

    default String toPairString() {
        return getBase() + "/" + getCounter();
    }

    default String toBinanceSymbol() {
        return getBase().toUpperCase() + getCounter().toUpperCase();
    }
}
