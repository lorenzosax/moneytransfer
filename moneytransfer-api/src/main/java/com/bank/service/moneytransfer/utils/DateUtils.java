package com.bank.service.moneytransfer.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

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
     * Method used to get Date object from formatted string.
     * @param stringDate        string date to transform in Date object
     * @param customPattern     pattern used to format data
     * @return                  a Date with date
     */
    public static Date getDateFromFormattedDate(String stringDate, String customPattern) {
        Date date = null;
        String pattern = customPattern != null ? customPattern : "dd/MM/yyyy";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            date = sdf.parse(stringDate);
        } catch (ParseException e) {
            logger.error("Error parsing string to date. String sDate=" + stringDate + " with pattern=" + pattern);
        }
        return date;
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

    /**
     * Method used to add days to a date passed as parameter.
     * Date will be returned as Date object.
     * @param date  add days to this date
     * @param days  days to add
     * @return      Date object contain the date with +/- days
     */
    public static Date addDaysToDate(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }
}
