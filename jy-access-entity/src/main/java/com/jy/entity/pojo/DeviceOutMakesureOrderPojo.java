package com.jy.entity.pojo;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.jy.entity.po.DeviceOutMakesureOrder;

public class DeviceOutMakesureOrderPojo extends DeviceOutMakesureOrder {
	private static final long serialVersionUID = 1L;
	private List<DeviceOutMakesurePojo> deviceIdList;
	public List<DeviceOutMakesurePojo> getDeviceIdList() {
		return deviceIdList;
	}
	public void setDeviceIdList(List<DeviceOutMakesurePojo> deviceIdList) {
		this.deviceIdList = deviceIdList;
	}
	
	@JSONField(serialize=false) 
	public void copyFrom(DeviceOutMakesureOrderPojo other){
		setContractOrderId(other.getContractOrderId());
		setdInMakeSureId(other.getdInMakeSureId());
		setCustomerId(other.getCustomerId());
		setCustomerName(other.getCustomerName());
		setLeasePhone(other.getLeasePhone());
		setBillingType(other.getBillingType());
		setLeaseTerm(other.getLeaseTerm());
		setExitTime(other.getExitTime());
		setWorkSite(other.getWorkSite());
		setDeviceId(other.getDeviceId());
		setOperators(other.getOperators());
		setCreator(other.getCreator());
		setLessorId(other.getLessorId());
		setCreateTime(other.getCreateTime());
		setRemark(other.getRemark());
		setRepairPhone(other.getRepairPhone());
		setStatus(other.getStatus());
		
	}

}
