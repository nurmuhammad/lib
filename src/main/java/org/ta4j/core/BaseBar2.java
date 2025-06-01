package org.ta4j.core;

import com.jrobot.lib.Interval;
import org.ta4j.core.num.DecimalNum;
import org.ta4j.core.num.Num;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;

public class BaseBar2 extends BaseBar {

    String name;

    boolean closedBar = false;

    public BaseBar2(String interval, Long beginTime, Long endTime, BigDecimal openPrice, BigDecimal highPrice, BigDecimal lowPrice, BigDecimal closePrice,
                    BigDecimal volume, BigDecimal amount, long trades, String name, boolean closedBar) {
        super(Interval.duration(interval), Instant.ofEpochMilli(endTime), DecimalNum.valueOf(openPrice),
                DecimalNum.valueOf(highPrice), DecimalNum.valueOf(lowPrice), DecimalNum.valueOf(closePrice),
                DecimalNum.valueOf(volume), DecimalNum.valueOf(amount), trades);
        this.beginTime = Instant.ofEpochMilli(beginTime);
        this.name = name;
        this.closedBar = closedBar;
    }

    public BaseBar2(Duration timePeriod, Instant beginTime, Instant endTime, Num openPrice, Num highPrice, Num lowPrice, Num closePrice,
                    Num volume, Num amount, long trades, String name, boolean closedBar) {
        super(timePeriod, endTime, openPrice, highPrice, lowPrice, closePrice, volume, amount, trades);
        this.beginTime = beginTime;
        this.name = name;
        this.closedBar = closedBar;
    }

    public void change(BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close, BigDecimal volume, Num amount, long trades, boolean closedBar){
        this.openPrice = DecimalNum.valueOf(open);
        this.highPrice = DecimalNum.valueOf(high);
        this.lowPrice = DecimalNum.valueOf(low);
        this.closePrice = DecimalNum.valueOf(close);
        this.volume = DecimalNum.valueOf(volume);
        this.amount = amount;
        this.trades = trades;
        this.closedBar = closedBar;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isClosedBar() {
        return closedBar;
    }

    public void setClosedBar(boolean closedBar) {
        this.closedBar = closedBar;
    }
}
