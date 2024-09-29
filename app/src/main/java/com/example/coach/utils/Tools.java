package com.example.coach.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

abstract public class Tools
{
    public static Date getStringAsDate(String date)
    {
        String expectedPattern = "EEE MMM dd hh:mm:ss 'GMT+00:00' yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
        try {
            Date d = formatter.parse(date);
            return d;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String getDateAsString(Date date)
    {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return d.format(date);
    }
}
