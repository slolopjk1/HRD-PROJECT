package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Item_ListVO;
import util.DBManager;

public class Item_ListDAO {
	private Item_ListDAO() {}
	
	private static Item_ListDAO instance = new Item_ListDAO();
	
	public static Item_ListDAO getInstance() {
		return instance;
	}
	
	
	// 총 물품 리스트 select 하는 함수
	public List<Item_ListVO> getitemlist() {
		String sql = "select * from item_list inner join department on item_list.dep_code = department.depcode order by Item_num asc";
		List<Item_ListVO> list = new ArrayList<Item_ListVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getMysqlConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Item_ListVO iVo = new Item_ListVO();
					
				iVo.setItem_num(rs.getString("Item_num"));      
				iVo.setItem_name(rs.getString("Item_name"));
				iVo.setSN(rs.getString("SN"));
				iVo.setManufacture(rs.getString("Manufacture"));
				iVo.setModel_name(rs.getString("Model_name"));
				iVo.setStandard(rs.getString("Standard"));
				iVo.setDep_code(rs.getInt("dep_code"));
				iVo.setUse_where(rs.getString("Use_where"));
				iVo.setGet_date(rs.getString("Get_date"));
				iVo.setPro_date(rs.getString("Pro_date"));
				iVo.setGet_cost(rs.getInt("Get_cost"));
				iVo.setDepname(rs.getString("depname"));

				list.add(iVo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	//검색한 물품 리스트 select 하는 함수
	public List<Item_ListVO> searchitemlist(String value) {
		String sql = "select * from item_list inner join department on item_list.dep_code=department.depcode where"+
				" depname like ? or Item_num like ? or Item_name like ? or Manufacture like ? or Model_name like ? or Standard like ?"+
				" or Use_where like ? or Get_date like ? or Pro_date like ? or Get_cost like ? order by Item_num asc";
				     
		List<Item_ListVO> list = new ArrayList<Item_ListVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getMysqlConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);
			pstmt.setString(2, value);
			pstmt.setString(3, value);
			pstmt.setString(4, value);
			pstmt.setString(5, value);
			pstmt.setString(6, value);
			pstmt.setString(7, value);
			pstmt.setString(8, value);
			pstmt.setString(9, value);
			pstmt.setString(10, value);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Item_ListVO iVo = new Item_ListVO();
					
				iVo.setItem_num(rs.getString("Item_num"));     // 물품일련번호    
				iVo.setItem_name(rs.getString("Item_name"));   // 품명
				iVo.setSN(rs.getString("SN"));                 // 일련번호
				iVo.setManufacture(rs.getString("Manufacture")); // 제조업체
				iVo.setModel_name(rs.getString("Model_name"));  // 모델명
				iVo.setStandard(rs.getString("Standard"));      // 규격
				iVo.setDep_code(rs.getInt("dep_code"));         // 부서코드
				iVo.setUse_where(rs.getString("Use_where"));    // 위치
				iVo.setGet_date(rs.getString("Get_date"));      // 취득일자
				iVo.setPro_date(rs.getString("Pro_date"));      // 처리일자
				iVo.setGet_cost(rs.getInt("Get_cost"));         // 취득단가
				iVo.setDepname(rs.getString("depname"));        // 부서명

				list.add(iVo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	// 부서 개수 리턴하는 함수
	public int depcount() {
		int count = 0;
		String sql ="select count(*) from (select distinct dep_code from item_list) b";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getMysqlConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return count;
	}
	
	//부서별 검색
	public List<Item_ListVO> depsearch(String value) {
		String sql = "select *,count(*) from item_list inner join department on item_list.dep_code=department.depcode"+
					 " where Item_name like ? group by dep_code order by dep_code asc";
		List<Item_ListVO> list = new ArrayList<Item_ListVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBManager.getMysqlConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Item_ListVO iVo = new Item_ListVO();
					
				iVo.setItem_num(rs.getString("Item_num"));      
				iVo.setItem_name(rs.getString("Item_name"));
				iVo.setSN(rs.getString("SN"));
				iVo.setManufacture(rs.getString("Manufacture"));
				iVo.setModel_name(rs.getString("Model_name"));
				iVo.setStandard(rs.getString("Standard"));
				iVo.setDep_code(rs.getInt("dep_code"));
				iVo.setUse_where(rs.getString("Use_where"));
				iVo.setGet_date(rs.getString("Get_date"));
				iVo.setPro_date(rs.getString("Pro_date"));
				iVo.setGet_cost(rs.getInt("Get_cost"));
				iVo.setDepname(rs.getString("depname"));
				iVo.setCount(rs.getInt("count(*)"));

				list.add(iVo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
}
