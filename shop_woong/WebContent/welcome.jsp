<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%!
	String greeting = "쇼핑몰 방문을 환영합니다";
	String tagline = "자바 쇼핑몰에 어서오세요";
	
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="resources/js/jquery.js"></script>

</head>
<body>
<%@ include file = "menu.jsp" %>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">
				<%=greeting %>
			</h1>
		</div>
	</div>
	<div class="container">
		<div class="text-center">
			<h3>
				<%=tagline%>
			</h3>
			
			<c:set var = "today" value = "<%= new java.util.Date() %>" />
			<h3 id="clock"><fmt:formatDate value = "${today}" type = "both" pattern = "yyyy-MM-dd hh:mm:ss"/></h3>
			
			<%
				response.setIntHeader("Refresh", 1);
			%>
		</div>
	</div>
	<%@ include file = "footer.jsp" %>
</body>
</html>