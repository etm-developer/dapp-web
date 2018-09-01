package com.entanmo.dapp.service;

import com.entanmo.dapp.param.DappParam;
import com.entanmo.dapp.po.Dapp;
import com.entanmo.dapp.vo.PaginationResult;

/**
 * 
 * 业务接口
 * 
 */
public interface DappService {

	/**
	 * 
	 * 分页查询
	 * 
	 */
	public PaginationResult<Dapp> findListByPage(DappParam param);

	/**
	 * 
	 * 新增
	 * 
	 */
	public Integer add(Dapp bean);

	/**
	 * 
	 * 修改
	 * 
	 */
	public Integer update(Dapp bean);

	/**
	 * 
	 * 删除
	 * 
	 */
	public Integer delete(Dapp bean);

	/**
	 * 
	 * 根据primaryKey删除
	 * 
	 */
	public Integer deleteDappByPrimaryKey(Integer dappId);

	/**
	 * 
	 * 根据primaryKey获取对象
	 * 
	 */
	public Dapp getDappByPrimaryKey(Integer dappId);

	public int getCount(DappParam param);

}