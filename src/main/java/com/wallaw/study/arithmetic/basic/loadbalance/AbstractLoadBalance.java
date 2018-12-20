package com.wallaw.study.arithmetic.basic.loadbalance;

import java.util.List;

/**
 * 负载均衡抽象类
 * @author zhangzb
 * @since 2018/3/21 10:42
 */
public abstract class AbstractLoadBalance {
    public List<Refer> referList;

    abstract Refer doSelectRefer();

    public List<Refer> getReferList() {
        return referList;
    }

    public void setReferList(List<Refer> referList) {
        this.referList = referList;
    }
}
