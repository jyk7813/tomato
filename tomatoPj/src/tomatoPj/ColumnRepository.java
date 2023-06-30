package tomatoPj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBUtil;

public class ColumnRepository {
	
	public Column selectByColNo(Connection conn, int column_no) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			String query = "SELECT * FROM `column` WHERE column_no = ?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, column_no);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int column_noParse = rs.getInt("column_no");
				String title = rs.getString("title");
				int column_index = rs.getInt("column_Index");
				int active = rs.getInt("active");
				
				return new Column(column_noParse, title, column_index, active);
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
		}
		return null;
		
	}
	
	public List<Column> selectByColNo(int project_no) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Column> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			String query = "SELECT a.column_no, title, column_Index, active FROM `column` AS a\r\n"
					+ "JOIN (SELECT * FROM project_column WHERE project_no = ?) AS b\r\n"
					+ "ON a.column_no = b.column_no";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, project_no);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int column_noParse = rs.getInt("column_no");
				String title = rs.getString("title");
				int column_index = rs.getInt("column_Index");
				int active = rs.getInt("active");
				
				list.add(new Column(column_noParse, title, column_index, active));
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return list;
		
	}
	
	// 나좀살려줘
	// 컬럼추가(db에 동시에 저장), 컬럼객체 리턴
	public Column addColumn(int project_no) {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		PreparedStatement stmt4 = null;
		ResultSet rs = null;
		Column column = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("INSERT INTO `column` VALUES()");
			stmt.executeUpdate();
			
			stmt2 = conn.prepareStatement("SELECT `column_no`,title FROM `column` ORDER BY column_no DESC");
			rs = stmt2.executeQuery();
			rs.next();
			int column_no = rs.getInt("column_no");
			
			stmt3 = conn.prepareStatement("INSERT INTO `project_column` (`project_no`, `column_no`) VALUES (?,?)");
			stmt3.setInt(1, project_no);
			stmt3.setInt(2, column_no);
			stmt3.executeUpdate();
			
			stmt4 = conn.prepareStatement("SELECT * FROM `column` WHERE `column_no` = ?");
			stmt4.setInt(1, column_no);
			String title = rs.getString("title");
			int column_index = rs.getInt("column_Index");
			int active = rs.getInt("active");
			
			return new Column(column_no, title, column_index, active);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(stmt2);
			DBUtil.close(stmt3);
			DBUtil.close(stmt4);
			DBUtil.close(conn);
		}
		return column;
	}
	
}
