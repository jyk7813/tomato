package tomatoPj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbutil.DBUtil;

public class FeedbackRepository {

	
	// 태스크넘버를 보내면 feedback객체리턴
	public Feedback searchFeedbackBytask_no(int task_no) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Feedback feedback = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM `feedback`\r\n" + 
					"WHERE `task_no` = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, task_no);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				int feedback_no = rs.getInt("feedback_no");
				int task_noParse = rs.getInt("task_no");
				int member_no = rs.getInt("member_no");
				String comment = rs.getString("comment");
				feedback = new Feedback(feedback_no, task_noParse, member_no, comment);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return feedback;
	}
}
