<%@page import="model.photobook"%>
<%@page import="model.guestbook"%>
<%@page import="model.guestbookDAO"%>
<%@page import="model.photobookDAO"%>
<%@page import="model.member"%>
<%@page import="model.memberDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String id = request.getParameter("userid");
String id2 = (String) session.getAttribute("id2");

memberDAO memberdao = new memberDAO();
member user = memberdao.selectMember(id);

guestbookDAO dao = new guestbookDAO();
int result = dao.checkDate(id);

photobookDAO photobookdao = new photobookDAO();
List<photobook> photobookList = photobookdao.GetPhotoBookList(id);
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
										<a href="guestbook.jsp?userid=${member.id}">방명록 <c:set
												var="result" value="<%=result%>" /> <c:if
												test="${result != 0 }">
												<span style="color: blue">N</span>
											</c:if>
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="main">
						<h4>나중에 해볼게요 ..</h4>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>