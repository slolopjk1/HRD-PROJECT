package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;
import dto.*;

public class MemberDAO {
	private MemberDAO() {}
	
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance() {
		return instance;
	}
	
	// 로그인 할때 사용자 인증시 사용하는 메소드
	public int userCheck(int enumber, String password) {
		int result = -1;
		String sql = "select * from member where enumber='"+enumber+"'";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getMysqlConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if(rs.getString("authority").equals("2")) {
					result = -1;
				}else{
					if (rs.getString("password") != null && rs.getString("password").equals(password)) {
						result = 1; // 성공
					}else {
						result = 0; // 비밀번호가 맞지 않음
					}
				}
			}else{
					result = -1; // 존재하지 않는 회원
				 }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBManager.close(conn, pstmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// 아이디로 회원 정보 가져오는 메소드
		public MemberVO getMember(int enumber) {
			MemberVO mVo = null;
			String sql = "select * from member where enumber='"+enumber+"'";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = DBManager.getMysqlConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					mVo = new MemberVO();
					mVo.setEnumber(rs.getInt("enumber"));
					mVo.setPassword(rs.getString("password"));
					mVo.setSsn(rs.getInt("ssn"));
					mVo.setName(rs.getString("name"));
					mVo.setAuthority(rs.getInt("authority"));
					mVo.setStatus(rs.getInt("status"));
					mVo.setApproval(rs.getInt("approval"));
					mVo.setPosition(rs.getString("position"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					DBManager.close(conn, pstmt, rs);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return mVo;
		}
		
	// 회원 정보에 비밀번호 입력 하기 위한 메소드
	public int updatePassword(int enumber, String password) {
		int result = -1;
		String sql = "update member set password='"+ password +"' where enumber= '"+enumber+"'";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getMysqlConnection();
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();         //삽입,삭제,수정 되면 1리턴 아니면 0
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBManager.close(conn, pstmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// 아이디 중복 체크를 위한 메소드 추가하기
	public int confirmEnumber(int enumber) {
		int result = -1;
		String sql = "select enumber from member where enumber=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getMysqlConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, enumber);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
			} else {
				result = -1;
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBManager.close(conn, pstmt, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// 회원 정보 모두 출력
	public List<MemberVO> getMemberList() {
		String sql = "select * from member where authority=2";
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getMysqlConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO mVo = new MemberVO();
				
				mVo.setEnumber(rs.getInt("enumber"));      
				mVo.setPassword(rs.getString("password"));
				mVo.setSsn(rs.getInt("ssn"));
				mVo.setName(rs.getString("name"));
				mVo.setApproval(rs.getInt("approval"));
				mVo.setPosition(rs.getString("position"));
				
				list.add(mVo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	// 사용자 사용할수 있게 권한 주는 메소드
	public int setApproval(int enumber) {
		int result = -1;
		String sql = "update member set approval=1 where enumber='"+enumber+"'";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getMysqlConnection();
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();         //삽입,삭제,수정 되면 1리턴 아니면 0
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBManager.close(conn, pstmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// 사용자 사용 못하게 권한 주는 메소드
	public int dropApproval(int enumber) {
		int result = -1;
		String sql = "update member set approval=2 where enumber='"+enumber+"'";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getMysqlConnection();
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();         //삽입,삭제,수정 되면 1리턴 아니면 0
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBManager.close(conn, pstmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	// 사용자 추가하는 메소드
	public int insertMember(String[] arr) {
		int result = -1;
		String sql = "insert into member values ('" + Integer.parseInt(arr[0]) + "','" + "" + "','" + Integer.parseInt(arr[1]) + "','" + arr[2] + "'" +
				",'" + "2" + "','"  + "1"  + "','" + "0" + "','" +arr[3] +"')";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getMysqlConnection();
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();         //삽입,삭제,수정 되면 1리턴 아니면 0
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBManager.close(conn, pstmt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	
}
