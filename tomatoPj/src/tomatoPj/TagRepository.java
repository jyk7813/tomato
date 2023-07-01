package tomatoPj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBUtil;

public class TagRepository {
	
	// 기능태그
	public List<Function_Tag> getFunction_tagByTask_no(int task_no) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Function_Tag> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			String query = "SELECT * FROM function_tag WHERE task_no = ?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, task_no);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				int task_noParse = rs.getInt("task_no");
				String color = rs.getString("color");
				String text = rs.getString("text");
				
				list.add(new Function_Tag(no, task_noParse, color, text));
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return list;
	}
	
	// 멤버태그
	public List<Member> getMember_taskListByTask_no(int task_no) throws SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Member> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			String query = "SELECT * FROM `member` AS a\r\n"
					+ "JOIN (\r\n"
					+ "SELECT * FROM member_task WHERE task_no = ? ) AS b\r\n"
					+ "ON a.member_no = b.member_no";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, task_no);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int member_no = rs.getInt("member_no");
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String e_mail = rs.getString("e-mail");
				String name = rs.getString("name");
				String mbti = rs.getString("mbti");
				int active = rs.getInt("active");
				byte[] image = rs.getBytes("image");
				list.add(new Member(member_no, id, pwd, e_mail, name, mbti, active, image));
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return list;
	}
}
