package com.jy.access.mapper;

/**
/**
 * 基本Mapper
 * @author liukh
 * @date 2017-7-6 下午9:30:27
 */
public interface BaseMapper {

	public int deleteByPrimaryKey(String id);

	public int insert(Object record);

	public int insertSelective(Object record);

	public Object selectByPrimaryKey(String id);

	public int updateByPrimaryKeySelective(Object record);

	public int updateByPrimaryKey(Object record);
}
