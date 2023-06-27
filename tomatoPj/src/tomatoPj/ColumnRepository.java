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
}
