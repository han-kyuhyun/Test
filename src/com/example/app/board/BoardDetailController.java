package com.example.app.board;

import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Execute;
import com.example.app.Result;
import com.example.app.board.dao.BoardDAO;
import com.example.app.board.vo.BoardDTO;
import com.example.app.file.dao.FileDAO;

public class BoardDetailController implements Execute {

	@Override
	public Result execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServerException {
		Result result = new Result();
		int boardNumber = Integer.valueOf(req.getParameter("boardNumber"));
		BoardDAO boardDAO = new BoardDAO();
		FileDAO fileDAO = new FileDAO();
		BoardDTO boardDTO = boardDAO.select(boardNumber);
		boardDTO.setFiles(fileDAO.select(boardNumber));
		req.setAttribute("board", boardDTO);
		
		result.setPath("/app/board/detail.jsp");
		return result;
	}

}
