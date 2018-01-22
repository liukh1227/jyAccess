package com.jy.entity.pojo;

import java.io.Serializable;

import com.jy.entity.po.DeviceType;

public class DeviceTypePojo extends DeviceType implements Serializable{
	
	private static final long serialVersionUID = 1L;
    
    private String parentTypeName;

	public String getParentTypeName() {
		return parentTypeName;
	}

	public void setParentTypeName(String parentTypeName) {
		this.parentTypeName = parentTypeName;
	}
    
}

