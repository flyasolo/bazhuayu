package com.example.demo.Bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class VerifyCode {
    private String code;
    private byte[] imgBytes;
    private long expireTime;
}
