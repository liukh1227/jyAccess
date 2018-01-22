package com.jy.access.mapper;

import java.util.List;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.form.DeviceQueryForm;
import com.jy.entity.vo.DeviceAmountVo;
import com.jy.entity.vo.DeviceVo;


public interface DeviceMapper extends BaseMapper{
	public List<DeviceVo> getDevicePageList(DeviceQueryForm form);
	public List<DeviceVo> getAllDeviceList(DeviceQueryForm form);
	public List<DeviceAmountVo> getDeviceStatisticsList(DeviceQueryForm form);
	public List<DeviceAmountVo> getDeviceGroupByCompanyStatisticsList(DeviceQueryForm form);
}