package com.jy.entity.vo;

import com.jy.entity.po.UserAccount;

public class UserAccountVo extends UserAccount {
	private static final long serialVersionUID = 1L;
	private String companyName;
	

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
