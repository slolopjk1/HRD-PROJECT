<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.*"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="util.DBManager"%>
<%@page import="dao.MemberDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
   //Request 데이터 받아오기
	String S_enumber = request.getParameter("enumber");
	int enumber = Integer.parseInt(S_enumber);
	String S_password = request.getParameter("password");
	MemberDAO dao = MemberDAO.getInstance();
	System.out.println(enumber);
	System.out.println(S_password);
   
	JSONObject jsonMain = new JSONObject(); // 객체
	JSONArray jArray = new JSONArray(); // 배열
	JSONObject jObject = new JSONObject(); // JSON내용을 담을 객체. 회원 정보
   
   //Request 데이터를 DB에 저장
   dao.updatePassword(enumber, S_password);
   
%>