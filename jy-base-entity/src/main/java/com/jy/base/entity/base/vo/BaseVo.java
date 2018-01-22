package com.jy.base.entity.base.vo;

import java.io.Serializable;
/**
 * 
 * @author liukh
 * @date 2017-7-3 下午4:32:51
 */
public class BaseVo implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer rcode;
	private String rinfo;
	public Integer getRcode() {
		return rcode;
	}
	public void setRcode(Integer rcode) {
		this.rcode = rcode;
	}
	public String getRinfo() {
		return rinfo;
	}
	public void setRinfo(String rinfo) {
		this.rinfo = rinfo;
	}
}
