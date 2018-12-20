package com.wallaw.mywork.test;

import com.wallaw.util.GsonUtil;

public class JavaTest {
	public static void main(String[] args) {
		String sa = "{\"code\":15,\"msg\":\"Remote service error\",\"sub_code\":\"isv.data-duplicate-error\",\"sub_msg\":\"all data exists in db\",\"request_id\":\"qm2ylyy1utyp\"}";
		Object sb = GsonUtil.getObjectFromJson(sa, Object.class);
		System.out.println(sb.toString());
		String sour = "耗儿鱼啊耗儿鱼快点起飞了起飞了";
		System.out.println("length:"+sour.substring(0, 10));		
				
				
	}

}
