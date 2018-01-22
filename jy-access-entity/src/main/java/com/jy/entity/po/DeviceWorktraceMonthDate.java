package com.jy.entity.po;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class DeviceWorktraceMonthDate  implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer workDay;

	private Integer inMonth;

	private Integer inYear;

	private Date monthStartDate;

	private Date monthEndDate;

	public Integer getWorkDay() {
		return workDay;
	}

	public void setWorkDay(Integer workDay) {
		this.workDay = workDay;
	}

	public Integer getInMonth() {
		return inMonth;
	}

	public void setInMonth(Integer inMonth) {
		this.inMonth = inMonth;
	}

	public Integer getInYear() {
		return inYear;
	}

	public void setInYear(Integer inYear) {
		this.inYear = inYear;
	}
	@JSONField(format = "yyyy-MM-dd")
	public Date getMonthStartDate() {
		return monthStartDate;
	}

	public void setMonthStartDate(Date monthStartDate) {
		this.monthStartDate = monthStartDate;
	}
	
	@JSONField(format = "yyyy-MM-dd")
	public Date getMonthEndDate() {
		return monthEndDate;
	}

	public void setMonthEndDate(Date monthEndDate) {
		this.monthEndDate = monthEndDate;
	}

	@Override
	public String toString() {
		return "DeviceWorktraceMonthDate [workDay=" + workDay + ", inMonth="
				+ inMonth + ", inYear=" + inYear + ", monthStartDate="
				+ monthStartDate + ", monthEndDate=" + monthEndDate + "]";
	}
	
	
	

}
