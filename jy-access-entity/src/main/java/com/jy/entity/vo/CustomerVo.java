package com.jy.entity.vo;

import com.jy.entity.po.Customer;

public class CustomerVo extends Customer {
	
	private static final long serialVersionUID = 1L;
	private String companyName;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "CustomerVo [companyName=" + companyName + "]";
	}
	

}
