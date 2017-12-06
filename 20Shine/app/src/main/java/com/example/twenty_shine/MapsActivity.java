package com.example.twenty_shine;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.OnConnectionFailedListener{
    //onConnectionFailed
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    //onMapReady
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is ready", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onMapReady: ***map is ready");
        mMap = googleMap;

        if (mLocationPermissionsGranted) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);

            init();
        }
    }

    //constant
    private static final String TAG = "MapsActivity";
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISION_RESQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 14f ;
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(new LatLng(-40, -168), new LatLng(71, 136));


    //widgets
    private AutoCompleteTextView mSearchText;
    private ImageView mGps;


    //vars
    private Boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private PlaceAutocomplete mPlaceAutocomplete;
    private GoogleApiClient mGoogleApiClient;


    //nearbyplace
    int PROXIMITY_RADIUS = 1000;
    double latitude, longtitude;

    //branches address
    LatLng branch1 = new LatLng(10.723191, 106.712949);
    LatLng branch2 = new LatLng(10.729263, 106.718185);
    LatLng branch3 = new LatLng(10.729263, 106.718185);
    LatLng branch4 = new LatLng(10.729685, 106.721189);
    LatLng branch5 = new LatLng(10.731203, 106.703250);
    LatLng branch6 = new LatLng(10.730269, 106.708349);
    LatLng branch7 = new LatLng(10.729582, 106.703318);
    LatLng branch8 = new LatLng(10.729693, 106.702544);
    LatLng branch9 = new LatLng(10.729243, 106.700244);
    LatLng branch10 = new LatLng(10.729462, 106.699975);

    LatLng[] branchArr = {branch1, branch2, branch3, branch4, branch5, branch6, branch7, branch8, branch9, branch10};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mSearchText = (AutoCompleteTextView) findViewById(R.id.input_search);
        mGps = (ImageView) findViewById(R.id.ic_gps);

        getLocationPermission();

//        init();
    }

    private void init() {
        Log.d(TAG, "init: ****initializing");

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this,this)
                .build();

        mPlaceAutocomplete = new PlaceAutocomplete(this, Places.getGeoDataClient(this,null), LAT_LNG_BOUNDS, null);

        mSearchText.setAdapter(mPlaceAutocomplete);

        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    geoLocate();
                    return true;
                }
                return false;
            }
        });
        mGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: *****clicked gps icon - your location");
                getDeviceLocation();
            }
        });

        //show all branches
        for(int i = 0; i < branchArr.length; i++){
            MarkerOptions mbranch = new MarkerOptions()
                    .position(branchArr[i])
                    .title("20 Shine! BRANCH " + (i+1));
            mMap.addMarker(mbranch);
        }

    }

    private void geoLocate() {
        Log.d(TAG, "geoLocate: *****geoLocating");

        String searchString = mSearchText.getText().toString();

        Geocoder geocoder = new Geocoder(MapsActivity.this);
        List<Address> list = new ArrayList<>();

        try{
            list = geocoder.getFromLocationName(searchString, 1);
        } catch (IOException e){
            Log.e(TAG, "geoLocate: IOException: " + e.getMessage() );
        }

//        if(list.size() > 0) {
//            Address address = list.get(0);
//
//            Log.d(TAG, "geoLocate: *****Found a location" + address.toString());
//
//            moveCamera(new LatLng(address.getLatitude(), address.getLongitude()),
//                    DEFAULT_ZOOM, address.getAddressLine(0));
//        }

//            "20Shine! branch here"
        if(list.size() > 0) {
            Address address = list.get(0);

            Log.d(TAG, "geoLocate: *****Found a location" + address.toString() );

            if(searchString.equals("branch 1")) {
                moveCamera(branch1, DEFAULT_ZOOM, "20Shine! branch 1 here");
            }
        }
    }

    private void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: ***getting the current devices current location");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            if(mLocationPermissionsGranted){
                Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: ***found location");
                            Location currentLocation = (Location) task.getResult();


                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                                    DEFAULT_ZOOM, "Your Location");
                        } else {
                            Log.d(TAG, "onComplete: ***current location is null");
                            Toast.makeText(MapsActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }catch (SecurityException e){
            Log.e(TAG, "getDeviceLocation: ***Security Exception: " + e.getMessage() );
        }
    }

    private void moveCamera(LatLng latlng, float zoom, String title) {
        Log.d(TAG, "moveCamera: moving camera to: lat " + latlng.latitude + ", lng: " + latlng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, zoom));

        if(!title.equals("Your Location")){
            MarkerOptions options = new MarkerOptions()
                    .position(latlng)
                    .title(title);
            mMap.addMarker(options);
        }
    }

    private void initMap() {
        Log.d(TAG, "initMap: ***initialize map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(MapsActivity.this);
    }


    private void getLocationPermission() {
        Log.d(TAG, "getLocationPermission: ***get location permission");
        String[] permissions = {FINE_LOCATION, COURSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISION_RESQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISION_RESQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        mLocationPermissionsGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISION_RESQUEST_CODE: {
                if(grantResults.length > 0){
                    for (int i = 0; i < grantResults.length; i++){
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: ***permission failed");
                            return;
                        }
                    }
                    mLocationPermissionsGranted = true;
                    Log.d(TAG, "onRequestPermissionsResult: ***permission granted");
                    //initialize our map
                    initMap();
                }
            }
        }
    }
}
