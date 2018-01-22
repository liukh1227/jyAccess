package com.jy.access.service;

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
import com.jy.access.mapper.CustomerMapper;
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.bo.CustomerBo;
import com.jy.entity.form.CustomerQueryForm;
import com.jy.entity.po.Customer;
import com.jy.entity.pojo.SuccessReturnPojo;
import com.jy.entity.vo.CustomerVo;
@Service("customerService")
@Scope("prototype")
public class CustomerServiceImpl implements CustomerService {
	private static final Logger log = Logger.getLogger(CustomerServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;
	@Autowired
	private CompanyService companyService;
	
	@Override
	public String addCustomer(String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if (StringUtils.isNotBlank(data)) {
			try {
				Customer customer = JSON.parseObject(data,Customer.class);
				if (customer == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				if (customer.getCustomerName() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("客户名称不能为空 !");
					return JSON.toJSONString(dto);
				}
				if (customer.getCompanyId() == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("客户所属公司不能为空 !");
					return JSON.toJSONString(dto);
				}
				String returnCompanyStr = companyService.getCompany(StringUtils.trim(customer.getCompanyId()));
				if(!FrameworkUtils.isSuccess(returnCompanyStr)){
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("客户所属公司Id不正确 !");
					return JSON.toJSONString(dto);
				}
				
				Date createDate = new Date();
				customer.setCreateTime(createDate);
				customer.setStatus(EntityConstant.STATUS_VALID);
				dto = baseDao.insertSelective(CustomerMapper.class,customer);

				if (FrameworkUtils.isSuccess(dto)) {
					log.info("addCustomer success!");
					BaseObjDto<SuccessReturnPojo> returnDto = new BaseObjDto<SuccessReturnPojo>();
					SuccessReturnPojo successPojo = new SuccessReturnPojo();
					successPojo.setId(customer.getCustomer_Id());
					successPojo.setCreateTime(createDate);
					returnDto.setData(successPojo);
					FrameworkUtils.setSuccess(returnDto);
					return JSON.toJSONString(returnDto);
				} else {
					log.error("addCustomer failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("addCustomer exception!");
				throw new RuntimeException("addCustomer Exception!");
			}
		} else {
			log.error("---addCustomer -------- data is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String updateCustomer(String customerId, String data) {
		String jsonStr = "";
		BaseDto dto = new BaseDto();
		if(StringUtils.isBlank(customerId)){
			dto.setRcode(BaseDto.ERROR_RCODE);
			dto.setRinfo("传入的参数customerId不能为空 !");
			return JSON.toJSONString(dto);
		}
		if (StringUtils.isNotBlank(customerId)
				&& StringUtils.isNotBlank(data)) {
			try {
				Customer customer = JSON.parseObject(data,Customer.class);
				if (customer == null) {
					dto.setRcode(BaseDto.ERROR_RCODE);
					dto.setRinfo("传入的参数为空 !");
					return JSON.toJSONString(dto);
				}
				customer.setCustomer_Id(customerId);
				if(customer.getCompanyId()!= null){
					String returnCompanyStr = companyService.getCompany(StringUtils.trim(customer.getCompanyId()));
					if(!FrameworkUtils.isSuccess(returnCompanyStr)){
						dto.setRcode(BaseDto.ERROR_RCODE);
						dto.setRinfo("客户所属公司Id不正确 !");
						return JSON.toJSONString(dto);
					}
				}
				dto = baseDao.updateByPrimaryKeySelective(CustomerMapper.class, customer);
				if (FrameworkUtils.isSuccess(dto)) {
					log.info("updateCustomer success!");
				} else {
					log.error("updateCustomer failure!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.error("updateCustomer exception!");
				throw new RuntimeException("updateCustomer Exception!");
			}

		} else {
			log.error("---updateCustomer -------- data or customerId is null ==== ");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getCustomer(String customerId) {
		String jsonStr = "";
		BaseObjDto<CustomerVo> dto = new BaseObjDto<CustomerVo>();
		try {
			if (StringUtils.isBlank(customerId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数customerId不能为空 !");
				return JSON.toJSONString(dto);
			}
			BaseObjDto<CustomerVo> customerDto = baseDao.selectByPrimaryKey(CustomerMapper.class,StringUtils.trim(customerId));
			if (FrameworkUtils.isSuccess(customerDto)) {
				CustomerVo customerVo = customerDto.getData();
				dto.setData(customerVo);
				FrameworkUtils.setSuccess(dto);
				log.info("getCustomer success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getCustomer failure");
			}
		} catch (Exception e) {
			log.error("getCustomer exception!");
			e.printStackTrace();
			throw new RuntimeException("getCustomer Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}
	
	@Override
	public String getCustomerAndBankAccountList(String customerId) {
		String jsonStr = "";
		BaseObjDto<CustomerBo> dto = new BaseObjDto<CustomerBo>();
		try {
			if (StringUtils.isBlank(customerId)) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("传入的参数customerId不能为空 !");
				return JSON.toJSONString(dto);
			}
			Map<String,Object> paramsMap = new HashMap<String,Object>();
			paramsMap.put("customer_Id", StringUtils.trim(customerId));
			BaseObjDto<CustomerBo> customerBoDto = baseDao.getObjProperty(CustomerMapper.class, "selectCustomerAndBankAccountById", paramsMap);
			if (FrameworkUtils.isSuccess(customerBoDto)) {
				CustomerBo customerBo = customerBoDto.getData();
				dto.setData(customerBo);
				FrameworkUtils.setSuccess(dto);
				log.info("getCustomerAndBankAccountList success!");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getCustomerAndBankAccountList failure");
			}
		} catch (Exception e) {
			log.error("getCustomerAndBankAccountList exception!");
			e.printStackTrace();
			throw new RuntimeException("getCustomerAndBankAccountList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getCustomerList(CustomerQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<CustomerVo>> dto = new BaseObjDto<ItemPage<CustomerVo>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getCompanyId()== null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("客户所属的公司(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getCompanyIdArray()== null || (form.getCompanyIdArray()!= null && form.getCompanyIdArray().length<0)){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("客户所属的公司(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.isQueryAll()){
				form.setCompanyIdArray(null);
				form.setCompanyId(null);
			}
			BaseObjDto<ItemPage<CustomerVo>> pagesDto = baseDao.getPageList(
					CustomerMapper.class, CustomerVo.class, form,
					"getCustomerPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getCustomerList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getCustomerList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getCustomerList Exception !");
			throw new RuntimeException("getCustomerList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	
	
	@Override
	public String getCustomerAndBankAccountPageList(CustomerQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<CustomerBo>> dto = new BaseObjDto<ItemPage<CustomerBo>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getCompanyId()== null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("客户所属的公司(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getCompanyIdArray()== null || (form.getCompanyIdArray()!= null && form.getCompanyIdArray().length<0)){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("客户所属的公司(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.isQueryAll()){
				form.setCompanyIdArray(null);
				form.setCompanyId(null);
			}
			BaseObjDto<ItemPage<CustomerBo>> pagesDto = baseDao.getPageList(
					CustomerMapper.class, CustomerBo.class, form,
					"getCustomerAndBankAccountPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getCustomerAndBankAccountPageList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getCustomerAndBankAccountPageList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getCustomerAndBankAccountPageList Exception !");
			throw new RuntimeException("getCustomerAndBankAccountPageList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getAllCustomerList(CustomerQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<CustomerVo>> dto = new BaseObjDto<List<CustomerVo>>();
		try {
			if (form == null) {
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("请求参数不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getCompanyId()== null){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("客户所属的公司(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.getCompanyIdArray()== null || (form.getCompanyIdArray()!= null && form.getCompanyIdArray().length<0)){
				dto.setRcode(BaseDto.ERROR_RCODE);
				dto.setRinfo("客户所属的公司(companyId)不能为空 !");
				return JSON.toJSONString(dto);
			}
			if(form.isQueryAll()){
				form.setCompanyIdArray(null);
				form.setCompanyId(null);
			}
			BaseObjDto<List<CustomerVo>> dataDto = baseDao.getList(
					CustomerMapper.class, CustomerVo.class,
					"getAllCustomerlList", form);
			if (FrameworkUtils.isSuccess(dataDto)
					&& dataDto.getData() != null) {
				dto = dataDto;
			
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllCustomerList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllCustomerList Exception");
			throw new RuntimeException("getAllCustomerList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	

}
