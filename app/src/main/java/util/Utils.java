package util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by mahbub on 3/18/2018.
 */

public class Utils {

    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    public static final String APPID = "&appid=9a36da9b098df077d94ed3f341eaac4f";
    public static final String UNITS_URL = "&units=metric";
    public static final String IMAGE_ICON_URL = "https://openweathermap.org/img/w/";

    // to return json object
    public static JSONObject getJsonobject(String tagName, JSONObject jsonObject) throws JSONException {
        JSONObject jsonObject1 = jsonObject.getJSONObject(tagName);
        return jsonObject1;
    }

    // to return string data
    public static String getString(String tagName,JSONObject jsonObject) throws JSONException{
        return jsonObject.getString(tagName);

    }

    // to return float data
    public static float getFloat(String tagName, JSONObject jsonObject) throws JSONException {
        return (float) jsonObject.getDouble(tagName);
    }

    // to return double data
    public static double getDouble(String tagName, JSONObject jsonObject) throws JSONException {
        return (double) jsonObject.getDouble(tagName);
    }

    // to return int data
    public static int getInt(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getInt(tagName);
    }


}
