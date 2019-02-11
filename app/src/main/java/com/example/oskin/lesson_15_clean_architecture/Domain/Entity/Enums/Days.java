package com.example.oskin.lesson_15_clean_architecture.Domain.Entity.Enums;

public enum Days {
    One(1),
    Two(2),
    Three(3),
    Four(4),
    Five(5),
    Six(6),
    Seven(7);
    /*Eight(8),
    Nine(9),
    Ten(10);*/

    private final int value;

    Days(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
