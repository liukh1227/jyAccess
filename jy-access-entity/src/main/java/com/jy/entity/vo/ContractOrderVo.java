package com.jy.entity.vo;

import com.jy.entity.po.ContractOrder;

public class ContractOrderVo extends ContractOrder {
	
	private static final long serialVersionUID = 1L;
	private String customerName;
	private String cusLinkman;
	private String cusTelephone;
	private String companyName;
	private String salesManUserName;
	private String creatorUserName;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCusLinkman() {
		return cusLinkman;
	}
	public void setCusLinkman(String cusLinkman) {
		this.cusLinkman = cusLinkman;
	}
	public String getCusTelephone() {
		return cusTelephone;
	}
	public void setCusTelephone(String cusTelephone) {
		this.cusTelephone = cusTelephone;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getSalesManUserName() {
		return salesManUserName;
	}
	public void setSalesManUserName(String salesManUserName) {
		this.salesManUserName = salesManUserName;
	}
	public String getCreatorUserName() {
		return creatorUserName;
	}
	public void setCreatorUserName(String creatorUserName) {
		this.creatorUserName = creatorUserName;
	}
	@Override
	public String toString() {
		return "ContractOrderVo [customerName=" + customerName
				+ ", cusLinkman=" + cusLinkman + ", cusTelephone="
				+ cusTelephone + ", companyName=" + companyName
				+ ", salesManUserName=" + salesManUserName
				+ ", creatorUserName=" + creatorUserName + "]";
	}
	
}
