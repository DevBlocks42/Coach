package com.example.coach.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coach.utils.MySQLiteOpenHelper;

import java.util.Date;
import com.example.coach.utils.Tools;

public class LocalAccess
{
    private String baseName = "bdCoach.sqlite";
    private int baseVersion = 1;
    private static LocalAccess instance = null;
    private MySQLiteOpenHelper dbAccess;
    private SQLiteDatabase database;

    private LocalAccess(Context context)
    {
        this.dbAccess = new MySQLiteOpenHelper(context, baseName, baseVersion);
    }
    public static LocalAccess getInstance(Context context)
    {
        if(instance == null)
        {
            return new LocalAccess(context);
        }
        return instance;
    }
    public void addProfile(Profile profile)
    {
        database = dbAccess.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("datemesure", profile.getDateAsString());
        values.put("poids", profile.getWeight());
        values.put("age", profile.getAge());
        values.put("taille", profile.getHeight());
        values.put("sexe", profile.getSex());
        database.insert("profil", null, values);
        database.close();
    }
    public Profile getLastProfile()
    {
        Profile profile = null;
        database = dbAccess.getReadableDatabase();
        String query = "SELECT * FROM profil;";
        Cursor cursor = database.rawQuery(query, null);
        cursor.moveToLast();
        if(cursor.isAfterLast() == false)
        {
            Date date = Tools.getStringAsDate(cursor.getString(0));
            int w = cursor.getInt(1);
            int h = cursor.getInt(2);
            int a = cursor.getInt(3);
            int s = cursor.getInt(4);
            profile = new Profile(date, w, a, h, s);
        }
        cursor.close();
        database.close();
        return profile;
    }
}
