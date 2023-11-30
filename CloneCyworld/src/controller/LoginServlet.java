package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member;
import model.memberDAO;

public class LoginServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		memberDAO dao = new memberDAO();
		member dto = new member();
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");

		int user_id = dao.CheckDuplicateId(id);
		int user_pw = dao.LoginCheck(id, pw); 
		
		dto = dao.selectMember(id);
		
		try {
			if (user_id == 0) {
				resp.sendRedirect("login.jsp?error=0");
			} else if (user_pw == 0) {
				resp.sendRedirect("login.jsp?error=1");
			} else {
				req.getSession().setAttribute("id2", id);
				req.getSession().setAttribute("dto", dto);
				resp.sendRedirect("home.jsp?userid="+id);
			}
		} catch(Exception e) {
			e.printStackTrace();
			resp.sendRedirect("error.jsp");
		}
		dao.close();
	}
	
}
