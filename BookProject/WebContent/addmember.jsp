<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String error = request.getParameter("error");
	if (error != null) {
		out.print("<div>");
		out.print("아이디가 중복되었습니다");
		out.print("</div>");
	}
%>
	<form action = "addmemberprocess.jsp" method = "post">
		<label>아이디</label>
		<input type = "text" placeholder = "아이디 " name = "id"> <br>
		<label>비밀번호</label>
		<input type = "password" placeholder = "비밀번호" name = "pw"> <br>
		<label>이름</label>
		<input type = "text" placeholder = "이름" name = "name"> <br>
		<label>주소</label>
		<input type = "text" placeholder = "주소" name = "address"> <br>
		<label>전화번호</label>
		<input type = "text" placeholder = "010-1111-1111" name = "tel"> <br>
		<button type = "submit">확인</button>
		
	</form>
</body>
</html>