package org.ta4j.core.indicators.helpers;

import org.ta4j.core.Bar;
import org.ta4j.core.indicators.CachedIndicator;
import org.ta4j.core.num.Num;

public class SupertrendStatusIndicator extends CachedIndicator<Integer> {
    SupertrendIndicatorPlus supertrend;

    public SupertrendStatusIndicator(SupertrendIndicatorPlus indicator) {
        super(indicator);
    }

    @Override
    protected Integer calculate(int index) {
        Num closePrice = getBarSeries().getBar(index).getClosePrice();
        Num supertrendValue = supertrend.getValue(index);
        if (closePrice.isGreaterThan(supertrendValue)) {
            return 1;
        }
        if (closePrice.isLessThan(supertrendValue)) {
            return -1;
        }
        return 0;
    }

    @Override
    public int getCountOfUnstableBars() {
        return supertrend.getCountOfUnstableBars();
    }
}
