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
		//Column column = null;

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
			String query = "ELECT a.column_no, title, column_Index, active FROM `column` AS a\r\n"
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
	
}
