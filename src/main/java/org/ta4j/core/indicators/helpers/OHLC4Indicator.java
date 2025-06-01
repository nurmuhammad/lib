package org.ta4j.core.indicators.helpers;

import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.indicators.AbstractIndicator;
import org.ta4j.core.num.Num;

public class OHLC4Indicator extends AbstractIndicator<Num> {

    public OHLC4Indicator(BarSeries series) {
        super(series);
    }

    @Override
    public Num getValue(int index) {
        Bar bar = getBarSeries().getBar(index);
        return (bar.getOpenPrice()
                .plus(bar.getHighPrice())
                .plus(bar.getLowPrice())
                .plus(bar.getClosePrice()))
                .dividedBy(getBarSeries().numFactory().numOf(4));
    }

    @Override
    public int getCountOfUnstableBars() {
        return 0;
    }
}
