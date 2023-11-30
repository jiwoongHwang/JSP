<%@page import="dto.BookDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BookDTO dto = new BookDTO();
%>   
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action = "addlibraryprocess.jsp" method = "post">
		<label>도서아이디</label>
		<input type = "text" placeholder = "아이디" name = "id"> <br>
		<label>작가</label>
		<input type = "text" placeholder = "작가" name = "author"> <br>
		<label>도서제목</label>
		<input type = "text" placeholder = "도서제목" name = "tutle"> <br>
		<label>도서내용</label>
		<input type = "text" placeholder = "도서내용" name = "content"> <br>
		<label>도서가격</label>
		<input type = "text" placeholder = "도서가격" name = "price"> <br>
		<label>제작일</label>
		<input type = "text" placeholder = "제작일" name = "date"> <br>
		<label>아이디</label>
		<input type = "text" name = "memberid" value = "<%=dto.getMemberid()%>"> <br>
		<button type = "submit">확인</button>
	</form>
</body>
</html>