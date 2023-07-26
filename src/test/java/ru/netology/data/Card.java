package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@AllArgsConstructor
@Data
@Value
public class Card {
    private String number;
    private String month;
    private String year;
    private String owner;
    private String cvv;

}
