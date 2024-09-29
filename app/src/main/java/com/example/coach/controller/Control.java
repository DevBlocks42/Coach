package com.example.coach.controller;

import android.content.Context;

import com.example.coach.model.Profile;
import com.example.coach.utils.Serializer;

public final class Control
{
    private static Control instance = null;
    private static Profile profile;
    private static String fileName = "saveprofile";
    private Control()
    {
        super();
    }
    public static Control getInstance(Context context)
    {
        if(Control.instance == null)
        {
            Control.instance = new Control();
        }
        getSerialized(context);
        return Control.instance;
    }
    private static void getSerialized(Context context)
    {
        Control.profile = (Profile) Serializer.deSerialize(Control.fileName, context);
    }
    public static void createProfile(int weight, int age, int height, int sex, Context context)
    {
        Control.profile = new Profile(weight, age, height, sex);
        Serializer.serialize(Control.fileName, Control.profile, context);
    }
    public static float getImg()
    {
        if(Control.profile == null)
        {
            return (float)0;
        }
        return Control.profile.getImg();
    }
    public static String getMessage()
    {
        if(Control.profile == null)
        {
            return "";
        }
        return Control.profile.getMessage();
    }
    public static Integer getWeight()
    {
        if(Control.profile == null)
        {
            return null;
        }
        return (Integer) Control.profile.getWeight();
    }
    public static Integer getHeight()
    {
        if(Control.profile == null)
        {
            return null;
        }
        return Control.profile.getHeight();
    }
    public static Integer getAge()
    {
        if(Control.profile == null)
        {
            return null;
        }
        return Control.profile.getAge();
    }
    public static Integer getSex()
    {
        if(Control.profile == null)
        {
            return null;
        }
        return Control.profile.getSex();
    }
}
