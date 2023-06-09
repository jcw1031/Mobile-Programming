package com.woopaca.exercise9;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.*;
import androidx.appcompat.app.AppCompatActivity;

public class Exercise92 extends AppCompatActivity {

    final static int LINE = 1, CIRCLE = 2, RECTANGLE = 3;
    static int curShape = LINE;
    static int curColor = Color.DKGRAY;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CustomGraphicView(this));
        setTitle("간단 그림판 (개선)");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "선 그리기");
        menu.add(0, 2, 0, "원 그리기");
        menu.add(0, 3, 0, "사각형 그리기");
        SubMenu sMenu = menu.addSubMenu("색상 변경 >>");
        sMenu.add(0, 4, 0, "빨강");
        sMenu.add(0, 5, 0, "초록");
        sMenu.add(0, 6, 0, "파랑");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1: {
                curShape = LINE; // 선
                return true;
            }
            case 2: {
                curShape = CIRCLE; // 원
                return true;
            }
            case 3: {
                curShape = RECTANGLE; // 사각형
                return true;
            }
            case 4: {
                curColor = Color.RED;
                return true;
            }
            case 5: {
                curColor = Color.GREEN;
                return true;
            }
            case 6: {
                curColor = Color.BLUE;
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private static class CustomGraphicView extends View {

        int startX = -1;
        int startY = -1;
        int stopX = -1;
        int stopY = -1;

        public CustomGraphicView(Context context) {
            super(context);
        }

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    startX = (int) event.getX();
                    startY = (int) event.getY();
                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                }
                case MotionEvent.ACTION_UP: {
                    stopX = (int) event.getX();
                    stopY = (int) event.getY();
                    this.invalidate();
                    break;
                }
            }
            return true;
        }

        @SuppressLint("DrawAllocation")
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(curColor);

            switch (curShape) {
                case LINE: {
                    canvas.drawLine(startX, startY, stopX, stopY, paint);
                    break;
                }
                case CIRCLE: {
                    int radius = (int) Math.sqrt(Math.pow(stopX - startX, 2)
                            + Math.pow(stopY - startY, 2));
                    canvas.drawCircle(startX, startY, radius, paint);
                    break;
                }
                case RECTANGLE: {
                    Rect rect = new Rect(startX, startY, stopX, stopY);
                    canvas.drawRect(rect, paint);
                    break;
                }
            }
        }
    }
}