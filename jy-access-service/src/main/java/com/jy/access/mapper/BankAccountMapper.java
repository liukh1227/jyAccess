package com.jy.access.mapper;

import java.util.List;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.form.BankAccountQueryForm;
import com.jy.entity.po.BankAccount;

public interface BankAccountMapper extends BaseMapper{
	public List<BankAccount>getBankAccountPageList(BankAccountQueryForm form);
	public List<BankAccount>getAllBankAccountList(BankAccountQueryForm form);
}