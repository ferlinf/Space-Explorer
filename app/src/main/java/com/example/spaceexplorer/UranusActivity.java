package com.example.spaceexplorer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class UranusActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.desc_uranus);

        Button buttonNavigate =findViewById(R.id.learnmore);
        ImageView arrow=findViewById(R.id.arrowBackImageView);

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(UranusActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        buttonNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(UranusActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });


    }
}
