package com.jy;

public class EntityConstant {
	/**
	 * 无效
	 */
	public static final String STATUS_INVALID = "0";
	/**
	 * 有效
	 */
	public static final String STATUS_VALID = "1";
	

	
	
	/**
	 * 用户级别
	 */
	
	//系统用户
	public static final String USERACCOUNT_LEVEL_SYSTEM = "S";
	//普通用户
	public static final String USERACCOUNT_LEVEL_USER = "U";
	//承租方
	public static final String USERACCOUNT_LEVEL_CHENGZU = "C";
	
	/**
	 * 数据范围范围
	 */
	//全局
	public static final String USERACCOUNT_DATASCOPE_GLOBAL = "G";
	//局部
	public static final String USERACCOUNT_DATASCOPE_PART = "P";
	
	
	/**
	 * 设备状态
	 */
	
	//待租
	public static final String DEVICE_STATUS_WAITWORK = "20001";
	//工作中
	public static final String DEVICE_STATUS_WORKING = "30001";
	//报停
	public static final String DEVICE_STATUS_STOPING = "30002";
	//报修
	public static final String DEVICE_STATUS_REPARIRSING = "30003";
	//故障中
	public static final String DEVICE_STATUS_FAULTING = "40001";
	//废弃
	public static final String DEVICE_STATUS_DISCARD ="90001";
	
	/**
	 * 结款方式
	 */
	//月结
	 public static final String TM_PAYMETHOD_MONTH="M";
	 //预付
	 public static final String TM_PAYMETHOD_YUFU="Y";
	 
	 /**
	  * 计费模式
	  */
	//月租
	 public static final String TM_BILLINGTYPE_MONTH="M";
	//周租
	public static final String TM_BILLINGTYPE_WEEK="W";
	//日租
	public static final String TM_BILLINGTYPE_DAY="D";
	
	/**
	 * 签约单来源
	 */
	//手工
	public static final String TM_CONTRACTORDER_SOURCE_HAND="0";
	//报价单转换
	public static final String TM_CONTRACTORDER_SOURCE_QUOTAION="1";
	//网上下单
	public static final String TM_CONTRACTORDER_SOURCE_ONLINE="2";
	
	/**
	 * 签约单状态
	 */
	//废弃
	public static final String TM_CONTRACTORDER_STATUS_DELETE="0";
	//未签约
	public static final String TM_CONTRACTORDER_STATUS_NOTMAKESURE="1";
	//已签约
	public static final String TM_CONTRACTORDER_STATUS_MAKESURE="2";
	//执行中
	public static final String TM_CONTRACTORDER_STATUS_WORKING="3";
	//终止
	public static final String TM_CONTRACTORDER_STATUS_STOP="4";
	//终止
	public static final String TM_CONTRACTORDER_STATUS_OVER="5";
	
	
	public static final String QUERY_ALL = "ALL";
	
	/**
	 * 报价单状态
	 */
	//删除
	public static final String TM_QUOTATION_STATUS_DELETE="0";
	//未执行
	public static final String TM_QUOTATION_STATUS_CREATE="1";
	
	
	/**
	 * 进出库类型
	 */
	//进库
	public static final String TM_DELIVERYORDER_DELIVERYTYPE_INPUT ="I";
	//出库
	public static final String TM_DELIVERYORDER_DELIVERYTYPE_OUTPUT ="O";
	/**
	 * 报修处理类型
	 */
	//未处理
	public static final String TM_REPARIRSORDER_REPAIRSTYPE_CREATE = "0";
	//现场已维修
	public static final String TM_REPARIRSORDER_REPAIRSTYPE_DONE = "1";
	//更换设备
	public static final String TM_REPARIRSORDER_REPAIRSTYPE_REPLACE = "2";
	
	/**
	 * 报停状态
	 */
	//报停中
	public static final String TM_STOPORDER_STOPSTATUS_STOP = "0";
	//已恢复
	public static final String TM_STOPORDER_STOPSTATUS_RESET = "1";
	
	/**
	 * 设备退场延期类型
	 */
	//延期
	public static final String TM_CHANGORDER_CHANGETYPE_YANQI = "Y";
	//退场
	public static final String TM_CHANGORDER_CHANGETYPE_TUICHANG = "T";
	//正常
	public static final String TM_CHANGORDER_CHANGETYPE_ZHENGCHANG = "Z";
	
	/**
	 * 打印
	 */
	//签约单
	public static final String DOWN_FILE_CONTRACTORDER_DIRECTORY ="velocityTemplate";
	public static final String DOWN_FILE_CONTRACTORDER_VELOCITY_NAME ="contractOrder.vm";
    public static final String PDF_FILE_NAME_CONTRACTORDER_NAME = "签约单";
	public static final String DOWN_FILE_CONTRACTORDER_PDF_NAME ="contractOrder.pdf";
	public static final String DOWN_FILE_CONTRACTORDER_PDF_PATH_TEST ="d:/test/velocityTemplate/contractOrder.pdf";
	public static final String DOWN_FILE_CONTRACTORDER_PDF_PATHT ="/home/jy/usr/local/velocityTemplate/contractOrder.pdf";
	public static final String DOWN_FILE_CONTRACTORDER_PDF_PATH ="contractOrder.pdf";

	
	//进出场确认单
	public static final String SITE_PDF_FILE_PACKAGE = "pdfTemplate";
	//进场单模板
	public static final String PDF_FILE_NAME_DEVICEINMAKESUREORDER = "DeviceInMakesureOrder.pdf";
	public static final String PDF_FILE_NAME_DEVICEINMAKESUREORDER_NAME = "进场确认单";
	//退场单模板
	public static final String PDF_FILE_NAME_DEVICEOUTMAKESUREORDER = "DeviceOutMakesureOrder.pdf";
	public static final String PDF_FILE_NAME_DEVICEOUTMAKESUREORDER_NAME = "退场确认单";
	
	/**
	 * 设备分配类型
	 */
	// 存在公司设备类型
	//存在
	public static final String DEVICE_COMPANYMODEL_ALLOTTYPE_EXIST = "E";
	//不存在（要增加公司设备型号）
	public static final String DEVICE_COMPANYMODEL_ALLOTTYPE_NONEXISTENCE = "N";
}
