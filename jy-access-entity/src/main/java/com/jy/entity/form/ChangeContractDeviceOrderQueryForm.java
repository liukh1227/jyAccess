package com.jy.entity.form;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.jy.EntityConstant;
import com.jy.base.entity.base.form.PageQueryForm;

public class ChangeContractDeviceOrderQueryForm extends PageQueryForm implements
		Serializable {

	private static final long serialVersionUID = 1L;
	private String contractDeviceId;

	private String contractOrderId;

	private String dInMakeSureId;

	private String changeType;

	private String status;

	private String creator;

	public String getContractDeviceId() {
		return contractDeviceId;
	}

	public void setContractDeviceId(String contractDeviceId) {
		this.contractDeviceId = contractDeviceId;
	}

	public String getContractOrderId() {
		return contractOrderId;
	}

	public void setContractOrderId(String contractOrderId) {
		this.contractOrderId = contractOrderId;
	}

	public String getdInMakeSureId() {
		return dInMakeSureId;
	}

	public void setdInMakeSureId(String dInMakeSureId) {
		this.dInMakeSureId = dInMakeSureId;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@JSONField(serialize = false)
	public boolean isRightChangeType() {
		return getChangeType().equals(
				EntityConstant.TM_CHANGORDER_CHANGETYPE_TUICHANG)
				|| getChangeType().equals(
						EntityConstant.TM_CHANGORDER_CHANGETYPE_YANQI);
	}

}
