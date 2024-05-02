package dao;

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
			CONN = DS.getConnection() {
				
			}
		}
	}
}