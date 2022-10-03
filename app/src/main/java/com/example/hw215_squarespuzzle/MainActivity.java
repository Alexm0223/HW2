package com.example.hw215_squarespuzzle;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;


public class MainActivity extends AppCompatActivity {


    private Button newGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newGame =(Button)findViewById(R.id.button);

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FifteenPuzzle.class));
            }
        });
    }
}