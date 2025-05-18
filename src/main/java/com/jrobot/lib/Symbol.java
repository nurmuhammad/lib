package com.jrobot.lib;

import java.io.Serializable;

public class Symbol implements Serializable {
    String b; //base
    String cp; //counter
    String i; // interval

    public Symbol() {
    }

    public Symbol(String b, String cp, String i) {
        this.b = b;
        this.cp = cp;
        this.i = i;
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
}
