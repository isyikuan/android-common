package com.yikuan.androidcommon.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author yikuan
 * @date 2019/10/04
 */
public class TimeUtils {
    private static final Map<String, ThreadLocal<SimpleDateFormat>> SDF_MAP = new HashMap<>();

    private TimeUtils() {
        throw new UnsupportedOperationException("cannot be instantiated.");
    }

    private static SimpleDateFormat getSdf(final String pattern) {
        if (pattern == null) {
            throw new NullPointerException("pattern cannot be null.");
        }
        ThreadLocal<SimpleDateFormat> threadLocal = SDF_MAP.get(pattern);
        if (threadLocal == null) {
            synchronized (TimeUtils.class) {
                threadLocal = SDF_MAP.get(pattern);
                if (threadLocal == null) {
                    threadLocal = new ThreadLocal<>();
                    threadLocal.set(new SimpleDateFormat(pattern, Locale.getDefault()));
                    SDF_MAP.put(pattern, threadLocal);
                }
            }
        }

        return threadLocal.get();
    }

    public static String format(Date date, String pattern) {
        return getSdf(pattern).format(date);
    }

    public static Date parse(String dateStr, String pattern) throws ParseException {
        return getSdf(pattern).parse(dateStr);
    }


    private static final String PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    private static final String PATTERN_FILE_NAME = "yyyyMMddHHmmsss";

    public static String millis2String(long millis) {
        return millis2String(millis, getSdf(PATTERN_DEFAULT));
    }

    public static String millis2String(long mills, DateFormat dateFormat) {
        if (dateFormat == null) {
            throw new NullPointerException("dateFormat cannot be null.");
        }
        return dateFormat.format(new Date(mills));
    }

    public static String formatFileName() {
        return getSdf(PATTERN_FILE_NAME).format(new Date());
    }

}