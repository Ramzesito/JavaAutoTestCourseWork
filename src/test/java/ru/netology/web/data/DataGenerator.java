package ru.netology.web.data;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.*;

public class DataGenerator {
    private static final String approovedCardNum = "1111 2222 3333 4444";
    private static final String declinedCardNum = "5555 6666 7777 8888";
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

    public static String getApproovedCardNum() {
        return approovedCardNum;
    }

    public static String getDeclinedCardNum() {
        return declinedCardNum;
    }

    public static String getDateFormatted(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

}