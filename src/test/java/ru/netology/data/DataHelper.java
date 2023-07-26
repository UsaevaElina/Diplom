package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataHelper {
    private DataHelper() {

    }

    public static Card cardNumberApproved() {

        return new Card("4444444444444441", "09", "23", "USAEVA ELINA", "999");
    }

    public static Card cardNumberDeclined() {

        return new Card("4444444444444442", "09", "23", "USAEVA ELINA", "999");
    }

    public static Card invalidCardNumber() {
        return new Card("4444 4444 4444 4450", "09", "23", "USAEVA ELINA", "999");
    }

    public static Card cardEmpty() {
        return new Card("", "", "", "", "");
    }

    public static Card cardNumberEmpty() {
        return new Card("", "09", "23", "USAEVA ELINA", "999");
    }

    public static String getShiftedMonth() {
        int shift = (int) (Math.random() * 10);
        return LocalDate.now().plusMonths(shift).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getShiftedYear(int yearCount) {
        return LocalDate.now().plusYears(yearCount).format(DateTimeFormatter.ofPattern("YY"));
    }

    public static Card cardNumberLowerBound() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        String number = faker.number().digits(14);
        return new Card(number, month, year, holder, cvv);
    }

    public static Card cardLettersCharInNumber() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        return new Card("qwerty", month, year, holder, cvv);
    }

    public static Card cardSymbolsInNumber() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        return new Card("*-)=+&%", month, year, holder, cvv);
    }

    public static Card emptyMonth() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", "", year, holder, cvv);
    }

    public static Card upperBoundMonth() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", "18", year, holder, cvv);
    }

    public static Card lettersInMonth() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", "qwerty", year, holder, cvv);
    }

    public static Card symbolsInMonth() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getShiftedYear(1);
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", "_--=+", year, holder, cvv);
    }

    public static Card emptyYear() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, "", holder, cvv);
    }

    public static Card lowerBoundYear() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, "22", holder, cvv);
    }

    public static Card upperBoundYear() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, "29", holder, cvv);
    }

    public static Card lettersInYear() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, "qwerty", holder, cvv);
    }

    public static Card symbolsInYear() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String month = getShiftedMonth();
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, "_--=+", holder, cvv);
    }

    public static Card emptyOwner() {
        Faker faker = new Faker();
        String year = getShiftedYear(1);
        String month = getShiftedMonth();
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "", cvv);
    }

    public static Card numbersInOwner() {
        Faker faker = new Faker();
        String year = getShiftedYear(1);
        String month = getShiftedMonth();
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "123345", cvv);
    }

    public static Card specSymbolCharInOwner() {
        Faker faker = new Faker();
        String year = getShiftedYear(1);
        String month = getShiftedMonth();
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "_--=+", cvv);
    }

    public static Card lowerBoundInOwnerOwner() {
        Faker faker = new Faker();
        String year = getShiftedYear(1);
        String month = getShiftedMonth();
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "E", cvv);
    }

    public static Card upperBoundInOwnerOwner() {
        Faker faker = new Faker();
        String year = getShiftedYear(1);
        String month = getShiftedMonth();
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "UsaevaElinaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", cvv);
    }

    public static Card incorrectLayoutInOwner() {
        Faker faker = new Faker();
        String year = getShiftedYear(1);
        String month = getShiftedMonth();
        String cvv = faker.number().digits(3);
        return new Card("4444444444444441", month, year, "Элина Усаева", cvv);
    }

    public static Card emptyCvv() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getShiftedYear(1);
        String month = getShiftedMonth();
        return new Card("4444444444444441", month, year, holder, "");
    }

    public static Card lettersInCvv() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getShiftedYear(1);
        String month = getShiftedMonth();
        return new Card("4444444444444441", month, year, holder, "qwerrt");
    }

    public static Card specSymbolCharInCvv() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getShiftedYear(1);
        String month = getShiftedMonth();
        return new Card("4444444444444441", month, year, holder, "_--=+");
    }

    public static Card lowerBoundInCvv() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getShiftedYear(1);
        String month = getShiftedMonth();
        return new Card("4444444444444441", month, year, holder, "12");
    }

    public static Card upperBoundInCvv() {
        Faker faker = new Faker();
        String holder = faker.name().firstName() + " " + faker.name().lastName();
        String year = getShiftedYear(1);
        String month = getShiftedMonth();
        return new Card("4444444444444441", month, year, holder, "1234");
    }
}
