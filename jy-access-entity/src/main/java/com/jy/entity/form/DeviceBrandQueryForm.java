package com.jy.entity.form;

import java.io.Serializable;

import com.jy.base.entity.base.form.PageQueryForm;


public class DeviceBrandQueryForm extends PageQueryForm implements Serializable {

	private static final long serialVersionUID = 1L;
	   private Boolean isDisplay;

	    private String brandName;

		public Boolean getIsDisplay() {
			return isDisplay;
		}

		public void setIsDisplay(Boolean isDisplay) {
			this.isDisplay = isDisplay;
		}

		public String getBrandName() {
			return brandName;
		}

		public void setBrandName(String brandName) {
			this.brandName = brandName;
		}
	    

}
