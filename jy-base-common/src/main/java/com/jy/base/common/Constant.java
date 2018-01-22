package com.jy.base.common;



/**
 * 普通常量
 * @author liukh
 * @date 2017-7-3 下午4:34:38
 */
public class Constant {

	/**
	 * 有效
	 */
	public static final String STATUS_VALID = "0";
	/**
	 * 无效
	 */
	public static final String STATUS_INVALID = "1";
	
	/**
	 * 环境变量key
	 */
	public static final String ENV = "ENVIRONMENT";

	/**
	 * shiro start
	 */
	/** 加密类型*/
	public static final String ALGORITHMNAME = "MD5";
	/** 加密循环次数*/
	public static final int HASHITERATIONS = 2;
	/**
	 * shiro end
	 */
	/**
	 * 左方菜单名称
	 */
	public static final String MENU = "MENU";
	/**
	 * 分页
	 */
	public static final String ATTRIBUTE_ITEMPAGE = "ITEMPAGE";
	/**
	 * 用户
	 */
	public static final String SYSTEM_VISITOR = "SYSTEM_VISITOR";
	/**
	 * FTP BIZ
	 */
	public static final String FTP_BIZ = "http://api.chebaotec.com/biz";
	/**
	 * FTP APP
	 */
	public static String FTP_APP = "http://api.chebaotec.com/app";

	/**
	 * http 连接超时
	 */
	public static final int HTTP_CONNECT_TIMEOUT = 10000;
	/**
	 * http 请求超时
	 */
	public static final int HTTP_REQUEST_TIMEOUT = 10000;
	/**
	 * http socket链接超时
	 */
	public static final int HTTP_SOCKET_TIMEOUT = 10000;
	/**
	 * http 编码
	 */
	public static final String HTTP_ENCODING = "UTF-8";
	/**
	 * http content type
	 */
	public static final String HTTP_CONTENT_TYPE = "application/json";
	
	/**
	 * Redis
	 */
	public static final String WASH_REDIS_ROLE = "washRole";
	public static final String WASH_REDIS_ROLE_PRIVILGES = "washRolePrevilges";
	public static final String WASH_REDIS_MODULE = "washModule";
	public static final String WASH_REDIS_LEVEL = "washLevel";
	public static final String WASH_REDIS_TAGS = "washTags";
	public static final String WASH_REDIS_INDUSTRY_TAGS = "washIndustryTags";
	//读的redis
	public static final String REDIS_SLAVE_READ_KEY = "jedisShardInfoSlave";
	//写的redis
	public static final String REDIS_MASTER_WRITE_KEY = "jedisShardInfoMaster";
	//第几个库
	public static final Integer REDIS_DB = 9;
	

	/**
	 * app key
	 */
	public static final String KEY = "key:washing.com/android.apk";
	
	/**
	 * 系统用户信息以及系统处理feedback的配置
	 */
	public static final String SYSTEM_NAME = "系统";
	public static final String SYSTEM_ID = "0";
	public static final String SYSTEM_DISPOSE_STATUS = "1";
	/**辅助程序*/
	public static final String ASSIS_TYPE = "1";
	/**主程序*/
	public static final String MAIN_TYPE = "0";
	

	
	
	


	/*
	 * 加密的key
	 */
	public static final String JSON_RESPONSE_KEY = "key:zhaojinet.com/android.apk";

	/**
	 * 抛单抢单时监视keys
	 */
	public static final String WATCHKEYS = "watchkeys";
	public static final String WATCHKEYS_RENTTHROW = "watchkeys4RentThrow";
	
	/**
	 * 设备上传token
	 */
	public static final String APP_DEVICEUPLOAD_TOKEN = "DEVICEUPLOAD";
	/**
	 * 设备上传token
	 */
	public static final String APP_USERUPLOAD_TOKEN = "USERUPLOAD";

	/*
	 * 请求成功及描述
	 */
	public static final Integer JSON_RESPONSE_STATUS_SUCCESS = 0;

	public static final Integer USER_LOGON_ERROR_USERNOTEXIST = 61;
	public static final String USER_LOGON_ERROR_USERNOTEXIST_INFOR = "您输入的账号不存在,请重新输入!";

	public static final Integer USER_LOGON_ERROR_PASSWORDNOTMATCH = 62;
	public static final String USER_LOGON_ERROR_PASSWORDNOTMATCH_INFOR = "您输入的密码和账户名不匹配,请重新输入!";

	public static final String JSON_RESPONSE_STATUS_SUCCESS_INFOR = "request sucess";
	/*
	 * 请求不成功及描述
	 */
	public static final Integer JSON_RESPONSE_STATUS_ERROR = 21;

	public static final String JSON_RESPONSE_STATUS_ERROR_INFOR = "request error or request data is null";

	/**
	 * 没有符合要求的数据
	 */
	public static final int NO_DATA_RCODE = 55;
	/**
	 * 所用的账号在客户端登录的时候匹配
	 */
	public static final int NOT_MATCH_COMPANYBUSINESSTYPE = 22;

	public static final String LOGON_LEASINGSIDE = "你所用的账号属于机主端账号，请在找机网-机主端登录 !";
	public static final String LOGON_LESSEESIDE = "你所用的账号属于找机网账号，请在找机网客户端登录 !";


	/**
	 * 默认没有图片的空路径
	 */
	public static final String REQUIRED_PICTURE_TEMP = "picture temp path";
	/**
	 * 身份证或者营业执照暂未添加标识
	 */
	public static final String COMPANY_INFOR_TENP = "0";




	/**
	 * 询价单的状态 0：草稿 1：询价中 2：待确认 3：确认订单（结束）---- 承租方确认下单 4：关闭询价（结束）---- 承租方结束询价
	 * 询价单的状态 0：询价车 1：询价中 2：已报价 3：确认订单（结束）---- 承租方确认下单 4：关闭询价（结束）---- 承租方结束询价
	 */
	public static final Integer INQUERYORDER_STATUS_CREATE = 0;
	public static final Integer INQUERYORDER_STATUS_REPLY = 1;
	public static final Integer INQUERYORDER_STATUS_UNMAKESURE = 2;
	public static final Integer INQUERYORDER_STATUS_MAKEORDER = 3;
	public static final Integer INQUERYORDER_STATUS_OVER = 4;

	/**
	 * 求租需求单的状态 0：待报价 1：已报价 2：已结束 3、待新的报价 求租需求单的状态 0：待报价 1：已报价 2：已结束 3、待新的报价
	 */
	public static final Integer INQUERYRENT_STATUS_CREATE = 0;
	public static final Integer INQUERYRENT_STATUS_MAKESURE = 1;
	public static final Integer INQUERYRENT_STATUS_OVER = 2;
	public static final Integer INQUERYRENT_STATUS_WAIT = 3;

	/**
	 * 求租需求单报价单的状态 0：待确认 1：已确认（选中） 2：为未选中 求租需求单报价单的状态 0：竞标中 1：已中标 2：未中标
	 */
	public static final Integer INQUERYRENTQUOTE_STATUS_CREATE = 0;
	public static final Integer INQUERYRENTQUOTE_STATUS_MAKESURE = 1;
	public static final Integer INQUERYRENTQUOTE_STATUS_OVER = 2;

	/**
	 * 抛单状态 0 待抢单 1 已接单 2 关闭 抛单状态 0 待抢单 1 已接单 2 关闭
	 */
	public static final Integer INQUERYRENTTHROW_STATUS_CREATE = 0;
	public static final Integer INQUERYRENTTHROW_STATUS_MAKESURE = 1;
	public static final Integer INQUERYRENTTHROW_STATUS_CLOSE = 2;




	// 时间工具类参数
	public static final int MONTH_DAYS = 30;
	public static final String DATETYPE_DAY = "1";
	public static final String DATETYPE_MONTH = "0";
	public static final String DATEFORMATTYPE = "yyyy-MM-dd";
	public static final String DATE_DAY = "day";
	public static final String DATE_MONTH = "month";
	public static final String DATE_YEAR = "year";

	public static final Integer BILLINGTYPE_MONTH = 0;
	public static final Integer BILLINGTYPE_DAY = 1;




	/**
	 * 用户的状态 0:有效 1：黑名单
	 */

	public static final int USER_STATUS_TAKEEFFICACY = 0;
	public static final int USER_STATUS_LOSEEFFICACY = 1;

	/**
	 * 用户所属公司的状态 0:待审核 1：审核通过 2：审核不通过
	 */

	public static final int COMAPNY_STATUS_CREATE = 0;
	public static final int COMAPNY_STATUS_TAKEEFFICACY = 1;
	public static final int COMAPNY_STATUS_NOPASS = 2;


	/**
	 * 公司性质 0：租赁方 1：承租方 公司性质 0：出租方 1：承租方
	 * 
	 */

	public static final String COMPANY_BUSSINESS_LEASINGSID = "0";
	public static final String COMPANY_BUSSINESS_LESSEESIDE = "1";



	/**
	 * 内部消息类型 0：未读 1：已读
	 */
	public static final Integer MESSAGE_STATUS_UNREAD = 0;
	public static final Integer MESSAGE_STATUS_READED = 1;



	/**
	 * 短信下发历史状态 0:未发送 1：已发送
	 */
	public static final Integer SENDCODE_USEND = 0;
	public static final Integer SENDCODE_SENDED = 1;
	/**
	 * 单价标识
	 */
	public static final String PRICETAG_MONTH = " 元/月";
	public static final String PRICETAG_DAY = " 元/天";

	/**
	 * log记录
	 */
	public static final String LOG_LOCAL_FILE_PATH = "E:\\log";
	/**
	 * 0:登录 1:找机设备 2:求租需求单 3：抛单 4：项目信息
	 */
	public static final String LOG_BUSINESSNAME_LOGON = "0";
	public static final String LOG_BUSINESSNAME_COMPANYDEVICE = "1";
	public static final String LOG_BUSINESSNAME_RENTORDER = "2";
	public static final String LOG_BUSINESSNAME_RENTTHROWORDER = "3";
	public static final String LOG_BUSINESSNAME_PROJECT = "4";

	/**
	 * 操作类型 0：登录 1：查看
	 */
	public static final String LOG_PROCESSTYPENAME_LOGON = "0";
	public static final String LOG_PROCESSTYPENAME_VIEW = "1";
	/**
	 * 操作端类型 0：出租方 1：承租方
	 * 
	 */
	public static final String LOG_CLIENTSIDE_LEASING = "0";
	public static final String LOG_CLIENTSIDE_LESSEE = "1";
	/**
	 * 日志来源 0：android 1:ios 2:web
	 */
	public static final String LOG_CLIENTSIDETYPE_ANDROID = "0";
	public static final String LOG_CLIENTSIDETYPE_IOS = "1";
	public static final String LOG_CLIENTSIDETYPE_WEB = "2";
	/**
	 * 日志操作者 0：游客
	 */
	public static final String LOG_OPERATOR_VISITOR = "0";

	public static final String dataFormat1 = "yyyy-MM-dd";
	public static final String dataFormat4 = "yyyy-MM-dd HH:mm:ss";
	
	public static final Integer DEFAULTPAGEINDEX = 1;
	public static final Integer DEFAULTPAGESIZE = 10;


}
