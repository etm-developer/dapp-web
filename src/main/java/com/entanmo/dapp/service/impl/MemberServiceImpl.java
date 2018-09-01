package com.entanmo.dapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entanmo.dapp.dao.enums.PageSize;
import com.entanmo.dapp.mapper.MemberMapper;
import com.entanmo.dapp.param.MemberParam;
import com.entanmo.dapp.po.Member;
import com.entanmo.dapp.service.MemberService;
import com.entanmo.dapp.vo.PaginationResult;
import com.entanmo.dapp.vo.SimplePage;

/**
 * 
 * 业务接口实现
 * 
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper<Member, MemberParam> memberMapper;

	/**
	 * 
	 * 分页查询方法
	 * 
	 */
	public PaginationResult<Member> findListByPage(MemberParam param) {
		int count = this.memberMapper.selectCount(param);
		int pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();
		if (pageSize <= 0) {
			pageSize = PageSize.SIZE15.getSize();
		}
		int pageNo = 1;
		if (null != param.getPageNo() && param.getPageNo() > 0) {
			pageNo = param.getPageNo();
		}
		SimplePage page = new SimplePage(pageNo, count, pageSize);
		param.setPage(page);
		List<Member> list = this.memberMapper.selectList(param);
		PaginationResult<Member> result = new PaginationResult<Member>(page, list);
		return result;
	}

	/**
	 * 
	 * 新增
	 * 
	 */
	public Integer add(Member bean) {
		return this.memberMapper.insert(bean);
	}

	/**
	 * 
	 * 修改
	 * 
	 */
	public Integer update(Member bean) {
		return this.memberMapper.update(bean);
	}

	/**
	 * 
	 * 删除
	 * 
	 */
	public Integer delete(Member bean) {
		return this.memberMapper.delete(bean);
	}

	/**
	 * 
	 * 根据primaryKey删除
	 * 
	 */
	public Integer deleteMemberByPrimaryKey(Integer memberId) {
		return this.memberMapper.deleteByPrimaryKey(memberId);
	}

	/**
	 * 
	 * 根据primaryKey获取对象
	 * 
	 */
	public Member getMemberByPrimaryKey(Integer memberId) {
		return this.memberMapper.selectByPrimaryKey(memberId);
	}

	public Member getMemberByAccount(String account) {

		return memberMapper.selectByAccount(account);
	}

}
