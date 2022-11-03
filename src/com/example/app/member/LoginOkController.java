package com.example.app.member;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.app.Execute;
import com.example.app.Result;
import com.example.app.member.dao.MemberDAO;

public class LoginOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServerException {
		String memberId = req.getParameter("memberId");
		String memberPw = req.getParameter("memberPw");
		String saveId = req.getParameter("saveId");
		
		int memberNumber = 0;
		MemberDAO memberDAO = new MemberDAO();
		Result result = new Result();
		HttpSession session = req.getSession();
		
		try {
			memberNumber = memberDAO.login(memberId, memberPw);
			session.setAttribute("memberNumber", memberNumber);
			//로그인 성공
			if(saveId != null) {
				Cookie memberIdCookie = new Cookie("memberId", memberId);
				Cookie saveIdCookie = new Cookie("saveId", saveId);
				resp.addCookie(memberIdCookie);
				resp.addCookie(saveIdCookie);
			}else {
				String cookieCheck = req.getHeader("Cookie");
				Cookie[] cookies = req.getCookies();
				if(cookieCheck != null){
					for(Cookie cookie : cookies){
						if(cookie.getName().equals("saveId") || cookie.getName().equals("memberId")) {
							cookie.setMaxAge(0); //초단위
							resp.addCookie(cookie);
						}
					}
				}
			}
			result.setPath("/board/boardListOk.bo");
		} catch (Exception e) {
			result.setPath("/member/login.me?login=false");
		}
		
		return result;
	}

}
