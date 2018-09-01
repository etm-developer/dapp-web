package com.entanmo.dapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.entanmo.dapp.po.Member;
import com.entanmo.dapp.service.MemberService;


@Component
public class BaseController {
	
	@Resource
	private MemberService memberService;
	
	
	protected static Map<String, Member> memberMap = new HashMap<String, Member>();
	
	public Member getLoginMember(HttpServletRequest request) {
		String token = request.getHeader("X-Token");
		if (StringUtils.isEmpty(token)) {
			return null;
		}
		return memberMap.get(token);
		
//		String uri = request.getRequestURI();
//		int memberId = 1;
//		// /api/dapp/register.do
//		if (uri.indexOf("/dapp/") > 0){  // developer
//			memberId = 2;
//		}else if (uri.indexOf("/node/") > 0){  // provider
//			memberId = 3;
//		}
//		else if (uri.indexOf("/member/") > 0){  // administrator
//			memberId = 1;
//		}
//		return memberService.getMemberByPrimaryKey(memberId);
		
	}
	
	public void requestLogout(HttpServletRequest request){
		String token = request.getHeader("X-Token");
		memberMap.remove(token);
	}
	
	public void addToCache(String token, Member member) {
		memberMap.put(token, member);
	}
	
	public Member getMember(String token) {
		return memberMap.get(token);
	}
}
