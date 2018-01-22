package com.jy.entity.po;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class DeviceWorktrace extends DeviceWorktraceMonthDate {
	private String dWorkTrace_Id;

	private String contractOrderId;

	private String dInMakeSureId;

	private String dOutMakeSureId;

	private String deviceId;

	private String deviceModelId;

	private Integer modelAvaliableDeviceCount;

	private String deviceTypeId;

	private Integer typeAvaliableDeviceCount;

	private String companyId;

	private Integer avaliableDeviceCount;
	
	private Date dEnterTime;

	private Date dExitTime;

	private String status;

	private static final long serialVersionUID = 1L;

	public String getdWorkTrace_Id() {
		return dWorkTrace_Id;
	}

	public void setdWorkTrace_Id(String dWorkTrace_Id) {
		this.dWorkTrace_Id = dWorkTrace_Id == null ? null : dWorkTrace_Id
				.trim();
	}

	public String getdInMakeSureId() {
		return dInMakeSureId;
	}

	public void setdInMakeSureId(String dInMakeSureId) {
		this.dInMakeSureId = dInMakeSureId == null ? null : dInMakeSureId
				.trim();
	}

	public String getdOutMakeSureId() {
		return dOutMakeSureId;
	}

	public void setdOutMakeSureId(String dOutMakeSureId) {
		this.dOutMakeSureId = dOutMakeSureId == null ? null : dOutMakeSureId
				.trim();
	}

	public Integer getAvaliableDeviceCount() {
		return avaliableDeviceCount;
	}

	public void setAvaliableDeviceCount(Integer avaliableDeviceCount) {
		this.avaliableDeviceCount = avaliableDeviceCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getContractOrderId() {
		return contractOrderId;
	}

	public void setContractOrderId(String contractOrderId) {
		this.contractOrderId = contractOrderId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceModelId() {
		return deviceModelId;
	}

	public void setDeviceModelId(String deviceModelId) {
		this.deviceModelId = deviceModelId;
	}

	public Integer getModelAvaliableDeviceCount() {
		return modelAvaliableDeviceCount;
	}

	public void setModelAvaliableDeviceCount(Integer modelAvaliableDeviceCount) {
		this.modelAvaliableDeviceCount = modelAvaliableDeviceCount;
	}

	public String getDeviceTypeId() {
		return deviceTypeId;
	}

	public void setDeviceTypeId(String deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}

	public Integer getTypeAvaliableDeviceCount() {
		return typeAvaliableDeviceCount;
	}

	public void setTypeAvaliableDeviceCount(Integer typeAvaliableDeviceCount) {
		this.typeAvaliableDeviceCount = typeAvaliableDeviceCount;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	
	@JSONField(format = "yyyy-MM-dd")
	public Date getdEnterTime() {
		return dEnterTime;
	}

	public void setdEnterTime(Date dEnterTime) {
		this.dEnterTime = dEnterTime;
	}
	@JSONField(format = "yyyy-MM-dd")
	public Date getdExitTime() {
		return dExitTime;
	}

	public void setdExitTime(Date dExitTime) {
		this.dExitTime = dExitTime;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", dWorkTrace_Id=").append(dWorkTrace_Id);
		sb.append(", contractOrderId=").append(contractOrderId);
		sb.append(", dInMakeSureId=").append(dInMakeSureId);
		sb.append(", dOutMakeSureId=").append(dOutMakeSureId);
		sb.append(", deviceId=").append(deviceId);
		sb.append(", deviceModelId=").append(deviceModelId);
		sb.append(", modelAvaliableDeviceCount=").append(modelAvaliableDeviceCount);
		sb.append(", deviceTypeId=").append(deviceTypeId);
		sb.append(", typeAvaliableDeviceCount=").append(typeAvaliableDeviceCount);
		sb.append(", companyId=").append(companyId);
		sb.append(", avaliableDeviceCount=").append(avaliableDeviceCount);
		sb.append(", dEnterTime=").append(dEnterTime);
		sb.append(", dExitTime=").append(dExitTime);
		sb.append(", status=").append(status);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	
	}
	
	@JSONField(serialize = false)
	public void copyFrom(DeviceOutMakesureOrder other){
		setContractOrderId(other.getContractOrderId());
		setdInMakeSureId(other.getdInMakeSureId());
		setdOutMakeSureId(other.getdOutMakeSure_Id());
		setDeviceId(other.getDeviceId());
		setdExitTime(other.getExitTime());
	}
	
	public void copyFromOther(DeviceWorktrace other,DeviceWorktraceMonthDate dateOther){
		
		// from DeviceWorktrace
		setContractOrderId(other.getContractOrderId());
		setdInMakeSureId(other.getdInMakeSureId());
		setdOutMakeSureId(other.getdOutMakeSureId());
		setDeviceId(other.getDeviceId());
		setDeviceModelId(other.getDeviceModelId());
		setModelAvaliableDeviceCount(other.getModelAvaliableDeviceCount());
		setDeviceTypeId(other.getDeviceTypeId());
		setTypeAvaliableDeviceCount(other.getTypeAvaliableDeviceCount());
		setCompanyId(other.getCompanyId());
		setAvaliableDeviceCount(other.getAvaliableDeviceCount());
		setdEnterTime(other.getdEnterTime());
		setdExitTime(other.getdExitTime());
		setStatus(other.getStatus());
		// from DeviceWorktraceMonthDate
		setWorkDay(dateOther.getWorkDay());
		setInMonth(dateOther.getInMonth());
		setInYear(dateOther.getInYear());
		setMonthStartDate(dateOther.getMonthStartDate());
		setMonthEndDate(dateOther.getMonthEndDate());
		
		
		
	}
	

}