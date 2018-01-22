package com.jy.entity.form;

import java.io.Serializable;
import java.util.Date;

import com.jy.EntityConstant;
import com.jy.base.entity.base.form.PageQueryForm;

public class QuotationQueryForm extends PageQueryForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private String creator;
	private String lessorId;
	private String[]  lessorIdArray;
	private String salesMan;
	private Date startTime;
	private Date endTime;
	private String status;
	private String quotationId;
	private String customerName;
	private String quotationNum;

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getLessorId() {
		return lessorId;
	}

	public void setLessorId(String lessorId) {
		this.lessorId = lessorId;
	}

	public String[] getLessorIdArray() {
		if(lessorIdArray == null && getLessorId()!= null){
			String[] strLessorId = getLessorId().split(",");
			if(strLessorId!= null){
				return strLessorId;
			}
		}
		
		return lessorIdArray;
	}

	public void setLessorIdArray(String[] lessorIdArray) {
		this.lessorIdArray = lessorIdArray;
	}

	public String getSalesMan() {
		return salesMan;
	}

	public void setSalesMan(String salesMan) {
		this.salesMan = salesMan;
	}
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(String quotationId) {
		this.quotationId = quotationId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public boolean isQueryAll() {
		return getLessorId()!= null && getLessorId().trim().equals(EntityConstant.QUERY_ALL);
	}

	public String getQuotationNum() {
		return quotationNum;
	}

	public void setQuotationNum(String quotationNum) {
		this.quotationNum = quotationNum;
	}
	
	

}
