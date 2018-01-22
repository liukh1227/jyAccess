package com.jy.entity.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.jy.entity.vo.ContractDeviceModelVo;

public class ContractDeviceModel4PdfPojo extends ContractDeviceModelVo {
	
	private static final long serialVersionUID = 1L;
	private String billingTypeStr;
	private String leaseTermStr;
	private String unitPriceStr;
	private String planStartTimeStr;
	
	public String getBillingTypeStr() {
		return billingTypeStr;
	}
	public void setBillingTypeStr(String billingTypeStr) {
		this.billingTypeStr = billingTypeStr;
	}
	public String getLeaseTermStr() {
		return leaseTermStr;
	}
	public void setLeaseTermStr(String leaseTermStr) {
		this.leaseTermStr = leaseTermStr;
	}
	public String getUnitPriceStr() {
		return unitPriceStr;
	}
	public void setUnitPriceStr(String unitPriceStr) {
		this.unitPriceStr = unitPriceStr;
	}
	
	public String getPlanStartTimeStr() {
		return planStartTimeStr;
	}
	public void setPlanStartTimeStr(String planStartTimeStr) {
		this.planStartTimeStr = planStartTimeStr;
	}
	public void copyFrom(ContractDeviceModelVo other){
		if(other.getTypeName()!= null){
			setTypeName(other.getTypeName());
		}else{
			setTypeName("");
		}
		if(other.getModelName()!= null){
			setModelName(other.getModelName());
		}else{
			setModelName("");
		}
		if(other.getQuantity()!= null){
			setQuantity(other.getQuantity());
		}else{
			setQuantity(0);
		}
		if(other.getPlanStartTime()!= null){
			String pattern="yyyy-MM-dd";
			  SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			String  str = sdf.format(other.getPlanStartTime());
			setPlanStartTimeStr(str);
		}else{
			setPlanStartTimeStr("");
		}
	
		
		if(other.getBillingType()!= null){
			StringBuffer leaseTermSbf = new StringBuffer();
			leaseTermSbf.append(other.getLeaseTerm());
			leaseTermSbf.append("/");
			StringBuffer unitPriceSbf = new StringBuffer();
			unitPriceSbf.append(other.getUnitPrice());
			unitPriceSbf.append("/");
			
			if(other.isMonthBillingType()){
				setBillingTypeStr("月租");
				leaseTermSbf.append("月");
				unitPriceSbf.append("月");
			}
			if(other.isWeekBillingType()){
				setBillingTypeStr("周租");
				leaseTermSbf.append("周");
				unitPriceSbf.append("周");
			}
			if(other.isDayBillingType()){
				setBillingTypeStr("日租");
				leaseTermSbf.append("日");
				unitPriceSbf.append("日");
			}
			setLeaseTermStr(leaseTermSbf.toString());
			setUnitPriceStr(unitPriceSbf.toString());
		}
		
		
		
	}
	

}
