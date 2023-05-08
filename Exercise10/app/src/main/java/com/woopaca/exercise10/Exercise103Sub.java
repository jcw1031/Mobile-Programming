package com.woopaca.exercise10;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Exercise103Sub extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise103_sub);
        setTitle("Second 액티비티");

        Intent inIntent = getIntent();

        String calc = (inIntent.getStringExtra("Calc"));

        int calValue = 0;
        if (calc.equals("+")) {
            calValue = inIntent.getIntExtra("Num1", 0)
                    + inIntent.getIntExtra("Num2", 0);
        } else if (calc.equals("-")) {
            calValue = inIntent.getIntExtra("Num1", 0)
                    - inIntent.getIntExtra("Num2", 0);
        } else if (calc.equals("*")) {
            calValue = inIntent.getIntExtra("Num1", 0)
                    * inIntent.getIntExtra("Num2", 0);
        } else {
            calValue = inIntent.getIntExtra("Num1", 0)
                    / inIntent.getIntExtra("Num2", 0);
        }

        final int retValue = calValue;

        Button btnReturn = (Button) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(view -> {
            Intent outIntent = new Intent(getApplicationContext(), Exercise103Main.class);
            outIntent.putExtra("Hap", retValue);
            setResult(RESULT_OK, outIntent);
            finish();
        });
    }

}
