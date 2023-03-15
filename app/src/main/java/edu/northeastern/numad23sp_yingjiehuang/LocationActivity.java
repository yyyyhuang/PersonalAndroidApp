package edu.northeastern.numad23sp_yingjiehuang;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class LocationActivity extends AppCompatActivity {

    private static final int PERMISSIONS_FINE_LOCATION = 9;

    public LocationManager locationManager;
    Button reset, getLocation;
    TextView lat,lon, dist;
    double latitude, longitude, change;
    boolean startLocation, locGrant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        latitude = 0;
        longitude = 0;

        reset = (Button) findViewById(R.id.reset);
        getLocation = (Button) findViewById(R.id.getLocation);
        lat = findViewById(R.id.latitude);
        lon = findViewById(R.id.longitude);
        dist = findViewById(R.id.distance);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if(startLocation) {requestLocation();}

        getLocation.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                startLocation = true;
                requestLocation();
                if(locGrant){
                    Toast.makeText(LocationActivity.this, "updating", Toast.LENGTH_SHORT).show();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dist.setText("0.00 meters");
                change = 0;
                Toast.makeText(LocationActivity.this, "reset succesfully", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void requestLocation() {
        if (ContextCompat.checkSelfPermission(LocationActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locGrant = true;
            LocationListener locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {
                    updateUI(location);
                }
            };
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 0, locationListener);
        } else {
            ActivityCompat.requestPermissions(LocationActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_FINE_LOCATION);
        }
    }

    public void updateUI(Location location) {
        if(location != null) {
            double currLat = (double)location.getLatitude();
            double currLon = (double)location.getLongitude();
            // double currLat = (double)Math.round(location.getLatitude() * 100000d) / 100000d;
            // double currLon = (double)Math.round(location.getLongitude()* 100000d) / 100000d;

            lat.setText(String.valueOf(currLat));
            lon.setText(String.valueOf(currLon));

            if(latitude != 0 && longitude != 0) {
                change = change + distance(latitude, longitude, currLat, currLon);
                dist.setText("Distance: " + change + "meters");
            }
            latitude = currLat;
            longitude = currLon;
        } else {
            Log.i("update", "no location");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_FINE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    Toast.makeText(LocationActivity.this,"Permission granted!", Toast.LENGTH_SHORT).show();
                    requestLocation();
                }

            }
            else{
                Toast.makeText(LocationActivity.this,"Need location permission! Please allow and come back later.",
                        Toast.LENGTH_SHORT).show();

            }
        }
    }

    public double distance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                  + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                  * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double res = 6371 * c * 1000;
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.CEILING);

        return Double.valueOf(df.format(res));
    }

}


