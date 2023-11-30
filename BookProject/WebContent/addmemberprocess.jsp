<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
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
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String name = request.getParameter("name");
	String address = request.getParameter("address");
	String tel = request.getParameter("tel");
	
	MemberShipDAO dao = new MemberShipDAO();
	
	String sql = "select * from membership";
	PreparedStatement psmt = dao.con.prepareStatement(sql);
	ResultSet rs = psmt.executeQuery();
	
	String result = "";
	while(rs.next()) {
		String id2 = rs.getString(1); 
		
		if(id.equals(id2)) {
			result="기존회원입니다";
			request.getRequestDispatcher("addmember.jsp?error=1").forward(request, response);
			break;
		} 
	}
	dao.insertMember(id, pw, name, address, tel);
	result = "회원 가입을 환영합니다.";
	dao.close();
%>

<%@ include file = "top.jsp" %> 
	<h1> <%=result %> </h1>
</body>
</html>