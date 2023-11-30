<%@page import="dto.BoardDTO"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "LoginCheck.jsp" %>
<%	
	request.setCharacterEncoding("utf-8");
	String num = request.getParameter("num");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String id = request.getParameter("id");
	String id2 = (String)session.getAttribute("user_id");
	
	if(id.equals(id2)) {
	BoardDTO dto = new BoardDTO();
	dto.setNum(Integer.parseInt(num));
	dto.setTitle(title);
	dto.setContent(content);

	BoardDAO dao = new BoardDAO();
	int result = dao.updateEdit(dto);
	dao.close();

	if (result == 1) {
		response.sendRedirect("boardmain.jsp?num=" + dto.getNum());
	} else {
		JSFunction.alertBack("수정하기 실패했습니다.", out);
	}
	
	} else {
		JSFunction.alertBack("니 아이디로 작성한게 아니여유", out);
	}



%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>