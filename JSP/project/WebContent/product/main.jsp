<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty loginUser}">
	<jsp:forward page="Servlet?command=product_loginaction"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 메인창 </title>
<link rel="stylesheet" type="text/css" href="css/background.css">
<style>
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);
#info{
	color:white;
	float:right;
}
#logout{
	float:right;
}
#maintable{
	position:relative;
	padding:0;
	top: 250px;
	margin: auto;
	text-align:center;
}
#maintable th,td {
	border:1px solid gray;
}
</style>
</head>
<body>
	<div id="headerblock">
		<span id="header">물품 관리 시스템</span>
		<form id="info" action="Servlet" name="frm" method="get">
		<input type="hidden" name="command" value="product_logoutaction">
				${loginUser.name}님 환영합니다.<br>
				<input id="logout" type="submit" value="로그아웃">
		</form>
	</div>
	<div id="mainblock">
		<span id="mainheader">메인화면</span>
	</div>
	<div id="subblock">
		<table id="maintable">
			<tr >
				<td><img src="image\approval.png" width="150" height="150" onclick="location.href='Servlet?command=product_authority'"></td>
				<td><img src="image\memberimport.png" width="150" height="150" onclick="location.href='Servlet?command=product_memberimport'"></td>
				<td><img src="image\statistics.jpg" width="150" height="150" onclick="location.href='Servlet?command=product_search'"></td>
			</tr>
			<tr>
				<td>사용자 권한 부여</td>
				<td>사원 추가</td>
				<td>물품 검색</td>
			</tr>
		</table>
			
		
	</div>
</body>
</html>