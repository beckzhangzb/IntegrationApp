package com.wallaw.mywork.entity;

/**
 * 封装合同请求
 * @author zhangzb
 * @since 2017/9/5 13:49
 */
public class ContractRequestDTO {

    /**甲方签约用户类型 1 个人 2 企业**/
    private Integer aCaType;
    /**乙方签约用户类型 1 个人 2 企业**/
    private Integer bCaType;
    /**
     * 甲方userId
     */
    private Long    aUserId;
    /**
     * 乙方userId
     */
    private Long    bUserId;
    /**
     * 业务合同模板唯一code
     */
    private String  bizCode;
    /**
     * 业务类型
     */
    private String  bizType;
    /**
     * 模板类型
     */
    private String  templateType;
    /**
     * 参数json
     */
    private String  paramJson;

    /**
     * 业务单号（需求单号等）
     */
    private String bizOrderNo;

    /**回调地址**/
    private String callbackUrl;

    /** 合同模板关系 **/
    private OrderTypeContractTemplateRel templateRel;

    public OrderTypeContractTemplateRel getTemplateRel() {
        return templateRel;
    }

    public void setTemplateRel(OrderTypeContractTemplateRel templateRel) {
        this.templateRel = templateRel;
    }

    public String getBizOrderNo() {
        return bizOrderNo;
    }

    public void setBizOrderNo(String bizOrderNo) {
        this.bizOrderNo = bizOrderNo;
    }

    public Integer getaCaType() {
        return aCaType;
    }

    public void setaCaType(Integer aCaType) {
        this.aCaType = aCaType;
    }

    public Integer getbCaType() {
        return bCaType;
    }

    public void setbCaType(Integer bCaType) {
        this.bCaType = bCaType;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public Long getaUserId() {
        return aUserId;
    }

    public void setaUserId(Long aUserId) {
        this.aUserId = aUserId;
    }

    public Long getbUserId() {
        return bUserId;
    }

    public void setbUserId(Long bUserId) {
        this.bUserId = bUserId;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getParamJson() {
        return paramJson;
    }

    public void setParamJson(String paramJson) {
        this.paramJson = paramJson;
    }
}
