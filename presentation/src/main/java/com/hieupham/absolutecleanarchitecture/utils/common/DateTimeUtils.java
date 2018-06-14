package com.hieupham.absolutecleanarchitecture.utils.common;

import android.support.annotation.NonNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jollyjoker992 on 02/06/2018.
 */

public class DateTimeUtils {

    public static final String DATE_TIME_FORMAT_1 = "yyyy-MM-DD'T'HH:mm:ss.SSSSSS'Z'";
    public static final String DATE_TIME_FORMAT_2 = "yyyy MMM DD HH:mm:ss";

    public static String changeFormat(String dateStr, String inputFormat, String outputFormat) {
        long millis = stringToMillis(dateStr, inputFormat);
        return millis == -1 ? null : millisToStr(millis, outputFormat);
    }

    public static String changeFormat(String dateStr, String outputFormat) {
        return changeFormat(dateStr, DATE_TIME_FORMAT_1, outputFormat);
    }

    public static String millisToStr(long millis, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        Date date = calendar.getTime();
        return sdf.format(date);
    }

    public static Date millisToDate(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    public static long stringToMillis(@NonNull String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        try {
            Date date = sdf.parse(dateStr);
            return date.getTime();
        } catch (ParseException | NullPointerException e) {
            return -1;
        }
    }
}
