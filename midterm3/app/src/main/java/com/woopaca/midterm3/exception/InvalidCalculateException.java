package com.woopaca.midterm3.exception;

import android.content.Context;
import android.widget.Toast;

public class InvalidCalculateException extends RuntimeException {

    public static final String MESSAGE_PREFIX = "수식 입력 오류: ";

    private final Context context;
    private final String message;

    public InvalidCalculateException(Context context, String message) {
        this.context = context;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void showErrorToast() {
        Toast.makeText(context, MESSAGE_PREFIX + "[" + message + "]", Toast.LENGTH_LONG).show();
    }
}
