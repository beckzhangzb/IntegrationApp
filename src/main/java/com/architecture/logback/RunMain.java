package com.architecture.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangzb
 * @since 2018/12/6 14:13
 */
public class RunMain {
    public static void main(String[] args) {
        Logger INFO_LOG = LoggerFactory.getLogger("infoLog");
        Logger ERROR_LOG = LoggerFactory.getLogger("errorLog");

        for (int i = 0; i < 5; i++) {
            INFO_LOG.info("业务日志 " + i);
            ERROR_LOG.error("异常日志 " + i);
        }

    }
}
