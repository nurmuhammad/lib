package com.jrobot.lib;

import java.time.Duration;
import java.util.Arrays;

public interface Interval {

    String MINUTE_1 = "1m";
    String MINUTE_3 = "3m";
    String MINUTE_5 = "5m";
    String MINUTE_15 = "15m";
    String MINUTE_30 = "30m";
    String HOUR_1 = "1h";
    String HOUR_2 = "2h";
    String HOUR_4 = "4h";
    String HOUR_6 = "6h";
    String HOUR_8 = "8h";
    String HOUR_12 = "12h";
    String DAY_1 = "1d";
    String DAY_3 = "3d";
    String WEEK_1 = "1w";
    String MONTH_1 = "1M";

    String ALL_INTERVALS = $.join(", ", MINUTE_1, MINUTE_3, MINUTE_5, MINUTE_15, MINUTE_30,
            HOUR_1, HOUR_2, HOUR_4, HOUR_6, HOUR_8, HOUR_12,
            DAY_1, DAY_3, WEEK_1, MONTH_1);


    String[] ALL_INTERVALS_ARRAY = new String[]{MINUTE_1, MINUTE_3, MINUTE_5, MINUTE_15, MINUTE_30,
            HOUR_1, HOUR_2, HOUR_4, HOUR_6, HOUR_8, HOUR_12,
            DAY_1, DAY_3, WEEK_1, MONTH_1};

    String[] ALL_INTERVALS_WS_ARRAY = new String[]{MINUTE_5, MINUTE_15, MINUTE_30, HOUR_1, HOUR_2};
    String[] ALL_INTERVALS_KLINE_ARRAY = new String[]{HOUR_4, HOUR_6, HOUR_8, HOUR_12, DAY_1, DAY_3, WEEK_1, MONTH_1};

    static String next(String interval) {
        return switch (interval) {
            case MINUTE_1 -> MINUTE_3;
            case MINUTE_3 -> MINUTE_5;
            case MINUTE_5 -> MINUTE_15;
            case MINUTE_15 -> MINUTE_30;
            case MINUTE_30 -> HOUR_1;
            case HOUR_1 -> HOUR_2;
            case HOUR_2 -> HOUR_4;
            case HOUR_4 -> HOUR_6;
            case HOUR_6 -> HOUR_8;
            case HOUR_8 -> HOUR_12;
            case HOUR_12 -> DAY_1;
            case DAY_1 -> DAY_3;
            case DAY_3 -> WEEK_1;
            case WEEK_1 -> MONTH_1;
            default -> null;
        };
    }

    static String previous(String interval) {
        return switch (interval) {
            case MINUTE_3 -> MINUTE_1;
            case MINUTE_5 -> MINUTE_3;
            case MINUTE_15 -> MINUTE_5;
            case MINUTE_30 -> MINUTE_15;
            case HOUR_1 -> MINUTE_30;
            case HOUR_2 -> HOUR_1;
            case HOUR_4 -> HOUR_2;
            case HOUR_6 -> HOUR_4;
            case HOUR_8 -> HOUR_6;
            case HOUR_12 -> HOUR_8;
            case DAY_1 -> HOUR_12;
            case DAY_3 -> DAY_1;
            case WEEK_1 -> DAY_3;
            case MONTH_1 -> WEEK_1;
            default -> null;
        };
    }

    static int getDurationInSecond(String interval) {
        return switch (interval) {
            case MINUTE_1 -> 60;
            case MINUTE_3 -> 3 * 60;
            case MINUTE_5 -> 5 * 60;
            case MINUTE_15 -> 15 * 60;
            case MINUTE_30 -> 30 * 60;
            case HOUR_1 -> 60 * 60;
            case HOUR_2 -> 2 * 60 * 60;
            case HOUR_4 -> 4 * 60 * 60;
            case HOUR_6 -> 6 * 60 * 60;
            case HOUR_8 -> 8 * 60 * 60;
            case HOUR_12 -> 12 * 60 * 60;
            case DAY_1 -> 24 * 60 * 60;
            case DAY_3 -> 3 * 24 * 60 * 60;
            case WEEK_1 -> 7 * 24 * 60 * 60;
            case MONTH_1 -> 30 * 24 * 60 * 60;
            default -> -1;
        };
    }

    static Duration duration(String interval) {
        return Duration.ofSeconds(getDurationInSecond(interval));
    }

    static String[] split(String intervals) {
        return Arrays.stream(intervals.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .distinct().toArray(String[]::new);
    }
}
