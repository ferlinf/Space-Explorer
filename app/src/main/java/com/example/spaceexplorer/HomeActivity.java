package com.example.spaceexplorer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
   ;
    private BottomNavigationView bottomNavigationView;
    private ViewFlipper viewFlipper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
         Button buttonEarth =findViewById(R.id.earttitle);
        Button buttonJupiter=findViewById(R.id.jupitertitle);
         Button buttonMars=findViewById(R.id.marstitle);
        Button  buttonMercury=findViewById(R.id.merkuriustitle);
        Button buttonNeptunus=findViewById(R.id.neptunustitle);
         Button buttonSaturnus=findViewById(R.id.saturnustitle);
        Button buttonUranus=findViewById(R.id.uranustitle);
         Button buttonVenus=findViewById(R.id.venustitle);
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        viewFlipper =findViewById(R.id.view_flipper);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab_home:
                        // Tampilkan konten untuk tab Home
                        viewFlipper.setDisplayedChild(0);
                        return true;
                    case R.id.tab_search:
                        // Tampilkan konten untuk tab Profile
                        Intent intent = new Intent(HomeActivity.this, SearchActivity.class);
                        startActivity(intent);
                        viewFlipper.setDisplayedChild(1);
                        return true;
                }
                return false;
            }
        });

        LayoutInflater inflater = LayoutInflater.from(this);
        View homeView = inflater.inflate(R.layout.home, viewFlipper, false);
        View searchView = inflater.inflate(R.layout.search, viewFlipper, false);




        buttonEarth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d("Button Click", "Earth button clicked");
                Intent intent =new Intent(HomeActivity.this, EarthActivity.class);
                startActivity(intent);
            }
        });

        buttonJupiter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent =new Intent(HomeActivity.this, JupiterActivity.class);
                startActivity(intent);
            }
        });
        buttonMars.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent =new Intent(HomeActivity.this, MarsActivity.class);
                startActivity(intent);
            }
        });
        buttonMercury.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent =new Intent(HomeActivity.this, MercuryActivity.class);
                startActivity(intent);
            }
        });

        buttonNeptunus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent =new Intent(HomeActivity.this, NeptunusActivity.class);
                startActivity(intent);
            }
        });

        buttonSaturnus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent =new Intent(HomeActivity.this, SaturnusActivity.class);
                startActivity(intent);
            }
        });
        buttonUranus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent =new Intent(HomeActivity.this, UranusActivity.class);
                startActivity(intent);
            }
        });
        buttonVenus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent =new Intent(HomeActivity.this, VenusActivity.class);
                startActivity(intent);
            }
        });

    }
}