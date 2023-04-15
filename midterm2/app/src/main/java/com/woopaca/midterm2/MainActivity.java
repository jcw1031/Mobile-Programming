package com.woopaca.midterm2;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import static android.widget.LinearLayout.LayoutParams.MATCH_PARENT;
import static android.widget.LinearLayout.VERTICAL;

public class MainActivity extends AppCompatActivity {

    private static final int LAYOUT_PADDING = 20;

    private final SignUpView signUpView = new SignUpView(MainActivity.this);

    private LinearLayout baseLayout;
    private SignUpViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(MainActivity.this).get(SignUpViewModel.class);
        viewModel.setContext(MainActivity.this);
        viewModel.setSignUpView(signUpView);

        initBaseLayout();

        setContentView(baseLayout, new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));

        signUpView.signUpFormConfigure(baseLayout);
        signUpView.getSubmitButton().setOnClickListener(view -> {
            viewModel.submit();
        });
    }

    private void initBaseLayout() {
        baseLayout = new LinearLayout(MainActivity.this);
        baseLayout.setOrientation(VERTICAL);
        baseLayout.setPadding(LAYOUT_PADDING, LAYOUT_PADDING, LAYOUT_PADDING, LAYOUT_PADDING);
        baseLayout.setGravity(Gravity.CENTER_HORIZONTAL);
    }
}