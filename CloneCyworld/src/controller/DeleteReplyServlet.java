package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.guestbookDAO;
import model.guestbookreplyDAO;

public class DeleteReplyServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		guestbookreplyDAO  dao = new guestbookreplyDAO();
		String userid = req.getParameter("id");
		int r_no = Integer.parseInt(req.getParameter("guestbook_r_no"));
		
		dao.DeleteGuestbookReply(r_no);
		
		resp.sendRedirect("guestbook.jsp?userid="+userid);
		}
	}
	
	
