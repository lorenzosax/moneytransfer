package com.bank.service.moneytransfer.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static String getFormattedDate(Date date) {
        return getFormattedDate(date, null);
    }

    /**
     * Method used to format a Date object with a specific pattern is passed.
     * @param date      date to format
     * @param pattern   pattern used to format data
     * @return          a string with data
     */
    public static String getFormattedDate(Date date, String pattern) {
        if (date == null) {
            return "";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(pattern != null ? pattern : "dd/MM/yyyy");
        return sdf.format(date);
    }

    /**
     * Method used to add days to a date passed as parameter.
     * Date will be returned as string formatted with default pattern used in getFormattedDate.
     * @param date  add days to this date
     * @param days  days to add
     * @return      string contain the date with +/- days and formatted
     */
    public static String addDaysAndGetFormattedDate(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return getFormattedDate(cal.getTime());
    }
}
