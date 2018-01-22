package com.jy.entity.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.jy.EntityConstant;
import com.jy.entity.bo.QuotationBo;

public class ContractOrder implements Serializable {
    private String contractOrder_Id;
    
    private String contractOrderNum;

    private String sourceId;

    private String projectId;

    private String projectName;

    private String jobNature;

    private String workSite;

    private BigDecimal totalPrice;

    private String remark;

    private String salesMan;

    private Date createTime;

    private String creator;

    private String status;

    private String sourceType;

    private String lessorId;
    
    private String lesBankAccountId;

    private String customerId;
    
    private String cusBankAccountId;

    private Boolean includeInvoice;

    private Boolean includeShippingFee;

    private BigDecimal shippingFee;

    private Boolean includeJiShou;

    private Boolean includeFue;

    private Boolean includeInsurance;

    private BigDecimal otherExpense;

    private String otherExpenseComment;

    private String payMethod;

    private BigDecimal prepayFee;

    private static final long serialVersionUID = 1L;

    public String getContractOrder_Id() {
        return contractOrder_Id;
    }

    public void setContractOrder_Id(String contractOrder_Id) {
        this.contractOrder_Id = contractOrder_Id == null ? null : contractOrder_Id.trim();
    }
    
   
    public String getContractOrderNum() {
		return contractOrderNum;
	}

	public void setContractOrderNum(String contractOrderNum) {
		  this.contractOrderNum = contractOrderNum == null ? null : contractOrderNum.trim();
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		 this.sourceId = sourceId == null ? null : sourceId.trim();
	}

	public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getJobNature() {
        return jobNature;
    }

    public void setJobNature(String jobNature) {
        this.jobNature = jobNature == null ? null : jobNature.trim();
    }

    public String getWorkSite() {
        return workSite;
    }

    public void setWorkSite(String workSite) {
        this.workSite = workSite == null ? null : workSite.trim();
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getSalesMan() {
        return salesMan;
    }

    public void setSalesMan(String salesMan) {
        this.salesMan = salesMan == null ? null : salesMan.trim();
    }
    @JSONField(format="yyyy-MM-dd HH:mm:ss" ) 
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType == null ? null : sourceType.trim();
    }

    public String getLessorId() {
        return lessorId;
    }

    public void setLessorId(String lessorId) {
        this.lessorId = lessorId == null ? null : lessorId.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }
    
    public String getLesBankAccountId() {
		return lesBankAccountId;
	}

	public void setLesBankAccountId(String lesBankAccountId) {
		this.lesBankAccountId = lesBankAccountId;
	}

	public String getCusBankAccountId() {
		return cusBankAccountId;
	}

	public void setCusBankAccountId(String cusBankAccountId) {
		this.cusBankAccountId = cusBankAccountId;
	}

	public Boolean getIncludeInvoice() {
        return includeInvoice;
    }

    public void setIncludeInvoice(Boolean includeInvoice) {
        this.includeInvoice = includeInvoice;
    }

    public Boolean getIncludeShippingFee() {
        return includeShippingFee;
    }

    public void setIncludeShippingFee(Boolean includeShippingFee) {
        this.includeShippingFee = includeShippingFee;
    }

    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    public Boolean getIncludeJiShou() {
		return includeJiShou;
	}

	public void setIncludeJiShou(Boolean includeJiShou) {
		this.includeJiShou = includeJiShou;
	}

	public Boolean getIncludeFue() {
		return includeFue;
	}

	public void setIncludeFue(Boolean includeFue) {
		this.includeFue = includeFue;
	}

	public Boolean getIncludeInsurance() {
		return includeInsurance;
	}

	public void setIncludeInsurance(Boolean includeInsurance) {
		this.includeInsurance = includeInsurance;
	}

	public BigDecimal getOtherExpense() {
        return otherExpense;
    }

    public void setOtherExpense(BigDecimal otherExpense) {
        this.otherExpense = otherExpense;
    }

    public String getOtherExpenseComment() {
        return otherExpenseComment;
    }

    public void setOtherExpenseComment(String otherExpenseComment) {
        this.otherExpenseComment = otherExpenseComment == null ? null : otherExpenseComment.trim();
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod == null ? null : payMethod.trim();
    }

    public BigDecimal getPrepayFee() {
        return prepayFee;
    }

    public void setPrepayFee(BigDecimal prepayFee) {
        this.prepayFee = prepayFee;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", contractOrder_Id=").append(contractOrder_Id);
        sb.append(", contractOrderNum=").append(contractOrderNum);
        sb.append(", sourceId=").append(sourceId);
        sb.append(", projectId=").append(projectId);
        sb.append(", projectName=").append(projectName);
        sb.append(", jobNature=").append(jobNature);
        sb.append(", workSite=").append(workSite);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", remark=").append(remark);
        sb.append(", salesMan=").append(salesMan);
        sb.append(", createTime=").append(createTime);
        sb.append(", creator=").append(creator);
        sb.append(", status=").append(status);
        sb.append(", sourceType=").append(sourceType);
        sb.append(", lessorId=").append(lessorId);
        sb.append(", customerId=").append(customerId);
        sb.append(", includeInvoice=").append(includeInvoice);
        sb.append(", includeShippingFee=").append(includeShippingFee);
        sb.append(", shippingFee=").append(shippingFee);
        sb.append(", includeJiShou=").append(includeJiShou);
        sb.append(", includeFue=").append(includeFue);
        sb.append(", includeInsurance=").append(includeInsurance);
        sb.append(", otherExpense=").append(otherExpense);
        sb.append(", otherExpenseComment=").append(otherExpenseComment);
        sb.append(", payMethod=").append(payMethod);
        sb.append(", prepayFee=").append(prepayFee);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
    
    @JSONField(serialize=false) 
    public boolean  isPayMethodYuFu(){
    	return getPayMethod().equals(EntityConstant.TM_PAYMETHOD_YUFU);
    	
    }
    @JSONField(serialize=false) 
    public boolean  isPayMethodYueJie(){
    	return getPayMethod().equals(EntityConstant.TM_PAYMETHOD_MONTH);
    	
    }
    
    @JSONField(serialize=false)
    public void copyFromQuotation(QuotationBo other,String creator){
    	setSourceId(other.getQuotation_Id());
    	setSourceType(EntityConstant.TM_CONTRACTORDER_SOURCE_QUOTAION);
    	setCreateTime(new Date());
    	setSalesMan(creator);
    	setCreator(creator);
    	setStatus(EntityConstant.TM_CONTRACTORDER_STATUS_NOTMAKESURE);
    	setLessorId(other.getLessorId());
    	setCustomerId(other.getCustomerId());
    	setIncludeFue(other.getIncludeFue());
    	setIncludeInsurance(other.getIncludeInsurance());
    	setIncludeInvoice(other.getIncludeInvoice());
    	setIncludeJiShou(other.getIncludeJiShou());
    	setIncludeShippingFee(other.getIncludeShippingFee());
    	setOtherExpense(other.getOtherExpense());
    	setOtherExpenseComment(other.getOtherExpenseComment());
    	setPayMethod(other.getPayMethod());
    	setPrepayFee(other.getPrepayFee());
    	setRemark(other.getRemark());
    	
    	
    	
    }
}