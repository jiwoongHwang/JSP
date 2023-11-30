<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="dao.BoardDAO"%>
<%@page import="dto.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "LoginCheck.jsp" %>

<% 	
	request.setCharacterEncoding("utf-8");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	SimpleDateFormat curdateformat = new SimpleDateFormat("yyyy-MM-dd");
	String postdate = curdateformat.format(new Date());
	
	BoardDTO dto = new BoardDTO();
	dto.setTitle(title);
	dto.setContent(content);
	dto.setId(session.getAttribute("user_id").toString());
	dto.setPostdate(postdate);
	
	BoardDAO dao = new BoardDAO();
	
	int Result = dao.insertWrite(dto);

/*	게시글 1000개 넣기
	int Result = 0;
	for (int i=1; i<1000; i++) {
		dto.setTitle(title + "-" + i);
		Result = dao.insertWrite(dto);
	}
*/
	
	dao.close();
	
	if(Result == 1) {
		response.sendRedirect("boardmain.jsp");
	} else {
		JSFunction.alertBack("글쓰기에 실패했습니다.", out);
	}
%>
