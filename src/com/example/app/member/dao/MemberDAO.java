package com.example.app.member.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.example.app.member.vo.MemberVO;
import com.mybatis.config.MyBatisConfig;

public class MemberDAO {
	public SqlSession sqlSession;
	
	public MemberDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
	public boolean checkId(String memberId) {
		return (Integer)sqlSession.selectOne("member.checkId", memberId) == 0;
	}
	
	public void join(MemberVO memberVO) {
		sqlSession.insert("member.join", memberVO);
	}
	
	public int login(String memberId, String memberPw){
		HashMap<String, String> loginMap = new HashMap<String, String>();
		loginMap.put("memberId", memberId);
		loginMap.put("memberPw", memberPw);
		
		return sqlSession.selectOne("member.login", loginMap);
	}
	
	public String getMemberId(int memberNumber) {
		return sqlSession.selectOne("member.getMemberId", memberNumber);
	}
}















