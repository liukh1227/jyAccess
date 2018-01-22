package com.jy.entity.form;

import java.io.Serializable;

import com.jy.EntityConstant;
import com.jy.base.entity.base.form.PageQueryForm;

public class DeviceQueryForm extends PageQueryForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private String deviceModelId;
	private String deviceNum;
	private String sequenceNum;
	private String plateNumber;
	private String manufactureYear;
	private Boolean isImported;
	private Boolean isRealDevice;
	private String gPSCode;
	private String status;
	private String deviceBrandId;
	private String deviceTypeId;
	private String companyId;
	private String[] companyIdArray;
	private String contractOrderId;

	public String getDeviceModelId() {
		return deviceModelId;
	}

	public void setDeviceModelId(String deviceModelId) {
		this.deviceModelId = deviceModelId;
	}

	public String getDeviceNum() {
		return deviceNum;
	}

	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}

	public String getSequenceNum() {
		return sequenceNum;
	}

	public void setSequenceNum(String sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getManufactureYear() {
		return manufactureYear;
	}

	public void setManufactureYear(String manufactureYear) {
		this.manufactureYear = manufactureYear;
	}

	public Boolean getIsImported() {
		return isImported;
	}

	public void setIsImported(Boolean isImported) {
		this.isImported = isImported;
	}

	public Boolean getIsRealDevice() {
		return isRealDevice;
	}

	public void setIsRealDevice(Boolean isRealDevice) {
		this.isRealDevice = isRealDevice;
	}

	public String getgPSCode() {
		return gPSCode;
	}

	public void setgPSCode(String gPSCode) {
		this.gPSCode = gPSCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeviceBrandId() {
		return deviceBrandId;
	}

	public void setDeviceBrandId(String deviceBrandId) {
		this.deviceBrandId = deviceBrandId;
	}

	public String getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(String deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String[] getCompanyIdArray() {
		if (companyIdArray == null && getCompanyId() != null) {
			String[] strCompanyId = getCompanyId().split(",");
			if (strCompanyId != null) {
				return strCompanyId;
			}
		}

		return companyIdArray;
	}

	public void setCompanyIdArray(String[] companyIdArray) {
		this.companyIdArray = companyIdArray;
	}

	public boolean isQueryAll() {
		return getCompanyId()!= null && getCompanyId().trim().equals(EntityConstant.QUERY_ALL);
	}

	public String getContractOrderId() {
		return contractOrderId;
	}

	public void setContractOrderId(String contractOrderId) {
		this.contractOrderId = contractOrderId;
	}
	
}
