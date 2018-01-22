package com.jy.entity.form;

import java.io.Serializable;

import com.jy.base.entity.base.form.PageQueryForm;


public class BillQueryForm extends PageQueryForm implements Serializable {

	private static final long serialVersionUID = 1L;
	   private Boolean isDisplay;

	   private String typeName;

	    private String parentId;

	    private String parid;

	    private Boolean isChild;

		public Boolean getIsDisplay() {
			return isDisplay;
		}

		public void setIsDisplay(Boolean isDisplay) {
			this.isDisplay = isDisplay;
		}

		public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		public String getParentId() {
			return parentId;
		}

		public void setParentId(String parentId) {
			this.parentId = parentId;
		}

		public String getParid() {
			return parid;
		}

		public void setParid(String parid) {
			this.parid = parid;
		}

		public Boolean getIsChild() {
			return isChild;
		}

		public void setIsChild(Boolean isChild) {
			this.isChild = isChild;
		}
	    
	    
}
