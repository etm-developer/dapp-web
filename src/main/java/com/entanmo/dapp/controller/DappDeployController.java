package com.entanmo.dapp.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entanmo.dapp.dao.enums.MemberTypeEnum;
import com.entanmo.dapp.dao.enums.ResponseEnum;
import com.entanmo.dapp.param.DappDeployParam;
import com.entanmo.dapp.po.DappDeploy;
import com.entanmo.dapp.po.Member;
import com.entanmo.dapp.service.DappDeployService;
import com.entanmo.dapp.vo.BaseResponse;
import com.entanmo.dapp.vo.PaginationResult;

/**
 * 
 * 控制层
 * 
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/dappDeploy")
@Controller
public class DappDeployController extends BaseController{

	@Resource
	private DappDeployService dappDeployService;
	
	//private static final Logger logger = LoggerFactory.getLogger(DappDeployController.class);

	/**
	 * 
	 * 分页查询方法
	 * 
	 */
	@RequestMapping("/getList.do")
	@ResponseBody
	public BaseResponse getList(HttpServletRequest req, String account, Integer state, Integer nodeId, String transactionId,
			Integer pageNo,	Integer pageSize) {

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

		DappDeployParam param = new DappDeployParam();
		param.setPageNo(pageNo);
		param.setPageSize(pageSize);
		param.setState(state);
		param.setNodeId(nodeId);
		param.setTransactionId(transactionId);

		if (loginUser.getUserType() == MemberTypeEnum.PROVIDER.getType()) {
			param.setOwnerId(loginUser.getMemberId());
		}

		PaginationResult<DappDeploy> result = this.dappDeployService.findListByPage(param);
		resp.setMessage("查询成功");
		resp.setData(result);
		return resp;
	}
	
}

