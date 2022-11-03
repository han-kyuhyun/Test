package com.example.app.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.ServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.Execute;
import com.example.app.Result;

public class DownloadController implements Execute {

	@Override
	public Result execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServerException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String uploadPath = req.getSession().getServletContext().getRealPath("/") + "upload";
		String fileSystemName = req.getParameter("fileSystemName");
		String fileOriginalName = req.getParameter("fileOriginalName");
		
		InputStream in = null;
		OutputStream out = null;
		byte[] datas = null;
		try {
			File file = new File(uploadPath, fileSystemName);
			datas = new byte[(int)file.length()];
			in = new FileInputStream(file);
			
//			Header에 Content-Type을 설정하는 것과 setContentType()에 설정하는 것은 동일하다.
//			byte[]을 응답할 경우 브라우저는 자동으로 ContentType을 octet-stream으로 인식한다.
//			resp.setContentType("application/octet-stream");
//			resp.setHeader("Content-Type", "application/octet-stream;charset=utf-8");
			resp.setHeader("Content-Length", file.length() + "");
			resp.setHeader("Content-Disposition", "attachment;filename=\"" + new String(("[한동석짱]_" + fileOriginalName).getBytes(), "ISO-8859-1") + "\"");
			
			out = resp.getOutputStream();
		
//			만약 해당 파일의 내용이 없다면, read()는 0을 리턴하기 때문에 "== -1"일 경우 0이면, 무한 반복한다.
//			따라서 " > 0"로 작성해야 0일 때에도 탈출할 수 있다.
			while(in.read(datas) > 0) {
				out.write(datas); // read()를 통해 읽은 byte가 0일 경우에는 write()를 사용하지 않아도 자동으로 응답된다.
			}
//			out.write(in.readAllBytes());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(in != null) {
					in.close();
				}
				if(out != null) {
					out.close();
				}
			} catch (IOException e) {
				System.gc();
				System.runFinalization();
			}
		}
		
		return null;
	}

}























