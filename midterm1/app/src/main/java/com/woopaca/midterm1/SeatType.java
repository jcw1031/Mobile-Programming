package com.woopaca.midterm1;

public enum SeatType {

    WINDOW(5_000, "창가측"),
    ANYWHERE(0, "미지정");

    private final int additionalExpenses;
    private final String name;

    SeatType(int additionalExpenses, String name) {
        this.additionalExpenses = additionalExpenses;
        this.name = name;
    }

    public int getAdditionalExpenses() {
        return additionalExpenses;
    }

    public String getName() {
        return name;
    }
}
