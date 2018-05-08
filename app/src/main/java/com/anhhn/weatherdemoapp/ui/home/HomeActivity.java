package com.anhhn.weatherdemoapp.ui.home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anhhn.weatherdemoapp.R;
import com.anhhn.weatherdemoapp.base.activity.BaseActivity;
import com.anhhn.weatherdemoapp.pojo.dto.WeatherDTO;
import com.anhhn.weatherdemoapp.ui.weatherlist.WeatherListPresenter;
import com.anhhn.weatherdemoapp.utils.AppUtils;
import com.anhhn.weatherdemoapp.utils.ImageUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import butterknife.BindView;

/**
 * Created by Andy Ha on 5/8/18.
 */
public class HomeActivity extends BaseActivity<HomeContract.Presenter> implements HomeContract.View,
        GoogleApiClient.ConnectionCallbacks, LocationListener {

    @BindView(R.id.llMyLocation)
    LinearLayout llMyLocation;
    @BindView(R.id.tvMyDegree)
    TextView tvMyDegree;
    @BindView(R.id.ivMyWeather)
    SimpleDraweeView ivMyWeather;

    private WeatherListPresenter weatherListPresenter;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private boolean isLocationEnabled;

    private static final int RC_LOCATION = 8888;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;

    // Location updates intervals in sec
    private static final int UPDATE_INTERVAL = 10000; // 10 sec
    private static final int FATEST_INTERVAL = 5000; // 5 sec
    private static final int DISPLACEMENT = 10; // 10 meters

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        weatherListPresenter = new WeatherListPresenter();
        addFragment(R.id.listContainer, weatherListPresenter.getFragment());

        Fragment buttonFragment = new ButtonFragment();
        addFragment(R.id.buttonContainer, buttonFragment);

        if (checkPlayServices()) {
            // Building the GoogleApi client
            buildGoogleApiClient();
            createLocationRequest();
        }
        requestPermissions();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    public HomeContract.Presenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            stopLocationUpdates();
            mGoogleApiClient.disconnect();
        }
    }

    private void addFragment(int container, Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(container, fragment)
                .commit();
    }

    public void refreshList() {
        weatherListPresenter.fetchWeather();
    }

    @Override
    public void onFetchMyLocationWeatherSuccess(WeatherDTO weatherDTO) {
        if(llMyLocation == null) return;
        llMyLocation.setVisibility(View.VISIBLE);
        tvMyDegree.setText(String.format(getString(R.string.degree_text),
                weatherDTO.getCurrentTemp()));
        ImageUtils.loadImage(ivMyWeather, AppUtils.getInstance().
                getWeatherIconByName(weatherDTO.getIcon()));
    }

    /**
     * Get weather for current location
     */
    @SuppressLint("MissingPermission")
    private void fetchMyLocationWeather() {
        Location myLocation = LocationServices.FusedLocationApi
                .getLastLocation(mGoogleApiClient);
        getPresenter().fetchMyLocationWeather(myLocation.getLatitude(), myLocation.getLongitude());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (RC_LOCATION == requestCode && permissions.length == 1
                && grantResults.length == 1) {
            isLocationEnabled = true;
            if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
                startLocationUpdates();
            }
        }
    }

    private void requestPermissions() {
        if (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            isLocationEnabled = true;

        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, RC_LOCATION);
        }
    }

    private boolean checkPermission(String permission) {
        return ContextCompat.checkSelfPermission(this, permission)
                == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Creating google api client object
     */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API).build();
    }

    /**
     * Creating location request object
     */
    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FATEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(DISPLACEMENT);
    }

    /**
     * Method to verify google play services on the device
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "This device is not supported by Google Play Service.", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
            return false;
        }
        return true;
    }

    /**
     * Starting the location updates
     */
    @SuppressLint("MissingPermission")
    protected void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);

    }

    /**
     * Stopping location updates
     */
    protected void stopLocationUpdates() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(
                    mGoogleApiClient, this);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (isLocationEnabled) {
            startLocationUpdates();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        fetchMyLocationWeather();
    }
}
