package com.myblog7.utils;

import java.util.Random;

public class OtpGenerator {
    public static String generateOtp() {
        int otpLength = 6;
        StringBuilder otp = new StringBuilder();

        Random random = new Random();
        for (int i=0; i<otpLength; i++){
            otp.append(random.nextInt(10));

        }
        return otp.toString();

    }
}
