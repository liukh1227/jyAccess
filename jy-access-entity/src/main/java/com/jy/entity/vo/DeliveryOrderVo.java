package com.jy.entity.vo;

import com.jy.entity.po.DeliveryOrder;

public class DeliveryOrderVo extends DeliveryOrder {
	private static final long serialVersionUID = 1L;
	private String operatorsUserName;
	private String creatorUserName;
	 private String contractOrderNum;
		public String getContractOrderNum() {
			return contractOrderNum;
		}
		public void setContractOrderNum(String contractOrderNum) {
			this.contractOrderNum = contractOrderNum;
		}
	public String getOperatorsUserName() {
		return operatorsUserName;
	}
	public void setOperatorsUserName(String operatorsUserName) {
		this.operatorsUserName = operatorsUserName;
	}
	public String getCreatorUserName() {
		return creatorUserName;
	}
	public void setCreatorUserName(String creatorUserName) {
		this.creatorUserName = creatorUserName;
	}
	@Override
	public String toString() {
		return "DeliveryOrderVo [operatorsUserName=" + operatorsUserName
				+ ", creatorUserName=" + creatorUserName + "]";
	}
	

}
