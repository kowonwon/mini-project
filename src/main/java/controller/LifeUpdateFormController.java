package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import dao.LifeDao;
import vo.Board;
import vo.BoardLife;

@WebServlet("/lifeUpdateForm")
public class LifeUpdateFormController extends HttpServlet {
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
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
			out.println("alert('잘못된 접근입니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return;
		}
		
		LifeDao dao = new LifeDao();
		int no = Integer.parseInt(sNo);
		boolean isPassCheck = dao.isPassCheck(no, pass);
		
		if(! isPassCheck) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 다릅니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return;
		}
		
		BoardLife board = dao.getBoard(no);
		
		request.setAttribute("board", board);
		request.setAttribute("pageNum", pageNum);
		
		RequestDispatcher rd = 
				request.getRequestDispatcher("/WEB-INF/board/lifeUpdateForm.jsp");
		rd.forward(request, response);
	}
}