package com.example.app.member;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Execute;
import com.example.app.Result;

public class LogoutController implements Execute{
	@Override
	public Result execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServerException {
//		req.getSession().removeAttribute("memberNumber");
		Result result = new Result();
		req.getSession().invalidate();
		result.setPath("/member/login.me");
		return result;
	}

}







