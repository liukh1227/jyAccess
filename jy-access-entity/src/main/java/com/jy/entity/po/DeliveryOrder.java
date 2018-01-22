package com.jy.entity.po;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.jy.EntityConstant;

public class DeliveryOrder implements Serializable {
	private String deliveryOrder_Id;

	private String contractOrderId;

	private String consignorId;

	private String consignorName;

	private String consignorAddress;

	private String consignorPhone;

	private String buyerId;

	private String buyerName;

	private String buyerAddress;

	private String buyerPhone;

	private String superintendent;

	private String superintendentPhone;

	private String operators;

	private String deliveryType;

	private Date sentTime;
	
	private Date receiveTime;

	private Date createTime;

	private String creator;

	private String status;
	
	private static final long serialVersionUID = 1L;

	public String getDeliveryOrder_Id() {
		return deliveryOrder_Id;
	}

	public void setDeliveryOrder_Id(String deliveryOrder_Id) {
		this.deliveryOrder_Id = deliveryOrder_Id == null ? null
				: deliveryOrder_Id.trim();
	}

	public String getContractOrderId() {
		return contractOrderId;
	}

	public void setContractOrderId(String contractOrderId) {
		this.contractOrderId = contractOrderId == null ? null : contractOrderId
				.trim();
	}

	public String getConsignorId() {
		return consignorId;
	}

	public void setConsignorId(String consignorId) {
		this.consignorId = consignorId == null ? null : consignorId.trim();
	}

	public String getConsignorName() {
		return consignorName;
	}

	public void setConsignorName(String consignorName) {
		this.consignorName = consignorName == null ? null : consignorName
				.trim();
	}

	public String getConsignorAddress() {
		return consignorAddress;
	}

	public void setConsignorAddress(String consignorAddress) {
		this.consignorAddress = consignorAddress == null ? null
				: consignorAddress.trim();
	}

	public String getConsignorPhone() {
		return consignorPhone;
	}

	public void setConsignorPhone(String consignorPhone) {
		this.consignorPhone = consignorPhone == null ? null : consignorPhone
				.trim();
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId == null ? null : buyerId.trim();
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName == null ? null : buyerName.trim();
	}

	public String getBuyerAddress() {
		return buyerAddress;
	}

	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress == null ? null : buyerAddress.trim();
	}

	public String getBuyerPhone() {
		return buyerPhone;
	}

	public void setBuyerPhone(String buyerPhone) {
		this.buyerPhone = buyerPhone == null ? null : buyerPhone.trim();
	}

	public String getSuperintendent() {
		return superintendent;
	}

	public void setSuperintendent(String superintendent) {
		this.superintendent = superintendent == null ? null : superintendent
				.trim();
	}

	public String getSuperintendentPhone() {
		return superintendentPhone;
	}

	public void setSuperintendentPhone(String superintendentPhone) {
		this.superintendentPhone = superintendentPhone == null ? null
				: superintendentPhone.trim();
	}

	public String getOperators() {
		return operators;
	}

	public void setOperators(String operators) {
		this.operators = operators == null ? null : operators.trim();
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType == null ? null : deliveryType.trim();
	}

	@JSONField(format = "yyyy-MM-dd")
	public Date getSentTime() {
		return sentTime;
	}

	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}
	
	
	@JSONField(format = "yyyy-MM-dd")
	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
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
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", deliveryOrder_Id=").append(deliveryOrder_Id);
		sb.append(", contractOrderId=").append(contractOrderId);
		sb.append(", consignorId=").append(consignorId);
		sb.append(", consignorName=").append(consignorName);
		sb.append(", consignorAddress=").append(consignorAddress);
		sb.append(", consignorPhone=").append(consignorPhone);
		sb.append(", buyerId=").append(buyerId);
		sb.append(", buyerName=").append(buyerName);
		sb.append(", buyerAddress=").append(buyerAddress);
		sb.append(", buyerPhone=").append(buyerPhone);
		sb.append(", superintendent=").append(superintendent);
		sb.append(", superintendentPhone=").append(superintendentPhone);
		sb.append(", operators=").append(operators);
		sb.append(", deliveryType=").append(deliveryType);
		sb.append(", sentTime=").append(sentTime);
		sb.append(", receiveTime=").append(receiveTime);
		sb.append(", createTime=").append(createTime);
		sb.append(", creator=").append(creator);
		sb.append(", status=").append(status);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}

	@JSONField(serialize = false)
	public boolean isRightDeliveryType() {
		return getDeliveryType() != null
				&& (getDeliveryType().equals(
						EntityConstant.TM_DELIVERYORDER_DELIVERYTYPE_INPUT) || getDeliveryType()
						.equals(EntityConstant.TM_DELIVERYORDER_DELIVERYTYPE_OUTPUT));
	}
	@JSONField(serialize = false)
	public boolean  isIntputDeliveryType(){
		return getDeliveryType() != null&& getDeliveryType().equals(EntityConstant.TM_DELIVERYORDER_DELIVERYTYPE_INPUT);
	}
	@JSONField(serialize = false)
	public boolean  isOutputDeliveryType(){
		return getDeliveryType() != null&& getDeliveryType().equals(EntityConstant.TM_DELIVERYORDER_DELIVERYTYPE_OUTPUT);

	}
}