package com.jy.access.control.um;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jy.access.control.base.CommonController;
import com.jy.access.service.CompanyService;
import com.jy.base.entity.base.dto.BaseDto;
import com.jy.entity.form.CompanyQueryForm;

@Controller
@Scope("prototype")
@RequestMapping(value = "/um")
public class CompanyController extends CommonController {

	private static final Logger log = Logger.getLogger(CompanyController.class);
	@Autowired
	private CompanyService companyService;

	/**
	 * 新增公司信息
	 * 
	 * @author liukh
	 * @date 2017-7-18 下午3:05:48
	 * @param request
	 * @param response
	 * @param data
	 * @return
	 */
	@RequestMapping(value = "/company", method = { RequestMethod.POST })
	@ResponseBody
	public String addCompany(HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@RequestParam(value = "data", required = true) String data) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			if (session.getAttribute("addCompany") != null) {
				BaseDto dto = new BaseDto();
				dto.setRcode(BaseDto.EXIST_RCODE);
				dto.setRinfo("请求已提交，请耐心等待!");
				return JSON.toJSONString(dto);
			}
			session.setAttribute("addCompany", "addCompany");
			jsonStr = companyService.addCompany(data);
		} catch (Exception e) {
			log.error("addCompany ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("addCompany", null);
		return jsonStr;
	}

	/**
	 * 获取公司信息
	 * 
	 * @author liukh
	 * @date 2017-7-18 下午3:06:30
	 * @param request
	 * @param response
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value = "/company/{companyId}", method = { RequestMethod.GET })
	@ResponseBody
	public String getCompany(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "companyId") String companyId) {
		String jsonStr = companyService.getCompany(companyId);
		return jsonStr;
	}
	/**
	 * 获取公司及关联的银行账户信息
	 * @author liukh
	 * @date 2017-8-3 下午10:07:43
	 * @param request
	 * @param response
	 * @param companyId
	 * @return
	 */
	@RequestMapping(value = "/companyAndBankAccountList/{companyId}", method = { RequestMethod.GET })
	@ResponseBody
	public String getCompanyAndBankAccountList(HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value = "companyId") String companyId) {
		String jsonStr = companyService.getCompanyAndBankAccountList(companyId);
		return jsonStr;
	}

	/**
	 * 更新公司信息
	 * 
	 * @author liukh
	 * @date 2017-7-18 下午3:07:16
	 * @param companyId
	 * @param data
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/company/{companyId}", method = { RequestMethod.POST })
	@ResponseBody
	public String updateCompany(
			@PathVariable(value = "companyId") String companyId,
			@RequestParam(value = "data", required = true) String data,
			HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = companyService.updateCompany(companyId, data);
		return jsonStr;
	}

	/**
	 * 获取分页的公司及关联的银行账户信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-18 下午3:07:48
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/companyListAndBankAccountList/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getCompanyAndBankAccountPageList(HttpServletRequest request,
			HttpServletResponse response, CompanyQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = companyService.getCompanyAndBankAccountPageList(form);
		} catch (Exception e) {
			log.error("getCompanyAndBankAccountPageList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}
	@RequestMapping(value = "/companyAndBankAccountAndCustomer/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getCompanyAndBankAccountAndCustomerPageList(HttpServletRequest request,
			HttpServletResponse response, CompanyQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = companyService.getCompanyAndBankAccountAndCustomerPageList(form);
		} catch (Exception e) {
			log.error("getCompanyAndBankAccountAndCustomerPageList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
		
	}
	
	@RequestMapping(value = "/company/list", method = { RequestMethod.GET })
	@ResponseBody
	public String getCompanyList(HttpServletRequest request,
			HttpServletResponse response, CompanyQueryForm form) {
		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = companyService.getCompanyList(form);
		} catch (Exception e) {
			log.error("getCompanyList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
		
	}

	/**
	 * 获取所有的公司信息列表
	 * 
	 * @author liukh
	 * @date 2017-7-18 下午3:08:17
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/company/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllCompanyList(HttpServletRequest request,
			HttpServletResponse response, CompanyQueryForm form) {

		String jsonStr = JSON.toJSONString(new BaseDto());
		try {
			jsonStr = companyService.getAllCompanyList(form);
		} catch (Exception e) {
			log.error("getAllCompanyList ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;

	}
	/**
	 * 获取当时公司及下属公司Id
	 * @author liukh
	 * @date 2017-7-24 下午4:02:40
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/companyAndSubordinate/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllCompanyAndSubordinate(HttpServletRequest request,
			HttpServletResponse response, CompanyQueryForm form) {
		BaseDto baseDto = new BaseDto();
		String jsonStr = JSON.toJSONString(baseDto);
		try {
			if(form  == null){
				baseDto.setRcode(BaseDto.ERROR_RCODE);
				baseDto.setRinfo("请求的参数不能为空!");
				return JSON.toJSONString(baseDto);
			}else if(form.getCompanyId() == null){
				baseDto.setRcode(BaseDto.ERROR_RCODE);
				baseDto.setRinfo("请求的参数公司Id(companyId)不能为空!");
				return JSON.toJSONString(baseDto);
			}
			jsonStr = companyService.getAllCompanyAndSubordinate(form.getCompanyId());
		} catch (Exception e) {
			log.error("getAllCompanyAndSubordinate ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
		
	}
	/**
	 * 根据账号Id(userAccountId)获取能访问的公司的详细信息列表
	 * @author liukh
	 * @date 2017-7-24 下午4:02:40
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/canVisit/companyAndSubordinateDetail/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllCanVisitCompanyAndSubordinateDetailByUserAccountId(HttpServletRequest request,
			HttpServletResponse response, CompanyQueryForm form) {
		BaseDto baseDto = new BaseDto();
		String jsonStr = JSON.toJSONString(baseDto);
		try {
			if(form  == null){
				baseDto.setRcode(BaseDto.ERROR_RCODE);
				baseDto.setRinfo("请求的参数不能为空!");
				return JSON.toJSONString(baseDto);
			}else if(form.getUserAccountId() == null){
				baseDto.setRcode(BaseDto.ERROR_RCODE);
				baseDto.setRinfo("请求的用户Id(userAccountId)不能为空!");
				return JSON.toJSONString(baseDto);
			}
			jsonStr = companyService.getAllCanVisitCompanyAndSubordinateDetailByUserAccountId(form.getUserAccountId());
		} catch (Exception e) {
			log.error("getAllCanVisitCompanyAndSubordinateDetailByUserAccountId ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
		
	}
	
	/**
	 * 根据账号Id(userAccountId)获取能访问的公司的信息列表
	 * @author liukh
	 * @date 2017-8-31 上午10:12:00
	 * @param request
	 * @param response
	 * @param form
	 * @return
	 */
	@RequestMapping(value = "/canVisit/companyAndSubordinate/allList", method = { RequestMethod.GET })
	@ResponseBody
	public String getAllCanVisitCompanyAndSubordinateByUserAccountId(HttpServletRequest request,
			HttpServletResponse response, CompanyQueryForm form) {
		BaseDto baseDto = new BaseDto();
		String jsonStr = JSON.toJSONString(baseDto);
		try {
			if(form  == null){
				baseDto.setRcode(BaseDto.ERROR_RCODE);
				baseDto.setRinfo("请求的参数不能为空!");
				return JSON.toJSONString(baseDto);
			}else if(form.getUserAccountId() == null){
				baseDto.setRcode(BaseDto.ERROR_RCODE);
				baseDto.setRinfo("请求的用户Id(userAccountId)不能为空!");
				return JSON.toJSONString(baseDto);
			}
			jsonStr = companyService.getAllCanVisitCompanyAndSubordinateByUserAccountId(form.getUserAccountId());
		} catch (Exception e) {
			log.error("getAllCanVisitCompanyAndSubordinateByUserAccountId ---- 异常,message = " + e.getMessage());
			e.printStackTrace();
		}
		return jsonStr;
		
	}

}
