package com.jy.entity.vo;

import com.jy.entity.po.DtKeyAttributevalue;

public class DtKeyAttributevalueVo extends DtKeyAttributevalue {

private static final long serialVersionUID = 1L;

private String dTKeyAttributeSpecName;

private String unit;

public String getdTKeyAttributeSpecName() {
	return dTKeyAttributeSpecName;
}

public void setdTKeyAttributeSpecName(String dTKeyAttributeSpecName) {
	this.dTKeyAttributeSpecName = dTKeyAttributeSpecName;
}

public String getUnit() {
	return unit;
}

public void setUnit(String unit) {
	this.unit = unit;
}

@Override
public String toString() {
	return "DtKeyAttributevalueVo [dTKeyAttributeSpecName="
			+ dTKeyAttributeSpecName + ", unit=" + unit + "]";
}



}
