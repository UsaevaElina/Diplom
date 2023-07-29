package ru.netology.data;

import lombok.Value;


@Value
public class Card {
    private String number;
    private String month;
    private String year;
    private String owner;
    private String cvv;

}
