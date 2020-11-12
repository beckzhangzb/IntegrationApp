package com.job.cannal.datasync.service.impl;

import com.job.cannal.datasync.service.ISystemConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * Created by haha on 2020/9/15.
 */
@Component
public class SystemConfigServiceImpl implements ISystemConfigService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Set<String> ignoredTables = new HashSet<>();
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Scheduled(fixedDelay = 300000)
    public void refreshIgnoredTable() {
        ignoredTables.clear();
        List<String> list = jdbcTemplate.queryForList("select tableName from datasync_ignored_table", String.class);
        ignoredTables.addAll(list);
        logger.info("ignored tables:{}", ignoredTables);
    }

    @Override
    public boolean isIgnoredTable(String tableName) {
        return ignoredTables.contains(tableName);
    }
}
