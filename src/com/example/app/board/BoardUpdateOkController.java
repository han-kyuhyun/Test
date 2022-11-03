package com.example.app.board;

import java.io.File;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Execute;
import com.example.app.Result;
import com.example.app.board.dao.BoardDAO;
import com.example.app.board.vo.BoardVO;
import com.example.app.file.dao.FileDAO;
import com.example.app.file.vo.FileVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardUpdateOkController implements Execute {

	@Override
	public Result execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServerException {
req.setCharacterEncoding("UTF-8");
		
		Result result = new Result();
		FileDAO fileDAO = new FileDAO();
		BoardDAO boardDAO = new BoardDAO();
		BoardVO boardVO = new BoardVO();
		
		String uploadPath = req.getSession().getServletContext().getRealPath("/") + "upload/";
		int fileSize = 1024 * 1024 * 5; // 5M
//		request 객체, 업로드할 경로, 파일의 크기, 인코딩 방식, 이름변경정책
//		0. 수정 후 파일들 서버 경로에 업로드
		MultipartRequest multipartRequest = new MultipartRequest(req, uploadPath, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		String boardTitle = multipartRequest.getParameter("boardTitle");
		String boardContent = multipartRequest.getParameter("boardContent");
		int boardNumber = Integer.valueOf(multipartRequest.getParameter("boardNumber"));
		int memberNumber = (Integer)req.getSession().getAttribute("memberNumber");
		
		boardVO.setBoardNumber(boardNumber);
		boardVO.setBoardTitle(boardTitle);
		boardVO.setBoardContent(boardContent);
		boardVO.setMemberNumber(memberNumber);
		
		//1. 게시글 수정
		boardDAO.update(boardVO);
		
		
		//2. 수정 전 파일 삭제
		fileDAO.select(boardNumber).stream().map(file -> file.getFileSystemName()).forEach(name -> {
			File file = new File(uploadPath, name);
			if(file.exists()) {
				file.delete();
			}
		});
		
		//3. 수정 전 파일 DB에서 삭제
		fileDAO.delete(boardNumber);
		
		//4. 수정 후 파일 DB에 추가
		Enumeration<String> fileNames = multipartRequest.getFileNames();
		
		while(fileNames.hasMoreElements()) {
			String fileName = fileNames.nextElement();
			String fileOriginalName = multipartRequest.getOriginalFileName(fileName);
			String fileSystemName = multipartRequest.getFilesystemName(fileName);
			
			if(fileOriginalName == null) {continue;}
			
			FileVO fileVO = new FileVO();
			fileVO.setFileOriginalName(fileOriginalName);
			fileVO.setFileSystemName(fileSystemName);
			fileVO.setBoardNumber(boardNumber);
			
			fileDAO.insert(fileVO);
		}
		result.setPath("/board/boardDetail.bo?boardNumber=" + boardNumber);
		return result;
	}

}
