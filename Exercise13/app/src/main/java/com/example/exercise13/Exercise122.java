package com.example.exercise13;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.example.exercise13.databinding.ActivityMainBinding;
import org.jetbrains.annotations.NotNull;

import static com.example.exercise13.FeedEntry.COLUMN_NAME_ID;
import static com.example.exercise13.FeedEntry.COLUMN_NAME_NUMBER;
import static com.example.exercise13.FeedEntry.DATABASE_VERSION;
import static com.example.exercise13.FeedEntry.TABLE_NAME;

public class Exercise122 extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private int databaseVersion = DATABASE_VERSION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        dbHelper = new DBHelper(Exercise122.this);

        binding.initButton.setOnClickListener(view -> {
            db = dbHelper.getWritableDatabase();
            dbHelper.onUpgrade(db, databaseVersion, ++databaseVersion);
            db.close();
        });

        binding.insertButton.setOnClickListener(view -> {
            db = dbHelper.getWritableDatabase();

            String insertName = binding.nameEditText.getText().toString();
            int insertNumber = Integer.parseInt(binding.numberEditText.getText().toString());
            ContentValues values = createValue(insertName, insertNumber);

            db.insert(TABLE_NAME, null, values);
            db.close();
            Toast.makeText(Exercise122.this, "입력 완료", Toast.LENGTH_SHORT).show();
        });

        binding.inquiryButton.setOnClickListener(view -> {
            StringBuilder resultName = new StringBuilder();
            StringBuilder resultNumber = new StringBuilder();

            db = dbHelper.getReadableDatabase();

            Cursor cursor = getSelectAll();
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME_ID));
                int number = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_NAME_NUMBER));

                resultName.append(name).append("\n");
                resultNumber.append(number).append("\n");
            }

            binding.nameResultEditText.setText(resultName);
            binding.numberResultEditText.setText(resultNumber);

            cursor.close();
            db.close();
        });

        binding.updateButton.setOnClickListener(view -> {
            db = dbHelper.getWritableDatabase();

            String selection = COLUMN_NAME_ID + " = ?";
            String[] selectionArguments = {binding.nameEditText.getText().toString()};

            String insertName = binding.nameEditText.getText().toString();
            int insertNumber = Integer.parseInt(binding.numberEditText.getText().toString());
            ContentValues values = createValue(insertName, insertNumber);

            db.update(TABLE_NAME, values, selection, selectionArguments);
            db.close();
            Toast.makeText(Exercise122.this, "수정 완료", Toast.LENGTH_SHORT).show();
        });

        binding.deleteButton.setOnClickListener(view -> {
            db = dbHelper.getWritableDatabase();

            String selection = COLUMN_NAME_ID + " = ?";
            String[] selectionArguments = {binding.nameEditText.getText().toString()};

            db.delete(TABLE_NAME, selection, selectionArguments);
            db.close();
            Toast.makeText(Exercise122.this, "삭제 완료", Toast.LENGTH_SHORT).show();
        });
    }

    private Cursor getSelectAll() {
        return db.query(TABLE_NAME, null, null, null,
                null, null, null);
    }

    @NotNull
    private static ContentValues createValue(String name, int number) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_ID, name);
        values.put(COLUMN_NAME_NUMBER, number);
        return values;
    }
}