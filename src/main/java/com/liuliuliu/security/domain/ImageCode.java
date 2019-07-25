package com.liuliuliu.security.domain;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图片验证码类
 *
 * @author liutianyang
 * @since 1.0
 */
@Data
public class ImageCode {

    private BufferedImage image;

    private String code;

    private LocalDateTime expireTime;

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime){
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
    }

    public ImageCode(BufferedImage image, String code, int expireIn){
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpire(){
        return LocalDateTime.now().isAfter(this.expireTime);
    }
}
