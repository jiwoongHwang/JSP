<%@page import="dao.MemberShipDAO"%>
<%@page import="dto.MemberShipDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	MemberShipDAO dao = new MemberShipDAO();
	MemberShipDTO dto = dao.selectMember(id, pw);
	
	String result ="";
	
	if(dto.getId() != null ) {
		session.setAttribute("member_id", dto.getId());
		session.setAttribute("member_name", dto.getName());
		result=dto.getName() + "님 환영합니다.";
	} else {
		request.getRequestDispatcher("login.jsp?error=1").forward(request,response);
	}
	
	dao.close();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1><%=result %></h1>
	<a href = "login.jsp">돌아가기</a>
</body>
</html>