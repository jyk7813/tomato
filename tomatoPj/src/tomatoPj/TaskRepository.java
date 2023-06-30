package tomatoPj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import dbutil.DBUtil;

public class TaskRepository {

	// 선택한 멤버들의 배열과 선택한 프로젝트 넘버를 보내면 가지고있는 task리스트 리턴
	// todo에 쓸 데이터베이스 함수구축. 완료
	public HashSet<Task> todoSelectTaskList(int project_no, List<Integer> member_noArray) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		HashSet<Task> list = new HashSet<>();

		try {
			conn = DBUtil.getConnection();
			String query = "SELECT * FROM task AS akatask\r\n" + "JOIN (SELECT a.task_no FROM `member_task` AS a\r\n"
					+ "JOIN (SELECT * FROM `member_tag`\r\n" + "WHERE project_no = ? AND member_no = ?) AS b\r\n"
					+ "ON a.member_no = b.member_no) AS akadupli\r\n" + "ON akatask.task_no = akadupli.task_no";
			stmt = conn.prepareStatement(query);

			for (int i = 0; i < member_noArray.size(); i++) {
				stmt.setInt(1, project_no);
				// stmt.setInt(2, member_noArray.get(i));
				stmt.setObject(2, member_noArray.get(i), Types.INTEGER);
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

	// 태스크넘버를 보내면 해당태스크 참여중인 멤버리스트 가져옴(멤버태그)
	public List<Member> searchMemberByTask_no(int task_no) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Member> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			String query = "SELECT * FROM `member` AS c\n" + "JOIN (SELECT member_no FROM member_task AS a\n"
					+ "JOIN (SELECT * FROM task WHERE task_no = ?) AS b\n" + "ON a.task_no = b.task_no) AS d\n"
					+ "ON c.member_no = d.member_no";
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
				list.add(new Member(member_no, id, pwd, e_mail, name, mbti, active));
			}

		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return list;
	}

	// 태스크넘버를 보내면 해당태스크 작성된 모든 기능태그 가져옴(function_tag)
	public List<Function_Tag> searchFunction_tagByTask_no(int task_no) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Function_Tag> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			String query = "SELECT a.`no`, a.task_no, color, `text` FROM `function_tag` AS a\n"
					+ "JOIN (SELECT * FROM `task` WHERE task_no = ?) AS b\n" + "ON a.task_no = b.task_no;";
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

	// 파라미터에 project_no을 넘기면 해당 프로젝트의 모든 태스크를 가진 task 리스트 리턴
	public List<Task> taskListBypjNo(int project_no) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Task> list = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			String query = "SELECT * FROM task AS a\n"
					+ "JOIN (SELECT task_no FROM (SELECT a.column_no, title, column_Index, active FROM `column` AS a\n"
					+ "JOIN (SELECT * FROM project_column WHERE project_no = ?) AS b\n"
					+ "ON a.column_no = b.column_no) AS a\n" + "JOIN column_task AS b\n"
					+ "ON a.column_no = b.column_no) AS b\n" + "ON a.task_no = b.task_no";
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

	// col_no파라미터로 보내면 해당 들고있는 taskList리턴
	public List<Task> taskListByColNo(int column_no) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Task> list = new ArrayList<>();

		try {
			conn = DBUtil.getConnection();
			String query = "SELECT * FROM `task` AS a\r\n"
					+ "JOIN (\r\n"
					+ "SELECT * FROM `column_task` WHERE `column_no` = ? ) AS b\r\n"
					+ "ON a.task_no = b.task_no";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, column_no);
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
			stmt = conn.prepareStatement(
					"INSERT INTO tomato_copy.task (title, content, importance, updateDate, deadLine)\n"
							+ "VALUES (?,?,?,?,?)");
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
	public int updateTask(int task_no, String title, String content, int importance, Timestamp updateDate,
			Timestamp deadLine) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(
					"UPDATE task\n" + "SET title = ?, content = ?, importance = ?, updateDate = ?, deadLine = ?\n"
							+ "WHERE task_no = ?;");
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
			stmt = conn.prepareStatement("DELETE FROM tomato_copy.task\n" + "WHERE task_no = ?");
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
