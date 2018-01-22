package com.jy.access.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.jy.EntityConstant;
import com.jy.access.base.dao.IBaseDao;
import com.jy.access.common.FrameworkUtils;
import com.jy.access.mapper.ChangeContractDeviceOrderMapper;
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.form.ChangeContractDeviceOrderQueryForm;
import com.jy.entity.po.ChangeContractDeviceOrder;
import com.jy.entity.po.DeviceInMakesureOrder;
import com.jy.entity.pojo.SuccessReturnPojo;
import com.jy.entity.vo.ChangeContractDeviceOrderVo;
import com.jy.entity.vo.DeviceInMakesureOrderVo;

@Service("changeContractDeviceOrderService")
@Scope("prototype")
public class ChangeContractDeviceOrderServiceImpl implements
		ChangeContractDeviceOrderService {
	private static final Logger log = Logger
			.getLogger(ChangeContractDeviceOrderServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private ContractOrderService contractOrderService;
	@Autowired
	private DeviceInMakesureOrderService deviceInMakesureOrderService;
	@Autowired
	private UserAccountServie userAccountServie;

	@SuppressWarnings("unchecked")
	@Override
	public String addChangeContractDeviceOrder(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				ChangeContractDeviceOrder changeContractDeviceOrder = JSON
						.parseObject(data, ChangeContractDeviceOrder.class);
				if (changeContractDeviceOrder == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (changeContractDeviceOrder.getChangeType() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数延期退场类型不能为空!");
					return JSON.toJSONString(dto);
				}
				if (!changeContractDeviceOrder.isRightChangeType()) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数延期退场类型不正确!");
					return JSON.toJSONString(dto);
				}
				if (changeContractDeviceOrder.getBillingType() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数计费模式不能为空!");
					return JSON.toJSONString(dto);
				}
				if (!changeContractDeviceOrder.isRightBillType()) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数计费模式不正确!");
					return JSON.toJSONString(dto);
				}
				if (changeContractDeviceOrder.getFactBillingType() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数实际计费模式不能为空!");
					return JSON.toJSONString(dto);
				}
				if (!changeContractDeviceOrder.isRightFactBillingType()) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数实际计费模式不正确!");
					return JSON.toJSONString(dto);
				}
				if (changeContractDeviceOrder.getPlanStartTime() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数预计租期日不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (changeContractDeviceOrder.getLeaseTerm() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数预计租期不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (changeContractDeviceOrder.getFactLeaseTerm() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数实际租期不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (changeContractDeviceOrder.getFactStartTime() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数实际起租日不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (changeContractDeviceOrder.getFactEndTime() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数实际结束时间不能为空 !");
					return JSON.toJSONString(dto);
				}

				if (changeContractDeviceOrder.getContractOrderId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数签约单Id(contractOrderId)不能为空!");
					return JSON.toJSONString(dto);
				}
				String returnContractOrdertr = contractOrderService
						.getContractOrder(StringUtils
								.trim(changeContractDeviceOrder
										.getContractOrderId()));
				if (!FrameworkUtils.isSuccess(returnContractOrdertr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数签约单Id(contractOrderId)不正确 !");
					return JSON.toJSONString(dto);
				}
				if (changeContractDeviceOrder.getContractDeviceId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数退场延期设备Id(contractDeviceId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnDeviceStr = deviceService.getDevice(StringUtils
						.trim(changeContractDeviceOrder.getContractDeviceId()));
				if (!FrameworkUtils.isSuccess(returnDeviceStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数退场延期设备Id(contractDeviceId)不正确 !");
					return JSON.toJSONString(dto);
				}
				if (changeContractDeviceOrder.getdInMakeSureId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数进场确认单Id(dInMakeSureId)不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnDInMakeSureIdStr = deviceInMakesureOrderService
						.getDeviceInMakesureOrder(StringUtils
								.trim(changeContractDeviceOrder
										.getdInMakeSureId()));
				if (!FrameworkUtils.isSuccess(returnDInMakeSureIdStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数进场确认单Id(dInMakeSureId)不正确 !");
					return JSON.toJSONString(dto);
				} else {
					BaseObjDto<DeviceInMakesureOrderVo> queryDInMakesureOrderDto = JSON
							.parseObject(returnDInMakeSureIdStr,
									BaseObjDto.class);
					if (queryDInMakesureOrderDto != null
							&& queryDInMakesureOrderDto.getData() != null) {
						DeviceInMakesureOrderVo queryDeviceInMakesureOrderVo = JSON
								.parseObject(JSON
										.toJSONString(queryDInMakesureOrderDto
												.getData()),
										DeviceInMakesureOrderVo.class);
						if (queryDeviceInMakesureOrderVo != null
								&& queryDeviceInMakesureOrderVo.getDeviceId() != null
								&& !queryDeviceInMakesureOrderVo
										.getDeviceId()
										.equals(StringUtils
												.trim(changeContractDeviceOrder
														.getContractDeviceId()))) {
							dto.setRcode(BaseDto.ERROR_RCODE);
							dto.setRinfo("参数中 dInMakeSureId 和 contractDeviceId 不匹配!");
							return JSON.toJSONString(dto);
						}
					}
				}
				if (changeContractDeviceOrder.getStatus() == null) {
					changeContractDeviceOrder
							.setStatus(EntityConstant.STATUS_VALID);
				}
				if (changeContractDeviceOrder.getCreator() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("创建者不能为空!");
					return JSON.toJSONString(dto);
				}
				String returnCreatorStr = userAccountServie
						.getUserAccountById(StringUtils
								.trim(changeContractDeviceOrder.getCreator()));
				if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("创建者Id参数不正确!");
					return JSON.toJSONString(dto);
				}

				Date createDate = new Date();
				changeContractDeviceOrder.setCreateTime(createDate);
				dto = baseDao.insertSelective(
						ChangeContractDeviceOrderMapper.class,
						changeContractDeviceOrder);
				if (FrameworkUtils.isSuccess(dto)) {
					DeviceInMakesureOrder updateData = new DeviceInMakesureOrder();
					updateData.setBusinessTye(changeContractDeviceOrder.getChangeType());
					jsonStr = deviceInMakesureOrderService.updateDeviceInMakesureOrder(changeContractDeviceOrder.getdInMakeSureId(), JSON.toJSONString(updateData));
					if(FrameworkUtils.isSuccess(jsonStr)){
						log.error("addChangeContractDeviceOrder success !");
						BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
						SuccessReturnPojo successPojo = new SuccessReturnPojo();
						successPojo.setId(changeContractDeviceOrder
								.getChangeOrder_Id());
						successPojo.setCreateTime(createDate);
						returnDto.setData(successPojo);
						FrameworkUtils.setSuccess(returnDto);
						return JSON.toJSONString(returnDto);
					}else{
						log.error("addChangeContractDeviceOrder failure!");
						return jsonStr;
					}
				
				} else {
					log.error("addChangeContractDeviceOrder failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addChangeContractDeviceOrder exception!");
				throw new RuntimeException(
						"addChangeContractDeviceOrder Exception!");
			}
		} else {
			log.error("---addChangeContractDeviceOrder -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String updateChangeContractDeviceOrder(String changeOrderId,
			String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isBlank(changeOrderId)) {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数changeOrderId不能为空 !");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(changeOrderId)
				&& StringUtils.isNotBlank(data)) {
			try {
				ChangeContractDeviceOrder changeContractDeviceOrder = JSON
						.parseObject(data, ChangeContractDeviceOrder.class);
				if (changeContractDeviceOrder == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				changeContractDeviceOrder.setChangeOrder_Id(changeOrderId);

				if (changeContractDeviceOrder.getChangeType() != null
						&& !changeContractDeviceOrder.isRightChangeType()) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数延期退场类型不正确!");
					return JSON.toJSONString(dto);
				}

				if (changeContractDeviceOrder.getBillingType() != null
						&& !changeContractDeviceOrder.isRightBillType()) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数计费模式不正确!");
					return JSON.toJSONString(dto);
				}

				if (changeContractDeviceOrder.getFactBillingType() != null
						&& !changeContractDeviceOrder.isRightFactBillingType()) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数实际计费模式不正确!");
					return JSON.toJSONString(dto);
				}

				if (changeContractDeviceOrder.getContractOrderId() != null) {
					String returnContractOrdertr = contractOrderService
							.getContractOrder(StringUtils
									.trim(changeContractDeviceOrder
											.getContractOrderId()));
					if (!FrameworkUtils.isSuccess(returnContractOrdertr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数签约单Id(contractOrderId)不正确 !");
						return JSON.toJSONString(dto);
					}
				}

				if (changeContractDeviceOrder.getContractDeviceId() != null) {
					String returnDeviceStr = deviceService
							.getDevice(StringUtils
									.trim(changeContractDeviceOrder
											.getContractDeviceId()));
					if (!FrameworkUtils.isSuccess(returnDeviceStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("延期退场设备Id(contractDeviceId)不正确 !");
						return JSON.toJSONString(dto);
					}
				}

				if (changeContractDeviceOrder.getdInMakeSureId() != null) {
					String returnDInMakeSureIdStr = deviceInMakesureOrderService
							.getDeviceInMakesureOrder(StringUtils
									.trim(changeContractDeviceOrder
											.getdInMakeSureId()));
					if (!FrameworkUtils.isSuccess(returnDInMakeSureIdStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("进场确认单Id(dInMakeSureId)不正确 !");
						return JSON.toJSONString(dto);
					}
				}

				if (changeContractDeviceOrder.getCreator() != null) {
					String returnCreatorStr = userAccountServie
							.getUserAccountById(StringUtils
									.trim(changeContractDeviceOrder
											.getCreator()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("创建者Id参数不正确!");
						return JSON.toJSONString(dto);
					}
				}
				dto = baseDao.updateByPrimaryKeySelective(
						ChangeContractDeviceOrderMapper.class,
						changeContractDeviceOrder);
				if(changeContractDeviceOrder.getChangeType()!= null && changeContractDeviceOrder.getdInMakeSureId()!= null){
					DeviceInMakesureOrder updateData = new DeviceInMakesureOrder();
					updateData.setBusinessTye(changeContractDeviceOrder.getChangeType());
					jsonStr = deviceInMakesureOrderService.updateDeviceInMakesureOrder(changeContractDeviceOrder.getdInMakeSureId(), JSON.toJSONString(updateData));
					if(FrameworkUtils.isSuccess(jsonStr)){
						log.error("addChangeContractDeviceOrder success !");
					}else{
						log.error("addChangeContractDeviceOrder failure!");
						return jsonStr;
					}
				}
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("updateChangeContractDeviceOrder success!");
				} else {
					log.error("updateChangeContractDeviceOrder failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateChangeContractDeviceOrder exception!");
				throw new RuntimeException(
						"updateChangeContractDeviceOrder Exception!");
			}

		} else {
			log.error("---updateChangeContractDeviceOrder -------- data or changeOrderId is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getChangeContractDeviceOrder(String changeOrderId) {
		String jsonStr = "";
		BaseObjDto<ChangeContractDeviceOrderVo> dto = new BaseObjDto<ChangeContractDeviceOrderVo>();
		try {
			if (StringUtils.isBlank(changeOrderId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数changeOrderId不能为空 !");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<ChangeContractDeviceOrderVo> changeOrderlDto = baseDao
					.selectByPrimaryKey(ChangeContractDeviceOrderMapper.class,
							StringUtils.trim(changeOrderId));
			if (FrameworkUtils.isSuccess(changeOrderlDto)) {
				ChangeContractDeviceOrderVo changeContractDeviceOrderVo = changeOrderlDto
						.getData();
				dto.setData(changeContractDeviceOrderVo);
				FrameworkUtils.setSuccess(dto);
				log.info("getChangeContractDeviceOrder success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getChangeContractDeviceOrder failure");
			}
		} catch (Exception e) {
			log.error("getChangeContractDeviceOrder exception!");
			e.printStackTrace();
			throw new RuntimeException(
					"getChangeContractDeviceOrder Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String deleteChangeContractDeviceOrder(String changeOrderId) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isBlank(changeOrderId)) {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("参数changeOrderId为空");
			log.error("---deleteChangeContractDeviceOrder -------- changeOrderId is null ==== ");
			return JSON.toJSONString(dto);
		}

		try {
			dto = baseDao.deleteByPrimaryKey(
					ChangeContractDeviceOrderMapper.class, changeOrderId);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("deleteChangeContractDeviceOrder exception!");
			throw new RuntimeException(
					"deleteChangeContractDeviceOrder Exception!");
		}

		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getChangeContractDeviceOrderList(
			ChangeContractDeviceOrderQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<ChangeContractDeviceOrderVo>> dto = new BaseObjDto<ItemPage<ChangeContractDeviceOrderVo>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空");
				return JSON.toJSONString(dto);
			}
			if (form.getChangeType() == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数延期退场类型不能为空");
				return JSON.toJSONString(dto);
			}
			if (!form.isRightChangeType()) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数延期退场类型不正确");
				return JSON.toJSONString(dto);
			}
			if (form.getContractOrderId() == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数签约单Id不能为空");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<ItemPage<ChangeContractDeviceOrderVo>> pagesDto = baseDao
					.getPageList(ChangeContractDeviceOrderMapper.class,
							ChangeContractDeviceOrderVo.class, form,
							"getChangeContractDeviceOrderPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getChangeContractDeviceOrderList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getChangeContractDeviceOrderList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getChangeContractDeviceOrderList Exception !");
			throw new RuntimeException(
					"getChangeContractDeviceOrderList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getAllChangeContractDeviceOrderList(
			ChangeContractDeviceOrderQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<ChangeContractDeviceOrderVo>> dto = new BaseObjDto<List<ChangeContractDeviceOrderVo>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空");
				return JSON.toJSONString(dto);
			}
			if (form.getChangeType() == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数延期退场类型不能为空");
				return JSON.toJSONString(dto);
			}
			if (!form.isRightChangeType()) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数延期退场类型不正确");
				return JSON.toJSONString(dto);
			}
			if (form.getContractOrderId() == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数签约单Id不能为空");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<List<ChangeContractDeviceOrderVo>> dataDto = baseDao
					.getList(ChangeContractDeviceOrderMapper.class,
							ChangeContractDeviceOrderVo.class,
							"getAllChangeContractDeviceOrderList", form);
			if (FrameworkUtils.isSuccess(dataDto) && dataDto.getData() != null) {
				dto = dataDto;

			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllChangeContractDeviceOrderList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllChangeContractDeviceOrderList Exception");
			throw new RuntimeException(
					"getAllChangeContractDeviceOrderList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

}
