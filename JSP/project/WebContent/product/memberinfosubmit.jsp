<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.*"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="util.DBManager"%>
<%@page import="dao.MemberDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//Request 데이터 받아오기
	String S_enumber = request.getParameter("enumber");
	int enumber = Integer.parseInt(S_enumber);
	String S_password = request.getParameter("password");
	MemberDAO dao = MemberDAO.getInstance();

	JSONObject jsonMain = new JSONObject(); // 객체
	JSONArray jArray = new JSONArray(); // 배열
	JSONObject jObject = new JSONObject(); // JSON내용을 담을 객체. 회원 정보
	
	Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
	//JSON으로 Member 정보 안드로이드 전송
   try{
	    
		conn = DBManager.getMysqlConnection();

      
		String sql = "select * from member where enumber='"+enumber+"'";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
	    if(rs.next()){
	    	System.out.println((rs.getString("approval")));
	    	if((rs.getString("approval")).equals("1")) {
		        jObject.put("enumber", rs.getString("enumber"));
		        jObject.put("password", rs.getString("password"));
		        jObject.put("ssn", rs.getString("ssn"));
		        jObject.put("name", rs.getString("name"));
		        jObject.put("authority", rs.getString("authority"));
		        jObject.put("status", rs.getString("status"));
		        jObject.put("approval", rs.getString("approval"));
		        jObject.put("position", rs.getString("position"));
	    	}
	      }
	      jArray.add(0,jObject);
	      jsonMain.put("dataSend",jArray);
	      
	      out.println(jsonMain);
	      System.out.println(jsonMain);
	      out.flush();
   }catch(Exception e){
      e.printStackTrace();
   }finally {
		try {
			DBManager.close(conn, pstmt, rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
%>