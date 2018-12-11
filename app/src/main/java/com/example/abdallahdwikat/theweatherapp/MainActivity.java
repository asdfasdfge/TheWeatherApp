package com.example.abdallahdwikat.theweatherapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abdallahdwikat.theweatherapp.data.JSONWeatherParser;
import com.example.abdallahdwikat.theweatherapp.data.WeatherHttpClient;
import com.example.abdallahdwikat.theweatherapp.model.CityWeather;

public class MainActivity extends AppCompatActivity {
    private TextView mCityName;
    private TextView mTemp;
    private ImageView mIconView;
    private TextView mDescription;
    private TextView mHumidity;
    private TextView mPressure;
    private TextView mWind;
    private TextView mSunrise;
    private TextView mSunset;
    private TextView mUpdated;
    private EditText mSearchEditText;
    private Button mSearchBtn;

    CityWeather mCityWeather = new CityWeather();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCityName = findViewById(R.id.CityText);
        mTemp = findViewById(R.id.tempText);
        mIconView = findViewById(R.id.ThumbnailIcon);
        mDescription = findViewById(R.id.RainText);
        mHumidity = findViewById(R.id.humidText);
        mPressure = findViewById(R.id.PressureText);
        mWind = findViewById(R.id.WindText);
        mSunrise = findViewById(R.id.RiseText);
        mSunset = findViewById(R.id.SetText);
        mUpdated = findViewById(R.id.UpdateText);
        mSearchEditText = findViewById(R.id.searchEditText);
        mSearchBtn = findViewById(R.id.searchBtn);

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                renderWeatherData("q="+ mSearchEditText.getText().toString());
            }
        });

    }

    public void renderWeatherData(String city) {
        WeatherTask weatherTask = new WeatherTask();
        weatherTask.execute(new String[]{city + "&units=metric"});

    }

    private class WeatherTask extends AsyncTask<String, Void, CityWeather> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected CityWeather doInBackground(String... params) {
            String data = ((new WeatherHttpClient()).getWeatherData(params[0]));
            mCityWeather = JSONWeatherParser.getWeather(data);
            return mCityWeather;
        }

        @Override
        protected void onPostExecute(CityWeather cityWeather) {
            mCityName.setText(cityWeather.getName());
            mTemp.setText(cityWeather.getMain().getTemp());
            mWind.setText(cityWeather.getWind().getSpeed());
            super.onPostExecute(cityWeather);

        }

    }
}
