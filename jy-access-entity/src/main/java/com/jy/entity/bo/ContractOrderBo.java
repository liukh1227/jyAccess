package com.jy.entity.bo;

import java.util.List;

import com.jy.entity.po.DeliveryOrder;
import com.jy.entity.pojo.ContractDeviceModel4PdfPojo;
import com.jy.entity.vo.ContractDeviceModelVo;
import com.jy.entity.vo.ContractOrderVo;

public class ContractOrderBo extends ContractOrderVo {
	
	private static final long serialVersionUID = 1L;
	private String cusAddress;
	private String cusEmail;
	private String cusBankName;
	private String cusBankAccountNum;
	private String comAddress;
	private String comEmail;
	private String comTelephone;
	private String comBankName;
	private String comBankAccountNum;
	private List<ContractDeviceModelVo> contractDeviceModels;
	private List<DeliveryOrder> deliveryOrderOuts;
	
	public String getCusAddress() {
		return cusAddress;
	}
	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}
	public String getCusEmail() {
		return cusEmail;
	}
	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}
	public String getCusBankName() {
		return cusBankName;
	}
	public void setCusBankName(String cusBankName) {
		this.cusBankName = cusBankName;
	}
	public String getCusBankAccountNum() {
		return cusBankAccountNum;
	}
	public void setCusBankAccountNum(String cusBankAccountNum) {
		this.cusBankAccountNum = cusBankAccountNum;
	}
	public String getComAddress() {
		return comAddress;
	}
	public void setComAddress(String comAddress) {
		this.comAddress = comAddress;
	}
	public String getComEmail() {
		return comEmail;
	}
	public void setComEmail(String comEmail) {
		this.comEmail = comEmail;
	}
	public String getComBankName() {
		return comBankName;
	}
	public void setComBankName(String comBankName) {
		this.comBankName = comBankName;
	}
	public String getComBankAccountNum() {
		return comBankAccountNum;
	}
	public void setComBankAccountNum(String comBankAccountNum) {
		this.comBankAccountNum = comBankAccountNum;
	}
	
	
	public String getComTelephone() {
		return comTelephone;
	}
	public void setComTelephone(String comTelephone) {
		this.comTelephone = comTelephone;
	}
	public List<ContractDeviceModelVo> getContractDeviceModels() {
		return contractDeviceModels;
	}
	public void setContractDeviceModels(
			List<ContractDeviceModelVo> contractDeviceModels) {
		this.contractDeviceModels = contractDeviceModels;
	}
	public List<DeliveryOrder> getDeliveryOrderOuts() {
		return deliveryOrderOuts;
	}
	public void setDeliveryOrderOuts(List<DeliveryOrder> deliveryOrderOuts) {
		this.deliveryOrderOuts = deliveryOrderOuts;
	}
	@Override
	public String toString() {
		return "ContractOrderBo [cusAddress=" + cusAddress + ", cusEmail="
				+ cusEmail + ", cusBankName=" + cusBankName
				+ ", cusBankAccountNum=" + cusBankAccountNum + ", comAddress="
				+ comAddress + ", comEmail=" + comEmail + ", comTelephone="
				+ comTelephone + ", comBankName=" + comBankName
				+ ", comBankAccountNum=" + comBankAccountNum
				+ ", contractDeviceModels=" + contractDeviceModels
				+ ", deliveryOrderOuts=" + deliveryOrderOuts + "]";
	}


	
}
