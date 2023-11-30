<%@page import="model.member"%>
<%@page import="model.memberDAO"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
<%
	String id = (String) session.getAttribute("id");
%>
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./resources/css/layout.css" />
<link rel="stylesheet" href="./resources/css/index.css" />
<link rel="stylesheet" href="./resources/css/register.css" />
<title>Insert title here</title>
</head>
<body class="center-layout-column">
	<div class="center-layout-column">
		<img class="logo" alt="logo" src="./resources/img/cyworld.png" />
		<div class="box center-layout-column">
		<%--<h2><%=id%></h2>--%>
 			<h2>${id2}님 가입을 환영합니다.</h2>  
		</div>
			<button type = "button" onclick = "location.href='login.jsp';">로그인</button>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>