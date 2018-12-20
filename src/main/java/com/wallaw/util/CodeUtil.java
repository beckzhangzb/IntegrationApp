package com.wallaw.util;

import com.wallaw.dto.CodeDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 登录验证码工具类
 * @since 2018/5/18
 */
public class CodeUtil {
    /**
     *     图片的宽度。
     */
    private static final int width     = 160;
    /**
     * 图片的高度。
     */
    private static final int height    = 40;
    /**
     *验证码字符个数
     */
    private static final int codeCount = 5;
    /**
     * 验证码干扰线数
     */
    private static final int lineCount = 150;

    /**
     * 验证码范围,去掉0(数字)和O(拼音)容易混淆的(小写的1和L也可以去掉,大写不用了)
     */
    private static final char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 验证码产生方法
     * @param businessCode
     * @return
     */
    public CodeDTO getCode(String businessCode, Long expireTime) {
        CodeDTO dto = new CodeDTO();
        //这个地方并发效率不高，但是目前在并发量不大的情况下，通过加锁，
        // 保证顺序执行，这样生成的key为唯一，如果以后要支持并发，要修改该处代码
        String key = null;
        synchronized (this) {
            Long current = System.currentTimeMillis();
            key = current + businessCode;
            dto.setCid(key);
        }
        String value = createCode(dto);
        if (StringUtils.isNotEmpty(value)) {
            redisTemplate.opsForValue().set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.MINUTES);
        }
        return dto;
    }

    /**
     * 校验验证码是否一致
     * @param cid
     * @param code
     * @return
     */
    public boolean checkCode(String cid, String code) {
        boolean flag = false;
        if (StringUtils.isNotEmpty(cid)) {
            String value = redisTemplate.opsForValue().get(cid);
            if (StringUtils.isNotEmpty(value) && value.equalsIgnoreCase(code)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 生成验证码图片
     * @param dto
     * @return
     */
    private String createCode(CodeDTO dto) {
        int fontWidth = 0, fontHeight = 0, codeY = 0;
        int red = 0, green = 0, blue = 0;
        //每个字符的宽度(左右各空出一个字符)
        fontWidth = width / (codeCount + 2);
        //字体的高度
        fontHeight = height - 2;
        codeY = height - 4;
        //图像buffer
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics2D g = bufferedImage.createGraphics();
        //将图片填充为白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        //创建字体
        Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
        g.setFont(font);
        //生成随机数
        Random random = new Random();
        //设置图片中的干扰线
        for (int i = 0; i < lineCount; i++) {
            //x坐标开始
            int xs = random.nextInt(width);
            //y坐标开始
            int ys = random.nextInt(height);
            //x坐标结束
            int xe = xs + random.nextInt(width / 8);
            //y坐标结束
            int ye = ys + random.nextInt(height / 8);
            // 产生随机的颜色值，让输出的每个干扰线的颜色值都将不同
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawLine(xs, ys, xe, ye);
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < codeCount; i++) {
            String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawString(strRand, (i + 1) * fontWidth, codeY);
            stringBuffer.append(strRand);
        }
        dto.setBufferedImage(bufferedImage);
        return stringBuffer.toString();
    }

}
