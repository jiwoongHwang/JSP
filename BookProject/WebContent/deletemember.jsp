<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> 회원탈퇴 </h1>
<%
		String error = request.getParameter("error");
		if (error != null) {
			out.print("<div>");
			out.print("아이디와 비밀번호를 확인해 주세요");
			out.print("</div>");
		}
%>
	<form action = "deletememberprocess.jsp" method = "post">
		<label>아이디</label>
		<input type = "text" placeholder = "아이디" name = "id"> <br>
		<label>비밀번호</label>
		<input type = "password" placeholder = "비밀번호" name = "pw"> <br>
		<button type = "submit">회원탈퇴</button>
		
	</form>
</body>
</html>