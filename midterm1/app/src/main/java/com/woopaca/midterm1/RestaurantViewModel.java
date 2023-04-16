package com.woopaca.midterm1;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class RestaurantViewModel extends ViewModel {

    public static final int OPTION_ADDITIONAL_EXPENSES = 10_000;
    public static final int INTERVER = 3;
    public static final String PEOPLE_UNIT = "명";
    public static final String MONEY_UNIT = "원";

    private final MutableLiveData<FoodType> foodType = new MutableLiveData<>(null);
    private final MutableLiveData<ArrayList<Option>> options = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<SeatType> seatType = new MutableLiveData<>(null);
    private final MutableLiveData<String> numberOfPeople = new MutableLiveData<>("1");
    private final MutableLiveData<String> totalPrice = new MutableLiveData<>("0");
    private final MutableLiveData<Boolean> isComplete = new MutableLiveData<>(false);

    public MutableLiveData<FoodType> getFoodType() {
        return foodType;
    }

    public String getFoodTypeName() {
        return Objects.requireNonNull(foodType.getValue()).getName();
    }

    public String getOptionsName() {
        StringBuilder optionsName = new StringBuilder();
        ArrayList<Option> options = this.options.getValue();
        assert options != null;

        for (Option option : options) {
            if (isNotEmpty(optionsName)) {
                optionsName.append(", ");
            }
            optionsName.append(option.getName());
        }

        return optionsName.toString();
    }

    private static boolean isNotEmpty(StringBuilder optionsName) {
        return optionsName.length() != 0;
    }

    public String getSeatTypeName() {
        SeatType seatType = this.seatType.getValue();
        assert seatType != null;
        return seatType.getName();
    }

    public MutableLiveData<String> getNumberOfPeople() {
        return numberOfPeople;
    }

    public String getNumberOfPeopleWithUnit() {
        String numberOfPeople = this.numberOfPeople.getValue();
        return numberOfPeople + PEOPLE_UNIT;
    }

    public MutableLiveData<String> getTotalPrice() {
        return totalPrice;
    }

    public String getTotalPriceWithUnit() {
        String totalPrice = this.totalPrice.getValue();
        totalPrice = reformatNumber(totalPrice);
        return totalPrice + MONEY_UNIT;
    }

    private String reformatNumber(String totalPrice) {
        int length = totalPrice.length();
        StringBuilder stringBuilder = new StringBuilder(totalPrice);
        for (int i = length - INTERVER; i > 0; i -= INTERVER) {
            stringBuilder.insert(i, ",");
        }
        return stringBuilder.toString();
    }

    public MutableLiveData<Boolean> getIsComplete() {
        return isComplete;
    }

    public void changeOption(Option option) {
        ArrayList<Option> options = this.options.getValue();
        assert options != null;
        if (options.contains(option)) {
            options.remove(option);
        } else {
            options.add(option);
        }

        ArrayList<Option> newOptions = (ArrayList<Option>) options.clone();
        this.options.setValue(newOptions);

        calculateTotalPrice();
    }

    public void selectFoodType(FoodType foodType) {
        this.foodType.setValue(foodType);

        calculateTotalPrice();
    }

    public void selectSeatType(SeatType seatType) {
        this.seatType.setValue(seatType);

        calculateTotalPrice();
    }

    public void increaseNumberOfPeople() {
        int oldValue = Integer.parseInt(Objects.requireNonNull(numberOfPeople.getValue()));
        int newValue = oldValue + 1;
        numberOfPeople.setValue(String.valueOf(newValue));

        calculateTotalPrice();
    }

    public void decreaseNumberOfPeople() {
        int oldValue = Integer.parseInt(Objects.requireNonNull(numberOfPeople.getValue()));
        if (oldValue == 1) {
            return;
        }
        int newValue = oldValue - 1;
        numberOfPeople.setValue(String.valueOf(newValue));

        calculateTotalPrice();
    }

    public void calculateTotalPrice() {
        if (foodType.getValue() == null) {
            return;
        }
        int foodTypePrice = Objects.requireNonNull(foodType.getValue()).getPrice();
        int optionPrice = OPTION_ADDITIONAL_EXPENSES * getSelectedOptionsNumber();

        int seatTypePrice = 0;
        if (seatType.getValue() != null) {
            seatTypePrice = Objects.requireNonNull(seatType.getValue()).getAdditionalExpenses();
        }

        int totalPrice = foodTypePrice + optionPrice + seatTypePrice;
        int peopleNumber = Integer.parseInt(Objects.requireNonNull(numberOfPeople.getValue()));
        this.totalPrice.setValue(String.valueOf(totalPrice * peopleNumber));
    }

    private int getSelectedOptionsNumber() {
        return Objects.requireNonNull(options.getValue()).size();
    }

    public void completeSelection() {
        if (isInvalidSelection()) {
            return;
        }
        isComplete.setValue(true);
    }

    private boolean isInvalidSelection() {
        if (foodType.getValue() == null) {
            return true;
        }
        if (seatType.getValue() == null) {
            return true;
        }
        return false;
    }
}
