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

	// 프로젝트생성(수정본)
	public Project generateProject(String title, int member_no) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		PreparedStatement stmt4 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		Project project = null;
		try {
			conn = DBUtil.getConnection();
			String query = "INSERT INTO tomato_copy.project (title, member_no)\r\n" + "VALUES (?,?)";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, title);
			stmt.setInt(2, member_no);
			stmt.executeUpdate();
			
			stmt2 = conn.prepareStatement("SELECT `project_no` FROM `project` ORDER BY `project_no` DESC");
			rs = stmt2.executeQuery();
			rs.next();
			int project_no = rs.getInt("project_no");
			System.out.println("신규프로젝트 pj_no" + project_no);
			stmt3 = conn.prepareStatement("INSERT INTO `member_tag` (`project_no`, `member_no`)"
					+ " VALUES (?,?)");
			stmt3.setInt(1, project_no);
			stmt3.setInt(2, member_no);
			stmt3.executeUpdate();
			
			stmt4 = conn.prepareStatement("SELECT * FROM `project` WHERE `project_no` = ?");
			stmt4.setInt(1, project_no);
			rs2 = stmt4.executeQuery();
			rs2.next();
			String pjtitle = rs2.getString("title");
			int active = rs2.getInt("active");
			project = new Project(project_no, pjtitle, member_no, active);
			
		} finally {
			DBUtil.close(rs);
			DBUtil.close(rs2);
			DBUtil.close(stmt);
			DBUtil.close(stmt2);
			DBUtil.close(stmt3);
			DBUtil.close(stmt4);
			DBUtil.close(conn);
		}
		return project;
	}

	// 프로젝트제거
	public int deleteProject(int project_no) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtil.getConnection();
			String query = "DELETE FROM tomato_copy.project\r\n" + "WHERE project_no = ?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, project_no);
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
			for (Project_Column_Package a : packlist) {
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
