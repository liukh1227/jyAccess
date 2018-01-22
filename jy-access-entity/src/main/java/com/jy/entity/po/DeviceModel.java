package com.jy.entity.po;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class DeviceModel implements Serializable {
    private String deviceModel_Id;
    
    private String deviceBrandId;
    
    private String deviceTypeId;
    
    private String  keyAttributeValueId;

    private String modelName;

    private String creator;

    private Date createTime;

    private String modelPicture;

    private Boolean isDisplay;

    private static final long serialVersionUID = 1L;

    public String getDeviceModel_Id() {
        return deviceModel_Id;
    }

    public void setDeviceModel_Id(String deviceModel_Id) {
        this.deviceModel_Id = deviceModel_Id == null ? null : deviceModel_Id.trim();
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

	public String getKeyAttributeValueId() {
		return keyAttributeValueId;
	}

	public void setKeyAttributeValueId(String keyAttributeValueId) {
		this.keyAttributeValueId = keyAttributeValueId;
	}

	public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName == null ? null : modelName.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }
    @JSONField(format="yyyy-MM-dd HH:mm:ss" ) 
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModelPicture() {
        return modelPicture;
    }

    public void setModelPicture(String modelPicture) {
        this.modelPicture = modelPicture == null ? null : modelPicture.trim();
    }

    public Boolean getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Boolean isDisplay) {
        this.isDisplay = isDisplay;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", deviceModel_Id=").append(deviceModel_Id);
        sb.append(", deviceBrandId=").append(deviceBrandId);
        sb.append(", deviceTypeId=").append(deviceTypeId);
        sb.append(", keyAttributeValueId=").append(keyAttributeValueId);
        sb.append(", modelName=").append(modelName);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", modelPicture=").append(modelPicture);
        sb.append(", isDisplay=").append(isDisplay);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}