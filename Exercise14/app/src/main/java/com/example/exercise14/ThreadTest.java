package com.example.exercise14;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ThreadTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_test);

        Thread threadA = new Thread(() -> {
            System.out.println("Thread A");
        });
        Thread threadB = new Thread(() -> {
            System.out.println("Thread B");
        });

        threadA.start();
        threadB.start();
    }
}