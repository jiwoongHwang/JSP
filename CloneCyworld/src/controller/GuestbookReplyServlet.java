package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.guestbookreply;
import model.guestbookreplyDAO;

public class GuestbookReplyServlet extends HttpServlet {
   

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

   }
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      req.setCharacterEncoding("UTF-8");
      resp.setContentType("text/html; charset=UTF-8");
      try {
         
      String userid = req.getParameter("id");
      HttpSession session = req.getSession();
      int b_no = Integer.parseInt(req.getParameter("b_no"));
      String loginUserId = (String) session.getAttribute("id2");
      String created = req.getParameter("formattedTime");
      String content = req.getParameter("content");
      
      guestbookreplyDAO dao = new guestbookreplyDAO();
      guestbookreply bookReply = new guestbookreply();
      bookReply.setB_no(b_no);
      bookReply.setId(loginUserId);
      bookReply.setCreated(created);
      bookReply.setContent(content);
      
      int result = dao.InsertBookReply(bookReply);
      
      if(result == 1) {
    	  resp.sendRedirect("guestbook.jsp?userid="+ userid );
      } else {
    	  resp.sendRedirect("guestbook.jsp?userid="+ userid );
      }
      
      }catch (Exception e) {
         e.printStackTrace();
      }
   }
}