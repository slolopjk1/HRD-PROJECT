<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 로그인 </title>
<link rel="stylesheet" type="text/css" href="css/background.css">
<style>
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);
#loginblock{
	position: relative;
	top: 100px;
	margin: auto;
	padding:20px;
	border: 1px solid gray;
	border-radius:5px;
	width:300px;
	height:100px;
	background-color:#FFFFFF;
}
#enumber,#password{
	margin-top:5px;
	float:left;
	width:210px;
	height:30px;
	border: 1px solid gray;
	border-radius:2px;
}
#button {
	margin:5px;
	height:72px;
	width:78px;
	border-radius:2px;
}
</style>
<script type="text/javascript" src="script/member.js"></script>
</head>
<body>
	<div id="headerblock">
		<span id="header">물품 관리 시스템</span>
	</div>
	<div id="mainblock">
		<span id="mainheader">로그인화면</span>
	</div>
	<div id="subblock">
		<div id="loginblock">
			<form action="Servlet" name="frm" method="get">
			<input type="hidden" name="command" value="product_loginaction">
			<input type="text" id="enumber" name="enumber" placeholder="사원번호">
				
			<input type="password" id="password" name="password" placeholder="비밀번호">
			<input type="submit" id="button" value="로그인" onclick="return loginCheck()">
			<br>
			${message}
			</form>
		</div>
	</div>
</body>
</html>