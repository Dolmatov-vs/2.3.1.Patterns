package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class UserData {
    private UserData(){
    }

    @Value
    public static class UserInfo {
        private String city;
        private String date;
        private String fullName;
        private String phone;
    }

    public static UserInfo getUserInfo() {
        Faker faker = new Faker(new Locale("ru"));
        return new UserInfo(
                faker.address().city(),
                dateMeeting(),
                faker.name().lastName()+" "+faker.name().firstName(),
                faker.phoneNumber().phoneNumber());
    }

    public static String dateMeeting() {
        Faker faker = new Faker(new Locale("ru"));

        final String dateFormatWithDots = "dd.MM.yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormatWithDots);

        int daysToAdd = faker.random().nextInt(5, 365);
        LocalDate newDateMeeting = LocalDate.now().plusDays(daysToAdd);
        String dateMeeting = newDateMeeting.format(formatter);
        return dateMeeting;
    }
}
