package com.jy.entity.form;

import java.io.Serializable;
import java.util.Date;

import com.jy.EntityConstant;
import com.jy.base.entity.base.form.PageQueryForm;


public class ContractOrderQueryForm extends PageQueryForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private String creator;
	private String lessorId;
    private String[] lessorIdArray;
	private String salesMan;
	private Date startTime;
	private Date endTime;
	private String status;
	private String sourceType;
	private String payMethod;
	private String projectId;
	private String projectName;
	private String contractOrderId;
	private String customerId;
	private String customerName;
    private String sourceId;
    private String contractOrderNum;
	
   
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
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
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
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getContractOrderId() {
		return contractOrderId;
	}
	public void setContractOrderId(String contractOrderId) {
		this.contractOrderId = contractOrderId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public boolean isQueryAll() {
		return getLessorId()!= null && getLessorId().trim().equals(EntityConstant.QUERY_ALL);

	}
	public String getContractOrderNum() {
		return contractOrderNum;
	}
	public void setContractOrderNum(String contractOrderNum) {
		this.contractOrderNum = contractOrderNum;
	} 
	
}
