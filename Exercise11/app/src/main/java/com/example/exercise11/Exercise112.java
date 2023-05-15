package com.example.exercise11;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Exercise112 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise112);
        setTitle("직접 풀어보기 11-2");

        Gallery gallery = findViewById(R.id.poster_gallery);
        MyGalleryAdapter galleryAdapter = new MyGalleryAdapter(this);
        gallery.setAdapter(galleryAdapter);
    }

    class MyGalleryAdapter extends BaseAdapter {

        private final Context context;

        private final Integer[] posterID = {
                R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04, R.drawable.mov05,
                R.drawable.mov06, R.drawable.mov07, R.drawable.mov08, R.drawable.mov09, R.drawable.mov10,
                R.drawable.mov11, R.drawable.mov12, R.drawable.mov13, R.drawable.mov14, R.drawable.mov15,
                R.drawable.mov16, R.drawable.mov17, R.drawable.mov18, R.drawable.mov19, R.drawable.mov20,
                R.drawable.mov21, R.drawable.mov22, R.drawable.mov23, R.drawable.mov24, R.drawable.mov25
        };

        private final String[] movieTitle = {
                "써니", "완득이", "괴물", "라디오 스타", "비열한 거리",
                "왕의 남자", "아일랜드", "웰컴투 동막골", "헬보이", "Back to the Future",
                "여인의 향기", "쥬라기 공원", "포레스트 검프", "Groundhog Day", "혹성탈출: 진화의 시작",
                "아름다운 비행", "내 이름은 칸", "해리포터: 죽음의 성물 2", "마더", "킹콩을 들다",
                "쿵푸팬더 2", "짱구는 못말려: 태풍을 부르는 나의 신부", "아저씨", "아바타", "The Godfather"
        };

        public MyGalleryAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return posterID.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new Gallery.LayoutParams(200, 300));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5, 5, 5, 5);
            imageView.setImageResource(posterID[position]);

            imageView.setOnClickListener(view -> {
                ImageView posterImageView = findViewById(R.id.poster_image_view);
                posterImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                posterImageView.setImageResource(posterID[position]);

                View toastView =
                        View.inflate(Exercise112.this, R.layout.exercise_112_toast, null);
                TextView toastText = toastView.findViewById(R.id.movie_toast_text);
                toastText.setText(movieTitle[position]);
                Toast toast = new Toast(Exercise112.this);
                toast.setView(toastView);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.show();
            });

            return imageView;
        }
    }
}