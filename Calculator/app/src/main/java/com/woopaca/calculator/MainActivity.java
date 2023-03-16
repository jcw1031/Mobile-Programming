package com.woopaca.calculator;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private EditText edit1;
    private EditText edit2;
    private Button btnAdd;
    private Button btnSub;
    private Button btnMul;
    private Button btnDiv;
    private TextView textResult;

    private String num1;
    private String num2;
    private Integer result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        btnAdd.setOnClickListener(view -> {
            num1 = String.valueOf(edit1.getText());
            num2 = String.valueOf(edit2.getText());

            result = Integer.parseInt(num1) + Integer.parseInt(num2);
            textResult.setText(String.valueOf(result));
        });

        btnSub.setOnClickListener(view -> {
            num1 = String.valueOf(edit1.getText());
            num2 = String.valueOf(edit2.getText());

            result = Integer.parseInt(num1) - Integer.parseInt(num2);
            textResult.setText(String.valueOf(result));
        });

        btnMul.setOnClickListener(view -> {
            num1 = String.valueOf(edit1.getText());
            num2 = String.valueOf(edit2.getText());

            result = Integer.parseInt(num1) * Integer.parseInt(num2);
            textResult.setText(String.valueOf(result));
        });

        btnDiv.setOnClickListener(view -> {
            num1 = String.valueOf(edit1.getText());
            num2 = String.valueOf(edit2.getText());

            result = Integer.parseInt(num1) / Integer.parseInt(num2);
            textResult.setText(String.valueOf(result));
        });
    }

    private void init() {
        edit1 = findViewById(R.id.edit_1);
        edit2 = findViewById(R.id.edit_2);

        btnAdd = findViewById(R.id.btn_add);
        btnSub = findViewById(R.id.btn_sub);
        btnMul = findViewById(R.id.btn_mul);
        btnDiv = findViewById(R.id.btn_div);

        textResult = findViewById(R.id.tv_result);
    }
}