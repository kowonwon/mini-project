package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.BoardDao;
import vo.Board;
import vo.Comment;

@WebServlet("/commentProcess")
public class CommentWriteController extends HttpServlet {
	
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String nameC = request.getParameter("nameC");		
		String passC = request.getParameter("passC");
		String content = request.getParameter("comment");
		
		Comment comment = new Comment();
		comment.setWriter(nameC);
		comment.setPass(passC);
		comment.setContent(content);
		
		BoardDao dao = new BoardDao();
		dao.insertComment(comment);
		
		response.sendRedirect("boardDetail");
	}
}