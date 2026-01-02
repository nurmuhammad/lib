package org.ta4j.core.indicators.helpers;

import org.ta4j.core.BarSeries;
import org.ta4j.core.indicators.ATRIndicator;
import org.ta4j.core.indicators.AbstractIndicator;
import org.ta4j.core.indicators.CachedIndicator;
import org.ta4j.core.num.Num;

public class AtrPercentageIndicator extends CachedIndicator<Num> {
    ATRIndicator atrIndicator;
    int barCount;

    public AtrPercentageIndicator(BarSeries series, int barCount) {
        super(series);
        this.barCount = barCount;
        atrIndicator = new ATRIndicator(series, barCount);
    }

    @Override
    protected Num calculate(int i) {
        if (i < barCount) {
            return null;
        }

        Num atr = atrIndicator.getValue(i);
        Num closePrice = getBarSeries().getBar(i).getClosePrice();
        return atr.dividedBy(closePrice).multipliedBy(getBarSeries().numFactory().numOf(100));
    }

    @Override
    public int getCountOfUnstableBars() {
        return barCount;
    }
}
