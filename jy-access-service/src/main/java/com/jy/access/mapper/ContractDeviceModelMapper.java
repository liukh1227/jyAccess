package com.jy.access.mapper;

import java.util.List;

import com.jy.access.base.mapper.BaseMapper;
import com.jy.entity.form.ContractDeviceModelQueryForm;
import com.jy.entity.vo.ContractDeviceModelVo;

public interface ContractDeviceModelMapper extends BaseMapper{
	public List<ContractDeviceModelVo>getContractDeviceModelPageList(ContractDeviceModelQueryForm form);
	public List<ContractDeviceModelVo>getContractDeviceModelAllList(ContractDeviceModelQueryForm form);

}