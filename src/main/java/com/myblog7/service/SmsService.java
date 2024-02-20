package com.myblog7.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${twilio.account-Sid}")
    private String accountSid;

    @Value("${twilio.auth-Token}")
    private String authToken;

    @Value("${twilio.phone-Number}")
    private String twilioPhoneNumber;

//    public SmsService(@Value("${twilio.accountSid}") String accountSid,
//                      @Value("${twilio.authToken}") String authToken,
//                      @Value("${twilio.phoneNumber}")String twilioPhoneNumber) {
//        this.accountSid = accountSid;
//        this.authToken = authToken;
//        this.twilioPhoneNumber = twilioPhoneNumber;
//    }
      //sms sending codeing
//    public void sendSms(String to, String message) {
//        Twilio.init(accountSid, authToken);
//         Message.creator(
//                        new PhoneNumber(to),
//                        new PhoneNumber(twilioPhoneNumber),
//                        message)
//                .create();
//
//        System.out.println("SMS sent successfully! SID: " +message );
//    }

    public void sendOtp(String toPhoneNumber, String otp) {
        Twilio.init(accountSid, authToken);
        Message.creator(
                        new PhoneNumber(toPhoneNumber),
                        new PhoneNumber(twilioPhoneNumber),
                        otp)
                .create();
        System.out.println("otp sent this mobile no successfully.." +otp);
    }
}

