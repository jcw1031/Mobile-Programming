package com.woopaca.midterm1;

public enum Option {

    APPETIZER("전채"),
    VEGETARIAN("채식"),
    DESSERT("디저트");

    private final String name;

    Option(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
