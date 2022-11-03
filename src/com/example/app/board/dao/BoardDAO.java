package com.example.app.board.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.example.app.board.vo.BoardDTO;
import com.example.app.board.vo.BoardVO;
import com.mybatis.config.MyBatisConfig;

public class BoardDAO {
	public SqlSession sqlSession;
	
	public BoardDAO() {
		sqlSession = MyBatisConfig.getSqlSessionFactory().openSession(true);
	}
	
	public List<BoardDTO> selectAll(HashMap<String, Integer> pageMap) {
		return sqlSession.selectList("board.selectAll", pageMap);
	}
	
	public int getTotal() {
		return sqlSession.selectOne("board.getTotal");
	}
	
	public void insert(BoardVO boardVO) {
		sqlSession.insert("board.insert", boardVO);
	}
	
	public int getSequence() {
		return sqlSession.selectOne("board.getSequence");
	}
	
	public BoardDTO select(int boardNumber) {
		return sqlSession.selectOne("board.select", boardNumber);
	}
	
	public void delete(int boardNumber) {
		sqlSession.delete("board.delete", boardNumber);
	}
	
	public void update(BoardVO boardVO) {
		sqlSession.update("board.update", boardVO);
	}
}














