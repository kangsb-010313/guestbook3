package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestbookVO;

public class GuestbookDAO {
	
	//필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/guestbook_db";
	private String id = "guestbook";
	private String pw = "guestbook";

	
	//생성자
	public GuestbookDAO() {
	}
	
	
	//메소드 gs
	
	
	//메소드 일반
	private void connect() {

		try {

			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}//connect
	
	private void close() {
		
		try {
			if(rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}//close
	
	////////////////////////////////////////////////////////////
	//리스트 가져오기
	public List<GuestbookVO> guestbookSelect(){
		
		System.out.println("guestbookSelect()");
		
		List<GuestbookVO> guestbookList = new ArrayList<GuestbookVO>();
		
		this.connect();
		
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행

			//SQL문 준비
			String query = "";
				   query += " select  no, ";
				   query += "		  name, ";
				   query += " 		  password, ";
				   query += "		  content, ";
				   query += "		  reg_date ";
				   query += " from guestbook ";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			
			//실행
			rs = pstmt.executeQuery();
			
			//4.결과처리
			while(rs.next()) {
				
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String regDate = rs.getString("reg_date");
				
				GuestbookVO guestbookVO = new GuestbookVO(no, name, password, content, regDate);
				
				guestbookList.add(guestbookVO);
				
				
				
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		this.close();
		return guestbookList;

	}
	
	
	//등록하기
	public int guestbookInsert(GuestbookVO guestbookVO) {
		
		System.out.println("guestbookInsert()");
		
		int count = -1;
		
		this.connect();
		
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행

			//SQL문 준비
			String query = "";
				   query += " insert into guestbook ";
				   query += " values(null, ?, ?, ?, now()) ";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, guestbookVO.getName());
			pstmt.setString(2, guestbookVO.getPassword());
			pstmt.setString(3, guestbookVO.getContent());
			
			//실행
			count = pstmt.executeUpdate();
			
			//4.결과처리
			System.out.println(count + "건 등록되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		this.close();
		return count;
		
		
	}
	
	//삭제하기
	public int guestbookDelete(int no, String password) {
		
		System.out.println("guestbookDelete()");
		
		int count = -1;
		
		this.connect();
		
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행

			//SQL문 준비
			String query = "";
				   query += " delete from guestbook ";
				   query += " where no = ? ";
				   query += " and password = ? ";
			
			//바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			pstmt.setString(2, password);
			
			//실행
			count = pstmt.executeUpdate();
			
			//4.결과처리
			System.out.println(count + "건이 삭제되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		this.close();
		return count;
		
		
	}
	
	

}
