<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.*"%>
<%@page import= "org.json.simple.JSONArray"%>
<%@page import="util.DBManager" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
   //Request 데이터 받아오기
   String S_enumber = request.getParameter("enumber");
   int enumber = Integer.parseInt(S_enumber);
   Connection conn = null;
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   JSONObject jsonMain = new JSONObject(); // 객체
   JSONArray jArray = new JSONArray(); // 배열
   JSONObject jObject = new JSONObject(); // JSON내용을 담을 객체. 성공, 실패 체크
   
   //DB 연결
   conn = DBManager.getMysqlConnection();
   
   //ID 사용가능 여부 확인
   try{
      String sql = "select * from member where enumber='"+enumber+"'";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      
	  if(!rs.next())
		  jObject.put("Msg1",2); //사원번호 자체가 없는것
	                                 
      rs.previous();             //rs 돌림
      
      if(rs.next()) {
         if(S_enumber.equals(rs.getString("enumber"))){  //성공
        	 if(rs.getInt("authority")==1) {
        		 jObject.put("Msg1",2);
        	 }else {
        		 jObject.put("Msg1",1);
        	 }
         }
      }
   }catch(Exception e){
      e.printStackTrace();
   }finally {
      try {
    	 DBManager.close(conn, pstmt, rs);
      }catch(Exception e) {
         e.printStackTrace();
      }
   }
   System.out.println(jObject);
   jArray.add(0, jObject);
   jsonMain.put("dataSend", jArray); // JSON의 제목 지정
   out.println(jsonMain);
   out.flush();
   
%>