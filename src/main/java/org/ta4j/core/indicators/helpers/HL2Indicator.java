package org.ta4j.core.indicators.helpers;

import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.indicators.CachedIndicator;
import org.ta4j.core.num.Num;

public class HL2Indicator extends CachedIndicator<Num> {
    public HL2Indicator(BarSeries series) {
        super(series);
    }

    @Override
    protected Num calculate(int index) {
        Bar bar = getBarSeries().getBar(index);

        Num highPrice = bar.getHighPrice();
        Num lowPrice = bar.getLowPrice();

        return highPrice.plus(lowPrice).dividedBy(getBarSeries().numFactory().numOf(2));
    }

    @Override
    public int getCountOfUnstableBars() {
        return 0;
    }
}
