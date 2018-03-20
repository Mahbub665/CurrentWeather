package com.mahbub.weatherapp.currentweather;

import android.app.ActionBar;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

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



            return mWeather;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            // initialize date format here
            DateFormat dateFormat = DateFormat.getTimeInstance();
            String lastUpdate = dateFormat.format(new Date(weather.place.getLastUpdate()));


            mCityNameTV.setText(weather.place.getCity()+","+weather.place.getCountry());
            mTemperatureTV.setText(weather.currentCondition.getTemp()+"° C");
            mDescriptionTV.setText(weather.currentCondition.getCondition()+" ("+weather.currentCondition.getDescription()+")");
            mLastUpdateTV.setText("Last Update :"+lastUpdate);
            mMinTempTV.setText("Min :"+weather.currentCondition.getTempMin()+"° C");
            mMaxTempTV.setText("Max :"+weather.currentCondition.getTempMax()+"° C");
            mPressureTV.setText("Pressure: "+weather.currentCondition.getPressure()+" hPa");
            mHumidityTV.setText("Humidity: "+weather.currentCondition.getHumidity()+" %");

           String imgName = weather.currentCondition.getIcon();
            if (imgName.equals("01d")){
                mWeatherIcon.setImageDrawable(getResources().getDrawable(R.drawable._01d));
            }else if (imgName.equals("01n")){
                mWeatherIcon.setImageDrawable(getResources().getDrawable(R.drawable._01n));
            }else if (imgName.equals("02d")){
                mWeatherIcon.setImageDrawable(getResources().getDrawable(R.drawable._02d));
            }else if (imgName.equals("02n")){
                mWeatherIcon.setImageDrawable(getResources().getDrawable(R.drawable._02n));
            }else if (imgName.equals("03d")){
                mWeatherIcon.setImageDrawable(getResources().getDrawable(R.drawable._03d));
            }else if (imgName.equals("03n")){
                mWeatherIcon.setImageDrawable(getResources().getDrawable(R.drawable._03n));
            }else if (imgName.equals("04d")){
                mWeatherIcon.setImageDrawable(getResources().getDrawable(R.drawable._04d));
            }else if (imgName.equals("04n")){
                mWeatherIcon.setImageDrawable(getResources().getDrawable(R.drawable._04n));
            }else if (imgName.equals("09d")){
                mWeatherIcon.setImageDrawable(getResources().getDrawable(R.drawable._09d));
            }else if (imgName.equals("09n")){
                mWeatherIcon.setImageDrawable(getResources().getDrawable(R.drawable._09n));
            }else if (imgName.equals("11d")){
                mWeatherIcon.setImageDrawable(getResources().getDrawable(R.drawable._11d));
            }else if (imgName.equals("11n")){
                mWeatherIcon.setImageDrawable(getResources().getDrawable(R.drawable._11n));
            }else if (imgName.equals("13d")){
                mWeatherIcon.setImageDrawable(getResources().getDrawable(R.drawable._13d));
            }else if (imgName.equals("13n")){
                mWeatherIcon.setImageDrawable(getResources().getDrawable(R.drawable._13n));
            }else if (imgName.equals("50d")){
                mWeatherIcon.setImageDrawable(getResources().getDrawable(R.drawable._50d));
            }else if (imgName.equals("50n")){
                mWeatherIcon.setImageDrawable(getResources().getDrawable(R.drawable._50n));
            }


        }
    }


}
