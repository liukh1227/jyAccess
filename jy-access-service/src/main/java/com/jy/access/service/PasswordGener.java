package com.jy.access.service;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.common.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.jy.base.common.utils.CommonUtils;
import com.jy.base.common.utils.PasswordUtils;
import com.jy.entity.bo.QuotationBo;
import com.jy.entity.vo.QuotationDeviceVo;

public class PasswordGener {

	/**
	 * @author liukh
	 * @date 2017-6-29 下午5:36:55
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String passwordSalt = CommonUtils.getRandomString(40).toLowerCase();
     System.out.println("----passwordSalt-----" + passwordSalt);
     String loginId="18321138606";
     String password="123456";
		String userPass = PasswordUtils.encyptPassword(loginId, password, passwordSalt);
		
	     System.out.println("----userPass-----" + userPass);
	     
	     /**
	     QuotationBo quotationBo = new QuotationBo();
	     quotationBo.setQuotation_Id("123334");
	     quotationBo.setCompanyName("公司名称");
	     QuotationDeviceVo quotationDeviceVo1 = new QuotationDeviceVo();
	     List<QuotationDeviceVo> listdevice = new ArrayList<QuotationDeviceVo>();
	     
	     quotationDeviceVo1.setQuodevice_Id("Quodevice_Id1");
	     quotationDeviceVo1.setQuantity(1);
	     listdevice.add(quotationDeviceVo1);
	     
	     QuotationDeviceVo quotationDeviceVo2 = new QuotationDeviceVo();
	     quotationDeviceVo2.setQuodevice_Id("Quodevice_Id2");
	     quotationDeviceVo2.setQuantity(2);
	     listdevice.add(quotationDeviceVo2);

	     quotationBo.setQuotationDevices(listdevice);
	  String jsonStr = JSON.toJSONString(quotationBo);
	     
	     System.out.println(jsonStr);
	     QuotationBo parsequotationBo = JSON.parseObject(jsonStr,QuotationBo.class);
	     System.out.println(parsequotationBo.getCompanyName());
	     System.out.println(parsequotationBo.getQuotationDevices().size());
	     for(QuotationDeviceVo queryQuotationDeviceVo :parsequotationBo.getQuotationDevices()){
	    	 System.out.println(queryQuotationDeviceVo.getQuodevice_Id()); 
	    	 System.out.println(queryQuotationDeviceVo.getQuantity()); 
	     }
	     
	     */
	     
	}

}
