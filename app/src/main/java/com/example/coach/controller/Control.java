package com.example.coach.controller;

import com.example.coach.model.Profile;

public final class Control
{
    private static Control instance = null;
    private static Profile profile;
    private Control()
    {
        super();
    }
    public static Control getInstance()
    {
        if(Control.instance == null)
        {
            Control.instance = new Control();
        }
        return Control.instance;
    }
    public static void createProfile(int weight, int age, int height, int sex)
    {
        Control.profile = new Profile(weight, age, height, sex);
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
}
