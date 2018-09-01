package com.entanmo.dapp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.entanmo.dapp.dao.enums.PageSize;
import com.entanmo.dapp.param.DappDeployParam;
import com.entanmo.dapp.po.DappDeploy;
import com.entanmo.dapp.vo.PaginationResult;
import com.entanmo.dapp.vo.SimplePage;
import com.entanmo.dapp.mapper.DappDeployMapper;
import com.entanmo.dapp.service.DappDeployService;


/**
 * 
 *  业务接口实现
 * 
 */
@Service("dappDeployService")
public class DappDeployServiceImpl implements DappDeployService {

	@Resource
	private DappDeployMapper<DappDeploy,DappDeployParam> dappDeployMapper;

	/**
	 * 
	 * 分页查询方法
	 * 
	 */
	public PaginationResult<DappDeploy> findListByPage(DappDeployParam param) {
		int count = this.dappDeployMapper.selectCount(param);
		int pageSize = param.getPageSize()==null?PageSize.SIZE15.getSize():param.getPageSize();
		if(pageSize <= 0) {
			 pageSize = PageSize.SIZE15.getSize();
		}
		int pageNo = 1;
		if (null != param.getPageNo() && param.getPageNo() > 0) {
			pageNo = param.getPageNo();
		}
		SimplePage page = new SimplePage(pageNo, count, pageSize);
		param.setPage(page);
		List<DappDeploy> list = this.dappDeployMapper.selectList(param);
		PaginationResult<DappDeploy> result = new PaginationResult<DappDeploy>(page, list);
		return result;
	}

	/**
	 * 
	 * 新增
	 * 
	 */
	public Integer add(DappDeploy bean){
		return this.dappDeployMapper.insert(bean);
	}

	/**
	 * 
	 * 修改
	 * 
	 */
	public Integer update(DappDeploy bean){
		return this.dappDeployMapper.update(bean);
	}

	/**
	 * 
	 * 删除
	 * 
	 */
	public Integer delete(DappDeploy bean){
		return this.dappDeployMapper.delete(bean);
	}

	/**
	 * 
	 * 根据primaryKey删除
	 * 
	 */
	 public Integer deleteDappDeployByPrimaryKey(Integer deployId){
		return this.dappDeployMapper.deleteByPrimaryKey(deployId);
	}


	/**
	 * 
	 * 根据primaryKey获取对象
	 * 
	 */
	 public DappDeploy getDappDeployByPrimaryKey(Integer deployId){
		return this.dappDeployMapper.selectByPrimaryKey(deployId);
	}

}