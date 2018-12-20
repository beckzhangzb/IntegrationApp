package com.wallaw.study.elasticsearch;

import com.wallaw.study.cache.model.OrderDetail;
import com.wallaw.study.cache.model.User;
import com.wallaw.util.DateUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.Random;

/**
 * @author zhangzb
 * @since 2018/7/12 17:17
 */
public class ProduceMsgMain {
    ApplicationContext Factory = new ClassPathXmlApplicationContext("spring/single_redis.xml");
    RedisOperate redisOperate = (RedisOperate) Factory.getBean("redisOperate");

    public static void main(String[] args) {
        ProduceMsgMain produceMsgMain = new ProduceMsgMain();

        produceMsgMain.produceForChannelCase();
    }

    /**
     * 产生以list作为 data_type 传输至logstash
     */
    private void produceForListCase() {
        User user = new User();
        user.setId(110);
        user.setName("zhangsan");
        user.setAddress("成都高新区天府软件园");
        redisOperate.redisCacheForListBLPOP("key_logstash_list", user);
    }

    private void produceForChannelCase() {
        OrderDetail orderDetail = new OrderDetail();
        Date now = new Date();
        Random random = new Random();

        for (int i=0;i<20;i++) {
            orderDetail.setId(random.nextInt(1000000000));
            orderDetail.setMainBusiness("VEHICLESERVICE");
            orderDetail.setStatus("NORMAL");
            orderDetail.setShipCompanyName("company" + i);
            orderDetail.setRecCompanyName("recCompany" + i);
            orderDetail.setOrderNo("DD" + orderDetail.getId());
            orderDetail.setCreateTime(DateUtil.formateDateyyyy_MM_ddHHmmss(now));
            orderDetail.setTakeTime(DateUtil.formateDateyyyy_MM_ddHHmmss(DateUtils.addDays(now, 1)));

            String key = "order_" + orderDetail.getId();
            redisOperate.redisCacheForPubSub(key, orderDetail);
        }
    }



}


