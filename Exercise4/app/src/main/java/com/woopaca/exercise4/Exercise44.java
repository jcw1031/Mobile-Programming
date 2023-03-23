package com.woopaca.exercise4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Exercise44 extends AppCompatActivity {

    private CheckBox selectAgreeCheckBox;
    private TextView questionTextView;
    private RadioGroup radioGroup;
    private RadioButton catRadioButton;
    private RadioButton dogRadioButton;
    private RadioButton rabbitRadioButton;
    private Button completeButton;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise44);

        init();

        selectAgreeCheckBox.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                questionTextView.setVisibility(View.VISIBLE);
                radioGroup.setVisibility(View.VISIBLE);
                completeButton.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.VISIBLE);
            } else {
                questionTextView.setVisibility(View.INVISIBLE);
                radioGroup.setVisibility(View.INVISIBLE);
                completeButton.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.INVISIBLE);
            }
        });

        completeButton.setOnClickListener(view -> {
            int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
            switch (checkedRadioButtonId) {
                case R.id.cat_rbtn: {
                    imageView.setImageResource(R.drawable.cat);
                    break;
                }
                case R.id.dog_rbtn: {
                    imageView.setImageResource(R.drawable.dog);
                    break;
                }
                case R.id.rabbit_rbtn: {
                    imageView.setImageResource(R.drawable.rabbit);
                    break;
                }
            }
        });
    }

    private void init() {
        selectAgreeCheckBox = findViewById(R.id.select_chbox);
        questionTextView = findViewById(R.id.question_tv);
        radioGroup = findViewById(R.id.rbtn_group);
        catRadioButton = findViewById(R.id.cat_rbtn);
        dogRadioButton = findViewById(R.id.dog_rbtn);
        rabbitRadioButton = findViewById(R.id.rabbit_rbtn);
        completeButton = findViewById(R.id.complete_btn);
        imageView = findViewById(R.id.img_view);
    }
}
