package com.example.exercise13;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.example.exercise13.databinding.ActivityMainBinding;

import static com.example.exercise13.FeedEntry.COLUMN_NAME_ID;
import static com.example.exercise13.FeedEntry.COLUMN_NAME_NUMBER;
import static com.example.exercise13.FeedEntry.TABLE_NAME;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        dbHelper = new DBHelper(MainActivity.this);
        db = dbHelper.getWritableDatabase();
        dbHelper.onCreate(db);

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_ID, "GroupA");
        values.put(COLUMN_NAME_NUMBER, 10000);
        db.insert(TABLE_NAME, null, values);
    }
}