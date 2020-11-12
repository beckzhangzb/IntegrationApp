package com.job.cannal.datasync.handler;

import com.job.cannal.datasync.annotation.TableHandler;
import com.job.cannal.datasync.util.Table;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by haha on 2020/8/21.
 */
@Component
public class HandlerContext implements ApplicationContextAware {
    private final Map<String, Handler> handlerMap = new HashMap<>();
    @Autowired
    List<Handler> handlers;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (!CollectionUtils.isEmpty(handlers)) {
            synchronized (this) {
                handlers.forEach(handler -> {
                    TableHandler tableHandler = AopUtils.getTargetClass(handler).getAnnotation(TableHandler.class);
                    String table = tableHandler.table();
                    if (!StringUtils.isEmpty(table)) {
                        handlerMap.put(table, handler);
                    }
                });
            }
        }
    }

    public Handler getHandler(String tableName) {
        Handler handler = handlerMap.get(tableName);
        return handler == null ? handlerMap.get(Table.DEFAULT) : handler;
    }
}
