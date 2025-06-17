package com.api_testing.utils;

import com.github.javafaker.Faker;

public class FakerUtils {
    private static final Faker faker = new Faker();

    public static String generateRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String generateRandomName() {
        return faker.name().fullName();
    }

    public static String generateRandomPhoneNumber(String countryCode) {
       if("US".equals(countryCode)) {
        return faker.numerify("(505) 32#-###6");
       } else {
        return faker.phoneNumber().phoneNumber();
       }
    }

    public static String generateRandomAddress() {
        return faker.address().fullAddress();
    }
}
