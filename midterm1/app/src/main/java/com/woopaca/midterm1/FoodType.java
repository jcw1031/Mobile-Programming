package com.woopaca.midterm1;

public enum FoodType {

    ITALIAN(20_000, "양식"),
    KOREAN(15_000, "한식"),
    JAPANESE(18_000, "일식");

    private final int price;
    private final String name;

    FoodType(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
