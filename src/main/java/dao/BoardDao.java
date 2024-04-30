package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import vo.Board;
import vo.Comment;

public class BoardDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private static DataSource ds;
	
public ArrayList<Comment> commentList(int no) {
		
		String sqlCommentList = "SELECT * FROM comments where no=? ORDER BY cNo";
		ArrayList<Comment> commentList = null;
		
		try {			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlCommentList);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			commentList = new ArrayList<Comment>();
			
			while(rs.next()) {
				Comment c = new Comment();
				
				c.setcNo(rs.getInt("cNo"));
				c.setNo(rs.getInt("no"));
				c.setWriter(rs.getString("writer"));
				c.setContent(rs.getString("content"));
				c.setPass(rs.getString("pass"));
				c.setRegDate(rs.getTimestamp("reg_date"));
				
				commentList.add(c);
			}			
		} catch(SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {}
		}
		return commentList;
	}

	public void insertComment(Comment comment) {
	String sqlInsert = "insert into comments values "
			+ "(c_seq.nextval, ?, ?, ?, ?, sysdate)";
	try {
		conn = ds.getConnection();
		pstmt = conn.prepareStatement(sqlInsert);
		pstmt.setInt(1, comment.getNo());
		pstmt.setString(2, comment.getWriter());
		pstmt.setString(3, comment.getContent());
		pstmt.setString(4, comment.getPass());
		pstmt.executeUpdate();
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
	
	public BoardDao() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/bbsDBPool");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Board> boardList(int startRow, int endRow) {
		String sqlBoardList = "SELECT * FROM (SELECT ROWNUM num, "
				+ "no, title, writer, reg_date, content, pass, file1 FROM "
				+ "(SELECT * FROM review ORDER BY no DESC)) "
				+ "WHERE num >= ? AND num <= ?";
		
		ArrayList<Board> boardList = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlBoardList);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				boardList = new ArrayList<Board>();
				
				do {
					Board b = new Board();
					b.setNo(rs.getInt("no"));
					b.setTitle(rs.getString("title"));
					b.setWriter(rs.getString("writer"));
					b.setRegDate(rs.getTimestamp("reg_date"));
					b.setContent(rs.getString("content"));
					b.setPass(rs.getString("pass"));
					b.setFile1(rs.getString("file1"));
					
					boardList.add(b);
					
				} while(rs.next());
			}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs != null) rs.close();
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
			}catch(SQLException e) {}
		}
		return boardList;
	}
	
	public Board getBoard(int no) {
		String sqlBoard = "select * from review where no=?";
		Board board = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlBoard);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board= new Board();
				board.setNo(rs.getInt("no"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setRegDate(rs.getTimestamp("reg_date"));
				board.setContent(rs.getString("content"));
				board.setPass(rs.getString("pass"));
				board.setFile1(rs.getString("file1"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return board;
	}
	
	public void insertBoard(Board board) {
		String sqlInsert = "insert into review values "
				+ "(b_seq.nextval, ?, ?, sysdate, ?, ?, ?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlInsert);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getPass());
			pstmt.setString(5, board.getFile1());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean isPassCheck(int no, String pass) {
		boolean isPass = false;
		String sqlPass = "select pass from review where no=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlPass);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isPass = rs.getString(1).equals(pass);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {}
		}
		return isPass;
	}
	
	public void updateBoard(Board board) {
		
		String fileUpdate = board.getFile1() != null ? ", file=?" : "";
		String sqlUpdate = "update review set title=?, writer=?, "
				+ "reg_date=sysdate, content=?"
				+ fileUpdate + " where no=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlUpdate);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			
			if(board.getFile1() != null) {
				pstmt.setString(4, board.getFile1());
				pstmt.setInt(5, board.getNo());
			} else {
				pstmt.setInt(4, board.getNo());
			}
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {}
		}
	}
	
	public void deleteBoard(int no) {
		String sqlDelete = "delete from review where no=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlDelete);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {}
		}
	}
	
	public int getBoardCount() {
		String sqlCount = "select count(*) from review";
		int count = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlCount);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {}
		}
		return count;
	}
	
	public int getBoardCount(String type, String keyword) {
		String sqlCount = "select count(*) from review where "
				+ type + " Like '%' || ? || '%'";
		
		int count = 0;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlCount);
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {}
		}
		return count;
	}
	
	public ArrayList<Board> searchList(String type,
			String keyword, int startRow, int endRow) {
		String sqlSearchList = "SELECT * FROM (SELECT ROWNUM num, no, title, "
				+ "writer, reg_date, content, pass, file1 from "
				+ "(select * from review where " + type + " like ? "
				+ "order by no desc)) where num >= ? and num <= ?";
		
		ArrayList<Board> boardList = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sqlSearchList);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardList = new ArrayList<Board>();
				do {
					Board board = new Board();
					board.setNo(rs.getInt("no"));
					board.setTitle(rs.getString("title"));
					board.setWriter(rs.getString("writer"));
					board.setContent(rs.getString("content"));
					board.setRegDate(rs.getTimestamp("reg_date"));
					board.setPass(rs.getString("pass"));
					board.setFile1(rs.getString("file1"));
					
					boardList.add(board);
					
				} while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(SQLException e) {}
		}
		return boardList;
	}
}