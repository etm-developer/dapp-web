package com.entanmo.dapp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.entanmo.dapp.po.Member;
import com.entanmo.dapp.service.MemberService;


@Component
public class BaseController {
	
	@Resource
	private MemberService memberService;
	
	private static final String TOKEN_KEY = "X-Token";
	private static final String SESSION_KEY = "dapp_login_user";
	
	protected static Map<String, Member> memberMap = new HashMap<String, Member>();
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	public Member getLoginMember(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member mem = null;
		if (session != null) {
			mem = (Member)session.getAttribute(SESSION_KEY);
		}
		if (mem != null) {
		//	logger.info("Get user from session, sessionid: {}", session.getId());
			return mem;
		}
		
		String token = request.getHeader(TOKEN_KEY);
		if (StringUtils.isEmpty(token)) {
			//token = request.getCookies().
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie: cookies) {
					if (cookie.getName().equals(TOKEN_KEY)) {
						token = cookie.getValue();
						break;
					}
				}
			}
			if (StringUtils.isEmpty(token)) {
				return null;
			}
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
		String token = request.getHeader(TOKEN_KEY);
		request.getSession().invalidate();
		memberMap.remove(token);
	}
	
	public void addToCache(HttpServletRequest req, String token, Member member) {
		HttpSession session = req.getSession(true);
		session.setAttribute(SESSION_KEY, member);
	//	logger.info("put member to session: {}", session.getId());
		memberMap.put(token, member);
	}
	
	public Member getMember(String token) {
		return memberMap.get(token);
	}
}
