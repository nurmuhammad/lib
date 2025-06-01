package org.ta4j.core.indicators.helpers;

import org.ta4j.core.BarSeries;
import org.ta4j.core.Indicator;
import org.ta4j.core.indicators.AbstractIndicator;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.averages.EMAIndicator;
import org.ta4j.core.indicators.averages.SMAIndicator;
import org.ta4j.core.indicators.candles.DojiIndicator;
import org.ta4j.core.num.Num;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractHelperIndicator<T> extends AbstractIndicator<T> {
    protected ClosePriceIndicator closePriceIndicator;
    protected VolumeIndicator volumeIndicator;

    protected DojiIndicator dojiIndicator;

    private Map<String, Indicator> indicatorMap = new HashMap<>();

    public AbstractHelperIndicator(BarSeries series) {
        super(series);
    }

    protected ClosePriceIndicator closePrice() {
        if (closePriceIndicator == null) {
            closePriceIndicator = new ClosePriceIndicator(getBarSeries());
        }
        return closePriceIndicator;
    }

    protected VolumeIndicator volume() {
        if (volumeIndicator == null) {
            volumeIndicator = new VolumeIndicator(getBarSeries());
        }
        return volumeIndicator;
    }

    protected DojiIndicator doji() {
        if (dojiIndicator == null) {
            dojiIndicator = new DojiIndicator(getBarSeries(), 10, 0.05);
        }
        return dojiIndicator;
    }

    public Num vol(int index) {
        return volume().getValue(index);
    }

    public Num rsi(int index, int length) {
        RSIIndicator rsiIndicator = (RSIIndicator) indicatorMap.get("rsi_" + length);
        if (rsiIndicator == null) {
            rsiIndicator = new RSIIndicator(closePrice(), length);
            indicatorMap.put("rsi_" + length, rsiIndicator);
        }
        return rsiIndicator.getValue(index);
    }

    public Num ema(int index, int length) {
        EMAIndicator emaIndicator = (EMAIndicator) indicatorMap.get("ema_" + length);
        if (emaIndicator == null) {
            emaIndicator = new EMAIndicator(closePrice(), length);
            indicatorMap.put("ema_" + length, emaIndicator);
        }
        return emaIndicator.getValue(index);
    }

    public Num rsi4(int index) {
        return rsi(index, 4);
    }

    public Num rsi9(int index) {
        return rsi(index, 9);
    }

    public Num rsi14(int index) {
        return rsi(index, 14);
    }

    public Num ema9(int index) {
        return ema(index, 9);
    }

    public Num ema20(int index) {
        return ema(index, 20);
    }

    public Num ema50(int index) {
        return ema(index, 50);
    }

    public Num ema100(int index) {
        return ema(index, 100);
    }

    public Num ema150(int index) {
        return ema(index, 150);
    }

    public Num ema200(int index) {
        return ema(index, 200);
    }

    public Num smaVol(int index, int length) {
        SMAIndicator smaIndicator = (SMAIndicator) indicatorMap.get("sma_volume_" + length);
        if (smaIndicator == null) {
            smaIndicator = new SMAIndicator(volume(), length);
            indicatorMap.put("sma_volume_" + length, smaIndicator);
        }
        return smaIndicator.getValue(index);
    }

    public Num sma20Vol(int index) {
        return smaVol(index, 20);
    }

    public Num sma3Vol(int index) {
        return smaVol(index, 3);
    }

    public boolean doji(int index) {
        return doji().getValue(index);
    }


}
