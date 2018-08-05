package com.wallet.wallet.Common;

import java.security.SecureRandom;

public class RandomStringGenerator {
    private final static String EXAMPLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom secureRandom = new SecureRandom();

    public static String getRandomStringGenerator(int stringSize) {
        StringBuilder stringBuilder = new StringBuilder(stringSize);
        for (int i=0; i<stringSize; i++) {
            stringBuilder.append(EXAMPLE.charAt(secureRandom.nextInt(EXAMPLE.length())));
        }
        return stringBuilder.toString();
    }
}
