package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCExample {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "community";
			String pw = "1234";
			
			conn = DriverManager.getConnection(url, user, pw);
			
			System.out.println(conn);
			
			String sql = "SELECT MEMBER_NO, MEMBER_EMAIL FROM MEMBER";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int memberNo = rs.getInt("MEMBER_NO");
				String memberEmail = rs.getString("MEMBER_EMAIL");
				
				System.out.printf("회원번호: %d, 이메일: %s\n", memberNo, memberEmail);
			}
			
		} catch(ClassNotFoundException e) {
			System.out.println("JDBC 경로 설정 잘못했삼~ㅋ");
		} catch(Exception e ) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}