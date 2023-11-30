<%@page import="common.JSFunction"%>
<%@page import="dao.BookDAO"%>
<%@page import="dto.BookDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String author = request.getParameter("author");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String priceStr = request.getParameter("price");
	String date = request.getParameter("date");
	String memberid = request.getParameter("memberid");

	int	price = Integer.parseInt(priceStr);
	
	BookDTO dto = new BookDTO();
	dto.setBookid(id);
	dto.setAuthor(author);
	dto.setBooktitle(title);
	dto.setBookcontent(content);
	dto.setBookprice(price);
	dto.setBookdate(date);
	dto.setMemberid(memberid);
	
	BookDAO dao = new BookDAO();
	int result = dao.addLibrary(dto);
	dao.close();
	
	if(result == 1) {
		response.sendRedirect("bookmain.jsp");
	} else {
		JSFunction.alertBack("도서추가실패", out);
	}
%>