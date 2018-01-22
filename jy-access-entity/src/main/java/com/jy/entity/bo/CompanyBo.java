package com.jy.entity.bo;

import java.util.List;

import com.jy.entity.po.BankAccount;
import com.jy.entity.po.Company;
import com.jy.entity.po.Customer;

public class CompanyBo extends Company {
	private static final long serialVersionUID = 1L;
	private List<BankAccount> bankAccountLists;
	private List<Customer> customerLists;

	public List<BankAccount> getBankAccountLists() {
		return bankAccountLists;
	}

	public void setBankAccountLists(List<BankAccount> bankAccountLists) {
		this.bankAccountLists = bankAccountLists;
	}

	public List<Customer> getCustomerLists() {
		return customerLists;
	}

	public void setCustomerLists(List<Customer> customerLists) {
		this.customerLists = customerLists;
	}

	@Override
	public String toString() {
		return "CompanyBo [bankAccountLists=" + bankAccountLists
				+ ", customerLists=" + customerLists + "]";
	}
	
	
}
