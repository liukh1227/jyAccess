package com.jy.base.entity.base.bo;

import java.io.Serializable;

import com.jy.base.entity.base.dto.BaseDto;

/**
 * 
 * @author liukh
 * @date 2017-7-3 下午4:33:01
 */
public class BaseBo implements Serializable {

	private static final long serialVersionUID = 1L;
	private BaseDto baseDto;

	public BaseDto getBaseDto() {
		return baseDto;
	}

	public void setBaseDto(BaseDto baseDto) {
		this.baseDto = baseDto;
	}

}
