package com.woopaca.midterm3;

import java.math.BigDecimal;
import java.math.MathContext;

public enum Operator {

    ADDITION("+", 1),
    SUBTRACTION("-", 1),
    MULTIPLICATION("*", 2),
    DIVISION("/", 2),
    MODULO("%", 2),
    EXPONENTIATION("^", 3);

    private final String symbol;
    private final int priorityOrder;

    Operator(String symbol, int priorityOrder) {
        this.symbol = symbol;
        this.priorityOrder = priorityOrder;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isHigherPriorityThen(Operator recentOperator) {
        return recentOperator.priorityOrder - this.priorityOrder < 0;
    }

    public BigDecimal calculate(BigDecimal frontOperand, BigDecimal backOperand) {
        switch (this) {
            case ADDITION: {
                return frontOperand.add(backOperand, MathContext.DECIMAL64);
            }
            case SUBTRACTION: {
                return frontOperand.subtract(backOperand, MathContext.DECIMAL64);
            }
            case MULTIPLICATION: {
                return frontOperand.multiply(backOperand, MathContext.DECIMAL64);
            }
            case DIVISION: {
                return frontOperand.divide(backOperand, MathContext.DECIMAL64);
            }
            case MODULO: {
                return frontOperand.remainder(backOperand, MathContext.DECIMAL64);
            }
            case EXPONENTIATION: {
                return frontOperand.pow(backOperand.intValue(), MathContext.DECIMAL64);
            }
        }

        return null;
    }
}
