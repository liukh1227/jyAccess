package com.jy.entity.form;

import java.io.Serializable;

import com.jy.base.entity.base.form.PageQueryForm;


public class DtKeyAttributeSpecificationQueryForm extends PageQueryForm implements Serializable {

	private static final long serialVersionUID = 1L;
	 private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	 
	 
}