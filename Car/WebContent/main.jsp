<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="car.CarDTO"%>
<%@page import="car.CarDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	CarDAO dao = new CarDAO();

request.setCharacterEncoding("utf-8");
String searchField = request.getParameter("searchField");
String searchWord = request.getParameter("searchWord");

List<CarDTO> list = dao.selectList(searchField, searchWord);

dao.close();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
<link rel="stylesheet" href="resources/css/main.css">
</head>
<body>
	<div class="container1">
		<div class="top">
			<div class="logo">
				<h1 style="padding-top: 30px">
					<span class="point-font">JW</span>렌트카
				</h1>
			</div>
			<div style="text-align: right">
				<p>
					<span style="color: blue; font-weight: bold;">황지웅님 반갑습니다 
				</p>
			</div>
		</div>
		<div class="top_menu">
			<ul>
				<li>예약하기</li>
				<li>예약확인</li>
				<li>자유게시판</li>
				<li>이벤트</li>
				<li>고객센터</li>
			</ul>
		</div>
	</div>

	<div class="container2">
		<div class="items">
			<c:forEach var="carlist" items="<%=list%>">
				<div class="item">
					<div>
						<img class="Image" alt="차량이미지"
							src="resources/images/${carlist.c_image}">
					</div>
					<p align="center">차량명 : ${carlist.c_name }</p>
					<p align="center">출고날짜 : ${carlist.c_releaseDate }</p>
					<p align="center">가격 : ${carlist.c_price }</p>
					<p align="center">색상 : ${carlist.c_color }</p>

				</div>
			</c:forEach>
		</div>
	</div>
	<div align="center">
		<form>
			<select name="searchField" class='txt'>
				<option value="c_name">차량명</option>
				<option value="c_color">차량색상</option>
			</select> <input name="searchWord" type="text"> <input type="submit"
				value="검색">
		</form>
	</div>
</body>
</html>