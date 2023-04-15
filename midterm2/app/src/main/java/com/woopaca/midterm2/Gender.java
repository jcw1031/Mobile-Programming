package com.woopaca.midterm2;

public enum Gender {

    MALE("남", "Male"),
    FEMALE("여", "Female");

    private final String genderNameKo;
    private final String genderNameEn;

    Gender(String genderNameKo, String genderNameEn) {
        this.genderNameKo = genderNameKo;
        this.genderNameEn = genderNameEn;
    }

    public String getGenderNameKo() {
        return genderNameKo;
    }

    public String getGenderNameEn() {
        return genderNameEn;
    }
}
