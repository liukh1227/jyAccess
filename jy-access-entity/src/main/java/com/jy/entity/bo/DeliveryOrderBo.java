package com.jy.entity.bo;

import java.util.List;

import com.jy.entity.po.DeliveryCar;
import com.jy.entity.vo.DeliveryDeviceVo;
import com.jy.entity.vo.DeliveryOrderVo;

public class DeliveryOrderBo extends DeliveryOrderVo {
	
	private static final long serialVersionUID = 1L;
	private List<DeliveryCar> deliveryCarLists;
	private List<DeliveryDeviceVo> deliveryDeviceLists;
	public List<DeliveryCar> getDeliveryCarLists() {
		return deliveryCarLists;
	}
	public void setDeliveryCarLists(List<DeliveryCar> deliveryCarLists) {
		this.deliveryCarLists = deliveryCarLists;
	}
	public List<DeliveryDeviceVo> getDeliveryDeviceLists() {
		return deliveryDeviceLists;
	}
	public void setDeliveryDeviceLists(List<DeliveryDeviceVo> deliveryDeviceLists) {
		this.deliveryDeviceLists = deliveryDeviceLists;
	}
	@Override
	public String toString() {
		return "DeliveryOrderBo [deliveryCarLists=" + deliveryCarLists
				+ ", deliveryDeviceLists=" + deliveryDeviceLists + "]";
	}
	
}
