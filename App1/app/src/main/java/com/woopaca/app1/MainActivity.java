package com.woopaca.app1;

import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button1);

        button1.setOnClickListener(view ->
                Toast.makeText(getApplicationContext(), "버튼 누름", Toast.LENGTH_SHORT).show());
    }
}