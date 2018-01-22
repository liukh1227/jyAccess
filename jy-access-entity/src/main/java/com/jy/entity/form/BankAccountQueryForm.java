package com.jy.entity.form;

import java.io.Serializable;

import com.jy.base.entity.base.form.PageQueryForm;


public class BankAccountQueryForm extends PageQueryForm implements Serializable {

	private static final long serialVersionUID = 1L;
	   private String companyId;

	    private String customerId;

	    private String bankName;

	    private String bankAccountNum;

	    private String bankAccountName;

	    private String status;

	    private Boolean isDefault;

		public String getCompanyId() {
			return companyId;
		}

		public void setCompanyId(String companyId) {
			this.companyId = companyId;
		}

		public String getCustomerId() {
			return customerId;
		}

		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}

		public String getBankName() {
			return bankName;
		}

		public void setBankName(String bankName) {
			this.bankName = bankName;
		}

		public String getBankAccountNum() {
			return bankAccountNum;
		}

		public void setBankAccountNum(String bankAccountNum) {
			this.bankAccountNum = bankAccountNum;
		}

		public String getBankAccountName() {
			return bankAccountName;
		}

		public void setBankAccountName(String bankAccountName) {
			this.bankAccountName = bankAccountName;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Boolean getIsDefault() {
			return isDefault;
		}

		public void setIsDefault(Boolean isDefault) {
			this.isDefault = isDefault;
		}
	     
}
