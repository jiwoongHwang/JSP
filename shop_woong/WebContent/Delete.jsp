<%@page import="common.JSFunction"%>
<%@page import="dao.BoardDAO"%>
<%@page import="dto.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "LoginCheck.jsp"%>

<%
	String num = request.getParameter("num");
	String sessionId = session.getAttribute("user_id").toString();
	
	BoardDAO dao = new BoardDAO();
	BoardDTO dto = dao.selectView(num);
	
	
	int result = 0;
	
	if(sessionId.equals(dto.getId())) {
		dto.setNum(Integer.parseInt(num));
		result = dao.deletePost(dto);
		dao.close();
	}
	
	if(result == 1) {
		JSFunction.alertLocation("삭제되었습니다.", "boardmain.jsp", out);
	} else {
		JSFunction.alertBack("작성자만 삭제할 수 있습니다.", out);
		return;
	}
	

%>