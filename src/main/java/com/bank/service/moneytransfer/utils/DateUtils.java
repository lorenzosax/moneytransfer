package com.bank.service.moneytransfer.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static String getFormattedDate(Date date) {
        return getFormattedDate(date, null);
    }

    public static String getFormattedDate(Date date, String pattern) {
        if (date == null) return "";

        SimpleDateFormat sdf = new SimpleDateFormat(pattern != null ? pattern : "dd/MM/yyyy");
        return sdf.format(date);
    }

    public static String addDaysAndGetFormattedDate(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return getFormattedDate(cal.getTime());
    }
}
