package com.jy.entity.pojo;

import com.jy.entity.vo.DeviceInMakesureOrderVo;

public class DeviceInMakesureOrder4PdfPojo extends DeviceInMakesureOrderVo{
	
	private static final long serialVersionUID = 1L;
	private String monthLeaseTerm;
	private String weekLeaseTerm;
	private String dayLeaseTerm;
	private String companyName;
	private String companyPhone;
	private String companyAddress;
	public String getMonthLeaseTerm() {
		return monthLeaseTerm;
	}
	public void setMonthLeaseTerm(String monthLeaseTerm) {
		this.monthLeaseTerm = monthLeaseTerm;
	}
	public String getWeekLeaseTerm() {
		return weekLeaseTerm;
	}
	public void setWeekLeaseTerm(String weekLeaseTerm) {
		this.weekLeaseTerm = weekLeaseTerm;
	}
	public String getDayLeaseTerm() {
		return dayLeaseTerm;
	}
	public void setDayLeaseTerm(String dayLeaseTerm) {
		this.dayLeaseTerm = dayLeaseTerm;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	@Override
	public String toString() {
		return "DeviceInMakesureOrder4PdfPojo [monthLeaseTerm="
				+ monthLeaseTerm + ", weekLeaseTerm=" + weekLeaseTerm
				+ ", dayLeaseTerm=" + dayLeaseTerm + ", companyName="
				+ companyName + ", companyPhone=" + companyPhone
				+ ", companyAddress=" + companyAddress + "]";
	}
	

}
