package com.example.app.member;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Execute;
import com.example.app.Result;
import com.example.app.member.dao.MemberDAO;
import com.example.app.member.vo.MemberVO;

public class JoinOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServerException {
		req.setCharacterEncoding("UTF-8");
		
		MemberDAO memberDAO = new MemberDAO();
		MemberVO memberVO = new MemberVO();
		Result result = new Result();
		
		memberVO.setMemberId(req.getParameter("memberId"));
		memberVO.setMemberPw(req.getParameter("memberPw"));
		memberVO.setMemberName(req.getParameter("memberName"));
		memberVO.setMemberAge(Integer.valueOf(req.getParameter("memberAge")));
		memberVO.setMemberGender(req.getParameter("memberGender"));
		memberVO.setMemberEmail(req.getParameter("memberEmail"));
		memberVO.setMemberZipcode(req.getParameter("memberZipcode"));
		memberVO.setMemberAddress(req.getParameter("memberAddress"));
		memberVO.setMemberAddressDetail(req.getParameter("memberAddressDetail"));
		
		memberDAO.join(memberVO);
		
		result.setRedirect(true);
		result.setPath(req.getContextPath() + "/member/login.me");
		
		return result;
	}

}














