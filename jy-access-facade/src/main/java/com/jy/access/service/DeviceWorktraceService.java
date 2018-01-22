package com.jy.access.service;

import com.jy.entity.po.DeviceWorktrace;

public interface DeviceWorktraceService {
	/**
	 * 新增设备工作跟踪记录
	 * @author liukh
	 * @date 2017-8-3 下午5:41:11
	 * @param data
	 * @return
	 */
	public String addDeviceWorktrace(String data);
	/**
	 * 处理新增设备工作跟踪记录
	 * @author liukh
	 * @date 2017-8-4 下午5:34:17
	 * @param data
	 * @return
	 */
	public String addDeviceWorktraceUseProcess(DeviceWorktrace deviceWorktrace);
	/**
	 * 查看设备跟踪记录
	 * @author liukh
	 * @date 2017-8-3 下午5:41:39
	 * @param dWorkTraceId
	 * @return
	 */
	public String getDeviceWorktraceService(String dWorkTraceId);

}
