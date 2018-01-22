package com.jy.access.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.jy.EntityConstant;
import com.jy.access.base.dao.IBaseDao;
import com.jy.access.common.FrameworkUtils;
import com.jy.access.mapper.QuotationDeviceMapper;
import com.jy.access.mapper.QuotationMapper;
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.bo.QuotationBo;
import com.jy.entity.form.QuotationDeviceQueryForm;
import com.jy.entity.form.QuotationQueryForm;
import com.jy.entity.po.Quotation;
import com.jy.entity.po.QuotationDevice;
import com.jy.entity.pojo.SuccessReturnPojo;
import com.jy.entity.vo.QuotationDeviceVo;
import com.jy.entity.vo.QuotationVo;

@Service("quotationService")
@Scope("prototype")
public class QuotationServiceImpl implements QuotationService {
	private static final Logger log = Logger
			.getLogger(QuotationServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private DeviceModelService deviceModelService;
	@Autowired
	private UserAccountServie userAccountServie;

	@Override
	public String addQuotation(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				Quotation quotation = JSON.parseObject(data, Quotation.class);
				if (quotation == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (quotation.getQuotationNum() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报价单编号不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (quotation.getCustomerId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("客户信息不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnCustomerStr = customerService
						.getCustomer(StringUtils.trim(quotation.getCustomerId()));
				if (!FrameworkUtils.isSuccess(returnCustomerStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数客户方Id不正确 !");
					return JSON.toJSONString(dto);
				}
				if (quotation.getLessorId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报价方信息不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnCompanyStr = companyService.getCompany(StringUtils
						.trim(quotation.getLessorId()));
				if (!FrameworkUtils.isSuccess(returnCompanyStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数报价方Id不正确 !");
					return JSON.toJSONString(dto);
				}

				if (quotation.getIncludeShippingFee() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数是否含运费不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (!quotation.getIncludeShippingFee()
						&& quotation.getShippingFee() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报价不含运费时，运费不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (quotation.getIncludeInvoice() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数是否含发票不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (quotation.getIncludeJiShou() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数是否含机手不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (quotation.getIncludeFue() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数是否含燃油不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (quotation.getIncludeInsurance() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数是否含保险不能为空 !");
					return JSON.toJSONString(dto);
				}

				if (quotation.getPayMethod() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("结款方式不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (!quotation.isRightPayMethod()) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("结款方式参数不正确 !");
					return JSON.toJSONString(dto);
				}
				if (quotation.getStatus() == null) {
					quotation.setStatus(EntityConstant.TM_QUOTATION_STATUS_CREATE);
				}
				
				if(quotation.getCreator() == null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("创建者不能为空 !");
					return JSON.toJSONString(dto);
				}
				if(quotation.getSalesMan() == null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报价人不能为空 !");
					return JSON.toJSONString(dto);
				}
				if(quotation.getCreator().equals(quotation.getSalesMan())){
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(quotation.getCreator()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("创建者和报价人参数不正确!");
						return JSON.toJSONString(dto);
					}
					
				}else{
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(quotation.getCreator()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("创建者参数不正确!");
						return JSON.toJSONString(dto);
					}
					String returnSalesManStr = userAccountServie.getUserAccountById(StringUtils.trim(quotation.getSalesMan()));
					if (!FrameworkUtils.isSuccess(returnSalesManStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("报价人参数不正确!");
						return JSON.toJSONString(dto);
					}
					
				}
				
				Date createDate = new Date();
				quotation.setCreateTime(createDate);
				dto = baseDao.insertSelective(QuotationMapper.class, quotation);

				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addQuotation success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(quotation.getQuotation_Id());
					successPojo.setCreateTime(createDate);
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addQuotation failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addQuotation exception!");
				throw new RuntimeException("addQuotation Exception!");
			}
		} else {
			log.error("---addQuotation -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String addQuotationAndQuotationDevice(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				QuotationBo quotationBo = JSON.parseObject(data,
						QuotationBo.class);
				if (quotationBo == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (quotationBo.getQuotationNum() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报价单编号不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (quotationBo.getCustomerId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("客户信息不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnCustomerStr = customerService
						.getCustomer(StringUtils.trim(quotationBo
								.getCustomerId()));
				if (!FrameworkUtils.isSuccess(returnCustomerStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数客户方Id不正确 !");
					return JSON.toJSONString(dto);
				}
				if (quotationBo.getLessorId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报价方信息不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnCompanyStr = companyService.getCompany(StringUtils
						.trim(quotationBo.getLessorId()));
				if (!FrameworkUtils.isSuccess(returnCompanyStr)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数报价方Id不正确 !");
					return JSON.toJSONString(dto);
				}

				if (quotationBo.getIncludeShippingFee() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数是否含运费不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (!quotationBo.getIncludeShippingFee()
						&& quotationBo.getShippingFee() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报价不含运费时，运费不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (quotationBo.getIncludeInvoice() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数是否含发票不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (quotationBo.getIncludeJiShou() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数是否含机手不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (quotationBo.getIncludeFue() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数是否含燃油不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (quotationBo.getIncludeInsurance() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("参数是否含保险不能为空 !");
					return JSON.toJSONString(dto);
				}

				if (quotationBo.getPayMethod() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("结款方式不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (!quotationBo.isRightPayMethod()) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("结款方式参数不正确 !");
					return JSON.toJSONString(dto);
				}
				if (quotationBo.getStatus() == null) {
					quotationBo.setStatus(EntityConstant.TM_QUOTATION_STATUS_CREATE);
				}
				
				if(quotationBo.getCreator() == null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("创建者不能为空 !");
					return JSON.toJSONString(dto);
				}
				if(quotationBo.getSalesMan() == null){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报价人不能为空 !");
					return JSON.toJSONString(dto);
				}
				if(quotationBo.getCreator().equals(quotationBo.getSalesMan())){
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(quotationBo.getCreator()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("创建者和报价人参数不正确!");
						return JSON.toJSONString(dto);
					}
					
				}else{
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(quotationBo.getCreator()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("创建者参数不正确!");
						return JSON.toJSONString(dto);
					}
					String returnSalesManStr = userAccountServie.getUserAccountById(StringUtils.trim(quotationBo.getSalesMan()));
					if (!FrameworkUtils.isSuccess(returnSalesManStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("报价人参数不正确!");
						return JSON.toJSONString(dto);
					}
					
				}
				
				if (quotationBo.getQuotationDevices() == null
						|| (quotationBo.getQuotationDevices() != null && quotationBo
								.getQuotationDevices().size() < 1)) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("报价设备信息不能为空 !");
					return JSON.toJSONString(dto);
				}
				ArrayList<QuotationDevice> cachQuotationDeviceList = new ArrayList<QuotationDevice>();
				for (QuotationDeviceVo quotationDeviceVo : quotationBo
						.getQuotationDevices()) {
					if (quotationDeviceVo.getDeviceModelId() == null) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("报价设备中型号Id不能为空 !");
						return JSON.toJSONString(dto);
					}
					String returnDeviceModelStr = deviceModelService
							.getDeviceModel(StringUtils.trim(quotationDeviceVo
									.getDeviceModelId()));
					if (!FrameworkUtils.isSuccess(returnDeviceModelStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("报价设备中型号Id不正确 !");
						return JSON.toJSONString(dto);
					}
					if (quotationDeviceVo.getQuantity() == null) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("报价设备数量不能为空 !");
						return JSON.toJSONString(dto);
					}
					if (quotationDeviceVo.getRentPerMonth() == null) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("报价设备月租金不能为空 !");
						return JSON.toJSONString(dto);
					}
					if (quotationDeviceVo.getRentPerWeek() == null) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("报价设备周租金不能为空 !");
						return JSON.toJSONString(dto);
					}
					if (quotationDeviceVo.getRentPerDay() == null) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("报价设备日租金不能为空 !");
						return JSON.toJSONString(dto);
					}
					QuotationDevice quotationDevice = new QuotationDevice();
					quotationDevice.setDeviceModelId(quotationDeviceVo
							.getDeviceModelId());
					quotationDevice
							.setQuantity(quotationDeviceVo.getQuantity());
					quotationDevice.setRentPerMonth(quotationDeviceVo
							.getRentPerMonth());
					quotationDevice.setRentPerWeek(quotationDeviceVo
							.getRentPerWeek());
					quotationDevice.setRentPerDay(quotationDeviceVo
							.getRentPerDay());
					cachQuotationDeviceList.add(quotationDevice);

				}
				Date createDate = new Date();
				quotationBo.setCreateTime(createDate);
				dto = baseDao.insertSelective(QuotationMapper.class,
						quotationBo);

				if (!FrameworkUtils.isSuccess(dto)) {
					log.error("adQuotationAndQuotationDevice........when addQuotation failure!");
					return JSON.toJSONString(dto);
				}
				for (QuotationDevice quotationDevice : cachQuotationDeviceList) {
					quotationDevice
							.setQuotationId(quotationBo.getQuotation_Id());
					dto = baseDao.insertSelective(QuotationDeviceMapper.class,
							quotationDevice);
					if (!FrameworkUtils.isSuccess(dto)) {
						return JSON.toJSONString(dto);
					}
				}
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addQuotation success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(quotationBo.getQuotation_Id());
					successPojo.setCreateTime(createDate);
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addQuotation failure!");
				}

			} catch (Exception e) {
				e.printStackTrace();
				log.error("addQuotation exception!");
				throw new RuntimeException("addQuotation Exception!");
			}
		} else {
			log.error("---addQuotation -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}
	
	
	@Override
	public String updateQuotation(String quotationId, String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if(StringUtils.isBlank(quotationId)){
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数quodeviceId不能为空 !");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(quotationId)
				&& StringUtils.isNotBlank(data)) {
			try {
				Quotation quotation = JSON.parseObject(data,Quotation.class);
				if (quotation == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				quotation.setQuotation_Id(quotationId);
				
				if (quotation.getCustomerId() != null) {
					String returnCustomerStr = customerService
							.getCustomer(StringUtils.trim(quotation
									.getCustomerId()));
					if (!FrameworkUtils.isSuccess(returnCustomerStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数客户方Id不正确 !");
						return JSON.toJSONString(dto);
					}
				}
				if (quotation.getLessorId() != null) {
					String returnCompanyStr = companyService
							.getCompany(StringUtils.trim(quotation
									.getLessorId()));
					if (!FrameworkUtils.isSuccess(returnCompanyStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数报价方Id不正确 !");
						return JSON.toJSONString(dto);
					}

				}
				if (quotation.getPayMethod() != null
						&& !quotation.isRightPayMethod()) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("结款方式参数不正确 !");
					return JSON.toJSONString(dto);
				}
				if(quotation.getCreator()!= null){
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(quotation.getCreator()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("创建者参数不正确!");
						return JSON.toJSONString(dto);
					}
				}
				if(quotation.getSalesMan()!= null){
					String returnSalesManStr = userAccountServie.getUserAccountById(StringUtils.trim(quotation.getSalesMan()));
					if (!FrameworkUtils.isSuccess(returnSalesManStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("报价人参数不正确!");
						return JSON.toJSONString(dto);
					}
				}
				
				dto = baseDao.updateByPrimaryKeySelective(QuotationMapper.class, quotation);
				if (FrameworkUtils.isSuccess(dto)) {

			
					log.info("updateQuotation success!");
				} else {
					log.error("updateQuotation failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateQuotation exception!");
				throw new RuntimeException("updateQuotation Exception!");
			}

		} else {
			log.error("---updateQuotation -------- data or quotationId is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}
	

	@Override
	public String updateQuotationAndQuotationDevice(String quotationId,
			String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isBlank(quotationId)) {
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数quotationId不能为空 !");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(quotationId) && StringUtils.isNotBlank(data)) {
			try {
				QuotationBo quotationBo = JSON.parseObject(data,
						QuotationBo.class);
				if (quotationBo.getCustomerId() != null) {
					String returnCustomerStr = customerService
							.getCustomer(StringUtils.trim(quotationBo
									.getCustomerId()));
					if (!FrameworkUtils.isSuccess(returnCustomerStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数客户方Id不正确 !");
						return JSON.toJSONString(dto);
					}
				}
				if (quotationBo.getLessorId() != null) {
					String returnCompanyStr = companyService
							.getCompany(StringUtils.trim(quotationBo
									.getLessorId()));
					if (!FrameworkUtils.isSuccess(returnCompanyStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("参数报价方Id不正确 !");
						return JSON.toJSONString(dto);
					}

				}
				if (quotationBo.getPayMethod() != null
						&& !quotationBo.isRightPayMethod()) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("结款方式参数不正确 !");
					return JSON.toJSONString(dto);
				}

				if(quotationBo.getCreator()!= null){
					String returnCreatorStr = userAccountServie.getUserAccountById(StringUtils.trim(quotationBo.getCreator()));
					if (!FrameworkUtils.isSuccess(returnCreatorStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("创建者参数不正确!");
						return JSON.toJSONString(dto);
					}
				}
				if(quotationBo.getSalesMan()!= null){
					String returnSalesManStr = userAccountServie.getUserAccountById(StringUtils.trim(quotationBo.getSalesMan()));
					if (!FrameworkUtils.isSuccess(returnSalesManStr)) {
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("报价人参数不正确!");
						return JSON.toJSONString(dto);
					}
				}
				QuotationDeviceQueryForm qDeviceForm = new QuotationDeviceQueryForm();
				qDeviceForm.setQuotationId(StringUtils.trim(quotationId));
				BaseObjDto<List<QuotationDeviceVo>> qDeviceListDto = baseDao
						.getList(QuotationDeviceMapper.class,
								QuotationDeviceVo.class,
								"getAllQuotationDeviceList", qDeviceForm);
				ArrayList<String> cachQueryQuotationDeviceList = new ArrayList<String>();
				if (FrameworkUtils.isSuccess(qDeviceListDto)
						&& qDeviceListDto.getData() != null
						&& qDeviceListDto.getData().size() > 0) {
					for (QuotationDeviceVo quotationDeviceVo : qDeviceListDto
							.getData()) {
						cachQueryQuotationDeviceList.add(quotationDeviceVo
								.getQuodevice_Id());
					}

				}

				ArrayList<QuotationDevice> cachUpdateQDeviceList = new ArrayList<QuotationDevice>();
				ArrayList<QuotationDevice> cachAddQDeviceList = new ArrayList<QuotationDevice>();
				if (quotationBo.getQuotationDevices() != null
						&& quotationBo.getQuotationDevices().size() > 0) {
					for (QuotationDeviceVo quotationDeviceVo : quotationBo
							.getQuotationDevices()) {
						QuotationDevice doQDevice = new QuotationDevice();
						if (quotationDeviceVo.getQuodevice_Id() == null) {
							if (quotationDeviceVo.getDeviceModelId() == null) {
								dto.setRcode(BaseDto.ERROR_RCODE);
								dto.setRinfo("新增报价设备中型号Id不能为空 !");
								return JSON.toJSONString(dto);
							}
							String returnDeviceModelStr = deviceModelService
									.getDeviceModel(StringUtils
											.trim(quotationDeviceVo
													.getDeviceModelId()));
							if (!FrameworkUtils.isSuccess(returnDeviceModelStr)) {
								dto.setRcode(BaseDto.ERROR_RCODE);
								dto.setRinfo("报价设备中型号Id不正确 !");
								return JSON.toJSONString(dto);
							}
							if (quotationDeviceVo.getQuantity() == null) {
								dto.setRcode(BaseDto.ERROR_RCODE);
								dto.setRinfo("报价设备数量不能为空 !");
								return JSON.toJSONString(dto);
							}
							if (quotationDeviceVo.getRentPerMonth() == null) {
								dto.setRcode(BaseDto.ERROR_RCODE);
								dto.setRinfo("报价设备月租金不能为空 !");
								return JSON.toJSONString(dto);
							}
							if (quotationDeviceVo.getRentPerWeek() == null) {
								dto.setRcode(BaseDto.ERROR_RCODE);
								dto.setRinfo("报价设备周租金不能为空 !");
								return JSON.toJSONString(dto);
							}
							if (quotationDeviceVo.getRentPerDay() == null) {
								dto.setRcode(BaseDto.ERROR_RCODE);
								dto.setRinfo("报价设备日租金不能为空 !");
								return JSON.toJSONString(dto);
							}
							doQDevice.setQuotationId(quotationId);
							doQDevice.setDeviceModelId(quotationDeviceVo
									.getDeviceModelId());
							doQDevice.setQuantity(quotationDeviceVo
									.getQuantity());
							doQDevice.setRentPerMonth(quotationDeviceVo
									.getRentPerMonth());
							doQDevice.setRentPerWeek(quotationDeviceVo
									.getRentPerWeek());
							doQDevice.setRentPerDay(quotationDeviceVo
									.getRentPerDay());
							cachAddQDeviceList.add(doQDevice);

						} else {
							if (cachQueryQuotationDeviceList.size() > 0
									&& !cachQueryQuotationDeviceList
											.contains(quotationDeviceVo
													.getQuodevice_Id())) {
								dto.setRcode(BaseDto.ERROR_RCODE);
								dto.setRinfo("修改报价设备信息Id不存在 !");
								return JSON.toJSONString(dto);
							}
							doQDevice.setQuodevice_Id(quotationDeviceVo
									.getQuodevice_Id());
							Boolean isContainUpdate = false;
							if (quotationDeviceVo.getDeviceModelId() != null) {
								String returnDeviceModelStr = deviceModelService
										.getDeviceModel(StringUtils
												.trim(quotationDeviceVo
														.getDeviceModelId()));
								if (!FrameworkUtils
										.isSuccess(returnDeviceModelStr)) {
									dto.setRcode(BaseDto.ERROR_RCODE);
									dto.setRinfo("报价设备中型号Id不正确 !");
									return JSON.toJSONString(dto);
								}
								doQDevice.setDeviceModelId(quotationDeviceVo
										.getDeviceModelId());
								isContainUpdate = true;
							}
							if (quotationDeviceVo.getQuantity() != null) {
								doQDevice.setQuantity(quotationDeviceVo
										.getQuantity());
								isContainUpdate = true;
							}
							if (quotationDeviceVo.getRentPerMonth() != null) {
								doQDevice.setRentPerMonth(quotationDeviceVo
										.getRentPerMonth());
								isContainUpdate = true;
							}
							if (quotationDeviceVo.getRentPerWeek() != null) {
								doQDevice.setRentPerWeek(quotationDeviceVo
										.getRentPerWeek());
								isContainUpdate = true;
							}
							if (quotationDeviceVo.getRentPerDay() != null) {
								doQDevice.setRentPerDay(quotationDeviceVo
										.getRentPerDay());
								isContainUpdate = true;
							}
							if (!isContainUpdate) {
								dto.setRcode(BaseDto.ERROR_RCODE);
								dto.setRinfo("报价设备中修改的参数为空!");
								return JSON.toJSONString(dto);
							}
							cachUpdateQDeviceList.add(doQDevice);
						}

					}
				}
				quotationBo.setQuotation_Id(quotationId);
				dto = baseDao.updateByPrimaryKeySelective(
						QuotationMapper.class, quotationBo);
				if (!FrameworkUtils.isSuccess(dto)) {
					return JSON.toJSONString(dto);
				}
				for (QuotationDevice updateQDevice : cachUpdateQDeviceList) {
					dto = baseDao.updateByPrimaryKeySelective(
							QuotationDeviceMapper.class, updateQDevice);
					if (!FrameworkUtils.isSuccess(dto)) {
						return JSON.toJSONString(dto);
					}
				}
				for (QuotationDevice addQDevice : cachAddQDeviceList) {
					dto = baseDao.insertSelective(QuotationDeviceMapper.class,
							addQDevice);
					if (!FrameworkUtils.isSuccess(dto)) {
						return JSON.toJSONString(dto);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateQuotation exception!");
				throw new RuntimeException("updateQuotation Exception!");
			}

		} else {
			log.error("---updateDevice -------- data or deviceId is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}
	
	@Override
	public String getQuotation(String quotationId) {
		String jsonStr = "";
		BaseObjDto<QuotationVo> dto = new BaseObjDto<QuotationVo>();
		try {
			if (StringUtils.isBlank(quotationId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数quotationId不能为空 !");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<QuotationVo> quotationVoDto = baseDao
					.selectByPrimaryKey(QuotationMapper.class,
							StringUtils.trim(quotationId));
			if (FrameworkUtils.isSuccess(quotationVoDto)) {
				QuotationVo quotationVo = quotationVoDto.getData();
				dto.setData(quotationVo);
				FrameworkUtils.setSuccess(dto);
				log.info("getQuotation success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getQuotation failure");
			}
		} catch (Exception e) {
			log.error("getQuotation exception!");
			e.printStackTrace();
			throw new RuntimeException(
					"getQuotation Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}
	
	@Override
	public String getQuotationAndQuotationDevice(String quotationId) {
		String jsonStr = "";
		BaseObjDto<QuotationBo> dto = new BaseObjDto<QuotationBo>();
		try {
			if (StringUtils.isBlank(quotationId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数quotationId不能为空 !");
				return JSON.toJSONString(dto);
			}
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("quotation_Id", quotationId);
			BaseObjDto<QuotationBo> quotationBoDto = baseDao.getObjProperty(QuotationMapper.class, "selectQuotationAndDeviceById", paramsMap);					
			if (FrameworkUtils.isSuccess(quotationBoDto)) {
				QuotationBo quotationBo = quotationBoDto.getData();
				dto.setData(quotationBo);
				FrameworkUtils.setSuccess(dto);
				log.info("getQuotationAndQuotationDevice success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getQuotationAndQuotationDevice failure");
			}
		} catch (Exception e) {
			log.error("getQuotationAndQuotationDevice exception!");
			e.printStackTrace();
			throw new RuntimeException(
					"getQuotationAndQuotationDevice Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getQuotationList(QuotationQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<QuotationVo>> dto = new BaseObjDto<ItemPage<QuotationVo>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.getLessorId() == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("报价方公司(lessorId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.getLessorIdArray() == null
					|| (form.getLessorIdArray() != null && form
							.getLessorIdArray().length < 0)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("报价方公司(lessorId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if (form.isQueryAll()) {
				form.setLessorIdArray(null);
				form.setLessorId(null);
			}
			BaseObjDto<ItemPage<QuotationVo>> pagesDto = baseDao.getPageList(
					QuotationMapper.class, QuotationVo.class, form,
					"getQuotationPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getQuotationList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getQuotationList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getQuotationList Exception !");
			throw new RuntimeException("getQuotationList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}




}
