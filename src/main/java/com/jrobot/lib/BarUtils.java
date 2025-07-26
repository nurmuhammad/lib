package com.jrobot.lib;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar2;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.aggregator.HeikinAshiBarAggregator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BarUtils {

    public static BarSeries barSeries(HazelcastInstance hazelcastInstance, String klineMapName) {
        IMap<String, KlineData> klineDataMap_30m = hazelcastInstance.getMap(klineMapName);
        ArrayList<KlineData> klineDataList = new ArrayList<>(klineDataMap_30m.values());
        klineDataList.sort(Comparator.comparing(KlineData::getOt));

        List<Bar> bars = new ArrayList<>(klineDataList.size());
        for (KlineData k : klineDataList) {
            BaseBar2 bar2 = new BaseBar2(k.getI(), k.getOt(), k.getCt(), k.getO(), k.getH(), k.getL(), k.getC(), k.getV(), k.getQv(), k.getTradeCount(), k.name(), k.isX());
            bars.add(bar2);
        }

        return new BaseBarSeriesBuilder().withBars(bars).withName(klineMapName).build();

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

}
