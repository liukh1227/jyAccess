package com.jy.entity.po;

import java.io.Serializable;

public class DtypeSpecification implements Serializable {
	private String dTSpec_Id;
	
    private String deviceTypeId;

    private String dSpecificationId;

    private static final long serialVersionUID = 1L;
    

    public String getdTSpec_Id() {
		return dTSpec_Id;
	}

	public void setdTSpec_Id(String dTSpec_Id) {
		this.dTSpec_Id = dTSpec_Id;
	}

	public String getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId == null ? null : deviceTypeId.trim();
    }

    public String getdSpecificationId() {
        return dSpecificationId;
    }

    public void setdSpecificationId(String dSpecificationId) {
        this.dSpecificationId = dSpecificationId == null ? null : dSpecificationId.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", deviceTypeId=").append(deviceTypeId);
        sb.append(", dSpecificationId=").append(dSpecificationId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}