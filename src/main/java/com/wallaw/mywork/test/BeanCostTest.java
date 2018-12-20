package com.wallaw.mywork.test;

import com.alibaba.fastjson.JSONObject;
import com.wallaw.mywork.entity.ContractRequestDTO;
import com.wallaw.mywork.entity.ContractRequestT;
import com.wallaw.mywork.entity.OrderTypeContractTemplateRel;
import net.sf.cglib.beans.BeanCopier;

import java.lang.reflect.InvocationTargetException;
//import org.springframework.beans.BeanUtils;
//import org.apache.commons.beanutils.BeanUtils;

/**
 * bean 复制所需要时间对比
 * @author zhangzb
 * @since 2017/9/13 16:39
 */
public class BeanCostTest {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        ContractRequestDTO source = new ContractRequestDTO();
        source.setaCaType(2);
        source.setbCaType(1);
        source.setaUserId(2223733L);
        source.setbUserId(1135009L);
        source.setBizCode("100011");
        source.setBizType("ORDER_TYPE");
        source.setTemplateType("waybill");
        source.setBizOrderNo("2017091155662318004");
        source.setCallbackUrl("http://000.16.91.114:8080/waybill/contract/callback-result");

        source.setParamJson(JSONObject.toJSONString("hello kitty"));

        OrderTypeContractTemplateRel templateRel = new OrderTypeContractTemplateRel();
        templateRel.setAsignx(1.2f);
        templateRel.setAsigny(1.3f);
        templateRel.setBizCode("0101231564");
        templateRel.setBizType("hello");

        long start = 0L;
        long end = 0L;

        ContractRequestT target1 = new ContractRequestT("spring");
        start = System.currentTimeMillis();
        org.springframework.beans.BeanUtils.copyProperties(source, target1);
        end = System.currentTimeMillis();
        System.out.println("springframework, cost:" + (end - start));
        System.out.println("object1:" + JSONObject.toJSONString(target1));

        ContractRequestT target2 = new ContractRequestT( "apache");
        start = System.currentTimeMillis();
        org.apache.commons.beanutils.BeanUtils.copyProperties(target2, source);
        end = System.currentTimeMillis();
        System.out.println("apache, cost:" + (end - start));
        //System.out.println("object2:" + JSONObject.toJSONString(target2));

        ContractRequestT target3 = new ContractRequestT("copier");
        start = System.currentTimeMillis();
        BeanCopier copier = BeanCopier.create(source.getClass(), target3.getClass(), false);
        System.out.println("BeanCopier 1, cost:" + (System.currentTimeMillis() - start));
        copier.copy(source, target3, null);
        end = System.currentTimeMillis();
        System.out.println("BeanCopier 2, cost:" + (end - start));
        //System.out.println("object3:" + JSONObject.toJSONString(target3));
    }

    public static void copyProperties(Object source,Object target){
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
        copier.copy(source, target, null);
    }

}
