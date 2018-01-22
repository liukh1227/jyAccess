package com.jy.base.entity.base.bo;

import java.io.Serializable;

import com.jy.base.entity.base.dto.BaseListDto;

/**
 * 
 * @author liukh
 * @date 2017-7-3 下午4:33:06
 * @param <T>
 */
public class BaseListBo<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private BaseListDto<T> baseObjList;

	public BaseListDto<T> getBaseObjList() {
		return baseObjList;
	}

	public void setBaseObjList(BaseListDto<T> baseObjList) {
		this.baseObjList = baseObjList;
	}

}
