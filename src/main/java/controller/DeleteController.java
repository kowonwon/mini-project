package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;

@WebServlet("/deleteProcess")
public class DeleteController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String sNo = request.getParameter("no");
		String pass = request.getParameter("pass");
		String pageNum = request.getParameter("pageNum");
		
		if(sNo == null || sNo.equals("") || pass == null || pass.equals("")
				|| pageNum == null || pageNum.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println(" alert('잘못된 접근입니다.');");
			out.println(" history.back();");
			out.println("</script>");
			return;
		}
		BoardDao dao = new BoardDao();
		int no = Integer.parseInt(sNo);
		
		boolean isPassCheck = dao.isPassCheck(no, pass);
		if(! isPassCheck) {
			StringBuilder sb = new StringBuilder();
			sb.append("<script>");
			sb.append("alert('비밀번호가 맞지 않습니다.');");
			sb.append("history.back();");
			sb.append("</script>");
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(sb.toString());
			return;
		}
		dao.deleteBoard(no);
		response.sendRedirect("boardList?pageNum=" +pageNum);
	}
}
