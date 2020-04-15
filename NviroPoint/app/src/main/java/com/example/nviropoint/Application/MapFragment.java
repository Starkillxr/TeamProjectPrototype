package com.example.nviropoint.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.nviropoint.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * The type Map fragment.
 */
public class MapFragment extends Fragment {

    /**
     * Instantiates a new Map fragment.
     */
//String url = "https://maps.openweathermap.io/map/temp_new/256/256/256.json?appid=faf15c72035a522d6e027c2be057069c";
    public MapFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //MainActivity.makeJsonArrayRequest();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapFrg);


        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });

        return rootView;
    }


}
