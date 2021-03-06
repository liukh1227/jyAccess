package com.jy.access.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jy.access.common.DataCache;
import com.jy.access.common.RedisSessionManager;
import com.jy.access.service.IInitService;


/**
 * @desc 
 * @author lliukh
 * @date 2016-12-9 上午10:23:17
 */
@Service("initService")
@Scope("prototype")
public class InitServiceImpl implements IInitService {

	@Resource
	private RedisSessionManager redisSessionManager;

	/**
	 * @desc 初始化redis
	 * @author lliukh
	 * @date 2016-12-5 上午11:17:17
	 */
	public void initRedis() {
		DataCache.getInstance(redisSessionManager);
	}
		
}
