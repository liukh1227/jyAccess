package com.jy.entity.form;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.jy.EntityConstant;
import com.jy.base.entity.base.form.PageQueryForm;

public class DeliveryOrderQueryForm extends PageQueryForm implements
		Serializable {

	private static final long serialVersionUID = 1L;
	private String contractOrderId;
	private String deliveryType;
	private String status;
	private String[] statusArray; 

	public String getContractOrderId() {
		return contractOrderId;
	}

	public void setContractOrderId(String contractOrderId) {
		this.contractOrderId = contractOrderId;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String[] getStatusArray() {
		if(statusArray == null && getStatus()!= null){
			String[] strStatus= getStatus().split(",");
			if(strStatus!= null){
				return strStatus;
			}
		}
		
		return statusArray;
	}

	public void setStatusArray(String[] statusArray) {
		this.statusArray = statusArray;
	}

	@JSONField(serialize = false)
	public boolean isRightDeliveryType() {
		return getDeliveryType() != null
				&& (getDeliveryType().equals(
						EntityConstant.TM_DELIVERYORDER_DELIVERYTYPE_INPUT) || getDeliveryType()
						.equals(EntityConstant.TM_DELIVERYORDER_DELIVERYTYPE_OUTPUT));
	}
	@JSONField(serialize = false)
	public boolean isQueryAll() {
		return getStatus()!= null && getStatus().trim().equals(EntityConstant.QUERY_ALL);

	} 

}
