package com.ali.work.mergefile;

/**
 * 分组对象实体
 */
public class GroupQuota {

    /**
     * id
     */
    private String id;

    /**
     * 分组id
     */
    private String groupId;

    /**
     * 指标
     */
    private Float quota;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Float getQuota() {
        return quota;
    }

    public void setQuota(Float quota) {
        this.quota = quota;
    }
}
