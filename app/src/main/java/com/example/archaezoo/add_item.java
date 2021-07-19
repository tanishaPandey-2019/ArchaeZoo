package com.example.archaezoo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;
import java.util.Map;


public class add_item extends AppCompatActivity implements View.OnClickListener{

    //variables

    Button btLocation;
    TextView tvLong, tvLat;
   FusedLocationProviderClient fusedLocationProviderClient;

    EditText etsite,etdate,etid,etzone,ettrench,etlayer,etnumber,etorientation,etdip,etsoil,etisolated,etarticulated,etdimensions,etlength,etbreadth,etthickness,
            etcolour,etweight,etremains,etsampled,etphotographed,etintegrity,ettaxongennus,etspecies,etunidentified,
            etskeletalelement,etlaterality,ettooth,etwearingstage,etrefitting,etfragments,etassociation,etmeasurements,etdiaphysis,etcircumference,
            etfracture,etcalcination,ettap_colour,etconcentration,etoxide,eterosion,etexfoilation,etrootEtching,etweathering,etrolling,ettrampling,etporous,etintrusions,
            etworkedBone,etcutMarks,etchopMarks,etscrapingMarks,ettoothMarks,etotheranthropic,etpathology,etnotes;

    Button buttonAddItem;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_item);
        etsite = (EditText)findViewById(R.id.site);
        etdate = (EditText)findViewById(R.id.date);
        etid = (EditText)findViewById(R.id.id);
        etzone = (EditText)findViewById(R.id.zone);
        ettrench = (EditText)findViewById(R.id.trench);
        etlayer = (EditText)findViewById(R.id.layer);
        etnumber = (EditText)findViewById(R.id.number);
        etorientation = (EditText)findViewById(R.id.orientation);
        etdip = (EditText)findViewById(R.id.dip);
        etsoil = (EditText)findViewById(R.id.soil);
        etisolated = (EditText)findViewById(R.id.isolated);
        etarticulated = (EditText)findViewById(R.id.articulated);
        etdimensions = (EditText)findViewById(R.id.dimensions);
        etlength = (EditText)findViewById(R.id.length);
        etbreadth = (EditText)findViewById(R.id.breadth);
        etthickness = (EditText)findViewById(R.id.thickness);
        etcolour = (EditText)findViewById(R.id.colour);
        etweight = (EditText)findViewById(R.id.weight);
        etremains = (EditText)findViewById(R.id.remains);
        etsampled = (EditText)findViewById(R.id.sampled);
        etphotographed = (EditText)findViewById(R.id.photographed);
        etintegrity = (EditText)findViewById(R.id.integrity);
        ettaxongennus = (EditText)findViewById(R.id.taxongennus);
        etspecies = (EditText)findViewById(R.id.species);
        etunidentified = (EditText)findViewById(R.id.unidentified);
        etskeletalelement = (EditText)findViewById(R.id.skeletalelement);
        etlaterality = (EditText)findViewById(R.id.laterality);
        ettooth = (EditText)findViewById(R.id.tooth);
        etwearingstage = (EditText)findViewById(R.id.wearingstage);
        etrefitting = (EditText)findViewById(R.id.refitting);
        etfragments = (EditText)findViewById(R.id.fragments);
        etassociation = (EditText)findViewById(R.id.association);
        etmeasurements = (EditText)findViewById(R.id.measurements);
        etdiaphysis = (EditText)findViewById(R.id.diaphysis);
        etcircumference = (EditText)findViewById(R.id.circumference);
        etfracture = (EditText)findViewById(R.id.fracture);
        etcalcination = (EditText)findViewById(R.id.calcination);
        ettap_colour = (EditText)findViewById(R.id.tap_colour);
        etconcentration = (EditText)findViewById(R.id.concentration);
        etoxide = (EditText)findViewById(R.id.oxide);
        eterosion = (EditText)findViewById(R.id.erosion);
        etexfoilation = (EditText)findViewById(R.id.exfoilation);
        etrootEtching = (EditText)findViewById(R.id.rootEtching);
        etweathering = (EditText)findViewById(R.id.weathering);
        etrolling = (EditText)findViewById(R.id.rolling);
        ettrampling = (EditText)findViewById(R.id.trampling);
        etporous = (EditText)findViewById(R.id.porous);
        etintrusions = (EditText)findViewById(R.id.intrusions);
        etworkedBone = (EditText)findViewById(R.id.workedBone);
        etcutMarks = (EditText)findViewById(R.id.cutMarks);
        etchopMarks = (EditText)findViewById(R.id.chopMarks);
        etscrapingMarks = (EditText)findViewById(R.id.scrapingMarks);
        ettoothMarks = (EditText)findViewById(R.id.toothMarks);
        etotheranthropic = (EditText)findViewById(R.id.otheranthropic);
        etpathology = (EditText)findViewById(R.id.pathology);
        etnotes = (EditText)findViewById(R.id.notes);

        tvLong = (TextView)findViewById(R.id.longpt);
        tvLat = (TextView)findViewById(R.id.latpt);

        buttonAddItem = (Button)findViewById(R.id.btn_add_item_form);
        buttonAddItem.setOnClickListener(this);

        btLocation = findViewById(R.id.btnCoordinates);


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(
                add_item.this
        );

        btLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //condition check
                if(ActivityCompat.checkSelfPermission(add_item.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(add_item.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED)
                {
                    getCurrentLocation();
                }
                else
                {
                    ActivityCompat.requestPermissions(add_item.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                            ,Manifest.permission.ACCESS_COARSE_LOCATION},100);
                }

            }
        });

    }

    private void addItemToSheet() {

        final ProgressDialog loading = ProgressDialog.show(this,"Adding Item","Please wait");
        final String site = etsite.getText().toString().trim();
        final String date = etdate.getText().toString().trim();
        final String id = etid.getText().toString().trim();
        final String zone = etzone.getText().toString().trim();
        final String trench = ettrench.getText().toString().trim();
        final String layer = etlayer.getText().toString().trim();
        final String longitude = tvLong.getText().toString().trim();
        final String latitude = tvLat.getText().toString().trim();
        final String number = etnumber.getText().toString().trim();
        final String orientation = etorientation.getText().toString().trim();
        final String dip = etdip.getText().toString().trim();
        final String soil = etsoil.getText().toString().trim();
        final String isolated = etisolated.getText().toString().trim();
        final String articulated = etarticulated.getText().toString().trim();
        final String dimensions = etdimensions.getText().toString().trim();
        final String length = etlength.getText().toString().trim();
        final String breadth = etbreadth.getText().toString().trim();
        final String thickness = etthickness.getText().toString().trim();
        final String colour = etcolour.getText().toString().trim();
        final String weight = etweight.getText().toString().trim();
        final String remains = etremains.getText().toString().trim();
        final String sampled = etsampled.getText().toString().trim();
        final String photographed = etphotographed.getText().toString().trim();
        final String integrity = etintegrity.getText().toString().trim();
        final String taxonGenus = ettaxongennus.getText().toString().trim();
        final String species = etspecies.getText().toString().trim();
        final String unidentified = etunidentified.getText().toString().trim();
        final String skeletalelement = etskeletalelement.getText().toString().trim();
        final String laterality = etlaterality.getText().toString().trim();
        final String tooth = ettooth.getText().toString().trim();
        final String wearingstage = etwearingstage.getText().toString().trim();
        final String refitting = etrefitting.getText().toString().trim();
        final String fragments = etfragments.getText().toString().trim();
        final String association = etassociation.getText().toString().trim();
        final String measurements = etmeasurements.getText().toString().trim();
        final String diaphysis = etdiaphysis.getText().toString().trim();
        final String circumference = etcircumference.getText().toString().trim();
        final String fracture = etfracture.getText().toString().trim();
        final String calcination = etcalcination.getText().toString().trim();
        final String tap_colour = ettap_colour.getText().toString().trim();
        final String concentration = etconcentration.getText().toString().trim();
        final String oxide = etoxide.getText().toString().trim();
        final String erosion = eterosion.getText().toString().trim();
        final String exfoilation = etexfoilation.getText().toString().trim();
        final String rootEtching = etrootEtching.getText().toString().trim();
        final String weathering = etweathering.getText().toString().trim();
        final String rolling = etrolling.getText().toString().trim();
        final String trampling = ettrampling.getText().toString().trim();
        final String porous = etporous.getText().toString().trim();
        final String intrusions = etintrusions.getText().toString().trim();
        final String workedBone = etworkedBone.getText().toString().trim();
        final String cutMarks = etcutMarks.getText().toString().trim();
        final String chopMarks = etchopMarks.getText().toString().trim();
        final String scrapingMarks = etscrapingMarks.getText().toString().trim();
        final String toothMarks = ettoothMarks.getText().toString().trim();
        final String otherAnthropic = etotheranthropic.getText().toString().trim();
        final String pathology = etpathology.getText().toString().trim();
        final String notes = etnotes.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbzzh34sM3QEXl5zrTVYrkYA6Yh2rP3HXJfH6eotjabCEUIXbBwYTRWOD4ZUlhpkXQ7L/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loading.dismiss();
                        Toast.makeText(add_item.this,response,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();

                //here we pass params
                parmas.put("action","add_item_form");
                parmas.put("site",site);
                parmas.put("date2",date);
                parmas.put("id2",id);
                parmas.put("zone",zone);
                parmas.put("trench",trench);
                parmas.put("layer",layer);
                parmas.put("number",number);
                parmas.put("longitude",longitude);
                parmas.put("latitude",latitude);
                parmas.put("orientation",orientation);
                parmas.put("dip",dip);
                parmas.put("soil",soil);
                parmas.put("isolated",isolated);
                parmas.put("articulated",articulated);
                parmas.put("dimensions",dimensions);
                parmas.put("length",length);
                parmas.put("breadth",breadth);
                parmas.put("thickness",thickness);
                parmas.put("tap_colour",tap_colour);
                parmas.put("weight",weight);
                parmas.put("remains",remains);
                parmas.put("sampled",sampled);
                parmas.put("photographed",photographed);
                parmas.put("integrity",integrity);
                parmas.put("taxonGenus",taxonGenus);
                parmas.put("species",species);
                parmas.put("unidentified",unidentified);
                parmas.put("skeletalElement",skeletalelement);
                parmas.put("laterality",laterality);
                parmas.put("tooth",tooth);
                parmas.put("wearingStage",wearingstage);
                parmas.put("refitting",refitting);
                parmas.put("fragments",fragments);
                parmas.put("association",association);
                parmas.put("measurements",measurements);
                parmas.put("diaphysis",diaphysis);
                parmas.put("circumference",circumference);
                parmas.put("fracture",fracture);
                parmas.put("calcination",calcination);
                parmas.put("colour",colour);
                parmas.put("concentration",concentration);
                parmas.put("oxide",oxide);
                parmas.put("erosion",erosion);
                parmas.put("exfoilation",exfoilation);
                parmas.put("rootEtching",rootEtching);
                parmas.put("weatheringStage",weathering);
                parmas.put("rolling",rolling);
                parmas.put("trampling",trampling);
                parmas.put("porous",porous);
                parmas.put("intrusions",intrusions);
                parmas.put("workedBone",workedBone);
                parmas.put("cutMarks",cutMarks);
                parmas.put("chopMarks",chopMarks);
                parmas.put("scrapingMarks",scrapingMarks);
                parmas.put("toothMarks",toothMarks);
                parmas.put("otherAnthropic",otherAnthropic);
                parmas.put("pathology",pathology);
                parmas.put("notes",notes);

                return parmas;
            }
        };

        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);


    }

    @Override
    public void onClick(View v) {

        if(v == buttonAddItem){
            addItemToSheet();

            //Define what to do when button is clicked
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length > 0 && (grantResults[0] + grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
            getCurrentLocation();
        } else {
            Toast.makeText(getApplicationContext(), "Permission Denied.", Toast.LENGTH_SHORT).show();
        }

    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {

        LocationManager locationManager = (LocationManager) getSystemService(
                Context.LOCATION_SERVICE
        );

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
        {
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();

                    if(location != null)
                    {
                        tvLat.setText((String.valueOf(location.getLatitude())));
                        tvLong.setText((String.valueOf(location.getLongitude())));
                    }
                    else
                    {
                        final LocationRequest locationRequest = new LocationRequest().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(10000)
                                .setFastestInterval(1000)
                                .setNumUpdates(1);

                        LocationCallback locationCallback =  new LocationCallback()
                        {
                            @Override
                            public void onLocationResult(LocationResult locationResult) {
                                super.onLocationResult(locationResult);

                                Location location1 = locationResult.getLastLocation();
                                tvLat.setText(String.valueOf(location1.getLatitude()));
                                tvLong.setText(String.valueOf(location1.getLongitude()));
                            }
                        };

                        fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper());
                    }

                }
            });
        }
        else
        {
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }
}