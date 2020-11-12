package com.job.cannal.datasync.dispatcher;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.otter.canal.protocol.FlatMessage;
import com.job.cannal.datasync.handler.Handler;
import com.job.cannal.datasync.handler.HandlerContext;
import com.job.cannal.datasync.service.ISystemConfigService;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by haha on 2020/8/21.
 */
@Component
@RocketMQMessageListener(consumeMode = ConsumeMode.ORDERLY, consumerGroup = "datasync", topic = "datasync-smzc")
public class RocketMQMessageDispatcher implements RocketMQListener<MessageExt> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    HandlerContext handlerContext;
    @Autowired
    ISystemConfigService systemConfigService;

    @Override
    public void onMessage(MessageExt messageExt) {
        FlatMessage message = JSON.parseObject(messageExt.getBody(), FlatMessage.class, new Feature[0]);
        logger.info("schema:{} table:{} type:{} data:{} old:{}", message.getDatabase(), message.getTable(), message.getType(), message.getData(), message.getOld());
        try {
            this.handleMessage(message);
        } catch (Exception e) {
            logger.error(e.getMessage());
            //e.printStackTrace();
        }
    }

    private void handleMessage(FlatMessage message) {
        if (systemConfigService.isIgnoredTable(message.getTable())) {
            logger.info("ignored table:{}", message.getTable());
            return;
        }
        Handler handler = handlerContext.getHandler(message.getTable());
        handler.doHandle(message);
    }

}
