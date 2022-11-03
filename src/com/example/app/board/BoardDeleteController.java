package com.example.app.board;

import java.io.File;
import java.io.IOException;
import java.rmi.ServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Execute;
import com.example.app.Result;
import com.example.app.board.dao.BoardDAO;
import com.example.app.file.dao.FileDAO;

public class BoardDeleteController implements Execute {

	@Override
	public Result execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServerException {
		Result result = new Result();
		int boardNumber = Integer.valueOf(req.getParameter("boardNumber"));
		String uploadPath = req.getSession().getServletContext().getRealPath("/") + "upload";
		BoardDAO boardDAO = new BoardDAO();
		FileDAO fileDAO = new FileDAO();
		
		fileDAO.select(boardNumber).stream().map(file -> file.getFileSystemName()).forEach(name -> {
			File file = new File(uploadPath, name);
			if(file.exists()) {
				file.delete();
			}
		});
		
		fileDAO.delete(boardNumber);
		boardDAO.delete(boardNumber);
		result.setRedirect(true);
		result.setPath("/board/boardListOk.bo");
		return result;
	}

}

















