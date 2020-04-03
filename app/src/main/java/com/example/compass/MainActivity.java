package com.example.compass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private Button compassButton;
    private Button accelerometersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        compassButton = (Button) findViewById(R.id.compass);
        compassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCompassActivity();
            }
        });

        accelerometersButton = (Button) findViewById(R.id.accelerometers);
        accelerometersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccelerometersActivity();
            }
        });
    }

    public void openCompassActivity() {
        Intent intent = new Intent(this, Compass.class);
        startActivity(intent);
    }

    public void openAccelerometersActivity() {
        Intent intent = new Intent(this, Accelerometers.class);
        startActivity(intent);
    }
}
