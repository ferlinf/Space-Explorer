package com.example.spaceexplorer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class EarthActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.desc_earth);

        Button buttonNavigate =findViewById(R.id.learnmore);
        ImageView arrowback = findViewById(R.id.arrowBackImageView);

        arrowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(EarthActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        buttonNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(EarthActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });


    }
}
