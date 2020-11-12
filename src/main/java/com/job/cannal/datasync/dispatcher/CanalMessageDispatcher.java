package com.job.cannal.datasync.dispatcher;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.job.cannal.datasync.handler.Handler;
import com.job.cannal.datasync.handler.HandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by haha on 2020/8/26.
 */
//@Component
public class CanalMessageDispatcher implements InitializingBean, DisposableBean {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    HandlerContext handlerContext;

    private CanalConnector connector;

    @Override
    public void afterPropertiesSet() throws Exception {
        connector = CanalConnectors.newClusterConnector("10.28.17.115:2181,10.28.17.99:2181,10.28.17.186:2181", "example", "root", "2819485608DC659AF03EE9DCE90E2D9D46DA02B7");
        connector.connect();
        connector.rollback();
        while (true) {
            Message message = connector.getWithoutAck(5);
            long batchId = message.getId();
            long size = message.getEntries().size();
            if (batchId == -1 || size == 0) {
                Thread.sleep(500L);
            } else {
                try {
                    for (CanalEntry.Entry entry : message.getEntries()) {
                        if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN || entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
                            continue;
                        }
                        String schemaName = entry.getHeader().getSchemaName();
                        String tableName = entry.getHeader().getTableName();
                        logger.info("accept message:{}", entry.toString());
                        Handler handler = handlerContext.getHandler(tableName);
                        if (handler != null) {
                            handler.doHandle(entry);
                        }
                    }
                } catch (Exception e) {
                    connector.rollback(batchId);
                }
            }
            connector.ack(message.getId());
        }
    }

    @Override
    public void destroy() throws Exception {
        if (connector != null) {
            connector.disconnect();
        }
    }
}
