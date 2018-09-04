<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>물품 관리 시스템</title>
<link rel="stylesheet" type="text/css" href="css/background.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/unit.css">
<script type="text/javascript" src="script/controller.js"></script>
<style>
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);
#loginmove {
	position:relative;
	top:600px;
	left:45%;
	width:300px;
	height:40px;
}
</style>
</head>
<body>
	<div id="headerblock">
		<span id="header">물품 관리 시스템</span>
	</div>
	<div id="mainblock">
		<span id="mainheader">초기화면</span>
	</div>
	<div id="subblock">
		<form action="Servlet" name="frm" method="post">
		<input type="hidden" name="command" value="product_login">
		<input type="submit" id="loginmove" value="로그인 페이지로 이동">
		</form>
	</div>
</body>
</html>