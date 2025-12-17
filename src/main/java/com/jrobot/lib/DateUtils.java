package com.jrobot.lib;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final DateTimeFormatter DEFAULT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter ISO_FORMATTER =
            DateTimeFormatter.ISO_OFFSET_DATE_TIME;

    public static String toString(OffsetDateTime dateTime) {
        return dateTime != null ? dateTime.format(DEFAULT_FORMATTER) : null;
    }

    public static String toIsoString(OffsetDateTime dateTime) {
        return dateTime != null ? dateTime.format(ISO_FORMATTER) : null;
    }

    public static String toCustomString(OffsetDateTime dateTime, String pattern) {
        return dateTime != null ?
                dateTime.format(DateTimeFormatter.ofPattern(pattern)) : null;
    }
}
