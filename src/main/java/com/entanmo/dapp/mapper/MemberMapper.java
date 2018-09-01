package com.entanmo.dapp.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * 
 *  数据库操作接口
 * 
 */
public interface MemberMapper<T,P> extends BaseMapper<T,P> {

	/**
	 * 
	 * 根据primaryKey删除
	 * 
	 */
	 public Integer deleteByPrimaryKey(@Param("memberId") Integer memberId);


	/**
	 * 
	 * 根据primaryKey获取对象
	 * 
	 */
	 public T selectByPrimaryKey(@Param("memberId") Integer memberId);


	public T selectByAccount(@Param("account")String account);


}
