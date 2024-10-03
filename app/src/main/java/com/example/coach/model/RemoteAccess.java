package com.example.coach.model;

import android.util.Log;

import com.example.coach.controller.Control;
import com.example.coach.utils.AccesHTTP;
import com.example.coach.utils.AsyncResponse;
import com.example.coach.utils.Tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class RemoteAccess implements AsyncResponse
{
    private static RemoteAccess instance = null;
    private static String SERVER_ADDRESS = "http://192.168.122.87/serveurcoach.php";
    private Control control;

    private RemoteAccess()
    {
        control = Control.getInstance();
    }
    public static RemoteAccess getInstance()
    {
        if(instance == null)
        {
            instance = new RemoteAccess();
        }
        return instance;
    }

    @Override public void processFinish(String output)
    {
        Log.d("serveur", "************" + output);
        try
        {
            JSONObject obj = new JSONObject(output);
            String code = obj.getString("code");
            String message = obj.getString("message");
            String result = obj.getString("result");
            Log.d("CODE", code);
            if(!code.equals("200"))
            {
                Log.d("erreur", "************ retour serveur code="+code+" result="+result);
            }
            else
            {
                if(message.equals("tous"))
                {
                    ArrayList<Profile> profiles = new ArrayList<Profile>();
                    JSONArray objects = new JSONArray(result);
                    for(int i = 0; i < objects.length(); i++)
                    {
                        JSONObject currentObj = new JSONObject(objects.get(i).toString());
                        Date date = Tools.getStringAsDate(currentObj.getString("datemesure"));
                        Integer weight = currentObj.getInt("poids");
                        Integer height = currentObj.getInt("taille");
                        Integer age = currentObj.getInt("age");
                        Integer sex = currentObj.getInt("sexe");
                        Profile profile = new Profile(date, weight, age, height, sex);
                        profiles.add(profile);
                    }
                    control.setProfiles(profiles);
                }
            }
        }
        catch (JSONException e)
        {
            Log.d("erreur", "************ output n’est pas au format JSON");
        }
    }
    public void send(String operation, JSONObject obj)
    {
        AccesHTTP httpAccess = new AccesHTTP();
        httpAccess.delegate = this;
        httpAccess.addParam("operation", operation);
        httpAccess.addParam("lesdonnees", obj.toString());
        httpAccess.execute(SERVER_ADDRESS);
    }
}
