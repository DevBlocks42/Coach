package com.example.coach.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import android.util.Log;

abstract public class Tools
{
    public static Date getStringAsDate(String date)
    {
        Log.d("FORMAT DATE ", date);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
