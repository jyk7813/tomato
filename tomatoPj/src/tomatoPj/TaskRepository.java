package tomatoPj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBUtil;

public class TaskRepository {
	
	// 파라미터에 project_no을 넘기면 해당 프로젝트의 모든 태스크를 가진 task 리스트 리턴
	public List<Task> taskListBypjNo(int project_no) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Task> list = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			String query = "SELECT * FROM task AS a\n" + 
					"JOIN (SELECT task_no FROM (SELECT a.column_no, title, column_Index, active FROM `column` AS a\n" + 
					"JOIN (SELECT * FROM project_column WHERE project_no = ?) AS b\n" + 
					"ON a.column_no = b.column_no) AS a\n" + 
					"JOIN column_task AS b\n" + 
					"ON a.column_no = b.column_no) AS b\n" + 
					"ON a.task_no = b.task_no";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, project_no);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int task_no = rs.getInt("task_no");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int importance = rs.getInt("importance");
				Timestamp updateDate = rs.getTimestamp("updateDate");
				Timestamp deadLine = rs.getTimestamp("deadLine");
				int active = rs.getInt("active");
				list.add(new Task(task_no, title, content, importance, updateDate, deadLine, active));
			}
			
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return list;
	}
}
