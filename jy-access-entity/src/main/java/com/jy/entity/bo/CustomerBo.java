package com.jy.entity.bo;

import java.util.List;

import com.jy.entity.po.BankAccount;
import com.jy.entity.vo.CustomerVo;
/**
 * 
 * @author liukh
 * @date 2017-7-11 下午6:36:11
 */
public class CustomerBo extends CustomerVo {
	
	private static final long serialVersionUID = 1L;
	private List<BankAccount> bankAccountLists;

	public List<BankAccount> getBankAccountLists() {
		return bankAccountLists;
	}

	public void setBankAccountLists(List<BankAccount> bankAccountLists) {
		this.bankAccountLists = bankAccountLists;
	}

	@Override
	public String toString() {
		return "CustomerBo [bankAccountLists=" + bankAccountLists + "]";
	}
	
}
