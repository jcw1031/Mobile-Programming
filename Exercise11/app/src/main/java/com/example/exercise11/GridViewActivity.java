package com.example.exercise11;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class GridViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        setTitle("GridView Movie Poster");

        GridView gridView = findViewById(R.id.grid_view);
        MyGridAdapter myGridAdapter = new MyGridAdapter(this);
        gridView.setAdapter(myGridAdapter);
    }

    class MyGridAdapter extends BaseAdapter {

        private final Integer[] posterId = {
                R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04, R.drawable.mov05,
                R.drawable.mov06, R.drawable.mov07, R.drawable.mov08, R.drawable.mov09, R.drawable.mov10,
                R.drawable.mov11, R.drawable.mov12, R.drawable.mov13, R.drawable.mov14, R.drawable.mov15,
                R.drawable.mov16, R.drawable.mov17, R.drawable.mov18, R.drawable.mov19, R.drawable.mov20,
                R.drawable.mov21, R.drawable.mov22, R.drawable.mov23, R.drawable.mov24, R.drawable.mov25
        };
        private Context context;

        public MyGridAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return posterId.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(200, 300));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5, 5, 5, 5);
            imageView.setImageResource(posterId[position]);

            imageView.setOnClickListener(v -> {
                @SuppressLint("ViewHolder")
                View dialogView = View.inflate(GridViewActivity.this, R.layout.dialog, null);
                ImageView posterImage = dialogView.findViewById(R.id.popup_poster_image);
                posterImage.setImageResource(posterId[position]);
                new AlertDialog.Builder(GridViewActivity.this)
                        .setTitle("큰 포스터")
                        .setIcon(R.drawable.ic_launcher_foreground)
                        .setView(dialogView)
                        .setNegativeButton("닫기", null)
                        .show();
            });

            return imageView;
        }
    }
}