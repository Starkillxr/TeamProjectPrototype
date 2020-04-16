package com.example.nviropoint;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.nviropoint.R;
import com.example.nviropoint.Volley.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ForecastFragment extends Fragment {
    TextView temp,city,description,date,pressure,humidity,windspeed,winddirection, Sunrise,
            tempMin, tempMax;

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
        String URL = "http://api.openweathermap.org/data/2" +
                ".5/weather?id=2640194&units=imperial&appid=faf15c72035a522d6e027c2be057069c";
//help with json parsing found at https://www.youtube.com/watch?v=8-7Ip6xum6E
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject main = response.getJSONObject("main");
                            JSONObject sys = response.getJSONObject("sys");
                            Long sunrise = sys.getLong("sunrise");
                            JSONArray array = response.getJSONArray("weather");
                            JSONObject object = array.getJSONObject(0);
                            String temperature = String.valueOf(main.getDouble("temp"));
                            String aDescription = object.getString("description");
                            String aCity = response.getString("name");
                            String thePressure = main.getString("pressure");
                            String theHumidity = main.getString("humidity");
                            JSONObject wind = response.getJSONObject("wind");
                            String theSpeed = wind.getString("speed");
                            String direction = wind.getString("deg");
                            String minTemp = String.valueOf(main.getDouble("temp_min"));


                            Sunrise.setText(new SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(new Date(sunrise * 1000)));
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

                            int directionInDegrees = Integer.parseInt(direction);
// conversion for direction found at https://blog.catzie.net/java-method-to-convert-degrees-into-directions-16-wind-compass-rose/
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

