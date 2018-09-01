package com.entanmo.dapp.service;

import com.entanmo.dapp.param.MemberParam;
import com.entanmo.dapp.po.Member;
import com.entanmo.dapp.vo.PaginationResult;


/**
 * 
 *  业务接口
 * 
 */
public interface MemberService {

	/**
	 * 
	 * 分页查询
	 * 
	 */
	public PaginationResult<Member> findListByPage(MemberParam param);

	/**
	 * 
	 * 新增
	 * 
	 */
	public Integer add(Member bean);

	/**
	 * 
	 * 修改
	 * 
	 */
	public Integer update(Member bean);

	/**
	 * 
	 * 删除
	 * 
	 */
	public Integer delete(Member bean);

	/**
	 * 
	 * 根据primaryKey删除
	 * 
	 */
	 public Integer deleteMemberByPrimaryKey(Integer memberId);


	/**
	 * 
	 * 根据primaryKey获取对象
	 * 
	 */
	 public Member getMemberByPrimaryKey(Integer memberId);

	public Member getMemberByAccount(String account);

}