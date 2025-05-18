package com.jrobot.lib;

import java.io.Serializable;
import java.math.BigDecimal;

public class KlineData implements Serializable {
    String i; // interval
    Long ot;
    Long ct;
    BigDecimal o;
    BigDecimal c;
    BigDecimal h;
    BigDecimal l;
    BigDecimal v;
    boolean x;
    String b; //base
    String cp; //counter
    BigDecimal qv; //quote volume
    Long tradeCount;
    BigDecimal buyBase; // taker buy base asset volume
    BigDecimal buyQuote; // taker buy quote asset volume

    public KlineData() {
    }

    public KlineData(String i, Long ot, Long ct, BigDecimal o, BigDecimal c, BigDecimal h, BigDecimal l, BigDecimal v, boolean x, String b, String cp, BigDecimal qv, Long tradeCount, BigDecimal buyBase, BigDecimal buyQuote) {
        this.i = i;
        this.ot = ot;
        this.ct = ct;
        this.o = o;
        this.c = c;
        this.h = h;
        this.l = l;
        this.v = v;
        this.x = x;
        this.b = b;
        this.cp = cp;
        this.qv = qv;
        this.tradeCount = tradeCount;
        this.buyBase = buyBase;
        this.buyQuote = buyQuote;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public Long getOt() {
        return ot;
    }

    public void setOt(Long ot) {
        this.ot = ot;
    }

    public Long getCt() {
        return ct;
    }

    public void setCt(Long ct) {
        this.ct = ct;
    }

    public BigDecimal getO() {
        return o;
    }

    public void setO(BigDecimal o) {
        this.o = o;
    }

    public BigDecimal getC() {
        return c;
    }

    public void setC(BigDecimal c) {
        this.c = c;
    }

    public BigDecimal getH() {
        return h;
    }

    public void setH(BigDecimal h) {
        this.h = h;
    }

    public BigDecimal getL() {
        return l;
    }

    public void setL(BigDecimal l) {
        this.l = l;
    }

    public BigDecimal getV() {
        return v;
    }

    public void setV(BigDecimal v) {
        this.v = v;
    }

    public boolean isX() {
        return x;
    }

    public void setX(boolean x) {
        this.x = x;
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

    public BigDecimal getQv() {
        return qv;
    }

    public void setQv(BigDecimal qv) {
        this.qv = qv;
    }

    public Long getTradeCount() {
        return tradeCount;
    }

    public void setTradeCount(Long tradeCount) {
        this.tradeCount = tradeCount;
    }

    public BigDecimal getBuyBase() {
        return buyBase;
    }

    public void setBuyBase(BigDecimal buyBase) {
        this.buyBase = buyBase;
    }

    public BigDecimal getBuyQuote() {
        return buyQuote;
    }

    public void setBuyQuote(BigDecimal buyQuote) {
        this.buyQuote = buyQuote;
    }
}
