<%@page import="util.DBManager"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 테스트 </title>
</head>
<body>
<%
	try {
		Connection conn = DBManager.getMysqlConnection();
		   out.print("db 연결 정보: " + conn);
		   
		   Statement stmt = conn.createStatement();
		   
		   String sql = "select * from member";
		   stmt.executeQuery(sql);
		   
		   ResultSet rs = null;
		   
		   rs = stmt.executeQuery(sql);
	if(rs.next()) {
	   do{
	      out.print("<br>");
	      out.print(rs.getLong("enumber")+ "<br>");
	      out.print(rs.getString("password")+ "<br>");
	   }while(rs.next());
	}else {
	   out.print("검색 결과가 없습니다.");
	}
}catch(Exception e) {
	e.printStackTrace();
}

%>
</body>
</html>