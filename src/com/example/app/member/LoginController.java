package com.example.app.member;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Execute;
import com.example.app.Result;

public class LoginController implements Execute {

	@Override
	public Result execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServerException {
		Result result = new Result();
		String cookieCheck = req.getHeader("Cookie");
		if(cookieCheck != null){
			Cookie[] cookies = req.getCookies();
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("memberId")) {
					req.setAttribute("memberId", cookie.getValue());
				}
				if(cookie.getName().equals("saveId")) {
					req.setAttribute("saveId", cookie.getValue());
				}
			}
		}
		
		result.setPath("/app/member/login.jsp");
		return result;
	}

}
