package com.bhorkarg.indooratlasexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.indooratlas.android.sdk.IALocation;
import com.indooratlas.android.sdk.IALocationListener;
import com.indooratlas.android.sdk.IALocationManager;
import com.indooratlas.android.sdk.IALocationRequest;

public class MainActivity extends AppCompatActivity {

    IALocationManager mLocationManager;

    //Listener for receiving location updates
    IALocationListener mLocationListener = new IALocationListener() {
        @Override
        public void onLocationChanged(IALocation iaLocation) {
            //Location updates will be received here  
            TextView txtLoc = (TextView) findViewById(R.id.txtLocation);
            txtLoc.setText(iaLocation.getLatitude() + ", " + iaLocation.getLongitude());
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocationManager = IALocationManager.create(this); //create a IALocationManager
    }

    protected void onResume() {
        super.onResume();
        //Start requesting location updates. Specify the listener.
        mLocationManager.requestLocationUpdates(IALocationRequest.create(), mLocationListener);

    }

    protected void onPause() {
        //Stop receiving location updates when app paused.
        mLocationManager.removeLocationUpdates(mLocationListener);
        super.onPause();
    }

    protected void onDestroy() {
        mLocationManager.destroy();
        super.onDestroy();
    }
}
