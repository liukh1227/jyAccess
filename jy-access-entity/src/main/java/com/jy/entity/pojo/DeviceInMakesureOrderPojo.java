package com.jy.entity.pojo;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.jy.entity.po.DeviceInMakesureOrder;

public class DeviceInMakesureOrderPojo extends DeviceInMakesureOrder {
	private static final long serialVersionUID = 1L;
	private List<DeviceMakesurePojo> deviceIdList;
	public List<DeviceMakesurePojo> getDeviceIdList() {
		return deviceIdList;
	}
	public void setDeviceIdList(List<DeviceMakesurePojo> deviceIdList) {
		this.deviceIdList = deviceIdList;
	}
	@JSONField(serialize=false) 
	public void copyFrom(DeviceInMakesureOrderPojo other){
		setContractOrderId(other.getContractOrderId());
		setCustomerId(other.getCustomerId());
		setCustomerName(other.getCustomerName());
		setLessorId(other.getLessorId());
		setLeasePhone(other.getLeasePhone());
		setBillingType(other.getBillingType());
		setLeaseTerm(other.getLeaseTerm());
		setEnterTime(other.getEnterTime());
		setWorkSite(other.getWorkSite());
		setDeviceId(other.getDeviceId());
		setOperators(other.getOperators());
		setCreator(other.getCreator());
		setCreateTime(other.getCreateTime());
		setRemark(other.getRemark());
		setRepairPhone(other.getRepairPhone());
		setStatus(other.getStatus());
		
	}
}
