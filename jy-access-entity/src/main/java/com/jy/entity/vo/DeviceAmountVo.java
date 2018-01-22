package com.jy.entity.vo;

import com.jy.entity.po.Device;

public class DeviceAmountVo extends Device {

	private static final long serialVersionUID = 1L;
	private Integer amount;
	private String companyName;
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Override
	public String toString() {
		return "DeviceAmountVo [amount=" + amount + ", companyName="
				+ companyName + "]";
	}
	
}
