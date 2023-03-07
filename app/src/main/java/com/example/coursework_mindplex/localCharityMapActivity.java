package com.example.coursework_mindplex;

import static com.google.android.gms.maps.CameraUpdateFactory.newLatLng;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.coursework_mindplex.databinding.ActivityLocalCharityMapBinding;

public class localCharityMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityLocalCharityMapBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            binding = ActivityLocalCharityMapBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }catch (Exception e){
            Log.w("MAP ERROR",e);
        }
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

        LatLng CygnetHosp = new LatLng(52.908528,-1.184964);
        LatLng BayTherapy = new LatLng(52.9388495, -1.125671);
        LatLng DayNightPharm = new LatLng(52.906123949999994, -1.1756192113025237);
        LatLng CliftonGP = new LatLng(52.9, -1.18333);
        LatLng JohnRyleGP = new LatLng(52.9039479, -1.1761751);

        mMap.addMarker(new MarkerOptions().position(CygnetHosp).title("Cygnet Hosptial Clifton"));
        mMap.addMarker(new MarkerOptions().position(BayTherapy).title("Bay Therapy Centre"));
        mMap.addMarker(new MarkerOptions().position(DayNightPharm).title("Day Night Pharmacy"));
        mMap.addMarker(new MarkerOptions().position(CliftonGP).title("Clifton Medical Practice"));
        mMap.addMarker(new MarkerOptions().position(JohnRyleGP).title("John Ryle Medical Practice"));

        CameraUpdate cameraLocation = CameraUpdateFactory.newLatLngZoom(CliftonGP,11);
        mMap.animateCamera(cameraLocation);
    }
}