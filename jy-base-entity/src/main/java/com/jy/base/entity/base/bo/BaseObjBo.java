package com.jy.base.entity.base.bo;

import java.io.Serializable;

import com.jy.base.entity.base.dto.BaseObjDto;

/**
 * 
 * @author liukh
 * @date 2017-7-3 下午4:33:10
 * @param <T>
 */
public class BaseObjBo<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private BaseObjDto<T> baseObjDto;

	public BaseObjDto<T> getBaseObjDto() {
		return baseObjDto;
	}

	public void setBaseObjDto(BaseObjDto<T> baseObjDto) {
		this.baseObjDto = baseObjDto;
	}

}
