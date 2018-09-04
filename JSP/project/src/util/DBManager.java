package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class DBManager {
	public static Connection getMysqlConnection() {
		Connection conn = null;
	      try {
	         Class.forName("com.mysql.jdbc.Driver");
	         
	         String url="jdbc:mysql://localhost:3306/book_ex?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
	         String user="zerock";
	         String password = "zerock";
	         
	         conn = DriverManager.getConnection(url, user, password);
	         
	      }catch(ClassNotFoundException | SQLException e) {
	         e.printStackTrace();
	      }
	      return conn;
	   }

	
	// select을 수행한 후 리소스 해제를 위한 메소드
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			rs.close();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// DML(insert, update, delete)을 수행한 후 리소스 해제를 위한 메소드
	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
