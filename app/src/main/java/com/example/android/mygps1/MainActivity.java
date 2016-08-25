//main



package com.example.android.mygps1;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private LocationManager locationManager;
    ArrayList<String> mylist = new ArrayList<String>();
    TextView dataText;
    String CurrentLat = "noData", CurrentLng="noData",mode="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        dataText  = (TextView) findViewById(R.id.dataText);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000,   // 3 sec
                2, this);



    }


    public void doTest(View view){
        String str ="btnworks";

        Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT).show();

 String lt = "11.222365";
 String lg = "56.999964";
 String type = "insert";
        mode="testInsert";

 BackgroundWorker backgroundWorker = new BackgroundWorker(this);
 backgroundWorker.execute(type, lt, lg,mode);

    }


    public void doUpload(View view){
        String str ="Uploading...";

        Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT).show();

       // String lt = "11.222365";
      //  String lg = "56.999964";
        String type = "insert";
        mode = "clickInsert";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,CurrentLat,CurrentLng,mode);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onLocationChanged(Location location) {
        String str = "Lat"+location.getLatitude()+"--Lng:"+location.getLongitude();

        mylist.add(str);
          dataText.append("\n*:");
          dataText.append(str);
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        String lt = String.valueOf(lat);
        String lg = String.valueOf(lng);

        CurrentLat = lt;
        CurrentLng = lg;
        mode = "autoInsert";
        String type = "insert";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,lt,lg,mode);


        Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getBaseContext(), "Gps turned on ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(getBaseContext(), "Gps turned off ", Toast.LENGTH_SHORT).show();
    }
}
//updated on 26