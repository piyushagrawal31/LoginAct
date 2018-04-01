package com.example.rucha.loginact.Location;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.rucha.loginact.R;

public class GetLocationActivity extends AppCompatActivity {

    private static final long ONE_MIN = 1000 * 60;
    private static final long TWO_MIN = ONE_MIN * 2;
    private static final long FIVE_MIN = ONE_MIN * 5;
    private static final long MEASURE_TIME = 1000 * 30;
    private static final long POLLING_FREQ = 1000 * 10;
    private static final float MIN_ACCURACY = 25.0f;
    private static final float MIN_LAST_READ_ACCURACY = 500.0f;
    private static final float MIN_DISTANCE = 10.0f;
    private static final int NETWORK_PERM_CODE = 20;
    private boolean permissionGranted = false;

    // Views for display location information
    private TextView mAccuracyView;
    private TextView mTimeView;
    private TextView mLatView;
    private TextView mLngView;

    private int mTextViewColor = Color.GRAY;

    // Current best location estimate
    private Location mBestReading;

    // Reference to the LocationManager and LocationListener
    private LocationManager mLocationManager;
    private LocationListener mLocationListener;

    private final String TAG = "GetLocationActivity";
    private boolean mFirstUpdate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);

        mAccuracyView = (TextView) findViewById(R.id.txtaccuracy_view);
        mTimeView = (TextView) findViewById(R.id.txttime_view);
        mLatView = (TextView) findViewById(R.id.txtlat_view);
        mLngView = (TextView) findViewById(R.id.txtlng_view);

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (mLocationManager == null)
            finish();

        // Get best last location measurement
        mBestReading = bestLastKnownLocation(MIN_LAST_READ_ACCURACY, FIVE_MIN);

        // Display last reading information
        if (null != mBestReading) {
            updateDisplay(mBestReading);
        }
        else {
            mAccuracyView.setText("No Initial Reading Available");
        }

        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                ensureColor();

//              Determine whether new location is better than current best estimate

                if (null == mBestReading ||
                        location.getAccuracy() < mBestReading.getAccuracy()) {

//                  update best estimate
                    mBestReading = location;

//                  Update display
                    updateDisplay(location);

                    if (mBestReading.getAccuracy() < MIN_ACCURACY){
                        mLocationManager.removeUpdates(mLocationListener);
                    }
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
    }

    @Override
    protected void onResume() {

        super.onResume();
        if (mBestReading == null)
            return;

        if (mBestReading.getAccuracy() > MIN_LAST_READ_ACCURACY ||
                mBestReading.getTime() < System.currentTimeMillis() - TWO_MIN) {

            if (!checkLocationPermission())
                return;

//             Register for network location updates
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    POLLING_FREQ, MIN_DISTANCE, mLocationListener);

//            Register for GPS location updates
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    POLLING_FREQ, MIN_DISTANCE, mLocationListener);

//            Schedule a runnable to unregister location listeners
            Executors.newScheduledThreadPool(1).schedule(new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, "Location Updates Cancelled");
                    mLocationManager.removeUpdates(mLocationListener);
                }
            }, MEASURE_TIME, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocationManager.removeUpdates(mLocationListener);

    }

    private void ensureColor() {
        if (mFirstUpdate) {
            setTextViewColor(mTextViewColor);
            mFirstUpdate = false;
        }
    }

    private void setTextViewColor(int color) {

        mAccuracyView.setTextColor(color);
        mTimeView.setTextColor(color);
        mLatView.setTextColor(color);
        mLngView.setTextColor(color);

    }


    //Update Display
    private void updateDisplay(Location location) {

        mAccuracyView.setText("Accuracy: " + location.getAccuracy());

        mTimeView.setText("Time: " + new SimpleDateFormat("MM/dd/yyyy HH:mm:ss",
                Locale.getDefault()).format(new Date(location.getTime())));

        mLatView.setText("Latitude: "+ location.getLatitude());

        mLngView.setText("Longitude: " + location.getLongitude());

    }

    private Location bestLastKnownLocation(float minAccuracy, long maxAge) {
        Location bestResult = null;
        float bestAccuracy = Float.MAX_VALUE;
        long bestAge = Long.MIN_VALUE;

        List<String> matchingProviders = mLocationManager.getAllProviders();

        for (String provider : matchingProviders) {
            if (!checkLocationPermission()) {
                return null;
            }

            Location location = mLocationManager.getLastKnownLocation(provider);

            if (location != null) {
                float accuracy = location.getAccuracy();
                long time = location.getTime();

                if (accuracy < bestAccuracy) {
                    bestResult = location;
                    bestAccuracy = accuracy;
                    bestAge = time;
                }
            }
        }

        if (mBestReading == null)
            return bestResult;
        // Return best reading or null
        if (bestAccuracy < minAccuracy || (System.currentTimeMillis() - bestAge) > maxAge) {
            return null;
        }else  {
            return bestResult;
        }
//        return bestResult;
    }

    private boolean checkLocationPermission() {

        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermission();
            return false;
        }
        return true;
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, NETWORK_PERM_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == NETWORK_PERM_CODE) {
            for (int i=0; i< permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    permissionGranted = true;
                }
            }
        }
    }
}
