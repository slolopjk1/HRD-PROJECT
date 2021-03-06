<%@ page import ="dao.MemberDAO" %>
<%@ page import ="dto.MemberVO" %>
<%@ page import ="java.util.List"%>
<%@page import="java.sql.*"%>
<%@page import="util.DBManager"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>권한 부여</title>
<script type="text/javascript" src="script/authority.js"></script>
<link rel="stylesheet" type="text/css" href="css/background.css">
<style>
@import url(//fonts.googleapis.com/earlyaccess/hanna.css);
#membertable {
	position:relative;
	top:30px;
	margin: auto;
	width: 700px;
	border:2px double pink;
	text-align:center;
}
#member td,th{
	border:1px solid pink;
}
#mainbutton {
	float:right;	
}
#membercount {
	float:right;
	font-size:18px;
}
</style>
</head>
<body>
	<div id="headerblock">
		<span id="header">물품 관리 시스템</span>
		<input id="mainbutton" type="button" value="메인화면으로" onclick="location.href='Servlet?command=product_main'">
	</div>
	<div id="mainblock">
		<span id="mainheader">사용자 정보</span>
	</div>
<%
	MemberDAO dao = MemberDAO.getInstance();
	List<MemberVO> list = dao.getMemberList();
	int membercount = 0;
	for(MemberVO mVo : list) {
		membercount++;
	}
%>
	<div id="subblock">
		<span id="membercount">사용자 총:<%=membercount%>명</span>
	<table id="membertable">
		<tr bgcolor="pink">
			<th>사원번호</th><th>비밀번호</th><th>생년월일</th><th>이름</th><th>직급</th><th>승인확인</th><th>승인취소</th>
		</tr>
<%
	int count = 0;
	for(MemberVO mVo :list) {
		String button_approval = "button_approval"+ count;
		String button_delete = "button_delete" + count;
		count++;

%>
		<tr>
			<td><%=mVo.getEnumber() %></td>
			<td><%=mVo.getPassword() %></td>
			<td><%=mVo.getSsn() %></td>
			<td><%=mVo.getName() %></td>
			<td><%=mVo.getPosition() %></td>
			<td><input type="button" value="확인" id=<%=button_approval%> onclick="setApproval('<%=mVo.getEnumber()%>','<%=mVo.getPassword()%>');"></td>
			<td><input type="button" value="취소" id=<%=button_delete%> onclick="dropApproval('<%=mVo.getEnumber()%>','<%=mVo.getPassword()%>');"></td>
			<%if(mVo.getApproval()==1) { %>
			<script>disableapproval('<%=button_approval%>', '<%=button_delete%>')</script>
			<%}else if(mVo.getApproval()==2) {%>
			<script>disabledrop('<%=button_approval%>', '<%=button_delete%>')</script>
			<%} %>
		</tr>
<%
}
%>
	</table>
	</div>
</body>
</html>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        