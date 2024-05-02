package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;

public class DBManager {
	private static DataSource DS = null;
	private static Connection CONN = null;
	private DBNanager() {}
	
	static {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:comp/env");
			DS = (DataSource) envContext.lookup("jdbc/bbsDBPool");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			CONN = DS.getConnection();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return CONN;
	}
	
	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch(SQLException e) {e.printStackTrace();}
	}
}