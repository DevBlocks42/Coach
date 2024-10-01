package com.example.coach.model;

import com.example.coach.utils.Serializer;
import com.example.coach.utils.Tools;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

public class Profile implements Serializable
{
    private Date date;
    private int weight;
    private int age;
    private int height;
    private float img = 0;
    private int sex;
    private String message = "";
    private static final int MIN_FEMME = 15;
    private static final int MAX_FEMME = 30;
    private static final int MIN_HOMME = 10;
    private static final int MAX_HOMME = 25;

    public Profile(Date date, int weight, int age, int height, int sex)
    {
        this.date = date;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.sex = sex;
    }
    public String getDateAsString()
    {
        return date.toString();
    }
    public int getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    public int getHeight() {
        return height;
    }

    public float getImg()
    {
        if(img == 0)
        {
            float h = (float)height / 100;
            img = (float) ((1.2 * weight/(h * h)) + 0.23 * age - 10.83 * sex - 5.4);
        }
        return img;
    }

    public int getSex() {
        return sex;
    }

    public String getMessage()
    {
        if(message == "")
        {
            img = getImg();
            if(sex == 1)
            {
                if(img < MIN_HOMME)
                {
                    message = "Maigreur.";
                }
                else if(img >= MIN_HOMME && img <= MAX_HOMME)
                {
                    message = "Normal.";
                }
                else
                {
                    message = "Surcharge.";
                }
            }
            else
            {
                if(img < MIN_FEMME)
                {
                    message = "Maigreur.";
                }
                else if(img >= MIN_FEMME && img <= MAX_FEMME)
                {
                    message = "Normal.";
                }
                else
                {
                    message = "Surcharge.";
                }
            }
        }
        return message;
    }
    public JSONObject convertToJSONObject()
    {
        JSONObject obj = new JSONObject();
        try
        {
            obj.put("datemesure", Tools.getDateAsString(this.date));
            obj.put("poids", this.getWeight());
            obj.put("taille", this.getHeight());
            obj.put("age", this.getAge());
            obj.put("sexe", this.getSex());
        }
        catch(JSONException e) {}
        return obj;
    }
}
