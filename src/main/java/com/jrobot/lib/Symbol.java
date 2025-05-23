package com.jrobot.lib;

import java.io.Serial;
import java.io.Serializable;

public class Symbol implements Serializable {

    @Serial
    private static final long serialVersionUID = 1453750327189350542L;

    String b; //base
    String cp; //counter
    String i; // interval
    int limit;

    public Symbol() {
    }

    public Symbol(String b, String cp, String i, int limit) {
        this.b = b;
        this.cp = cp;
        this.i = i;
        this.limit = limit;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "Symbol{" + b.toUpperCase() + '/' + cp.toUpperCase() + '_' + i + ", limit=" + limit + '}';
    }
}
