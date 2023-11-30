package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.guestbook;
import model.guestbookDAO;

public class GuestBookServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
	    resp.setContentType("text/html; charset=UTF-8");
	    
		String id = req.getParameter("id");
		String content = req.getParameter("content");
		String owner_id = req.getParameter("owner_id");

		guestbook dto = new guestbook();
		dto.setId(id);
		dto.setContent(content);
		dto.setOwner_id(owner_id);
		
		guestbookDAO dao = new guestbookDAO();
		int result = dao.writebook(dto);
		if(result == 1) {
			resp.sendRedirect("guestbook.jsp?userid="+owner_id);
		}
	}

}
