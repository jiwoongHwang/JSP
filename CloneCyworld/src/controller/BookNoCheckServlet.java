package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class BookNoCheckServlet extends HttpServlet {
   
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      req.setCharacterEncoding("UTF-8");
      resp.setContentType("text/html; charset=UTF-8");
      
      HttpSession session = req.getSession();
      String id = (String) session.getAttribute("owner_id");
      resp.sendRedirect("home.jsp?userid="+id);
      
   }
}