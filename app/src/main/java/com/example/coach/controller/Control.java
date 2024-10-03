package com.example.coach.controller;

import android.content.Context;
import android.util.Log;

import com.example.coach.view.CalculActivity;
import com.example.coach.model.Profile;
import com.example.coach.model.RemoteAccess;
import com.example.coach.utils.Serializer;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public final class Control
{
    private static Control instance = null;
    private static RemoteAccess remoteAccess;
    public ArrayList<Profile> profiles;

    private Control()
    {
        super();
    }
    public static Control getInstance()
    {
        if(instance == null)
        {
            instance = new Control();
            remoteAccess = RemoteAccess.getInstance();
            remoteAccess.send("tous", new JSONObject());
        }
        return instance;
    }
    public void createProfile(int weight, int age, int height, int sex)
    {
        Profile profile = new Profile(new Date(), weight, age, height, sex);
        profiles.add(profile);
        remoteAccess.send("enreg", profile.convertToJSONObject());
    }
    public float getImg()
    {
        if(profiles.size() > 0)
        {
            return profiles.get(profiles.size() - 1).getImg();
        }
        else
        {
            return (float) 0;
        }
    }
    public String getMessage()
    {
        if(profiles.size() > 0)
        {
            return profiles.get(profiles.size() - 1).getMessage();
        }
        else
        {
            return "";
        }
    }
    public ArrayList<Profile> getProfiles()
    {
        return profiles;
    }
    public void setProfiles(ArrayList<Profile> profiles)
    {
        this.profiles = profiles;
    }
    public void delProfil(Profile profile)
    {
        remoteAccess.send("suppr", profile.convertToJSONObject());
        Log.d("profil", "************** profil json = "+profile.convertToJSONObject());
        profiles.remove(profile);
    }
}
