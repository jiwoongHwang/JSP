<%@page import="java.util.List"%>
<%@page import="dao.BookDAO"%>
<%@page import="dto.BookDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	BookDAO dao = new BookDAO();
	List<BookDTO> Lists = dao.selectList();
	dao.close();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file = "top.jsp" %>
	<h2> 도서 목록 보기 </h2>
	<table border = "1" width = "100%">
	<tr align = "center">
		<th witdh = "15%">도서아이디</th>
		<th witdh = "15%">작가</th>
		<th witdh = "30%">도서목록</th>
		<th witdh = "40%">도서내용</th>
	</tr>
	<%
		for( BookDTO dto : Lists ) { 
	%>
		<tr>
		<td><%=dto.getBookid() %></td>
		<td><%=dto.getAuthor() %></td>
		<td><a href = "View.jsp?id=<%=dto.getBookid() %>"><%=dto.getBooktitle() %></a></td>
		<td><%=dto.getBookcontent() %>
		</tr>
	<% } %>
	</table>
<button type = "button" onclick ="location.href='addlibrary.jsp';">도서추가</button> 
</body>
</html>