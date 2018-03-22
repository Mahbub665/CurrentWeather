package com.mahbub.weatherapp.currentweather;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mahbub on 3/22/2018.
 */

public class CityPreference {
    SharedPreferences prefs;

    public CityPreference(Activity context){
        prefs= context.getPreferences(Context.MODE_PRIVATE);
    }

    public String getCity(){
        return prefs.getString("city","spokane,US");
    }
    public void setSity(String city){
        prefs.edit().putString("city",city).commit();
    }

}
