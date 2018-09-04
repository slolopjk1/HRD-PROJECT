<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>사원 입력 창</title>
<script type="text/javascript" src="script/memberimport.js"></script>
<link rel="stylesheet" type="text/css" href="css/background.css">
<style>
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);
#mainbutton {
	float:right;	
}
#fileid {
	position:relative;
	top:400px;
	left:48%;
}
#memberimport {
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
		<input id="mainbutton" type="button" value="메인화면으로" onclick="location.href='Servlet?command=product_main'">
	</div>
	<div id="mainblock">
		<span id="mainheader">사원 추가 화면</span>
	</div>
	<div id="subblock">
		<form action="memberimport" id="select" method="post" enctype="multipar/form-data">
			<input type ="file" id="fileid" onchange="getFileName()">
		</form>
		<input type ="button" id="memberimport" value="사원 추가" onclick="memberimport()">
	</div>
</body>
</html>