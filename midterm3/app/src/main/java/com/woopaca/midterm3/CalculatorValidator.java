package com.woopaca.midterm3;

import android.content.Context;
import com.woopaca.midterm3.exception.InvalidCalculateException;
import com.woopaca.midterm3.exception.InvalidInputException;

import java.math.BigDecimal;

public class CalculatorValidator {

    private final Context context;

    public CalculatorValidator(Context context) {
        this.context = context;
    }

    public void validateInputBit(String bit, StringBuilder input) {
        String inputString = input.toString();
        if (bit.equals(".")) {
            validatePeriod(inputString);
            return;
        }

        if (!inputString.contains(".")) {
            checkHigherDecimalPoint(inputString);
        }
        if (inputString.contains(".")) {
            checkLowerDecimalPoint(inputString);
        }
    }

    private static void checkHigherDecimalPoint(String inputString) {
        if (inputString.length() >= 7) {
            throw new InvalidInputException("소수점 앞자리는 최대 7자리 까지만 입력이 가능합니다.");
        }
    }

    private static void checkLowerDecimalPoint(String inputString) {
        if (inputString.endsWith(".")) {
            return;
        }

        String lowerDecimalPoint = inputString.split("\\.")[1];
        if (lowerDecimalPoint.length() >= 8) {
            throw new InvalidInputException("소수점 뒷자리는 최대 8자리 까지만 입력이 가능합니다.");
        }
    }

    private void validatePeriod(String inputString) {
        if (inputString.contains(".")) {
            throw new InvalidInputException("소수점은 하나만 입력 가능합니다.");
        }
        if (inputString.length() == 0) {
            throw new InvalidInputException("소수점을 입력할 수 없습니다");
        }
    }

    public void validateCalculate(Operator recentOperator, BigDecimal backOperand) {
        if (recentOperator == Operator.DIVISION && backOperand.equals(BigDecimal.ZERO)) {
            throw new InvalidCalculateException(context, "0으로 나눌 수 없습니다!");
        }
        if (recentOperator == Operator.EXPONENTIATION && invalidExponentiationNumber(backOperand)) {
            throw new InvalidCalculateException(context, "제곱승은 최대 1자리까지만 가능합니다!");
        }
    }

    private boolean invalidExponentiationNumber(BigDecimal backOperand) {
        if (!backOperand.remainder(BigDecimal.ONE).equals(BigDecimal.ZERO)) {
            return true;
        }
        if (backOperand.compareTo(BigDecimal.TEN) >= 0) {
            return true;
        }
        return false;
    }
}
