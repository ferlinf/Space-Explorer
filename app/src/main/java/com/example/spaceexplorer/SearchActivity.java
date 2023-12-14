package com.example.spaceexplorer;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private EditText editText;
    private Button buttonCari;
    private RecyclerView recyclerView;
    private ArrayList<ItemData> values;
    private PlanetAdapter PlanetAdapter;
    private BottomNavigationView bottomNavigationView;
    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        editText = findViewById(R.id.search_bar);
        buttonCari = findViewById(R.id.buttonSearch);
        recyclerView = findViewById(R.id.recycler_view);
        values = new ArrayList<>();
        PlanetAdapter = new PlanetAdapter(this, values);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(PlanetAdapter);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        viewFlipper = findViewById(R.id.view_flipper);
        buttonCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cariPlanet();
            }
        });
        Log.d("Activity", "RecyclerView initialized");


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab_home:
                        // Tampilkan konten untuk tab Home
                        Intent intent = new Intent(SearchActivity.this, HomeActivity.class);
                        startActivity(intent);
                        viewFlipper.setDisplayedChild(0);
                        return true;
                    case R.id.tab_search:
                        // Tampilkan konten untuk tab Profile

                        viewFlipper.setDisplayedChild(1);
                        return true;
                }
                return false;
            }
        });

        LayoutInflater inflater = LayoutInflater.from(this);
        View homeView = inflater.inflate(R.layout.home, viewFlipper, false);
        View searchView = inflater.inflate(R.layout.search, viewFlipper, false);



    }

    private void cariPlanet() {
        String queryString=editText.getText().toString();
        ConnectivityManager connectivityManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnected()) {

             new FetchPlanet(this,values,PlanetAdapter,recyclerView).execute(queryString);
            Log.d("FetchPlanet", "Start fetching data from API with query: " + queryString);

        }else {
            Toast.makeText(this,"Tidak terhubung ke internet",
                    Toast.LENGTH_SHORT).show();
            Log.e("FetchPlanet", "No internet connection");
        }
    }


}

