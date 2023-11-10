package com.example.de_013.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static String format(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static Date parseStringToDate(String dateStr) throws ParseException {
        return DATE_FORMAT.parse(dateStr);
    }
}
