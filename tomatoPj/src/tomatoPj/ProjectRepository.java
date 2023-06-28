package tomatoPj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBUtil;

public class ProjectRepository {
	private ColumnRepository colRepo;
	
	public ProjectRepository() {
		colRepo = new ColumnRepository();
	}

	public int generateProject(String title, int member_no) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtil.getConnection();
			String query = "INSERT INTO tomato_copy.project (title, member_no)\r\n" + 
					"VALUES (?,?)";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, title);
			stmt.setInt(2, member_no);
			return stmt.executeUpdate();

		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}
	
	public List<Column> returnProjectColumn(int project_no) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Column> list = new ArrayList<>();
		List<Project_Column_Package> packlist = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			String query = "SELECT * FROM `project_column` WHERE project_no = ?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, project_no);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int package_no = rs.getInt("package_no");
				int project_noParse = rs.getInt("project_no");
				int column_no = rs.getInt("column_no");
				packlist.add(new Project_Column_Package(package_no, project_noParse, column_no));
			}
			for(Project_Column_Package a : packlist) {
				int key = a.getColumn_no();
				list.add(colRepo.selectByColNo(conn, key));
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return list;
	}
	

}
