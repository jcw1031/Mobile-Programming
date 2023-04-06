package com.woopaca.exercise7;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout baseLayout;
    private Button colorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseLayout = findViewById(R.id.base_layout);
        colorButton = findViewById(R.id.color_button);

        registerForContextMenu(colorButton);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.color_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        changeColor(item);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);

        MenuInflater menuInflater = getMenuInflater();
        if (view == colorButton) {
            menu.setHeaderTitle("ㅋㅋ");
            menuInflater.inflate(R.menu.color_menu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        changeColor(item);
        return super.onContextItemSelected(item);
    }

    @SuppressLint("NonConstantResourceId")
    private void changeColor(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_red: {
                baseLayout.setBackgroundColor(Color.RED);
                break;
            }
            case R.id.item_blue: {
                baseLayout.setBackgroundColor(Color.BLUE);
                break;
            }
            case R.id.item_green: {
                baseLayout.setBackgroundColor(Color.GREEN);
            }
        }
    }
}