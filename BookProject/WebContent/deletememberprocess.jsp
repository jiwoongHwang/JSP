<%@page import="dao.MemberShipDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	String id = (String)(session.getAttribute("member_id"));
	String pw = request.getParameter("pw");
	
	MemberShipDAO dao = new MemberShipDAO();
	String result = dao.deleteMember(id, pw);
	
	if(result.equals("")) {
		request.getRequestDispatcher("deletemember.jsp?error=1").forward(request, response);
	} else {
		session.invalidate();
		dao.close();
		response.sendRedirect("logout.jsp");
	}
%>
</body>
</html>