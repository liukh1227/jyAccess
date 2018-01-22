package com.jy.entity.vo;

import com.jy.entity.po.DmAttributevalue;

public class DmAttributevalueVo extends DmAttributevalue {
	private static final long serialVersionUID = 1L;

	private String dSpecificationName;

    private String unit;

	public String getdSpecificationName() {
		return dSpecificationName;
	}

	public void setdSpecificationName(String dSpecificationName) {
		this.dSpecificationName = dSpecificationName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "DmAttributevalueVo [dSpecificationName=" + dSpecificationName
				+ ", unit=" + unit + "]";
	}
    
}
