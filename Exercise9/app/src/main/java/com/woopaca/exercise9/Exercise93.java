package com.woopaca.exercise9;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.*;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class Exercise93 extends AppCompatActivity {

    private CustomGraphicView customGraphicView;
    private static float scaleX = 1;
    private static float scaleY = 1;
    private static float angle = 0;
    private static float saturation = 1;
    private static boolean blur = false;
    private static boolean emboss = false;

    private ImageButton zoomInImageButton;
    private ImageButton zoomOutImageButton;
    private ImageButton rotateImageButton;
    private ImageButton brightImageButton;
    private ImageButton darkImageButton;
    private ImageButton blurImageButton;
    private ImageButton embossingImageButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_93);
        customGraphicView = new CustomGraphicView(this);
        LinearLayout imageLayout = findViewById(R.id.image_layout);
        imageLayout.addView(customGraphicView);

        initView();
        setClickEventListener();
    }

    private void initView() {
        zoomInImageButton = findViewById(R.id.zoom_in_btn);
        zoomOutImageButton = findViewById(R.id.zoom_out_btn);
        rotateImageButton = findViewById(R.id.rotate_btn);
        brightImageButton = findViewById(R.id.bright_btn);
        darkImageButton = findViewById(R.id.dark_btn);
        blurImageButton = findViewById(R.id.blur_btn);
        embossingImageButton = findViewById(R.id.embossing_btn);
    }

    private void setClickEventListener() {
        zoomInImageButton.setOnClickListener(view -> {
            scaleX = scaleX + 0.2f;
            scaleY = scaleY + 0.2f;
            customGraphicView.invalidate();
        });

        zoomOutImageButton.setOnClickListener(view -> {
            scaleX = scaleX - 0.2f;
            scaleY = scaleY - 0.2f;
            customGraphicView.invalidate();
        });

        rotateImageButton.setOnClickListener(view -> {
            angle = angle + 20;
            customGraphicView.invalidate();
        });

        brightImageButton.setOnClickListener(view -> {
            saturation = saturation + 0.2f;
            customGraphicView.invalidate();
        });

        darkImageButton.setOnClickListener(view -> {
            saturation = saturation - 0.2f;
            customGraphicView.invalidate();
        });

        blurImageButton.setOnClickListener(view -> {
            blur = !blur;
            customGraphicView.invalidate();
        });

        embossingImageButton.setOnClickListener(view -> {
            emboss = !emboss;
            customGraphicView.invalidate();
        });
    }

    private static class CustomGraphicView extends View {

        public CustomGraphicView(Context context) {
            super(context);
        }

        @SuppressLint("DrawAllocation")
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;
            canvas.scale(scaleX, scaleY, cenX, cenY);
            canvas.rotate(angle, cenX, cenY);

            Paint paint = new Paint();
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.setSaturation(saturation);

            paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));

            if (blur) {
                BlurMaskFilter bMask;
                bMask = new BlurMaskFilter(30, BlurMaskFilter.Blur.NORMAL);
                paint.setMaskFilter(bMask);
            }

            if (emboss) {
                EmbossMaskFilter embossMaskFilter;
                embossMaskFilter = new EmbossMaskFilter(new float[]{3, 3, 3}, 0.5f, 5, 10);
                paint.setMaskFilter(embossMaskFilter);
            }

            Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
            int imageX = (this.getWidth() - image.getWidth()) / 2;
            int imageY = (this.getHeight() - image.getHeight()) / 2;
            canvas.drawBitmap(image, imageX, imageY, paint);

            image.recycle();
        }
    }
}
