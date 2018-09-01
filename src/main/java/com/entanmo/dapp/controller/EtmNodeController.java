package com.entanmo.dapp.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.entanmo.dapp.dao.enums.MemberTypeEnum;
import com.entanmo.dapp.dao.enums.ResponseEnum;
import com.entanmo.dapp.param.EtmNodeParam;
import com.entanmo.dapp.po.EtmNode;
import com.entanmo.dapp.po.Member;
import com.entanmo.dapp.service.EtmNodeService;
import com.entanmo.dapp.vo.BaseResponse;
import com.entanmo.dapp.vo.PaginationResult;




/**
 * 
 *  控制层
 * 
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/node")
public class EtmNodeController extends BaseController{

	@Resource
	private EtmNodeService etmNodeService;
	
	private static final Logger logger = LoggerFactory.getLogger(EtmNodeController.class);

	/**
	 * 
	 * 分页查询方法
	 * 
	 */
	@RequestMapping("/getList.do")
	@ResponseBody
	public BaseResponse getList(HttpServletRequest req,
			Integer ownerId, Integer state, Integer pageNo, Integer pageSize, String sort) {
		
		BaseResponse resp = new BaseResponse();
		Member loginUser = getLoginMember(req);
		if (loginUser == null) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("帐号未登录");
			return resp;
		}

		if (loginUser.getUserType() == MemberTypeEnum.DEVELOPER.getType()) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("无效请求");
			return resp;
		}
		
		EtmNodeParam param = new EtmNodeParam();
		param.setPageNo(pageNo);
		param.setPageSize(pageSize);
		param.setState(state);
		if (!StringUtils.isEmpty(sort)){
			if (sort.charAt(0) == '+'){
				param.setOrderBy(sort.substring(1));
			}else if (sort.charAt(0) == '-'){
				param.setOrderBy(sort.substring(1) + " desc");
			}
		}
		
		if (loginUser.getUserType() == MemberTypeEnum.PROVIDER.getType()){
			param.setOwnerId(loginUser.getMemberId());
		}else if (ownerId != null){
			param.setOwnerId(ownerId);
		}

		PaginationResult<EtmNode> result = this.etmNodeService.findListByPage(param);
		resp.setMessage("查询成功");
		resp.setData(result);
		return resp;
	}
	
	@RequestMapping("/create.do")
	@ResponseBody
	public BaseResponse create(HttpServletRequest req, EtmNode node) {
		
		BaseResponse resp = new BaseResponse();
		Member loginUser = getLoginMember(req);
		if (loginUser == null) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("帐号未登录");
			return resp;
		}

		if (loginUser.getUserType() == MemberTypeEnum.DEVELOPER.getType()) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("无效请求");
			return resp;
		}
		
		if (StringUtils.isEmpty(node.getIp()) || StringUtils.isEmpty(node.getMasterPassword())
				|| node.getPort() == null){
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("节点参数不完整");
			return resp;
		}
		
		EtmNode saved = etmNodeService.getEtmNode(node.getIp(), node.getPort());
		if (saved != null){
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("相同ip 和端口的节点已经存在");
			return resp;
		}
		if (loginUser.getUserType() == MemberTypeEnum.ADMIN.getType()){
			if (node.getOwnerId() == null || node.getOwnerId() <= 0){
				resp.setCode(ResponseEnum.BAD_PARAM.getCode());
				resp.setMessage("请指定节点所有者");
				return resp;
			}
		}
		if (node.getOwnerId() == null) {
			node.setOwnerId(loginUser.getMemberId());
		}
		Date now = new Date();
		node.setCreateTime(now);
		node.setUpdateTime(now);
		try {
			etmNodeService.add(node);
		}catch (Exception e){
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("添加失败");
			logger.error("add node fail", e);
			return resp;
		}
		resp.setData(node.getNodeId());
		resp.setMessage("添加成功");
		return resp;
	}
	
	@RequestMapping("/update.do")
	@ResponseBody
	public BaseResponse update(HttpServletRequest req, EtmNode node) {
		
		BaseResponse resp = new BaseResponse();
		Member loginUser = getLoginMember(req);
		if (loginUser == null) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("帐号未登录");
			return resp;
		}

		if (loginUser.getUserType() == MemberTypeEnum.DEVELOPER.getType()) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("无效请求");
			return resp;
		}
		
		if (StringUtils.isEmpty(node.getIp()) || StringUtils.isEmpty(node.getMasterPassword())
				|| node.getPort() == null){
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("节点参数不完整");
			return resp;
		}
		
		EtmNode saved = etmNodeService.getEtmNode(node.getIp(), node.getPort());
		if (saved != null && !saved.getNodeId().equals(node.getNodeId())){
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("相同ip 和端口的节点已经存在");
			return resp;
		}
		//node.setOwnerId(loginUser.getMemberId());
		node.setUpdateTime(new Date());
		try {
			etmNodeService.update(node);
		}catch (Exception e){
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("添加失败");
			logger.error("add node fail", e);
			return resp;
		}
		resp.setMessage("更新成功");
		return resp;
	}
	
	@RequestMapping("/updateState.do")
	@ResponseBody
	public BaseResponse updateState(Integer nodeId, Integer state) {
		BaseResponse resp = new BaseResponse();
		//TODO:  添加检查机制
		EtmNode node = etmNodeService.getEtmNodeByPrimaryKey(nodeId);
		if (node == null) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("帐号不存在");
			return resp;
		}
		if (!node.getState().equals(state)){
			try {
				node.setState(state);
				etmNodeService.update(node);
			} catch (Exception e) {
				resp.setCode(ResponseEnum.BAD_PARAM.getCode());
				resp.setMessage("系统错误");
				logger.error("update member fail", e);
				return resp;
			}
		}
		resp.setMessage("修改成功");
		return resp;
	}
}

