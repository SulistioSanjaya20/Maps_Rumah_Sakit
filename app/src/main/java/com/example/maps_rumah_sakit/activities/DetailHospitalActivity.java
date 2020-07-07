package com.example.maps_rumah_sakit.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.maps_rumah_sakit.R;
import com.example.maps_rumah_sakit.model.ModelRumahSakit;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailHospitalActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap googleMaps;
    private double latitude, longitude;
    TextView txtNameRS, txtFax, txtJenisRs,txtMasp, txtKodePos, txtNoTlp, txtEmail, txtWebsite;
    String NameRS, JenisRs,Maps, KodePos, NoTlp, Email, Website, Alamat, Fax;
    ModelRumahSakit modelRumahSakitList;
    Button btnCall;
    Button btnMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hospital);

        Toolbar tbDetailRS = findViewById(R.id.tbDetailRS);
        setSupportActionBar(tbDetailRS);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        modelRumahSakitList = (ModelRumahSakit) getIntent().getSerializableExtra("rsDetail");
        if (modelRumahSakitList != null) {
            NameRS = modelRumahSakitList.getNamaRs();
            JenisRs = modelRumahSakitList.getJenisRs();
            KodePos = modelRumahSakitList.getKodePos();
            Maps = modelRumahSakitList.getMaps();
            NoTlp = modelRumahSakitList.getTelepon();
            Email = modelRumahSakitList.getEmail();
            Fax = modelRumahSakitList.getFaximile();
            Website = modelRumahSakitList.getWebsite();
            latitude = modelRumahSakitList.getLatitude();
            longitude = modelRumahSakitList.getLongitude();

            txtNameRS = findViewById(R.id.txtNameRS);
            txtJenisRs = findViewById(R.id.txtJenisRs);
            txtMasp = findViewById(R.id.txtMpas);
            txtKodePos = findViewById(R.id.txtKodePos);
            txtNoTlp = findViewById(R.id.txtNoTlp);
            txtEmail = findViewById(R.id.txtEmail);
            txtFax = findViewById(R.id.txtFax);
            txtWebsite = findViewById(R.id.txtWebsite);
            btnCall = findViewById(R.id.btnCall);
            btnMaps = findViewById(R.id.btbmaps);


            txtNameRS.setText("RS" + " " + NameRS);
            txtJenisRs.setText(JenisRs);
            txtKodePos.setText(KodePos);
            txtMasp.setText(Maps);
            txtNoTlp.setText("021 " + NoTlp);
            txtFax.setText("021 " + Fax);
            txtEmail.setText(Email);
            if (Website == null) {
                txtWebsite.setText("-");
            } else {
                SpannableString spannableString = new SpannableString("http://" + Website);
                spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
                txtWebsite.setText(spannableString);
                txtWebsite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + Website));
                        startActivity(intent);
                    }
                });
            }

            btnCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent;
                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "021 " + NoTlp));
                    startActivity(intent);
                }
            });

            btnMaps.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {

                    Uri gmmIntentUri = Uri.parse("google.navigation:q="+latitude+","+longitude);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    if (mapIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(mapIntent);
                    }

                }
            });

        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMaps = googleMap;
        LatLng latLng = new LatLng(latitude, longitude);
        googleMaps.addMarker(new MarkerOptions().position(latLng).title(Alamat));
        googleMaps.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMaps.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        googleMaps.getUiSettings().setAllGesturesEnabled(true);
        googleMaps.getUiSettings().setZoomGesturesEnabled(true);
        googleMaps.setTrafficEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
