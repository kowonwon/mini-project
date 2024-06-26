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

@WebServlet("/lifeBoardDetail")
public class LifeBoardDetailController extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String no = request.getParameter("no");
		String pageNum = request.getParameter("pageNum");
		
		if(no == null || no.equals("") || pageNum == null || pageNum.equals("")) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('잘못된 접근입니다.');");
			out.println("history.back();");
			out.println("</script>");
			return;
		}
		
		LifeDao dao = new LifeDao();
		BoardLife board = dao.getBoard(Integer.valueOf(no));
		
		request.setAttribute("board", board);
		request.setAttribute("pageNum", pageNum);
		
		RequestDispatcher rd = 
				request.getRequestDispatcher("/WEB-INF/board/lifeBoardDetail.jsp");
		rd.forward(request, response);
	}
}