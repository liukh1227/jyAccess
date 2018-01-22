package com.jy.entity.form;

import java.io.Serializable;
import com.jy.base.entity.base.form.PageQueryForm;


public class DeviceModelQueryForm extends PageQueryForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private String deviceBrandId;
    
    private String deviceTypeId;
    
    private String modelName;

    private Boolean isDisplay;
    
    private String deviceModeld;

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

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public Boolean getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(Boolean isDisplay) {
		this.isDisplay = isDisplay;
	}

	public String getDeviceModeld() {
		return deviceModeld;
	}

	public void setDeviceModeld(String deviceModeld) {
		this.deviceModeld = deviceModeld;
	}
        
}
