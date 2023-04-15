package com.woopaca.midterm2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.lifecycle.ViewModel;
import com.woopaca.midterm2.exception.BlankNameException;
import com.woopaca.midterm2.exception.GenderNotSelectedException;
import com.woopaca.midterm2.exception.InvalidSelectionException;

public class SignUpViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    private Context context;
    private SignUpView signUpView;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setSignUpView(SignUpView signUpView) {
        this.signUpView = signUpView;
    }

    public void submit() {
        try {
            validateSelection();
        } catch (InvalidSelectionException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(context, getWelcomeMessage(), Toast.LENGTH_LONG).show();
    }

    private void validateSelection() {
        String firstName = signUpView.getFirstNameEditText().getText().toString();
        if (firstName.isBlank()) {
            throw new BlankNameException("First Name 항목은 비어있을 수 없습니다. (공백 불가)");
        }

        String lastName = signUpView.getLastNameEditText().getText().toString();
        if (lastName.isBlank()) {
            throw new BlankNameException("Last Name 항목은 비어있을 수 없습니다. (공백 불가)");
        }

        RadioButton maleRadioButton = signUpView.getMaleRadioButton();
        RadioButton femaleRadioButton = signUpView.getFemaleRadioButton();
        if (!maleRadioButton.isChecked() && !femaleRadioButton.isChecked()) {
            throw new GenderNotSelectedException("Gender 항목이 선택되지 않았습니다.");
        }
    }

    private String getWelcomeMessage() {
        String firstName = signUpView.getFirstNameEditText().getText().toString();
        String lastName = signUpView.getLastNameEditText().getText().toString();
        Gender gender = signUpView.getMaleRadioButton().isChecked() ? Gender.MALE : Gender.FEMALE;
        int year = signUpView.getBirthdayDatePicker().getYear();
        int month = signUpView.getBirthdayDatePicker().getMonth() + 1;
        int day = signUpView.getBirthdayDatePicker().getDayOfMonth();

        return year + "-" + month + "-" + day + " " +
                lastName + firstName + "(" + gender.getGenderNameKo() + ")님 가입을 환영합니다.";
    }
}
