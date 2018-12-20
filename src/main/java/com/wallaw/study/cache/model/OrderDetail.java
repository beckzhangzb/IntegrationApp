package com.wallaw.study.cache.model;

import lombok.Data;

@Data
public class OrderDetail {

    /** 主键id       */
	private Integer id;

    /** 订单号        */
	private String orderNo;

    /** 订单状态:  NORMAL("正常"),TAKING("提货中"),TAKED("已提货"),TURNED_WAYBILL("已转运单"),CANCELED( "已取消")    */
	private String status;

    /** 发货企业名称       */
	private String shipCompanyName;

	/**
	 * 收货企业名称
	 */
	private String recCompanyName;

    /** 主营业务：("VEHICLESERVICE", "整车服务"),("CHANNELSERVICE", "通道服务")      */
	private String mainBusiness;

    /** 派件方式：("EMERGENCYW", "急件"),("SLOW", "慢件")       */
	private String sendWay;

    /** 提货时间        */
	private String takeTime;

    /** 创建下单时间      */
	private String createTime;

    /** 备注    */
	private String remark;

	/**
	 * 货物列表
	 */
    /*
	private List<OrderCargo> cargoList;
	private List<OrderAddress> shipAddresses;
    */

	public OrderDetail(){
	}

}

