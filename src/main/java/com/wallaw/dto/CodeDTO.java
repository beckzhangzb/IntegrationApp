package com.wallaw.dto;

import java.awt.image.BufferedImage;

/**
 * 验证码DTO
 * @since 2018/5/18
 */
public class CodeDTO {
    /**
     * 唯一标识码
     */
    private String        cid;
    /**
     * 验证码图片对象
     */
    private BufferedImage bufferedImage;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }
}
