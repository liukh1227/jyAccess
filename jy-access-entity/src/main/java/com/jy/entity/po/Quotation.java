package com.jy.entity.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.jy.EntityConstant;

public class Quotation implements Serializable {
    private String quotation_Id;
    
    private String quotationNum;

    private String busFreeItemId;

    private String remark;

    private String creator;

    private String salesMan;

    private Date createTime;

    private String status;

    private String lessorId;
    
    private String lesLinkman;
    
    private String lesTelphone;
    
    private String lesEmail;

    private String customerId;

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

    public String getQuotation_Id() {
        return quotation_Id;
    }

    public void setQuotation_Id(String quotation_Id) {
        this.quotation_Id = quotation_Id == null ? null : quotation_Id.trim();
    }

    public String getQuotationNum() {
		return quotationNum;
	}

	public void setQuotationNum(String quotationNum) {
		this.quotationNum = quotationNum == null ? null : quotationNum.trim();
	}

	public String getBusFreeItemId() {
        return busFreeItemId;
    }

    public void setBusFreeItemId(String busFreeItemId) {
        this.busFreeItemId = busFreeItemId == null ? null : busFreeItemId.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
    

    public String getLesLinkman() {
		return lesLinkman;
	}

	public void setLesLinkman(String lesLinkman) {
		this.lesLinkman = lesLinkman;
	}

	public String getLesTelphone() {
		return lesTelphone;
	}

	public void setLesTelphone(String lesTelphone) {
		this.lesTelphone = lesTelphone;
	}

	public String getLesEmail() {
		return lesEmail;
	}

	public void setLesEmail(String lesEmail) {
		this.lesEmail = lesEmail;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", quotation_Id=").append(quotation_Id);
        sb.append(", quotationNum=").append(quotationNum);
        sb.append(", busFreeItemId=").append(busFreeItemId);
        sb.append(", remark=").append(remark);
        sb.append(", creator=").append(creator);
        sb.append(", salesMan=").append(salesMan);
        sb.append(", createTime=").append(createTime);
        sb.append(", status=").append(status);
        sb.append(", lessorId=").append(lessorId);
        sb.append(", lesLinkman=").append(lesLinkman);
        sb.append(", lesTelphone=").append(lesTelphone);
        sb.append(", lesEmail=").append(lesEmail);
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
    public boolean  isRightPayMethod(){
    	return getPayMethod().equals(EntityConstant.TM_PAYMETHOD_YUFU) || getPayMethod().equals(EntityConstant.TM_PAYMETHOD_MONTH);
    	
    }
}