package com.example.coach.model;

import android.util.Log;

import com.example.coach.controller.Control;
import com.example.coach.utils.AccesHTTP;
import com.example.coach.utils.AsyncResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class RemoteAccess implements AsyncResponse
{
    private static RemoteAccess instance = null;
    private static String SERVER_ADDRESS = "http://192.168.122.87/serveurcoach.php";
    private Control control;

    private RemoteAccess()
    {
        control = Control.getInstance(null);
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
                if(message.equals("dernier"))
                {
                    JSONObject infos = new JSONObject(result);
                    //Date date = Tools.getStringAsDate(infos.getString("datemesure"));
                    Integer weight = infos.getInt("poids");
                    Integer height = infos.getInt("taille");
                    Integer age = infos.getInt("age");
                    Integer sex = infos.getInt("sexe");
                    Profile p = new Profile(new Date(), weight, age, height, sex);
                    Log.d("PROFILE", "PROFILE : " + weight);
                    control.setProfile(p);
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
