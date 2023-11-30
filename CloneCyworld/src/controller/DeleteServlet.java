package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.guestbookDAO;

public class DeleteServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		guestbookDAO  dao = new guestbookDAO();
		String userid = req.getParameter("id");
		String no = req.getParameter("guestbookNo");
		
		dao.deletebook(no);
		
		resp.sendRedirect("guestbook.jsp?userid="+userid);
	}
	
}
