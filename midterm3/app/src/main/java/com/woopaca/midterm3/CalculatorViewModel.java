package com.woopaca.midterm3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.woopaca.midterm3.exception.InvalidCalculateException;
import com.woopaca.midterm3.exception.InvalidInputException;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Stack;

public class CalculatorViewModel extends ViewModel {

    @SuppressLint("StaticFieldLeak")
    private Context context;
    private CalculatorValidator validator;

    private final MutableLiveData<StringBuilder> input = new MutableLiveData<>(new StringBuilder());
    private final MutableLiveData<String> inputString = new MutableLiveData<>("");
    private final Stack<BigDecimal> operandStack = new Stack<>();
    private final Stack<Operator> operatorStack = new Stack<>();
    private final MutableLiveData<String> displayFormula = new MutableLiveData<>("");
    private final MutableLiveData<Boolean> isAdvanced = new MutableLiveData<>(false);
    private final NumberFormat numberFormat = NumberFormat.getInstance();

    public MutableLiveData<String> getInputString() {
        return inputString;
    }

    public MutableLiveData<String> getDisplayFormula() {
        return displayFormula;
    }

    public MutableLiveData<Boolean> getIsAdvanced() {
        return isAdvanced;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setValidator(CalculatorValidator validator) {
        this.validator = validator;
    }

    /**
     * 피연산자 입력
     *
     * @param bit 한 자리 입력
     */
    public void inputOperandBit(char bit) {
        StringBuilder input = this.input.getValue();
        try {
            validator.validateInputBit(String.valueOf(bit), input);
        } catch (InvalidInputException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }

        if (input.length() == 1 && input.charAt(0) == '0') {
            handleExistingZero(bit, input);
            inputString.setValue(input.toString());
            return;
        }


        input.append(bit);
        inputString.setValue(input.toString());
    }

    private void handleExistingZero(char bit, StringBuilder input) {
        if (bit == '0') {
            return;
        }
        if (bit != '.') {
            input.replace(0, 1, String.valueOf(bit));
            inputString.setValue(input.toString());
            return;
        }
        input.append(".");
    }

    /**
     * 연산자 입력
     *
     * @param operator 연산자
     */
    public void inputOperator(Operator operator) {
        StringBuilder input = this.input.getValue();
        this.input.setValue(new StringBuilder());
        if (input.length() == 0) {
            Toast.makeText(context, "수를 입력하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        BigDecimal operand = new BigDecimal(input.toString());
        addOperand(operand);

        try {
            addOperator(operator);
        } catch (InvalidCalculateException e) {
            e.showErrorToast();
            clearCalculator();
            return;
        }

        updateDisplayFormula(operator, operand);
    }

    private void updateDisplayFormula(Operator operator, BigDecimal operand) {
        String displayString = displayFormula.getValue();
        String operandString = formatInteger(operand);
        if (displayString.startsWith("결과")) {
            displayString = "";
        }
        displayString += operandString + " " + operator.getSymbol() + " ";
        displayFormula.setValue(displayString);
    }

    public void addOperand(BigDecimal number) {
        operandStack.add(number);
    }

    public void addOperator(Operator operator) {
        if (operatorStack.isEmpty()) {
            operatorStack.add(operator);
            return;
        }

        Operator recentOperator = operatorStack.peek();
        if (operator.isHigherPriorityThen(recentOperator)) {
            operatorStack.add(operator);
            return;
        }

        calculate();
        operatorStack.add(operator);
    }

    /**
     * 입력한 피연산자 한 자리 삭제
     */
    public void deleteOperandBit() {
        StringBuilder input = this.input.getValue();
        if (input.length() == 0) {
            return;
        }

        input.deleteCharAt(input.length() - 1);
        inputString.setValue(input.toString());
    }

    /**
     * 최종 계산
     */
    public void calculateAll() {
        StringBuilder input = this.input.getValue();
        if (input.length() == 0) {
            return;
        }
        addOperand(new BigDecimal(input.toString()));
        while (!operatorStack.isEmpty()) {
            try {
                calculate();
            } catch (InvalidCalculateException e) {
                e.showErrorToast();
                clearCalculator();
                return;
            }
        }

        BigDecimal operand = operandStack.pop();
        String result = formatInteger(operand);

        clearCalculator();
        inputString.setValue(result);
        displayFormula.setValue("결과: " + result);
    }

    private String formatInteger(BigDecimal operand) {
        String operandString = String.valueOf(operand);
        if (operand.remainder(new BigDecimal(1)).equals(new BigDecimal(0))) {
            return operandString.split("\\.")[0];
        }
        if (operandString.contains(".") && !operandString.endsWith(".")) {
            return String.valueOf(formatFloat(operand, operandString));
        }
        return operandString;
    }

    @SuppressLint("DefaultLocale")
    private BigDecimal formatFloat(BigDecimal operand, String operandString) {
        String lowerDecimalPoint = operandString.split("\\.")[1];
        if (lowerDecimalPoint.length() >= 12) {
            String formatResult = String.format("%.12f", operand);
            BigDecimal formatBigDecimal = new BigDecimal(formatResult);
            return formatBigDecimal.stripTrailingZeros();
        }
        return operand;
    }

    /**
     * 하나의 연산자 계산
     */
    private void calculate() {
        Operator recentOperator = operatorStack.pop();
        BigDecimal backOperand = operandStack.pop();
        BigDecimal frontOperand = operandStack.pop();

        validator.validateCalculate(recentOperator, backOperand);

        BigDecimal result = recentOperator.calculate(frontOperand, backOperand);
        operandStack.add(result);
    }

    public void advance() {
        if (isAdvanced.getValue().equals(Boolean.TRUE)) {
            isAdvanced.setValue(false);
            return;
        }
        isAdvanced.setValue(true);
    }

    private void clearCalculator() {
        operatorStack.clear();
        operandStack.clear();
        input.setValue(new StringBuilder());
        inputString.setValue("");
        displayFormula.setValue("");
    }
}
