package ru.netology.web.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;

public class DataGenerator {

    private static final Faker faker = new Faker(new Locale("en"));
    private DataGenerator() {}

    public static String generateRandomCVC() {
        return faker.numerify("###");
    }

    public static String generateRandomOwner() {
        return faker.name().lastName() +" " + faker.name().firstName();
    }

    public static String generateLongOwner() {
        return faker.regexify("[A-Z][a-z]{50} [A-Z][a-z]{50}");
    }

    public static Date generateValidFutureDate() {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(new Date());
        gc.add(Calendar.MONTH, (new Random()).nextInt(36)+1); // к текущей дате прибавяляем случайно от 1 до 36 месяцев
        return gc.getTime();
    }
    public static Date generateInvalidFutureDate() {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(new Date());
        gc.add(Calendar.MONTH, 72); // к текущей дате прибавяляем 72 месяца (6 лет)
        return gc.getTime();
    }

}