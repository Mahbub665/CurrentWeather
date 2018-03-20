package data;

import android.provider.Contacts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Place;
import model.Weather;
import util.Utils;

/**
 * Created by mahbub on 3/19/2018.
 */

public class JSONWeatherParser {

    public static Weather getWeather(String data){
        Weather weather = new Weather();
        JSONObject jsonObject = null;

        //create JSONObject form data

        try {
            jsonObject = new JSONObject(data);

            Place place = new Place();
            // get data from coord object
            JSONObject coordObj = Utils.getJsonobject("coord",jsonObject);
            place.setLat(Utils.getFloat("lat",coordObj));
            place.setLon(Utils.getFloat("lon",coordObj));

            //get data from sys object
            JSONObject sysObj = Utils.getJsonobject("sys",jsonObject);
            place.setCountry(Utils.getString("country",sysObj));
            place.setLastUpdate(Utils.getInt("dt",jsonObject));
            place.setSunRise(Utils.getInt("sunrise",sysObj));
            place.setSunSet(Utils.getInt("sunset",sysObj));
            place.setCity(Utils.getString("name",jsonObject));
            weather.place = place ;

            // get data from weather info
            JSONArray jsonArray = jsonObject.getJSONArray("weather");
            JSONObject jsonWeatherObj = jsonArray.getJSONObject(0);
            weather.currentCondition.setWeatherId(Utils.getInt("id",jsonWeatherObj));
            weather.currentCondition.setDescription(Utils.getString("description",jsonWeatherObj));
            weather.currentCondition.setCondition(Utils.getString("main",jsonWeatherObj));
            weather.currentCondition.setIcon(Utils.getString("icon",jsonWeatherObj));

            //get main object
            JSONObject mainObj = Utils.getJsonobject("main",jsonObject);
            weather.currentCondition.setTemp(Utils.getDouble("temp",mainObj));
            weather.currentCondition.setHumidity(Utils.getInt("humidity",mainObj));
            weather.currentCondition.setPressure(Utils.getInt("pressure",mainObj));
            weather.currentCondition.setTempMin(Utils.getFloat("temp_min",mainObj));
            weather.currentCondition.setTempMax(Utils.getFloat("temp_max",mainObj));

            //get data from wind boject
            JSONObject windObj = Utils.getJsonobject("wind",jsonObject);
            weather.wind.setSpeed(Utils.getFloat("speed",windObj));
            weather.wind.setDeg(Utils.getInt("deg",windObj));

            // get data from clouds object
            JSONObject cloudsObj = Utils.getJsonobject("clouds",jsonObject);
            weather.clouds.setPrecipitation(Utils.getInt("all",cloudsObj));

            return weather;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
