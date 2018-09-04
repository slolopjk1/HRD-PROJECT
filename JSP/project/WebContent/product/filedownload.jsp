<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.*"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="util.DBManager"%>
<%@page import="dao.Item_ListDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    //부서명 안드로이드 데이터 받아오기
	String _depcode = request.getParameter("depcode");
	int depcode = Integer.parseInt(_depcode);
	//초기화
	Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
	
	int i=0;
	JSONObject jsonMain = new JSONObject(); // 객체
	JSONArray jArray = new JSONArray(); // 배열
	JSONObject jObject; // JSON내용을 담을 객체. 회원 정보
	
	  
    //JSON으로 Item_List정보 안드로이드 전송
    try{
 		conn = DBManager.getMysqlConnection();
		
 		String sql = "select * from item_list where dep_code='"+depcode+"'" + "order by Item_num asc";
 		pstmt = conn.prepareStatement(sql);
 		rs = pstmt.executeQuery();
 	    while(rs.next()){
 	    		jObject = new JSONObject(); // JSON내용을 담을 객체. 물품 정보
 		        jObject.put("Item_num", rs.getString("Item_num"));
 		        jObject.put("Item_name", rs.getString("Item_name"));
 		        jObject.put("SN", rs.getString("SN"));
 		        jObject.put("Manufacture", rs.getString("Manufacture"));
 		        jObject.put("Model_name", rs.getString("Model_name"));
 		        jObject.put("Standard", rs.getString("Standard"));
 		        jObject.put("Dep_code", rs.getString("dep_code"));
 		        jObject.put("Use_where", rs.getString("Use_where"));
 		        jObject.put("Image", rs.getBlob("Image"));
 		        jObject.put("Get_date", rs.getString("Get_date"));
 		        jObject.put("Pro_date", rs.getString("Pro_date"));
 		        jObject.put("Get_cost", rs.getString("Get_cost"));
 		       	jArray.add(i,jObject);
 		       	i++;
 	    }
 	      
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