package com.example.nviropoint;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.nviropoint.volley.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ForecastFragment extends Fragment {
    TextView temp,city,description,date,pressure,humidity,windspeed,winddirection, Sunrise, Sunset,
            tempMin, tempMax;

    ImageView icon;
    public ForecastFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_forecast, container, false);
        temp = (TextView) rootView.findViewById(R.id.current_temperature);
        city = (TextView) rootView.findViewById(R.id.city_field);
        description = (TextView) rootView.findViewById(R.id.description);
        date = (TextView) rootView.findViewById(R.id.updated_field);
        pressure = (TextView) rootView.findViewById(R.id.pressure);
        humidity = (TextView) rootView.findViewById(R.id.humidity);
        windspeed = (TextView) rootView.findViewById(R.id.windspeed);
        winddirection = (TextView) rootView.findViewById(R.id.winddirection);
        Sunrise = (TextView) rootView.findViewById(R.id.sunrise);
        tempMin =(TextView) rootView.findViewById(R.id.minTemp);
        tempMax =(TextView) rootView.findViewById(R.id.maxTemp);
        Sunset = rootView.findViewById(R.id.sunset);
        icon = rootView.findViewById(R.id.weatherCondition);
        String URL = "http://api.openweathermap.org/data/2.5/weather?id=2640194&units=imperial&appid=faf15c72035a522d6e027c2be057069c";
//help with json parsing found at https://www.youtube.com/watch?v=8-7Ip6xum6E
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject main = response.getJSONObject("main"); // parsses the main
                            // json object from the response
                            JSONObject sys = response.getJSONObject("sys"); // parses the object
                            // sys from the response
                            long sunset = sys.getLong("sunset"); // gets the sunset from the json
                            // object
                            Long sunrise = sys.getLong("sunrise"); // gets the sunrise from the
                            // json Object
                            JSONArray array = response.getJSONArray("weather"); // gets the
                            // weather array from the response
                            JSONObject object = array.getJSONObject(0);// gets the objects from
                            // the weather array
                            String temperature = String.valueOf(main.getDouble("temp"));// gets
                            // the temperature value from the main json object
                            String aDescription = object.getString("description");// gets the
                            // description from the object inside the jsonArray
                            String aCity = response.getString("name");// gets the city from the
                            // main response object
                            String thePressure = main.getString("pressure");// gets the pressure
                            // from inside the main json object
                            String theHumidity = main.getString("humidity");// gets the humidity
                            // from inside the main json object
                            JSONObject wind = response.getJSONObject("wind");// gets the wind
                            // objec from inside the response object
                            String theSpeed = wind.getString("speed");
                            // gets the wind speed from inside the wind obj
                            String direction = wind.getString("deg"); // gets the direction from
                            // inside the wind json object
                            String minTemp = String.valueOf(main.getDouble("temp_min")); // gets
                            // the min temp from inside the main json object
                            String maxTemp = String.valueOf(main.getDouble("temp_max"));// gets
                            // the max temp from inside the main json object
                            String aicon = object.getString("icon");
                            //information on images and codes found at https://openweathermap.org/weather-conditions#Weather-Condition-Codes-2
                            //Sets the image based on code is parsed from the icon string
                            if(aicon == "01d"){
                                icon.setImageResource(R.drawable.clearday);
                            }
                            else if(aicon.equals("01n")){
                                icon.setImageResource(R.drawable.clearnight);
                            }
                            else if(aicon.equals("02d")){
                                icon.setImageResource(R.drawable.fewcloudday);
                            }
                            else if (aicon.equals("02n"))
                            {
                                icon.setImageResource(R.drawable.fewcloudnight);
                            }
                            else if ((aicon.equals("03d")) &! (aicon.equals("o3n")))
                            {
                                icon.setImageResource(R.drawable.scatteredcloudday);
                            }
                            else if ((aicon.equals("04d")) &! (aicon.equals("04n"))){
                                icon.setImageResource(R.drawable.brokenclouds);
                            }

                            Sunset.setText(new SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(new Date(sunset * 1000)));// converts the unix time
                            Sunrise.setText(new SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(new Date(sunrise * 1000))); // converts the unix time
                            //temp.setText(temperature);
                            city.setText(aCity);
                            description.setText(aDescription);
                            pressure.setText(thePressure + " hPa");
                            humidity.setText(theHumidity +"%");
                            windspeed.setText(theSpeed + " mph");


                            Calendar calendar= Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY", Locale.ENGLISH);
                            String aDate = sdf.format(calendar.getTime());
                            date.setText(aDate);

                            double temp_int = Double.parseDouble(temperature);
                            double cels = (temp_int - 32) / 1.8000;
                            cels = Math.round(cels);
                            int i = (int) cels;
                            String celsius = String.valueOf(i);
                            temp.setText(celsius + "°C");

                            double minTemp_int = Double.parseDouble(minTemp);// parses the string
                            // to an int
                            double minCels = (minTemp_int - 32)/1.8000; // converts from
                            // farenheit to cels
                            minCels = Math.round(minCels);// rounds up
                            int j = (int) minCels; // declares min cels as variable j
                            String minCelsius = String.valueOf(j); // makes the string the value
                            // of int j
                            tempMin.setText("Min: " + minCelsius + "°C"); // sets the min
                            // temperature

                            double maxTemp_int = Double.parseDouble(maxTemp);
                            double maxCels = (maxTemp_int -32) / 1.8000;
                            maxCels = Math.round(maxCels);
                            int k = (int) maxCels;
                            String maxCelsius = String.valueOf(k);
                            tempMax.setText("Max: " + maxCelsius  +"°C" );

                            int directionInDegrees = Integer.parseInt(direction);
// conversion for direction found at https://blog.catzie.net/java-method-to-convert-degrees-into-directions-16-wind-compass-rose/
                            // converts the degrees into cardinal points on a compass
                            if( (directionInDegrees >= 348.75) && (directionInDegrees <= 360) ||
                                    (directionInDegrees >= 0) && (directionInDegrees <= 11.25)    ){
                                winddirection.setText("N");
                            } else if( (directionInDegrees >= 11.25 ) && (directionInDegrees <= 33.75)){
                                winddirection.setText("NNE");
                            } else if( (directionInDegrees >= 33.75 ) &&(directionInDegrees <= 56.25)){
                                winddirection.setText("NE");
                            } else if( (directionInDegrees >= 56.25 ) && (directionInDegrees <= 78.75)){
                                winddirection.setText("ENE");
                            } else if( (directionInDegrees >= 78.75 ) && (directionInDegrees <= 101.25) ){
                                winddirection.setText("E");
                            } else if( (directionInDegrees >= 101.25) && (directionInDegrees <= 123.75) ){
                                winddirection.setText("ESE");
                            } else if( (directionInDegrees >= 123.75) && (directionInDegrees <= 146.25) ){
                                winddirection.setText("SE");
                            } else if( (directionInDegrees >= 146.25) && (directionInDegrees <= 168.75) ){
                                winddirection.setText("SSE");
                            } else if( (directionInDegrees >= 168.75) && (directionInDegrees <= 191.25) ){
                                winddirection.setText("S");
                            } else if( (directionInDegrees >= 191.25) && (directionInDegrees <= 213.75) ){
                                winddirection.setText("SSW");
                            } else if( (directionInDegrees >= 213.75) && (directionInDegrees <= 236.25) ){
                                winddirection.setText("SW");
                            } else if( (directionInDegrees >= 236.25) && (directionInDegrees <= 258.75) ){
                                winddirection.setText("WSW");
                            } else if( (directionInDegrees >= 258.75) && (directionInDegrees <= 281.25) ){
                                winddirection.setText("W");
                            } else if( (directionInDegrees >= 281.25) && (directionInDegrees <= 303.75) ){
                                winddirection.setText("WNW");
                            } else if( (directionInDegrees >= 303.75) && (directionInDegrees <= 326.25) ){
                                winddirection.setText("NW");
                            } else if( (directionInDegrees >= 326.25) && (directionInDegrees <= 348.75) ){
                                winddirection.setText("NNW");
                            } else {
                                winddirection.setText("?");
                            }

                        }catch(JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){

            }
        }
        );
        AppController.getInstance(this).addToRequestQueue(jor);
        return rootView;
    }

}

