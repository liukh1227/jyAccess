package com.jy.entity.vo;

import com.jy.entity.po.Quotation;

public class QuotationVo extends Quotation {

	private static final long serialVersionUID = 1L;
	private String customerName;
	private String cusAddress;
	private String cusLinkman;
	private String cusTelephone;
	private String cusEmail;
	private String companyName;
	private String comAddress;
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
	public String getCusEmail() {
		return cusEmail;
	}
	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
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
	public String getCusAddress() {
		return cusAddress;
	}
	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}
	public String getComAddress() {
		return comAddress;
	}
	public void setComAddress(String comAddress) {
		this.comAddress = comAddress;
	}
	@Override
	public String toString() {
		return "QuotationVo [customerName=" + customerName + ", cusAddress="
				+ cusAddress + ", cusLinkman=" + cusLinkman + ", cusTelephone="
				+ cusTelephone + ", cusEmail=" + cusEmail + ", companyName="
				+ companyName + ", comAddress=" + comAddress
				+ ", salesManUserName=" + salesManUserName
				+ ", creatorUserName=" + creatorUserName + "]";
	}
	
}
