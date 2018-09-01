package com.entanmo.dapp.service;

import com.entanmo.dapp.param.DappDeployParam;
import com.entanmo.dapp.po.DappDeploy;
import com.entanmo.dapp.vo.PaginationResult;


/**
 * 
 *  业务接口
 * 
 */
public interface DappDeployService {

	/**
	 * 
	 * 分页查询
	 * 
	 */
	public PaginationResult<DappDeploy> findListByPage(DappDeployParam param);

	/**
	 * 
	 * 新增
	 * 
	 */
	public Integer add(DappDeploy bean);

	/**
	 * 
	 * 修改
	 * 
	 */
	public Integer update(DappDeploy bean);

	/**
	 * 
	 * 删除
	 * 
	 */
	public Integer delete(DappDeploy bean);

	/**
	 * 
	 * 根据primaryKey删除
	 * 
	 */
	 public Integer deleteDappDeployByPrimaryKey(Integer deployId);


	/**
	 * 
	 * 根据primaryKey获取对象
	 * 
	 */
	 public DappDeploy getDappDeployByPrimaryKey(Integer deployId);

}