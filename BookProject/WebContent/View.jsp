<%@page import="dao.BookDAO"%>
<%@page import="dto.BookDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
	BookDAO dao = new BookDAO();
	BookDTO dto = dao.selectView(id);
	dao.close();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">	
<title>Insert title here</title>
</head>
<body>
	<form action = "#" method = "post">
		<label>도서아이디</label>
		<input type = "text" placeholder = "아이디 " name = "id" value = "<%=dto.getBookid()%>"><br>
		<label>작가</label>
		<input type = "text" placeholder = "작가" name = "author" value = "<%=dto.getAuthor()%>"> <br>
		<label>도서제목</label>
		<input type = "text" placeholder = "도서제목" name = "title" value = "<%=dto.getBooktitle()%>"> <br>
		<label>도서내용</label>
		<input type = "text" placeholder = "도서내용" name = "content" value = "<%=dto.getBookcontent()%>"> <br>
		<label>도서가격</label>
		<input type = "text" placeholder = "도서가격" name = "price" value = "<%=dto.getBookprice()%>"> <br>
		<label>제작일</label>
		<input type = "text" placeholder = "제작일" name = "date" value = "<%=dto.getBookdate()%>"> <br>
		<label>아이디</label>
		<input type = "text" placeholder = "아이디" name = "memberid" value = "<%=dto.getMemberid()%>"> <br>
	</form>

</body>
</html>