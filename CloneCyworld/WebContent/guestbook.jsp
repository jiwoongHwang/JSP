<%@page import="model.guestbookreply"%>
<%@page import="model.guestbookreplyDAO"%>
<%@page import="model.member"%>
<%@page import="model.memberDAO"%>
<%@page import="model.guestbookDAO"%>
<%@page import="model.guestbook"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String id = request.getParameter("userid");
String id2 = (String) session.getAttribute("id2");

memberDAO memberdao = new memberDAO();
member user = memberdao.selectMember(id);

guestbookDAO guestbookdao = new guestbookDAO();
List<guestbook> guestbookList = guestbookdao.getGuestbook_list(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./resources/css/layout.css" />
<link rel="stylesheet" href="./resources/css/index.css" />
<link rel="stylesheet" href="./resources/css/home.css" />
<link rel="stylesheet" href="./resources/css/guestbook.css" />
<script type="text/javascript" src="./resources/js/home.js"></script>
<title>Insert title here</title>
</head>
<body class="center-layout" style="background-color: gray">
	<div class="wrap">
		<div class="wrap2">
			<div class="container">
				<div class="left-box">
					<div class="box-radius-5 center logout">
						${member.id}님의미니홈피
						<form action="LogoutServlet" method="post">
							<input type="submit" value="로그아웃">
						</form>
					</div>
					<div class="box-radius-5 center">Today 222 | Total 1111</div>
					<div class="box-radius-5 center-layout-column"
						style="height: 607px">
						<div style="height: 65%">
							<img class="profile" alt="profile"
								src="./resources/img/${member.imgFile}" />
						</div>
						<div class="dot-line"></div>
						<div style="height: 15%">
							<div class="box-radius-5">화이팅문구</div>
							<div>${member.id}님의미니홈피에오신것을환영합니다!</div>
						</div>
						<div class="dot-line"></div>
						<div>
							<div>소개글</div>
							<select id="pageSelect" onchange="openPage()">
								<option value="">파도타기</option>
								<option value="http://www.naver.com">네이버</option>
								<option value="http://www.google.com">구글</option>
							</select>
						</div>
					</div>
				</div>
				<div class="right-box">
					<div style="background-color: lightblue">
						<div class="box-radius-5 logo-wrapper" style="border: none">
							<a href="home.jsp?userid=${member.id}"> <img
								class="literal-logo" alt="cy-literal-logo"
								src="https://i.namu.wiki/i/zXYqeAh7kt0sLLN4XEBV4ZT7nbOGoKRJPsOvDYzttBWj1HDHPHJWquTsSnPmrOc4NYz38kudzfniwORnFM9wfQ.svg">
							</a>
						</div>
					</div>
					<div class="box-radius-5 center-layout-column">
						<div class="bgm-wrapper">
							<p>BGM</p>
						</div>
						<div class="right-box-content">
							<div>
								<p>일기</p>
								<p>사진</p>
							</div>
							<div style="border: 1px solid black">
								<div class="menu-wrapper" style="display: flex">
									<div class="menu">투데이</div>
									<div class="menu">주크박스</div>
								</div>
								<div class="menu-wrapper" style="display: flex">
									<div class="menu">
										<a href="photobook.jsp?userid=${member.id}">사진첩</a>
									</div>
									<div class="menu">
										<a href="guestbook.jsp?userid=${member.id}">방명록</a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="main">
						<form action="GuestBookServlet" method="post">
							<input type="hidden" name="owner_id" value="${param.userid}" />
							<input type="hidden" name="id" value="${dto.id}" /> <input
								type="hidden" name="imgFile" value="${dto.imgFile}" />
							<div class="box-content-main">
								<div class="box-content-top">작성 유저 : ${dto.id}</div>
								<div class="box-content-bottom">
									<div style="width: 30%">
										<img class="profile" alt="profile"
											src="./resources/img/${dto.imgFile}" style="margin: auto" />
									</div>
									<div style="width: 85%">
										<textarea name="content" placeholder="내용작성"></textarea>
										<input type="submit" value="등록">
									</div>
								</div>
							</div>
						</form>
						<c:set var="owner_id" value="<%=id%>" />
						<c:set var="login_id" value="<%=id2%>" />
						<c:set var="lists" value="<%=guestbookList%>" />
						<c:forEach var="list" items="${lists}">
							<div class="box-content-main">
								<div class="box-content-top">
									작성 유저 : ${list.id}

									<c:if test="${owner_id == login_id || list.id == login_id}">
										<div class="deletebutton">
											<form action="DeleteServlet" method="post">
												<input type="hidden" name="id" value="<%=id%>" /> <input
													type="hidden" name="guestbookNo" value="${list.no}" /> <input
													type="submit" value="삭제" />
											</form>
										</div>
									</c:if>
								</div>
								<div class="box-content-bottom">
									<div style="width: 30%">
										<img class="profile" alt="profile"
											src="./resources/img/${list.imgFile}" style="margin: auto" />
									</div>
									<div style="width: 85%">${list.content}</div>
								</div>
								<%
									guestbook dto = (guestbook) pageContext.getAttribute("list");
								guestbookreplyDAO guestbookreplydao = new guestbookreplyDAO();
								int b_no = Integer.parseInt(dto.getNo());
								List<guestbookreply> guestbookreplyList = guestbookreplydao.GetUserbookReplyList(b_no);
								%>
								<c:set var="lists2" value="<%=guestbookreplyList%>" />
								<c:forEach var="list2" items="${lists2}">
									<div class="reply">
										아이디 : ${list2.id} 등록 날짜 : ${list2.created} 댓글 :
										${list2.content}
										<c:if test="${owner_id == login_id || list2.id == login_id}">
											<form action="DeleteReplyServlet" method="post">
												<input type="hidden" name="id" value="<%=id%>" /> <input
													type="hidden" name="guestbook_r_no" value="${list2.r_no}" />
												<input type="submit" value="삭제" />
											</form>
										</c:if>
									</div>
								</c:forEach>
								<form action="GuestbookReplyServlet" method="post">
									<div class="box-content-bottom1">
										<div style="width: 90%">
											<textarea name="content" placeholder="댓글작성"></textarea>
										</div>
										<input type="hidden" name="b_no" value="${list.no}" /> <input
											type="hidden" name="id" value="<%=id%>" />
										<div class="button">
											<input type="submit" value="등록">
										</div>
									</div>
								</form>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>