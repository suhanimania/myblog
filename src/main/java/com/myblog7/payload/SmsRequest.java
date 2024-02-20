package com.myblog7.payload;

import lombok.Data;

@Data
public class SmsRequest {

    private String to;
    private String message;
}
