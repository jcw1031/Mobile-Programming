package com.woopaca.exercise10;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Exercise103Main extends Activity {

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise103_main);
        setTitle("메인 액티비티");

        final EditText edtNum1 = findViewById(R.id.edtNum1);
        final EditText edtNum2 = findViewById(R.id.edtNum2);
        final RadioGroup rdoGroup = findViewById(R.id.rdoGroup);

        Button btnNewActivity = findViewById(R.id.btnNewActivity);
        btnNewActivity.setOnClickListener(view -> {

            Intent intent = new Intent(getApplicationContext(), Exercise103Sub.class);

            switch (rdoGroup.getCheckedRadioButtonId()) {
                case R.id.rdoAdd: {
                    intent.putExtra("Calc", "+");
                    break;
                }
                case R.id.rdoSub: {
                    intent.putExtra("Calc", "-");
                    break;
                }
                case R.id.rdoMul: {
                    intent.putExtra("Calc", "*");
                    break;
                }
                case R.id.rdoDiv: {
                    intent.putExtra("Calc", "/");
                    break;
                }
                default: {
                    break;
                }
            }

            intent.putExtra("Num1", Integer.parseInt(edtNum1.getText().toString()));
            intent.putExtra("Num2", Integer.parseInt(edtNum2.getText().toString()));

            startActivityForResult(intent, 0);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            int hap = data.getIntExtra("Hap", 0);
            Toast.makeText(getApplicationContext(), "결과 :" + hap, Toast.LENGTH_SHORT).show();
        }
    }

}
