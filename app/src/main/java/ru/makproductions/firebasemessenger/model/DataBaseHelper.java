package ru.makproductions.firebasemessenger.model;

import android.util.Log;

public class DataBaseHelper {

    public static final String DATA_BASE_LOG = "DataBaseLog";

    public boolean save(String text){
        Log.e(DATA_BASE_LOG, "save: " + text);
        return true;
    }

    public String load(){
        return "Roma";
    }
}
