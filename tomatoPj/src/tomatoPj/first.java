
package tomatoPj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbutil.DBUtil;

public class first {

	public static void main(String[] args) {
		System.out.println("hello tomato!");
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			stmt= conn.prepareStatement("SHOW TABLES");
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getObject(1));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		
	}

}
