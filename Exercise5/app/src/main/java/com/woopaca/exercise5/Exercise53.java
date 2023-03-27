package com.woopaca.exercise5;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Exercise53 extends AppCompatActivity {

    private LinearLayout.LayoutParams params;
    private LinearLayout baseLayout;
    private EditText editText;
    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        button.setOnClickListener(view -> {
            String text = editText.getText().toString();
            textView.setText(text);
        });
    }

    private void initView() {
        params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        );
        baseLayout = new LinearLayout(this);
        baseLayout.setOrientation(LinearLayout.VERTICAL);

        editText = new EditText(this);
        button = new Button(this);
        button.setText("버튼임");
        textView = new TextView(this);
        setComponent(editText, button, textView);

        setContentView(baseLayout, params);
    }

    private void setComponent(View... views) {
        for (View view : views) {
            baseLayout.addView(view);
        }
    }
}