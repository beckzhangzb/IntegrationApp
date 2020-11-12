package com.job.cannal.datasync.handler;

import com.job.cannal.datasync.annotation.TableHandler;
import com.job.cannal.datasync.util.Table;
import org.springframework.stereotype.Component;

/**
 * Created by haha on 2020/9/1.
 */
@Component
@TableHandler(table = Table.DEFAULT)
public class DefaultTableHandler extends AbstractHandler {
}
