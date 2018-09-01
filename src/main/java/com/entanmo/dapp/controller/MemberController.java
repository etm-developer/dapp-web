package com.entanmo.dapp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.entanmo.dapp.dao.enums.MemberTypeEnum;
import com.entanmo.dapp.dao.enums.ResponseEnum;
import com.entanmo.dapp.param.MemberParam;
import com.entanmo.dapp.po.Member;
import com.entanmo.dapp.service.MemberService;
import com.entanmo.dapp.util.HttpUtils;
import com.entanmo.dapp.util.MD5Util;
import com.entanmo.dapp.vo.BaseResponse;
import com.entanmo.dapp.vo.PaginationResult;

/**
 * 
 * 控制层
 * 
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/member")
public class MemberController extends BaseController {

	@Resource
	private MemberService memberService;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	//Map<String, Member> tokenMap = new HashMap<String, Member>();

	/**
	 * 
	 * 帐号注册
	 * 
	 */
	@RequestMapping("/register.do")
	@ResponseBody
	public BaseResponse register(@RequestParam("account")String account, String password, Integer userType) {
		BaseResponse resp = new BaseResponse();

		if (StringUtils.isEmpty(account)) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("帐号不能为空");
			return resp;
		}

		if (StringUtils.isEmpty(password)) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("密码不能为空");
			return resp;
		}
		account = account.trim().toLowerCase();
		MemberTypeEnum membertype = MemberTypeEnum.getByType(userType);
		if (membertype == null /* || membertype == MemberTypeEnum.ADMIN */) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("帐号类型无效");
			return resp;
		}
		Member member = memberService.getMemberByAccount(account);
		if (member != null) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("帐号已经存在");
			return resp;
		}
		member = new Member();
		member.setAccount(account);
		String encPass = MD5Util.md5(password);
		member.setPassword(encPass);
		member.setUserType(userType);
		member.setCreateTime(new Date());
		try {
			memberService.add(member);
		} catch (Exception e) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("系统错误");
			logger.error("add member fail", e);
			return resp;
		}
		resp.setMessage("注册成功");
		return resp;
	}
	
	/**
	 * 
	 * 帐号创建
	 * 
	 */
	@RequestMapping("/create.do")
	@ResponseBody
	public BaseResponse create(String account, String password, Integer userType,
			String phone, String email) {
		BaseResponse resp = new BaseResponse();

		if (StringUtils.isEmpty(account)) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("帐号不能为空");
			return resp;
		}

		if (StringUtils.isEmpty(password)) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("密码不能为空");
			return resp;
		}
		account = account.trim().toLowerCase();
		MemberTypeEnum membertype = MemberTypeEnum.getByType(userType);
		if (membertype == null /* || membertype == MemberTypeEnum.ADMIN */) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("帐号类型无效");
			return resp;
		}
		Member member = memberService.getMemberByAccount(account);
		if (member != null) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("帐号已经存在");
			return resp;
		}
		Date now = new Date();
		member = new Member();
		member.setAccount(account);
		String encPass = MD5Util.md5(password);
		member.setPassword(encPass);
		member.setUserType(userType);
		member.setPhone(phone);
		member.setEmail(email);
		member.setCreateTime(now);
		member.setLastLogin(now);
		try {
			memberService.add(member);
		} catch (Exception e) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("系统错误");
			logger.error("add member fail", e);
			return resp;
		}
		resp.setData(member.getMemberId());
		resp.setMessage("创建成功");
		return resp;
	}
	
	/**
	 * 
	 * 帐号创建
	 * 
	 */
	@RequestMapping("/update.do")
	@ResponseBody
	public BaseResponse update(Integer memberId, String password, Integer userType,
			String phone, String email) {
		BaseResponse resp = new BaseResponse();

//		if (StringUtils.isEmpty(password)) {
//			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
//			resp.setMessage("密码不能为空");
//			return resp;
//		}
		if (userType != null) {
			MemberTypeEnum membertype = MemberTypeEnum.getByType(userType);
			if (membertype == null /* || membertype == MemberTypeEnum.ADMIN */) {
				resp.setCode(ResponseEnum.BAD_PARAM.getCode());
				resp.setMessage("帐号类型无效");
				return resp;
			}
		}
		Member member = memberService.getMemberByPrimaryKey(memberId);
		if (member == null) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("帐号不存在");
			return resp;
		}	
		if (!StringUtils.isEmpty(password) && (!password.equals(member.getPassword()))) {
			String encPass = MD5Util.md5(password);
			member.setPassword(encPass);
		}
		if (userType != null){
			member.setUserType(userType);
		}
		member.setPhone(phone);
		member.setEmail(email);
		try {
			memberService.update(member);
		} catch (Exception e) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("系统错误");
			logger.error("update member fail", e);
			return resp;
		}
		resp.setMessage("修改成功");
		return resp;
	}
	
	/**
	 * 
	 * 帐号创建
	 * 
	 */
	@RequestMapping("/updateState.do")
	@ResponseBody
	public BaseResponse updateState(Integer memberId, Integer state) {
		BaseResponse resp = new BaseResponse();
		
		Member member = memberService.getMemberByPrimaryKey(memberId);
		if (member == null) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("帐号不存在");
			return resp;
		}
		if (!member.getState().equals(state)){
			try {
				member.setState(state);
				memberService.update(member);
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

	@RequestMapping("/login.do")
	@ResponseBody
	public BaseResponse login(HttpServletRequest req, HttpServletResponse response) throws Exception{
		BaseResponse resp = new BaseResponse();

		String body = HttpUtils.readPostContent(req);
		JSONObject json = new JSONObject(body);
		String account = json.getString("username");
		String password = json.getString("password");
		if (account == null || password == null) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("请求参数不完整");
			return resp;
		}
		account = account.trim();
		Member member = memberService.getMemberByAccount(account);
		if (member == null) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("帐号不存在");
			return resp;
		}
		if (!MD5Util.md5(password).equals(member.getPassword())) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("帐号密码错误");
			return resp;
		}
		
		member.setLastLogin(new Date());
		memberService.update(member);
		
		String token = req.getSession(true).getId();
		//req.getSession().setAttribute("member:" + sessionId, member);
		//req.getSession().setAttribute("member", member);
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("token", token);
		resp.setData(retMap);
		this.addToCache(token, member);
		resp.setMessage("登录成功");
		return resp;
	}
	
	@RequestMapping("/logout.do")
	@ResponseBody
	public BaseResponse logout(HttpServletRequest req) {
		BaseResponse resp = new BaseResponse();
		resp.setResp(ResponseEnum.SUCCESS);
		requestLogout(req);
		return resp;
	}


	@RequestMapping("/info.do")
	@ResponseBody
	public BaseResponse getUserInfo(HttpServletRequest req, String token) {

		Member member = getMember(token);
		BaseResponse resp = new BaseResponse();
		if (member == null) {
			resp.setResp(ResponseEnum.TOKEN_INVALID);
			return resp;
		}
		resp.setResp(ResponseEnum.SUCCESS);
		//resp.setData(member);
		// name, avatar, roles
		Map<String, Object> payload = new HashMap<String, Object>();
		payload.put("name", member.getAccount());
		payload.put("avatar", "");
		MemberTypeEnum typeEnum = MemberTypeEnum.getByType(member.getUserType());
		//String roles[] = {typeEnum.getRole()};
		payload.put("roles", typeEnum.getRoles());
		resp.setData(payload);
		return resp;
	}

	@RequestMapping("/changePwd.do")
	@ResponseBody
	public BaseResponse changePwd(HttpServletRequest req, @RequestParam("account")String account, String oldPass, String newPass) {
		BaseResponse resp = new BaseResponse();

		if (account == null || oldPass == null || newPass == null) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("请求参数不完整");
			return resp;
		}
		account = account.trim();
		Member member = memberService.getMemberByAccount(account);
		if (member == null) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("帐号不存在");
			return resp;
		}
		if (!MD5Util.md5(oldPass).equals(member.getAccount())) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("帐号原密码错误");
			return resp;
		}
		member.setPassword(MD5Util.md5(newPass));
		try {
			memberService.update(member);
		} catch (Exception e) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("更新帐号异常");
			logger.error("change pwd fail", e);
			return resp;
		}

		req.getSession().setAttribute("member", member);
		resp.setMessage("修改密码成功");
		return resp;
	}

	@RequestMapping("/resetPwd.do")
	@ResponseBody
	public BaseResponse resetPwd(HttpServletRequest req, @RequestParam("account")String account, String newPass) {
		BaseResponse resp = new BaseResponse();

		if (account == null || newPass == null) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("请求参数不完整");
			return resp;
		}
		account = account.trim();
		Member member = memberService.getMemberByAccount(account);
		if (member == null) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("帐号不存在");
			return resp;
		}

		Member loginUser = (Member) req.getSession().getAttribute("member");
		if (loginUser == null) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("帐号未登录");
			return resp;
		}

		if (loginUser.getUserType() != MemberTypeEnum.ADMIN.getType()) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("只有管理员才能重置密码");
			return resp;
		}

		member.setPassword(MD5Util.md5(newPass));
		try {
			memberService.update(member);
		} catch (Exception e) {
			resp.setCode(ResponseEnum.BAD_PARAM.getCode());
			resp.setMessage("更新帐号异常");
			logger.error("reset password fail", e);
			return resp;
		}

		req.getSession().setAttribute("member", member);
		resp.setMessage("重置密码成功");
		return resp;
	}

	@RequestMapping("/getList.do")
	@ResponseBody
	public BaseResponse getList(@RequestParam(name="account", required=false)String account, Integer userType, Integer pageNo, Integer pageSize) {
		MemberParam param = new MemberParam();
		param.setPageNo(pageNo);
		param.setPageSize(pageSize);
		param.setUserType(userType);
		if (!StringUtils.isEmpty(account)){
			param.setAccountFuzzy(account);
		}
		param.setOrderBy("lastLogin desc");
		PaginationResult<Member> result = this.memberService.findListByPage(param);

		BaseResponse resp = new BaseResponse();
		resp.setMessage("查询成功");
		resp.setData(result);
		return resp;
	}
}
