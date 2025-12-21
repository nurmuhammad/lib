package com.jrobot.lib;

public interface IPair {
    String getBase();
    String getCounter();

    default String toPairString() {
        return getBase() + "/" + getCounter();
    }
}
