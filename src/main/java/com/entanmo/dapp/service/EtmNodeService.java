package com.entanmo.dapp.service;

import java.util.List;

import com.entanmo.dapp.param.EtmNodeParam;
import com.entanmo.dapp.po.EtmNode;
import com.entanmo.dapp.vo.PaginationResult;

/**
 * 
 * 业务接口
 * 
 */
public interface EtmNodeService {

	/**
	 * 
	 * 分页查询
	 * 
	 */
	public PaginationResult<EtmNode> findListByPage(EtmNodeParam param);

	/**
	 * 
	 * 新增
	 * 
	 */
	public Integer add(EtmNode bean);

	/**
	 * 
	 * 修改
	 * 
	 */
	public Integer update(EtmNode bean);

	/**
	 * 
	 * 删除
	 * 
	 */
	public Integer delete(EtmNode bean);

	/**
	 * 
	 * 根据primaryKey删除
	 * 
	 */
	public Integer deleteEtmNodeByPrimaryKey(Integer nodeId);

	/**
	 * 
	 * 根据primaryKey获取对象
	 * 
	 */
	public EtmNode getEtmNodeByPrimaryKey(Integer nodeId);

	public EtmNode getEtmNode(String ip, Integer port);
	
	public List<EtmNode> getRandomUsableNode(int num);

}