package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import util.Utils;

/**
 * Created by mahbub on 3/19/2018.
 */

public class WeatherHttpConnection {

    public String getWeatherData(String place){

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try{
            urlConnection = (HttpURLConnection)(new URL(Utils.BASE_URL + place).openConnection());
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            // Read the response
            StringBuffer stringBuffer = new StringBuffer();
            inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine())!=null){
                stringBuffer.append(line + "/r/n");
            }
            inputStream.close();
            urlConnection.disconnect();

            return stringBuffer.toString();
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
