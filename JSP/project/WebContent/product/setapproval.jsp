<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="dao.MemberDAO" %>
<%@ page import ="dto.MemberVO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	MemberDAO dao = MemberDAO.getInstance();
%>

<%
	String message = "";
	if(request.getParameter("password").equals("")) {
		System.out.println(request.getParameter("password"));
		message = "비밀번호가 없어 권한승인 할수 없습니다.";
	}else {
		int result = dao.setApproval(Integer.parseInt(request.getParameter("enumber")));
		if(result > 0 ) {
			message ="권한 승인 되었습니다.";
		}
	}
	
%>
<script>
	alert("<%=message%>");
	location.href='Servlet?command=product_authority';
</script>

</body>
</html>