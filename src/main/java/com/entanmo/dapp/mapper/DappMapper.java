package com.entanmo.dapp.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * 
 *  数据库操作接口
 * 
 */
public interface DappMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 
	 * 根据primaryKey删除
	 * 
	 */
	 public Integer deleteByPrimaryKey(@Param("dappId") Integer dappId);


	/**
	 * 
	 * 根据primaryKey获取对象
	 * 
	 */
	 public T selectByPrimaryKey(@Param("dappId") Integer dappId);


}
