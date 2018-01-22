package com.jy.entity.bo;

import java.util.List;

import com.jy.entity.vo.QuotationDeviceVo;
import com.jy.entity.vo.QuotationVo;

public class QuotationBo extends QuotationVo {
	
	private static final long serialVersionUID = 1L;
	private List<QuotationDeviceVo> quotationDevices;

	public List<QuotationDeviceVo> getQuotationDevices() {
		return quotationDevices;
	}

	public void setQuotationDevices(List<QuotationDeviceVo> quotationDevices) {
		this.quotationDevices = quotationDevices;
	}

	@Override
	public String toString() {
		return "QuotationBo [quotationDevices=" + quotationDevices + "]";
	}
	

}
