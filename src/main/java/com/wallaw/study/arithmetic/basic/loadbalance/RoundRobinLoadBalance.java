package com.wallaw.study.arithmetic.basic.loadbalance;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangzb
 * @since 2018/3/21 10:43
 */
public class RoundRobinLoadBalance extends AbstractLoadBalance {

    /**
     * 递增计数位
     */
    private AtomicInteger idx = new AtomicInteger(0);

    private int getNextPositiveIndex(){
        return idx.decrementAndGet();
    }

    @Override
    Refer doSelectRefer() {
        List<Refer> refers = getReferList();
        int positiveIdx = getNextPositiveIndex();
        for (int i=1; i<=refers.size();i++ ){
            int current = (i + positiveIdx)%refers.size();
            Refer refer = refers.get(current);
            if (refer.isAvailable()){
                return refer;
            }
        }
        return null;
    }
}
