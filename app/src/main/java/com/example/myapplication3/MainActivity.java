package com.example.myapplication3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout clayout = (ConstraintLayout) findViewById(R.id.main_layout);
        clayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("INFO", "the screen was clicked");
                startActivity(new Intent(MainActivity.this, GameActivity.class));
            }
        });
    }
}
