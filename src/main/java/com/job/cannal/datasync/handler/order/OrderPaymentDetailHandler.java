package com.job.cannal.datasync.handler.order;


import com.google.common.collect.Lists;

import com.job.cannal.datasync.annotation.TableHandler;
import com.job.cannal.datasync.handler.AbstractHandler;
import com.job.cannal.datasync.util.Table;
import com.job.cannal.datasync.util.Type;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Created by haha on 2020/10/20.
 */
@Component
@TableHandler(table = Table.ORDER_PAYMENT_DETAIL)
public class OrderPaymentDetailHandler extends AbstractHandler {
    private final List<Type> supportTypes = Lists.newArrayList(Type.INSERT, Type.UPDATE, Type.DELETE);

    @Override
    public void doHandle(FlatMessage message) {
        this.handleMessage(message, this.supportTypes);
    }
}
