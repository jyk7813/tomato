package tomatoPj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import dbutil.DBUtil;

public class TaskRepository {
	
	// 선택한 멤버들의 배열과 선택한 프로젝트 넘버를 보내면 가지고있는 task리스트 리턴
	// todo에 쓸 데이터베이스 함수구축.
	public HashSet<Task> todoSelectTaskList(int[] member_noArray, int project_no) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		HashSet<Task> list = new HashSet<>();

		try {
			conn = DBUtil.getConnection();
			String query = "SELECT * FROM task AS akatask\r\n"
					+ "JOIN (SELECT a.task_no FROM `member_task` AS a\r\n"
					+ "JOIN (SELECT * FROM `member_tag`\r\n"
					+ "WHERE project_no = ? AND member_no = ?) AS b\r\n"
					+ "ON a.member_no = b.member_no) AS akadupli\r\n"
					+ "ON akatask.task_no = akadupli.task_no";
			stmt = conn.prepareStatement(query);
			
			for(int i = 0;i < member_noArray.length; i++) {
				stmt.setInt(1, project_no);
				stmt.setInt(2, member_noArray[i]);
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
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return list;
	}
	
	//태스크 특정 // 공사중 ///////////////////////
	// 멤버리스트에 해당 태스크 참여중인 멤버리스트 가져와야함
	public Task searchByTask_no(int task_no) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Task task = null;
		try {
			conn = DBUtil.getConnection();
			String query = "SELECT * FROM task WHERE task_no = ?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, task_no);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int task_noParse = rs.getInt("task_no");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int importance = rs.getInt("importance");
				Timestamp updateDate = rs.getTimestamp("updateDate");
				Timestamp deadLine = rs.getTimestamp("deadLine");
				int active = rs.getInt("active");
				
				List<Member> list = null;
				
				task = new Task(task_noParse, title, content, importance, updateDate, deadLine, active);
			}
			
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return task;
	}
	
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
	
	// 새로운 태스크 생성
	public int insertTask(String title, String content, int importance, Timestamp updateDate, Timestamp deadLine) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("INSERT INTO tomato_copy.task (title, content, importance, updateDate, deadLine)\n" + 
					"VALUES (?,?,?,?,?)");
			stmt.setString(1, title);
			stmt.setString(2, content);
			stmt.setInt(3, importance);
			stmt.setTimestamp(4, updateDate);
			stmt.setTimestamp(5, deadLine);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
			
		}
		return 0;
	}
	
	// 태스크 수정
	public int updateTask(int task_no, String title, String content, int importance, Timestamp updateDate, Timestamp deadLine) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("UPDATE task\n" + 
					"SET title = ?, content = ?, importance = ?, updateDate = ?, deadLine = ?\n" + 
					"WHERE task_no = ?;");
			stmt.setString(1, title);
			stmt.setString(2, content);
			stmt.setInt(3, importance);
			stmt.setTimestamp(4, updateDate);
			stmt.setTimestamp(5, deadLine);
			stmt.setInt(6, task_no);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
			
		}
		return 0;
	}
	
	// 특정한 태스크 삭제
	public int deleteTask(int task_no) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("DELETE FROM tomato_copy.task\n" + 
					"WHERE task_no = ?");
			stmt.setInt(1, task_no);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
			
		}
		return 0;
	}
}
