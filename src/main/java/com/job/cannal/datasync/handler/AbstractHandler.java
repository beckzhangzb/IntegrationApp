package com.job.cannal.datasync.handler;

import com.google.common.collect.Lists;
import com.job.cannal.datasync.util.SqlBuilder;
import com.job.cannal.datasync.util.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by haha on 2020/8/27.
 */
public class AbstractHandler implements Handler<FlatMessage> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final List<Type> supportTypes = Lists.newArrayList(Type.INSERT, Type.UPDATE);
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void doHandle(FlatMessage message) {
        this.handleMessage(message, this.supportTypes);
    }

    public void handleMessage(FlatMessage message, List<Type> supportTypes) {
        Type type = Type.fromName(message.getType());
        if (!supportTypes.contains(type)) {
            return;
        }
        switch (type) {
            case INSERT:
                this.processInsert(message);
                break;
            case UPDATE:
                this.processUpdate(message);
                break;
            case DELETE:
                this.processDelete(message);
            default:
                break;
        }
    }

    private void processInsert(FlatMessage message) {
        String sql = SqlBuilder.buildInsertSql(message);
        logger.info("flatMessage:{}", sql);
        jdbcTemplate.batchUpdate(sql);
    }

    private void processUpdate(FlatMessage message) {
        List<String> sqls = SqlBuilder.buildUpdateSql(message);
        logger.info("flatMessage:{}", sqls);
        jdbcTemplate.batchUpdate(sqls.toArray(new String[sqls.size()]));
    }

    private void processDelete(FlatMessage message) {
        String sql = SqlBuilder.buildDeleteSql(message);
        logger.info("flatMessage:{}", sql);
        jdbcTemplate.update(sql);
    }
}
