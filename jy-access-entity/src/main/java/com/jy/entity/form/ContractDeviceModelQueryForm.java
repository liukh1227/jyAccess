package com.jy.entity.form;

import java.io.Serializable;

import com.jy.base.entity.base.form.PageQueryForm;

public class ContractDeviceModelQueryForm extends PageQueryForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private String contractOrderId;

	public String getContractOrderId() {
		return contractOrderId;
	}

	public void setContractOrderId(String contractOrderId) {
		this.contractOrderId = contractOrderId;
	}

}
