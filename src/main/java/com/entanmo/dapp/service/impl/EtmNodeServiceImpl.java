package com.entanmo.dapp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entanmo.dapp.dao.enums.PageSize;
import com.entanmo.dapp.mapper.EtmNodeMapper;
import com.entanmo.dapp.mapper.MemberMapper;
import com.entanmo.dapp.param.EtmNodeParam;
import com.entanmo.dapp.param.MemberParam;
import com.entanmo.dapp.po.EtmNode;
import com.entanmo.dapp.po.Member;
import com.entanmo.dapp.service.EtmNodeService;
import com.entanmo.dapp.vo.PaginationResult;
import com.entanmo.dapp.vo.SimplePage;


/**
 * 
 *  业务接口实现
 * 
 */
@Service("etmNodeService")
public class EtmNodeServiceImpl implements EtmNodeService {

	@Autowired
	private EtmNodeMapper<EtmNode, EtmNodeParam> etmNodeMapper;
	
	@Autowired
	private MemberMapper<Member, MemberParam> memberMapper;

	/**
	 * 
	 * 分页查询方法
	 * 
	 */
	public PaginationResult<EtmNode> findListByPage(EtmNodeParam param) {
		int count = this.etmNodeMapper.selectCount(param);
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
		List<EtmNode> list = this.etmNodeMapper.selectList(param);
		Map<Integer, Member> ownerMap = new HashMap<Integer, Member>();
		for (EtmNode node: list){
			Integer ownerId = node.getOwnerId();
			if (ownerId != null && ownerId > 0){
				Member member = ownerMap.get(ownerId);
				if (member != null){
					node.setOwner(member.getAccount());
				}else {
					member = memberMapper.selectByPrimaryKey(ownerId);
					if (member != null){
						node.setOwner(member.getAccount());
						ownerMap.put(ownerId, member);
					}
				}
			}
		}
		PaginationResult<EtmNode> result = new PaginationResult<EtmNode>(page, list);
		return result;
	}

	/**
	 * 
	 * 新增
	 * 
	 */
	public Integer add(EtmNode bean){
		return this.etmNodeMapper.insert(bean);
	}

	/**
	 * 
	 * 修改
	 * 
	 */
	public Integer update(EtmNode bean){
		return this.etmNodeMapper.update(bean);
	}

	/**
	 * 
	 * 删除
	 * 
	 */
	public Integer delete(EtmNode bean){
		return this.etmNodeMapper.delete(bean);
	}

	/**
	 * 
	 * 根据primaryKey删除
	 * 
	 */
	 public Integer deleteEtmNodeByPrimaryKey(Integer nodeId){
		return this.etmNodeMapper.deleteByPrimaryKey(nodeId);
	}


	/**
	 * 
	 * 根据primaryKey获取对象
	 * 
	 */
	 public EtmNode getEtmNodeByPrimaryKey(Integer nodeId){
		return this.etmNodeMapper.selectByPrimaryKey(nodeId);
	}

	@Override
	public EtmNode getEtmNode(String ip, Integer port) {

		return this.etmNodeMapper.selectNode(ip, port);
	}
	
	@Override
	public List<EtmNode> getRandomUsableNode(int num){
		
		return this.etmNodeMapper.selectRandomUsableNode(num);
	}
}
