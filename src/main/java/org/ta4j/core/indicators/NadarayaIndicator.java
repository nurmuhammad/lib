package org.ta4j.core.indicators;


import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.indicators.AbstractIndicator;
import org.ta4j.core.num.Num;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Nurmuhammad
 * Date: 04/12/2023 14:49
 */
public class NadarayaIndicator extends AbstractIndicator<Integer> {
    Num h;// =  numOf(8.0); //Bandwidth
    Num mult;// = numOf(3.0); //mult

    public NadarayaIndicator(BarSeries series) {
        super(series);
        this.h = series.numFactory().numOf(8.0);
        this.mult = series.numFactory().numOf(3.0);
    }

    public NadarayaIndicator(BarSeries series, double h, double mult) {
        super(series);
        this.h = series.numFactory().numOf(h);
        this.mult = series.numFactory().numOf(mult);
    }

    private double gauss(double x, double h) {
        return Math.exp(-(Math.pow(x, 2) / (h * h * 2)));
    }


    @Override
    public Integer getValue(int index) {
        double y2 = 0;
        double y1 = 0;

        int n = index;

        List<Bar> barArrayList = getBarSeries().getBarData().reversed();

        ArrayList<Double> nwe = new ArrayList<>();
        if (index == getBarSeries().getEndIndex()) {
            double sae = 0d;
            for (int i = 0; i <= Math.min(499, n - 1); i++) {
                double sum = 0d;
                double sumw = 0d;
                for (int j = 0; j <= Math.min(499, n - 1); j++) {
                    double w = gauss(i - j, h.doubleValue());
                    Bar bar = barArrayList.get(j);
                    sum += bar.getClosePrice().doubleValue() * w;
                    sumw += w;
                }

                Bar bar2 = barArrayList.get(i);
                y2 = sum / sumw;
                sae += Math.abs(bar2.getClosePrice().doubleValue() - y2);
                nwe.add(y2);
            }


            sae = sae / Math.min(499, n - 1) * mult.doubleValue();
            for (int i = 0; i <= Math.min(499, n - 1); i++) {
                /*if (i%2==0)
                    line.new(n-i+1, y1 + sae, n-i, nwe.get(i) + sae, color = upCss)
                    line.new(n-i+1, y1 - sae, n-i, nwe.get(i) - sae, color = dnCss)*/
                Bar bar = barArrayList.get(i);
                Bar bar2 = barArrayList.get(i + 1);
/*
                if (bar.getClosePrice().doubleValue() > nwe.get(i) + sae && bar2.getClosePrice().doubleValue() < nwe.get(i) + sae) {
                    log.info("-1, index=" + (i) + ", date=" + bar.getSimpleDateName());
                }*/

                if (bar.getClosePrice().doubleValue() < nwe.get(i) - sae && bar2.getClosePrice().doubleValue() > nwe.get(i) - sae) {
                    int pos = getBarSeries().getBarData().indexOf(bar);
                    if (pos >= 0) {
                        return pos;
                    }
                    log.info("+1, index=" + (i) + ", date=" + bar2.getSimpleDateName());
                }

                y1 = nwe.get(i);

            }
//            log.info("sae=" + sae + ", y2=" + y2 + ", y1=" + y1 + ", nwe.last=" + nwe.get(nwe.size()-1));
        }

        return -1;
    }

    @Override
    public int getCountOfUnstableBars() {
        return 0;
    }
}
