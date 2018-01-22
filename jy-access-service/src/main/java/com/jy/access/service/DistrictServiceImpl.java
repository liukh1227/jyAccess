package com.jy.access.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.jy.access.base.dao.IBaseDao;
import com.jy.access.common.FrameworkUtils;
import com.jy.access.mapper.DistrictMapper;
import com.jy.base.common.ItemPage;
import com.jy.base.entity.base.dto.BaseObjDto;
import com.jy.entity.form.DistrictQueryForm;
import com.jy.entity.po.District;

@Service("districtService")
@Scope("prototype")
public class DistrictServiceImpl implements DistrictService {
	private static final Logger log = Logger.getLogger(DistrictServiceImpl.class);
	@Autowired
	private IBaseDao baseDao;

	@Override
	public String getDistrictList(DistrictQueryForm form) {
		String jsonStr = "";
		BaseObjDto<ItemPage<District>> dto = new BaseObjDto<ItemPage<District>>();
		try {

			BaseObjDto<ItemPage<District>> pagesDto = baseDao.getPageList(
					DistrictMapper.class, District.class, form,
					"getDistrictPageList");
			if (FrameworkUtils.isSuccess(pagesDto)) {
				dto = pagesDto;
				log.info("getDistrictList success");
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getDistrictList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getDistrictList Exception !");
			throw new RuntimeException("getDistrictList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

	@Override
	public String getAllDistrictList(DistrictQueryForm form) {
		String jsonStr = "";
		BaseObjDto<List<District>> dto = new BaseObjDto<List<District>>();
		try {
			BaseObjDto<List<District>> dataDto = baseDao.getList(
					DistrictMapper.class, District.class,
					"getAllDistrictList", form);
			if (FrameworkUtils.isSuccess(dataDto)
					&& dataDto.getData() != null) {
				dto = dataDto;
			
			} else {
				FrameworkUtils.setNoData(dto);
				log.error("getAllDistrictList failure");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("getAllDistrictList Exception");
			throw new RuntimeException("getAllDistrictList Exception!");
		}
		jsonStr = JSON.toJSONString(dto);
		return jsonStr;
	}

}
