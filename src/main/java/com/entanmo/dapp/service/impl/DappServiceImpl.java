package com.entanmo.dapp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entanmo.dapp.dao.enums.PageSize;
import com.entanmo.dapp.mapper.DappMapper;
import com.entanmo.dapp.mapper.MemberMapper;
import com.entanmo.dapp.param.DappParam;
import com.entanmo.dapp.param.MemberParam;
import com.entanmo.dapp.po.Dapp;
import com.entanmo.dapp.po.Member;
import com.entanmo.dapp.service.DappService;
import com.entanmo.dapp.vo.PaginationResult;
import com.entanmo.dapp.vo.SimplePage;


/**
 * 
 *  业务接口实现
 * 
 */
@Service("dappService")
public class DappServiceImpl implements DappService {

	@Autowired
	private DappMapper<Dapp,DappParam> dappMapper;
	
	@Autowired
	private MemberMapper<Member, MemberParam> memberMapper;

	/**
	 * 
	 * 分页查询方法
	 * 
	 */
	public PaginationResult<Dapp> findListByPage(DappParam param) {
		int count = this.dappMapper.selectCount(param);
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
		List<Dapp> list = this.dappMapper.selectList(param);
		Map<Integer, Member> cachedMember = new HashMap<>();
		for (Dapp dapp: list){
			Integer memberId = dapp.getCreatorId();
			Member member = cachedMember.get(memberId);
			if (member != null){
				dapp.setOwner(member.getAccount());
			}else {
				member = memberMapper.selectByPrimaryKey(memberId);
				if (member != null){
					cachedMember.put(memberId, member);
					dapp.setOwner(member.getAccount());
				}
			}
		}
		
		PaginationResult<Dapp> result = new PaginationResult<Dapp>(page, list);
		return result;
	}

	/**
	 * 
	 * 新增
	 * 
	 */
	public Integer add(Dapp bean){
		return this.dappMapper.insert(bean);
	}

	/**
	 * 
	 * 修改
	 * 
	 */
	public Integer update(Dapp bean){
		return this.dappMapper.update(bean);
	}

	/**
	 * 
	 * 删除
	 * 
	 */
	public Integer delete(Dapp bean){
		return this.dappMapper.delete(bean);
	}

	/**
	 * 
	 * 根据primaryKey删除
	 * 
	 */
	 public Integer deleteDappByPrimaryKey(Integer dappId){
		return this.dappMapper.deleteByPrimaryKey(dappId);
	}


	/**
	 * 
	 * 根据primaryKey获取对象
	 * 
	 */
	 public Dapp getDappByPrimaryKey(Integer dappId){
		return this.dappMapper.selectByPrimaryKey(dappId);
	}

	@Override
	public int getCount(DappParam param) {
		// TODO Auto-generated method stub
		return dappMapper.selectCount(param);
	}

}