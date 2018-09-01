package com.entanmo.dapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * 数据库操作接口
 * 
 */
public interface EtmNodeMapper<T, P> extends BaseMapper<T, P> {

	/**
	 * 
	 * 根据primaryKey删除
	 * 
	 */
	public Integer deleteByPrimaryKey(@Param("nodeId") Integer nodeId);

	/**
	 * 
	 * 根据primaryKey获取对象
	 * 
	 */
	public T selectByPrimaryKey(@Param("nodeId") Integer nodeId);

	public T selectNode(@Param("ip")String ip, @Param("port")Integer port);
	
	
	public List<T> selectRandomUsableNode(@Param("num")int num);

}
