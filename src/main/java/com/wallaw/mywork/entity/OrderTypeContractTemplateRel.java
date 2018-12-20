package com.wallaw.mywork.entity;

import java.util.Date;

/**
 * 需求单类型合同模板关系实体类
 * @author zhangzb
 * @since 2017-9-6 10:09:34
 */
public class OrderTypeContractTemplateRel {

    /** id       db_column: id */
    private Long id;

    /** bizCode       db_column: biz_code */
    private String bizCode;

    /** 业务码       db_column: biz_type */
    private String bizType;

    /** 合同模板业务类型      db_column: template_type */
    private String templateType;

    /** 甲方签名横坐标       db_column: aSignx */
    private Float asignx;

    /** 甲方签名纵坐标       db_column: aSigny */
    private Float asigny;

    /** 乙方签名横坐标       db_column: bSignx */
    private Float bsignx;

    /** 乙方签名纵坐标       db_column: bSigny */
    private Float bsigny;

    /** 签名页码       db_column: page_num */
    private Integer pageNum;

    /** 状态，1有效，0无效       db_column: status */
    private Integer status;

    /** 创建者id       db_column: create_user_id */
    private Long createUserId;

    /** 更新者userId       db_column: update_user_id */
    private Long updateUserId;

    /** createTime       db_column: create_time */
    private Date createTime;

    /** updateTime       db_column: update_time */
    private Date updateTime;

    public OrderTypeContractTemplateRel() {
    }

    public Float getAsignx() {
        return asignx;
    }

    public void setAsignx(Float asignx) {
        this.asignx = asignx;
    }

    public Float getAsigny() {
        return asigny;
    }

    public void setAsigny(Float asigny) {
        this.asigny = asigny;
    }

    public Float getBsignx() {
        return bsignx;
    }

    public void setBsignx(Float bsignx) {
        this.bsignx = bsignx;
    }

    public Float getBsigny() {
        return bsigny;
    }

    public void setBsigny(Float bsigny) {
        this.bsigny = bsigny;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setId(Long value) {
        this.id = value;
    }

    public Long getId() {
        return this.id;
    }

    public void setBizCode(String value) {
        this.bizCode = value;
    }

    public String getBizCode() {
        return this.bizCode;
    }

    public void setBizType(String value) {
        this.bizType = value;
    }

    public String getBizType() {
        return this.bizType;
    }

    public void setTemplateType(String value) {
        this.templateType = value;
    }

    public String getTemplateType() {
        return this.templateType;
    }

    public void setStatus(Integer value) {
        this.status = value;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setCreateTime(Date value) {
        this.createTime = value;
    }

    public Date getCreateTime() {
        return this.createTime;
    }
}

