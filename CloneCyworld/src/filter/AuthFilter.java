package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.member;
import model.memberDAO;

public class AuthFilter implements Filter { 
	
	private memberDAO dao;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		dao = new memberDAO();
	}
	
	@Override
	public void destroy() {
		dao.close();
	}
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req  = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("id2") == null) {
			resp.sendRedirect("login.jsp");
		} else {
			req.setAttribute("member", dao.selectMember(req.getParameter("userid")));
			chain.doFilter(request, response);
		}
	}
}
