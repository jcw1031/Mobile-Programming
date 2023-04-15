package com.woopaca.midterm2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.InputFilter;
import android.view.ViewGroup;
import android.widget.*;
import org.jetbrains.annotations.NotNull;

import static android.view.Gravity.CENTER_VERTICAL;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.widget.LinearLayout.HORIZONTAL;

@SuppressLint("SetTextI18n")
public class SignUpView {

    private static final String LAST_NAME = "Last Name";
    private static final String FIRST_NAME = "First Name";
    private static final int MAX_NAME_LENGTH = 10;

    private final Context context;

    private ViewGroup baseLayout;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private DatePicker birthdayDatePicker;
    private Button submitButton;

    public SignUpView(Context context) {
        this.context = context;
    }

    public EditText getFirstNameEditText() {
        return firstNameEditText;
    }

    public EditText getLastNameEditText() {
        return lastNameEditText;
    }

    public RadioButton getMaleRadioButton() {
        return maleRadioButton;
    }

    public RadioButton getFemaleRadioButton() {
        return femaleRadioButton;
    }

    public DatePicker getBirthdayDatePicker() {
        return birthdayDatePicker;
    }

    public Button getSubmitButton() {
        return submitButton;
    }

    public void signUpFormConfigure(LinearLayout baseLayout) {
        this.baseLayout = baseLayout;

        firstNameLayoutConfigure();
        lastNameLayoutConfigure();
        genderLayoutConfigure();
        birthdayLayoutConfigure();
        submitButtonConfigure();
    }

    private void firstNameLayoutConfigure() {
        LinearLayout firstNameLayout = createNameLayout(FIRST_NAME);
        baseLayout.addView(firstNameLayout, createLayoutParams(MATCH_PARENT, WRAP_CONTENT));
    }

    private void lastNameLayoutConfigure() {
        LinearLayout lastNameLayout = createNameLayout(LAST_NAME);
        baseLayout.addView(lastNameLayout, createLayoutParams(MATCH_PARENT, WRAP_CONTENT));
    }

    private void genderLayoutConfigure() {
        LinearLayout genderLayout = createLinearLayout();

        TextView genderTextView = new TextView(context);
        genderTextView.setText("Gender : ");

        RadioGroup genderRadioGroup = new RadioGroup(context);
        genderRadioGroup.setOrientation(HORIZONTAL);
        radioButtonConfigure(genderRadioGroup, Gender.MALE);
        radioButtonConfigure(genderRadioGroup, Gender.FEMALE);

        genderLayout.addView(genderTextView, createLayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        genderLayout.addView(genderRadioGroup, createLayoutParams(WRAP_CONTENT, WRAP_CONTENT));

        baseLayout.addView(genderLayout, createLayoutParams(MATCH_PARENT, WRAP_CONTENT));
    }

    private void birthdayLayoutConfigure() {
        LinearLayout birthdayLayout = new LinearLayout(context);
        birthdayLayout.setOrientation(HORIZONTAL);

        TextView birthdayTextView = new TextView(context);
        birthdayTextView.setText("Birthday : ");

        birthdayDatePicker = new DatePicker(context);
        birthdayDatePicker.setMaxDate(System.currentTimeMillis());

        birthdayLayout.addView(birthdayTextView, createLayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        birthdayLayout.addView(birthdayDatePicker, createLayoutParams(WRAP_CONTENT, WRAP_CONTENT));

        baseLayout.addView(birthdayLayout, createLayoutParams(MATCH_PARENT, WRAP_CONTENT));
    }

    private void submitButtonConfigure() {
        submitButton = new Button(context);
        submitButton.setText("Submit");

        baseLayout.addView(submitButton, createLayoutParams(WRAP_CONTENT, WRAP_CONTENT));
    }

    private void radioButtonConfigure(RadioGroup radioGroup, Gender gender) {
        switch (gender) {
            case MALE: {
                maleRadioButton = new RadioButton(context);
                maleRadioButton.setText(gender.getGenderNameEn());
                radioGroup.addView(maleRadioButton, createLayoutParams(WRAP_CONTENT, WRAP_CONTENT));
                break;
            }
            case FEMALE: {
                femaleRadioButton = new RadioButton(context);
                femaleRadioButton.setText(gender.getGenderNameEn());
                radioGroup.addView(femaleRadioButton, createLayoutParams(WRAP_CONTENT, WRAP_CONTENT));
            }
        }
    }

    @NotNull
    private LinearLayout createNameLayout(String kindOfName) {
        LinearLayout nameLayout = createLinearLayout();

        TextView nameTextView = new TextView(context);
        nameTextView.setText(kindOfName + " : ");

        EditText nameEditText = new EditText(context);

        nameLayout.addView(nameTextView, createLayoutParams(WRAP_CONTENT, WRAP_CONTENT));
        nameLayout.addView(nameEditText, createLayoutParams(MATCH_PARENT, WRAP_CONTENT));

        setNameEditText(kindOfName, nameEditText);

        return nameLayout;
    }

    @NotNull
    private LinearLayout createLinearLayout() {
        LinearLayout nameLayout = new LinearLayout(context);
        nameLayout.setOrientation(HORIZONTAL);
        nameLayout.setGravity(CENTER_VERTICAL);
        return nameLayout;
    }

    @NotNull
    private LinearLayout.LayoutParams createLayoutParams(int width, int height) {
        return new LinearLayout.LayoutParams(
                width,
                height
        );
    }

    private void setNameEditText(String kindOfName, EditText nameEditText) {
        switch (kindOfName) {
            case FIRST_NAME: {
                firstNameEditText = nameEditText;
                firstNameEditText.setFilters(new InputFilter[]{
                        new InputFilter.LengthFilter(MAX_NAME_LENGTH)
                });
                break;
            }
            case LAST_NAME: {
                lastNameEditText = nameEditText;
                lastNameEditText.setFilters(new InputFilter[]{
                        new InputFilter.LengthFilter(MAX_NAME_LENGTH)
                });
                break;
            }
        }
    }
}
