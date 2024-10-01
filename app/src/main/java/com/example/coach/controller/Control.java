package com.example.coach.controller;

import android.content.Context;

import com.example.coach.view.MainActivity;
import com.example.coach.model.Profile;
import com.example.coach.model.RemoteAccess;
import com.example.coach.utils.Serializer;

import org.json.JSONObject;

import java.util.Date;

public final class Control
{
    private static Control instance = null;
    private static Profile profile;
    private static String fileName = "saveprofile";
    private static RemoteAccess remoteAccess;
    private static Context context;
    //private static LocalAccess localAccess;
    private Control(Context context)
    {
        if(context != null)
        {
            Control.context = context;
        }
    }
    public static Control getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new Control(context);
            remoteAccess = RemoteAccess.getInstance();
            remoteAccess.send("dernier", new JSONObject());
        }
        return instance;
    }
    private static void getSerialized(Context context)
    {
        Control.profile = (Profile) Serializer.deSerialize(Control.fileName, context);
    }
    public static void createProfile(int weight, int age, int height, int sex)
    {
        Control.profile = new Profile(new Date(), weight, age, height, sex);
        remoteAccess.send("enreg", Control.profile.convertToJSONObject());
        //localAccess.addProfile(Control.profile);
        //Serializer.serialize(Control.fileName, Control.profile, context);
    }
    public void setProfile(Profile profile)
    {
        Control.profile = profile;
        ((MainActivity)context).getProfile();
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
