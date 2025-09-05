package com.jrobot.lib;

import org.ta4j.core.Bar;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.indicators.ATRIndicator;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.NumFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class RenkoBuilder {
    public static BarSeries toRenkoFixed(BarSeries input, double brickSize) {
        return buildRenko(input, brickSize, false, 14, 1.0);
    }

    public static BarSeries toRenkoAtr(BarSeries input, int atrPeriod, double atrMultiplier) {
        return buildRenko(input, 0.0, true, atrPeriod, atrMultiplier);
    }

    public static BarSeries buildRenko(BarSeries input,
                                       double fixedBrickSize,
                                       boolean useAtr,
                                       int atrPeriod,
                                       double atrMultiplier) {

        BarSeries renko = new BaseBarSeriesBuilder()
                .withName((useAtr ? "RENKO_ATR" : "RENKO_FIXED") + " of " + input.getName())
                .withNumFactory(input.numFactory())
//                .withNumTypeOf(input.numOf(0))
                .build();


        NumFactory nf = input.numFactory();
        final Duration brickDuration = Duration.ofSeconds(1);
        final Num ZERO = nf.zero();
        Num atrMultiplierNum = nf.numOf(atrMultiplier);

        ATRIndicator atr = useAtr ? new ATRIndicator(input, atrPeriod) : null;

        Num lastBrickClose = null;

        for (int i = 0; i < input.getBarCount(); i++) {
            Bar bar = input.getBar(i);

            // Бар учун кирпич ҳажмини белгилаймиз (бар ичида ўзгармайди)
            Num brick = useAtr
                    ? atr.getValue(i).multipliedBy(atrMultiplierNum)
                    : nf.numOf(fixedBrickSize);

            if (brick.isZero() || brick.isNaN() || brick.isNegative()) continue;

            // Биринчи кирпич позициясини "snap" қиламиз
            if (lastBrickClose == null) {
                lastBrickClose = snapFloor(bar.getClosePrice(), brick, input);
            }

            // Intra-bar path: OHLC тартиби
            List<Num> path = buildPath(bar, input);

            // Таймстампни unique қилиш учун секунд санагич
            long secOffset = 0L;

            // Йўл бўйлаб нархни "қадамлаб" борамиз
            Num cursor = lastBrickClose; // бар бошланишидаги позиция
            for (Num target : path) {
                // target’гача бўлган йўлда қанча кирпич чегараси кесиб ўтилади — шуни while билан тугамиз
                // Юқорига
                while (target.isGreaterThanOrEqual(cursor.plus(brick))) {
                    Num open = cursor;
                    Num close = cursor.plus(brick);
                    Num hi = close.max(open);
                    Num lo = close.min(open);
                    Instant t = bar.getEndTime().plusSeconds(secOffset++);
                    renko.addBar(new BaseBar(brickDuration, t, open, hi, lo, close, ZERO, ZERO, 0));
                    cursor = close;
                    lastBrickClose = close;
                }
                // Пастга
                while (target.isLessThanOrEqual(cursor.minus(brick))) {
                    Num open = cursor;
                    Num close = cursor.minus(brick);
                    Num hi = close.max(open);
                    Num lo = close.min(open);
                    Instant t = bar.getEndTime().plusSeconds(secOffset++);
                    renko.addBar(new BaseBar(brickDuration, t, open, hi, lo, close, ZERO, ZERO, 0));
                    cursor = close;
                    lastBrickClose = close;
                }
                // target’га етилганда кирпич чегараси кесилмаса — ҳеч нарса қўшилмайди
            }
        }

        return renko;
    }

    /**
     * OHLC path: close>=open -> O-H-L-C, акс ҳолда O-L-H-C
     */
    private static List<Num> buildPath(Bar bar, BarSeries s) {
        Num o = bar.getOpenPrice();
        Num h = bar.getHighPrice();
        Num l = bar.getLowPrice();
        Num c = bar.getClosePrice();
        if (c.isGreaterThanOrEqual(o)) {
            return Arrays.asList(o, h, l, c);
        } else {
            return Arrays.asList(o, l, h, c);
        }
    }

    /**
     * Snap (floor) to brick grid
     */
    private static Num snapFloor(Num price, Num brick, BarSeries s) {
        Num k = price.dividedBy(brick).floor();
        return k.multipliedBy(brick);
    }
}
