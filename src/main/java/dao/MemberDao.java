package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	public int checkmember(String id, String pass) {
		String loginSql = "select pass from member where id=?";
		
		// -1 아이디 없음, 0 비밀번호 틀림, 1 로그인 성공
		int result = 1;
		String password = "";
		
		try {
			conn = DBManager.getconnection();
			pstmt = conn.prepareStatement(loginSql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				password = rs.getString("pass");
			} else {
				return result;
			}
			
			if(password.equals(pass)) {
				result = 1;
			} else {
				result = 0;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}
}