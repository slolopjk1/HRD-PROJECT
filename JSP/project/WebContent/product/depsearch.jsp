<%@ page import="dto.Item_ListVO" %>
<%@ page import="dao.Item_ListDAO"%>
<%@ page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>부서별 검색</title>
<script type="text/javascript" src="script/location.js"></script>
<script type="text/javascript" src="script/mycanvas.js"></script>
<link rel="stylesheet" type="text/css" href="css/piechart.css">
<link rel="stylesheet" type="text/css" href="css/background.css">
<style>
	@import url(//fonts.googleapis.com/earlyaccess/hanna.css);
#mainbutton {
	float:right;
}
#group {
	position:relative;
	left:30px;
	top:10px;
	float:left;
}
#search{
	position:relative;
	top:7px;
	left:100px;
}
#searchtable {
	position:relative;
	top:50px;
	border:2px double skyblue;
	margin:auto;
}
#searchtable td,th{
	border:1px skyblue soild;
	text-align:center;
	padding:5px;
}
#searchcount {
	font-size:18px;
	float:right;
}
</style>

</head>
<body>
<div id="headerblock">
	<span id="header">물품 관리 시스템</span>
	<input id="mainbutton" type="button" value="메인화면으로" onclick="location.href='Servlet?command=product_main'">
</div>
<div id="mainblock">
	<span id="mainheader">부서별 검색</span>
</div>
<%
	Item_ListDAO dao = Item_ListDAO.getInstance();
	int count = 0;
	String value = "";
	int length = dao.depcount();     // 배열 길이 저장
	int[] countarray = new int[length]; // 수량 배열 초기화
	String[] namearray = new String[length]; //부서명 배열 초기화
	List<Item_ListVO> list;
%>
<div id="subblock">
	<select name="selectbox" id="group" onchange="selectcarry();">
		<option value="1">전체검색</option>
		<option value="2" selected="selected">부서별검색</option>
	</select>
	<form id="search" action="Servlet" name="frm" method="get">	
			<input type="text" name="value">
			<input type="submit" value="검색">
			<input type="hidden" name="command" value="product_depsearchaction">
	</form>
<%
	value += "%"+(String)request.getParameter("value")+"%";
	list=dao.depsearch(value);
	for(Item_ListVO iVo:list) {
		count++;
	}
%>
	<span id="searchcount">총 : <%=count%>건</span>
	
	<table id='searchtable'>
		<tr bgcolor="skyblue">
			<th>사진</th><th>부서명</th><th>수량</th><th>품명</th><th>모델명</th><th>규격</th><th>취득단가</th>
		</tr>
<%
	for(Item_ListVO iVo:list) {
%>
		<tr>
			<td></td>
			<td><%=iVo.getDepname()%></td>
			<td><%=iVo.getCount()%>
			<td><%=iVo.getItem_name()%></td>
			<td><%=iVo.getModel_name()%></td>
			<td><%=iVo.getStandard()%></td>
			<td><%=iVo.getGet_cost()%></td>
		</tr>
<%
}
%>
	</table>
</div>

</body>
</html>