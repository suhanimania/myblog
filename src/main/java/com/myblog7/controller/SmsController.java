package com.myblog7.controller;

import com.myblog7.payload.SmsRequest;
import com.myblog7.repository.OtpRepository;
import com.myblog7.service.SmsService;
import com.myblog7.utils.OtpGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@RestController
@RequestMapping("/api/otp")
public class SmsController {

    @Autowired
    public SmsService smsService;

    @Autowired
    private OtpRepository otpRepository;


//    //http://localhost:8080/send-sms
//
////    @PostMapping("/sendSms")
////    public String sendSms(@RequestParam String toPhoneNumber, @RequestParam String message) {
////        smsService.sendSms(toPhoneNumber, message);
////        return "SMS sent successfully!";
////    }
//
////    @PostMapping("/send-sms")
////    public String sendSms(@RequestBody SmsRequest smsRequest) {
////        smsService.sendSms(smsRequest.getTo(), smsRequest.getMessage());
////        return "SMS sent successfully!";
////    }
//
//    //http://localhost:8080/api/otp/send-otp?phoneNumber=%2B919556986096
//    @PostMapping("/send-otp")
//    public String sendOtp(@RequestParam String phoneNumber ) {
//        String otp = generateOtp();
//        //String msg = "Your OTP is: " + otp;
//        smsService.sendOtp(phoneNumber, otp);
//        return "OTP Sent..";
//
//
//    }
//
//    private static String generateOtp() {
//        int otpLength = 6;
//        StringBuilder otp = new StringBuilder();
//
//        Random random = new Random();
//        for (int i=0; i<otpLength; i++){
//            otp.append(random.nextInt(10));
//
//        }
//        return otp.toString();
//
//    }
    @PostMapping("/send-otp")
    public ModelAndView sendOtp(@RequestParam String phoneNumber){
        String otp = OtpGenerator.generateOtp();
        smsService.sendOtp(phoneNumber,otp);

        ModelAndView modelAndView = new ModelAndView("/verify-otp");
        modelAndView.addObject("phoneNumber",phoneNumber);
        return modelAndView;

    }

    //http://localhost:8080/api/otp/verify-otp?phoneNumber=%2B919556986096&enteredOtp=204027
    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String phoneNumber, @RequestParam String enteredOtp) {
        String storedOTP =otpRepository.getOtp(phoneNumber);

        if (storedOTP != null && storedOTP.equals(enteredOtp)) {
            otpRepository.removeOtp(phoneNumber);
            return "otp verifird";
        } else {
            return "otp is not verified" + phoneNumber;
        }
    }


}

//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/otp")
//public class OtpController {
//
//    @PostMapping("/send-otp")
//    public ResponseEntity<String> sendOtp(@RequestParam String phoneNumber) {
//        // Logic for sending OTP
//        return ResponseEntity.ok("Otp sent successfully");
//
//    }
//
//    @PostMapping("/verify-otp")
//    public ResponseEntity<String> verifyOtp(@RequestParam String phoneNumber, @RequestParam String enteredOtp) {
//        // Logic for verifying OTP
//        if ("yourStoredOtp".equals(enteredOtp)) {
//            return ResponseEntity.ok("Otp verification successful");
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP");
//        }
//    }
//}
//
//
