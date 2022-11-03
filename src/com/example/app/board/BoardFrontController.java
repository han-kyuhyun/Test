package com.example.app.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Result;

public class BoardFrontController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req, resp);
	}
	
	protected void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String target = req.getRequestURI().substring(req.getContextPath().length());
		Result result = null;
		
		if(target.equals("/board/boardListOk.bo")) {
			result = new BoardListOkController().execute(req, resp);
		}else if(target.equals("/board/boardWrite.bo")) {
			result = new BoardWriteController().execute(req, resp);
		}else if(target.equals("/board/boardWriteOk.bo")) {
			result = new BoardWriteOkController().execute(req, resp);
		}else if(target.equals("/board/boardDetail.bo")) {
			result = new BoardDetailController().execute(req, resp);
		}else if(target.equals("/board/boardDelete.bo")) {
			result = new BoardDeleteController().execute(req, resp);
		}else if(target.equals("/board/boardUpdate.bo")) {
			result = new BoardUpdateController().execute(req, resp);
		}else if(target.equals("/board/boardUpdateOk.bo")) {
			result = new BoardUpdateOkController().execute(req, resp);
		}
		
		if(result != null) {
			if(result.isRedirect()) {
				resp.sendRedirect(result.getPath());
			}else {
				req.getRequestDispatcher(result.getPath()).forward(req, resp);
			}
			
		}
		
	}
}















