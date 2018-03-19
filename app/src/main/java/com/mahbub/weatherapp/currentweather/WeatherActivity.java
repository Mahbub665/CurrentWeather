package com.mahbub.weatherapp.currentweather;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.HttpURLConnection;

import data.JSONWeatherParser;
import data.WeatherHttpConnection;
import model.Weather;

public class WeatherActivity extends AppCompatActivity {

    TextView mCityNameTV;
    TextView mTemperatureTV;
    TextView mDescriptionTV;
    TextView mLastUpdateTV;
    TextView mMinTempTV;
    TextView mMaxTempTV;
    TextView mPressureTV;
    TextView mHumidityTV;
    ImageView mWeatherIcon;

    Weather mWeather = new Weather();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        mCityNameTV = (TextView) findViewById(R.id.tv_city_name);
        mTemperatureTV=(TextView)findViewById(R.id.tv_temperature);
        mDescriptionTV =(TextView)findViewById(R.id.tv_description);
        mLastUpdateTV =(TextView)findViewById(R.id.tv_last_update_time);
        mMinTempTV =(TextView)findViewById(R.id.tv_min_temperature);
        mMaxTempTV =(TextView)findViewById(R.id.tv_max_temperature);
        mPressureTV =(TextView)findViewById(R.id.tv_pressure);
        mHumidityTV =(TextView)findViewById(R.id.tv_humidity);
        mWeatherIcon=(ImageView)findViewById(R.id.iv_weather_icon);

        getRenderWeatherData("Moscow,RU");

    }

    public void getRenderWeatherData(String place){
        new WeatherTask().execute( new String[]{ place});

    }
    // create asyntak class for to do processing in background
    private class WeatherTask extends AsyncTask<String,Void, Weather>{
        @Override
        protected Weather doInBackground(String... strings) {
            String data = ((new WeatherHttpConnection()).getWeatherData(strings[0]));
            mWeather = JSONWeatherParser.getWeather(data);

            Log.v("Data: ",mWeather.currentCondition.getDescription());

            return mWeather;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);
            mCityNameTV.setText(weather.place.getCity()+","+weather.place.getCountry());
            mTemperatureTV.setText(weather.currentCondition.getTemperature()+" C");
            mDescriptionTV.setText(weather.currentCondition.getDescription());
            mLastUpdateTV.setText("Update"+weather.place.getLastUpdate());
            mMinTempTV.setText("Min:"+weather.currentCondition.getTempMin()+" C");
            mMaxTempTV.setText("Max:"+weather.currentCondition.getTempMax()+" C");
            mPressureTV.setText("Pressure:"+weather.currentCondition.getPressure()+"hPa");
            mHumidityTV.setText("Humidity"+weather.currentCondition.getHumidity()+"%");

        }
    }


}
