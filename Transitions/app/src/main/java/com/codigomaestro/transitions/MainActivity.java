package com.codigomaestro.transitions;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_scene1).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Scene1Activity.class);
            startActivity(intent);
        });

        findViewById(R.id.button_scene2).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Scene2Activity.class);
            startActivity(intent);
        });
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}