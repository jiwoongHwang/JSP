package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import model.member;
import model.memberDAO;

public class RegisterServlet extends HttpServlet {
	
	private memberDAO cymemberDAO;
	
	public void init() {
		cymemberDAO = new memberDAO();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String saveDirectory = req.getServletContext().getRealPath("resources/img");
		int maxSize = 1024 * 1024 * 30;
		MultipartRequest mr = new MultipartRequest(req, saveDirectory, maxSize);
		
		String id = mr.getParameter("id");
		String pw = mr.getParameter("pw");
		String email = mr.getParameter("email") + "@" + mr.getParameterValues("com")[0];
		String phone = mr.getParameterValues("phone-1")[0] +
				"-" + mr.getParameter("phone-2") + "-" + mr.getParameter("phone-3");
		String isAdmin = mr.getParameter("grant");
		String imgFile = mr.getFilesystemName("imgFile");
		
//		HttpSession session = req.getSession();
//		session.setAttribute("Id", id);
		
		member cyMember = new member();
		cyMember.setId(id);
		cyMember.setPassword(pw);
		cyMember.setEmail(email);
		cyMember.setPhone(phone);
		cyMember.setIsAdmin(isAdmin);
		cyMember.setImgFile(imgFile);
		
		
		try {
			cymemberDAO.CreateMember(cyMember);
			req.getSession().setAttribute("id2", id);
			resp.sendRedirect("Registersuccess.jsp");
//			req.getRequestDispatcher("Registersuccess.jsp").forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("error.jsp");
		}
	}
}
