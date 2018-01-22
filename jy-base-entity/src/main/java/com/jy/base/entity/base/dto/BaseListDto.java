package com.jy.base.entity.base.dto;

/**
 * 基本分页po
 * @author liukh
 * @date 2017-7-3 下午4:30:39
 */
public class BaseListDto<T> extends BaseDto {

	private static final long serialVersionUID = 1L;
	private Pages<T> data;

	public Pages<T> getData() {
		return data;
	}

	public void setData(Pages<T> data) {
		this.data = data;
	}
}
