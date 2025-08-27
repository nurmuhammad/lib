package com.jrobot.lib;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AbbrevMoney {
    private static final BigDecimal K = new BigDecimal("1000");
    private static final BigDecimal M = new BigDecimal("1000000");
    private static final BigDecimal B = new BigDecimal("1000000000");
    private static final BigDecimal T = new BigDecimal("1000000000000");

    public static String fmt(BigDecimal v) {
        if (v == null) v = BigDecimal.ZERO;

        BigDecimal abs = v.abs();
        BigDecimal unit = BigDecimal.ONE;
        String suffix = "";

        if (abs.compareTo(T) >= 0) { unit = T; suffix = "T"; }
        else if (abs.compareTo(B) >= 0) { unit = B; suffix = "B"; }
        else if (abs.compareTo(M) >= 0) { unit = M; suffix = "M"; }
        else if (abs.compareTo(K) >= 0) { unit = K; suffix = "K"; }

        BigDecimal n = v.divide(unit, 2, RoundingMode.HALF_UP).stripTrailingZeros();
        return (v.signum() < 0 ? "-" : "") + "$" + n.toPlainString() + suffix;
    }

    public static void main(String[] args) {
        System.out.println(fmt(new BigDecimal("34380500")));   // $34.38M
        System.out.println(fmt(new BigDecimal("2500000000"))); // $2.5B
        System.out.println(fmt(new BigDecimal("45005000000000"))); // $45T
        System.out.println(fmt(new BigDecimal("1500")));       // $1.5K
        System.out.println(fmt(new BigDecimal("999")));        // $999
    }
}