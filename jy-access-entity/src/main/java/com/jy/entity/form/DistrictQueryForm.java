package com.jy.entity.form;

import java.io.Serializable;

import com.jy.base.entity.base.form.PageQueryForm;


public class DistrictQueryForm extends PageQueryForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer pId;
    private Integer level;
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
    
    
	  
	    
}
