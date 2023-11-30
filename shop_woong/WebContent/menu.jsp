<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String user_id = (String) session.getAttribute("user_id");
 %>
<nav class="navbar navbar-expand navbar-dark bg-dark">
		<div class="container">
			<div class="navbar-header">
		<%
		if(user_id == null) {
		// 로그인 되어 있지 않은 상태 -> 상단바 메뉴에 로그인이 뜸
			
		%>
				<a class="navbar-brand" href="welcome.jsp">홈</a>
				<a class="navbar-brand" href="login.jsp">로그인</a>
				<a class="navbar-brand" href="addMember.jsp">회원가입</a>
				<!-- href="./welcome.jsp : 클릭하면 "welcome.jsp"파일로 돌아감 -->
				<!-- ./ : 이 표현은 현재 파일에서 상위 폴더로 넘어가 welcome 파일을 찾는 것을 의미
				          그냥 파일명(welcome.jsp)만 적으면 다른 폴더에 있는 파일을 찾지 못함 -->
				          
		<% } else {
		//  로그인이 된 상태 -> 상단바 메뉴에 로그아웃이 뜸
		%>
				<a class="navbar-brand" href="welcome.jsp">홈</a>
				<a class="navbar-brand" href="logout.jsp">로그아웃</a>
				<a class="navbar-brand" href="editmember.jsp">회원정보수정</a>
				<a class="navbar-brand" href="deletemember.jsp">회원탈퇴</a>
				<a class="navbar-brand" href="boardmain.jsp?pageNum=1">게시판</a>
				
		<% } %> 
			</div>
		</div>
		
		<div>
			<ul class = "navbar-nav mr-auto">
				<li class = "nav-item">
				<%
					if(user_id != null) {
						
				%>
				<a class="navbar-brand" href="products.jsp">상품목록</a>
				<a class="navbar-brand" href="addProduct.jsp">상품추가</a>
				<a class="navbar-brand" href="deleteProduct.jsp">상품삭제</a>
				<%
					}
				%>
			</ul>
		</div>
</nav>