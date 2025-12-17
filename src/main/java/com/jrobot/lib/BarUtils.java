package com.jrobot.lib;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.jrobot.renko.Renko;
import com.jrobot.renko.wrappers.OHLCV;
import org.ta4j.core.*;
import org.ta4j.core.aggregator.HeikinAshiBarAggregator;
import org.ta4j.core.indicators.ATRIndicator;
import org.ta4j.core.num.DecimalNum;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BarUtils {

    public static BarSeries barSeries(HazelcastInstance hazelcastInstance, String klineMapName) {
        try {
            IMap<String, KlineData> klineDataMap_30m = hazelcastInstance.getMap(klineMapName);
            ArrayList<KlineData> klineDataList = new ArrayList<>(klineDataMap_30m.values());
            klineDataList.sort(Comparator.comparing(KlineData::getOt));

            List<Bar> bars = new ArrayList<>(klineDataList.size());
            for (KlineData k : klineDataList) {
                BaseBar2 bar2 = new BaseBar2(k.getI(), k.getOt(), k.getCt(), k.getO(), k.getH(), k.getL(), k.getC(), k.getV(), k.getQv(), k.getTradeCount(), k.name(), k.isX());
                bars.add(bar2);
            }

            return new BaseBarSeriesBuilder().withBars(bars).withName(klineMapName).build();
        } catch (Exception e) {
            return null;
        }

    }

    public static BarSeries barSeries(List<KlineData> klines, String klineName) {
        ArrayList<KlineData> klineDataList = new ArrayList<>(klines);
        klineDataList.sort(Comparator.comparing(KlineData::getOt));

        List<Bar> bars = new ArrayList<>(klineDataList.size());
        for (KlineData k : klineDataList) {
            BaseBar2 bar2 = new BaseBar2(k.getI(), k.getOt(), k.getCt(), k.getO(), k.getH(), k.getL(), k.getC(), k.getV(), k.getQv(), k.getTradeCount(), k.name(), k.isX());
            bars.add(bar2);
        }

        return new BaseBarSeriesBuilder().withBars(bars).withName(klineName).build();

    }

    public static BarSeries heikinAshiBarSeries(BarSeries barSeries) {
        HeikinAshiBarAggregator unit = new HeikinAshiBarAggregator();
        List<Bar> bars = unit.aggregate(barSeries.getBarData());
        return new BaseBarSeriesBuilder().withBars(bars).withName(barSeries.getName()).build();
    }

    public static BarSeries renkoBarSeriesAtr(BarSeries barSeries, int atrPeriod, double atrMultiplier) {
        return renkoBarSeriesAtr(barSeries, atrPeriod, atrMultiplier, -1);
    }

    public static BarSeries renkoBarSeriesAtr(BarSeries barSeries, int atrPeriod, double atrMultiplier, int returnBarSize) {
        ATRIndicator atr = new ATRIndicator(barSeries, atrPeriod);
        double brickSize = atr.getValue(barSeries.getEndIndex()).doubleValue() * atrMultiplier;
        return renkoBarSeries(barSeries, brickSize, returnBarSize);
    }

    public static BarSeries renkoBarSeries(BarSeries barSeries, double brickSize) {
        return renkoBarSeries(barSeries, brickSize, -1);
    }

    public static BarSeries renkoBarSeries(BarSeries barSeries, double brickSize, int returnBarSize) {
        List<OHLCV> ohlcvList = new ArrayList<>(barSeries.getBarCount());
        for (int i = 0; i < barSeries.getBarCount(); i++) {
            ohlcvList.add(new OHLCV(barSeries.getBar(i)));
        }

        Renko renko = new Renko(ohlcvList, brickSize);
        List<OHLCV> list = renko.renkodf("wicks");
        returnBarSize = returnBarSize <= 0 ? list.size() : returnBarSize;
        returnBarSize = Math.min(returnBarSize, list.size());
        List<Bar> listBars = new ArrayList<>(returnBarSize);
        int startIndex = list.size() - returnBarSize;
        list = new ArrayList<>(list.subList(startIndex, list.size()));

        Duration duration = barSeries.getFirstBar().getTimePeriod();
        Instant endTime = barSeries.getFirstBar().getEndTime();
        for (OHLCV ohlcv : list) {
            Bar bar = new BaseBar(duration, endTime,
                    DecimalNum.valueOf(ohlcv.getOpen()),
                    DecimalNum.valueOf(ohlcv.getHigh()),
                    DecimalNum.valueOf(ohlcv.getLow()),
                    DecimalNum.valueOf(ohlcv.getClose()),
                    DecimalNum.valueOf(ohlcv.getVolume()),
                    DecimalNum.valueOf(0), 0L);
            listBars.add(bar);
            endTime = endTime.plus(duration);
        }

        return new BaseBarSeriesBuilder().withBars(listBars).withName(barSeries.getName()).build();
    }

}
