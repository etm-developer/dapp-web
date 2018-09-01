package com.entanmo.dapp.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * 
 *  数据库操作接口
 * 
 */
public interface DappDeployMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 
	 * 根据primaryKey删除
	 * 
	 */
	 public Integer deleteByPrimaryKey(@Param("deployId") Integer deployId);


	/**
	 * 
	 * 根据primaryKey获取对象
	 * 
	 */
	 public T selectByPrimaryKey(@Param("deployId") Integer deployId);


}
