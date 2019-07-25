package com.liuliuliu.security.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 短信验证码类
 *
 * @author liutianyang
 * @since 1.0
 */
@Data
public class SmsCode {
    private String code;
    private LocalDateTime expireTime;

    public SmsCode(String code, LocalDateTime expireTime){
        this.code = code;
        this.expireTime = expireTime;
    }

    public SmsCode(String code, int expireIn){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpire(){
        return LocalDateTime.now().isAfter(this.expireTime);
    }
}
