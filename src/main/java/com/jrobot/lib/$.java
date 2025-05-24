package com.jrobot.lib;

import java.net.URL;
import java.time.Instant;
import java.util.*;
import java.util.regex.Pattern;

public class $ {

    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

    public static boolean isEmail(String email) {
        if (email == null)
            return false;
        Pattern pat = Pattern.compile(EMAIL_REGEX);
        return pat.matcher(email).matches();
    }

    public static boolean isURL(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isEmpty(String value) {
        if (value == null) return true;
        return value.trim().isEmpty();
    }

    public static boolean isEmpty(Object value) {
        if (value == null) return true;
        if (value instanceof String)
            return ((String) value).trim().isEmpty();
        if (value instanceof Collection)
            return ((Collection<?>) value).isEmpty();
        if (value instanceof Map)
            return ((Map<?, ?>) value).isEmpty();
        if (value instanceof Object[]) {
            return ((Object[]) value).length == 0;
        }
        return false;
    }

    public static String notEmptyString(String value, String ifEmpty) {
        if (isEmpty(value)) return ifEmpty;
        return value;
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception ignored) {
        }
    }

    public static String join(String separator, String... text) {
        StringBuilder builder = new StringBuilder();
        for (String t : text) {
            if (t == null) continue;
            if (t.trim().isEmpty()) continue;
            if (builder.isEmpty()) {
                builder.append(t);
            } else {
                builder.append(separator).append(t);
            }
        }
        return builder.toString();
    }

    public static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    public static int now() {
        return (int) Instant.now().getEpochSecond();
    }

    public static Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    public static int convertSizeToByte(String value) {
        if (isEmpty(value)) return 0;
        Double size = Double.valueOf(value.substring(0, value.length() - 3));
        if (value.endsWith("TB")) {
            return ((Double) (size * 1024 * 1024 * 1024 * 1024)).intValue();
        } else if (value.endsWith("GB")) {
            return ((Double) (size * 1024 * 1024 * 1024)).intValue();
        } else if (value.endsWith("MB")) {
            return ((Double) (size * 1024 * 1024)).intValue();
        } else if (value.endsWith("KB")) {
            return ((Double) (size * 1024)).intValue();
        } else {
            return size.intValue();
        }
    }

    public static Date makeStartDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date makeEndDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    public static boolean or(Object object, Object... conditions) {
        for (Object cond : conditions) {
            if (Objects.equals(object, cond)) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsIgnoreCase(String content, String... searchStrings) {
        if (content == null || searchStrings == null) return false;
        String contentLower = content.toLowerCase();
        for (String searchString : searchStrings) {
            if (searchString == null) continue;
            if (contentLower.contains(searchString.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean equalsIgnoreCase(String string1, String string2 ) {
        if (string1 == null && string2 == null) return true;
        if (string1 == null || string2 == null) return false;
        return string1.equalsIgnoreCase(string2);
    }
}
