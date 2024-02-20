package com.myblog7.repository;

import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;

@Repository
public class OtpRepository {
    private final Map<String,String> otpMap = new HashMap<>();

    public void saveOtp(String phoneNumber,String otp){
        otpMap.put(phoneNumber, otp);
    }
    public String getOtp(String phoneNumber){
        return otpMap.get(phoneNumber);
    }
    public void removeOtp(String phoneNumber){
        otpMap.remove(phoneNumber);
    }

}
