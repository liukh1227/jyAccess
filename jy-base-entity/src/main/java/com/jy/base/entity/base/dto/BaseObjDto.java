package com.jy.base.entity.base.dto;

/**
 * 基本objPo
 * @author liukh
 * @date 2017-7-3 下午4:31:37
 * @param <T>
 */
public class BaseObjDto<T> extends BaseDto {

	private static final long serialVersionUID = 1L;
	public T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
