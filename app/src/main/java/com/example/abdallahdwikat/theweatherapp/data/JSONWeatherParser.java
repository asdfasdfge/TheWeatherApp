package com.example.abdallahdwikat.theweatherapp.data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.abdallahdwikat.theweatherapp.model.CityWeather;
import com.example.abdallahdwikat.theweatherapp.model.Clouds;
import com.example.abdallahdwikat.theweatherapp.model.Main;
import com.example.abdallahdwikat.theweatherapp.model.Sys;
import com.example.abdallahdwikat.theweatherapp.model.Weather;
import com.example.abdallahdwikat.theweatherapp.model.Wind;

public class JSONWeatherParser {
    public static CityWeather getWeather(String data) {

        Log.d("ttt", "getWeatherList:data" + data);

        CityWeather cityWeather = new CityWeather();
        try {
            JSONObject root = new JSONObject(data);
            JSONObject corrd = root.getJSONObject("coord");

            cityWeather.getCoord().setLon(corrd.getString("lon"));
            cityWeather.getCoord().setLat(corrd.getString("lat"));

            JSONArray weatherArray = root.getJSONArray("weather");
            JSONObject wetherObject = weatherArray.getJSONObject(0);

            String id = wetherObject.getString("id");
            String icon = wetherObject.getString("icon");
            String description = wetherObject.getString("description");
            String main = wetherObject.getString("main");

            cityWeather.getWeatherList().add(new Weather(id, icon, description, main));

            JSONObject mainObject = root.getJSONObject("main");

            String humidity = mainObject.getString("humidity");
            String pressure = mainObject.getString("pressure");
            String temp_max = mainObject.getString("temp_max");
            String temp_min = mainObject.getString("temp_min");
            String temp = mainObject.getString("temp");

            cityWeather.setMain(new Main(humidity, pressure, temp_max, temp_min, temp));

            String visibility = root.getString("visibility");
            cityWeather.setVisibility(visibility);

            JSONObject wind = root.getJSONObject("wind");
            String speed = wind.getString("speed");
            String deg = wind.getString("deg");

            cityWeather.setWind(new Wind(speed, deg));

            JSONObject clouds = root.getJSONObject("clouds");
            String all = clouds.getString("all");
            cityWeather.setClouds(new Clouds(all));


            String dt = root.getString("dt");
            cityWeather.setDt(dt);

            JSONObject sys = root.getJSONObject("sys");
            String message = sys.getString("message");
            String sysId = sys.getString("id");
            String sunset = sys.getString("sunset");
            String sunrise = sys.getString("sunrise");
            String type = sys.getString("type");
            String country = sys.getString("country");
            cityWeather.setSys(new Sys(message, sysId, sunset, sunrise, type, country));

            String rootId = root.getString("id");
            cityWeather.setId(rootId);

            String name = root.getString("name");
            cityWeather.setName(name);

            String cod = root.getString("cod");
            cityWeather.setCod(cod);

            return cityWeather;

        } catch (JSONException e) {
            e.printStackTrace();
            return cityWeather;
        }
    }
}
