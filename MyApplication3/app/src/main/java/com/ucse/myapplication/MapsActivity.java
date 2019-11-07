package com.ucse.myapplication;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ucse.myapplication.aggregates.ApiCallerService;
import com.ucse.myapplication.aggregates.Item;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {//, GoogleMap.OnMarkerClickListener {

    private final ApiCallerService service;
    private GoogleMap mMap;
    private List<Item> items;

    MapsActivity() {
        service = new ApiCallerService();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        items = service.getItemsToList();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        for (Item item : items) {
            // Add a marker in Sydney and move the camera
            LatLng event = new LatLng(item.getLatitude(), item.getLongitude());
            MarkerOptions marker = new MarkerOptions().position(event).title(item.getName());
            // Change marker png
            //marker = marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.beer));
            mMap.addMarker(marker);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(item.getLatitude(), item.getLongitude()), 12.0f));
        }
    }

    /*@Override
    public boolean onMarkerClick(Marker marker) {
        //handle click here
        return true;
    }*/
}
