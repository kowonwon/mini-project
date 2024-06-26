package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class MainController extends HttpServlet{
   
   @Override
   protected void doGet(
         HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
      
         RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/board/main.jsp");
         rd.forward(request, response);
   }
}