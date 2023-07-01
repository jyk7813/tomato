package tomatoPj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import dbutil.DBUtil;

public class Task_Service_Repository {

	
	/**
	 * @explain db에 선택한태스크가 있다는걸 기준으로 돌아감
	 * -> 새로운 태스크판넬을 만들었을때는 taskRepository안에있는 insertTask함수를사용해 db에 태스크데이터를 올리고 이기능을 사용할것
	 * -> 위의 주의사항을 따랐다면 태스크 바깥쪽에 업데이트되는 부분에서 이함수를 사용하면된다.
	 * --> Feedback, Function_Tag, Member_task객체에 pk는 새로만들었으면 반드시 0으로 세팅해줘야한다.
	 * --> 위의 모든 객체는 테이블과같은 생성자를 사용하여야한다.
	 * --> 멤버, 기능 태그는 반드시 List의 형태로 넘겨주어야하며
	 * ---> 작성한 태그가없을때 List에 null을 넣어서 보내면 안된다.
	 * ---> 작성할 태그가없을때는 List<Generic> list = new ArrayList<>(); 로 보낼것.
	 * 
	 * @param Task
	 * @param Feedback
	 * @param List<Function_Tag>
	 * @param List<Member_task>
	 */
	public String updateTask(Task task, Feedback feedback, List<Function_Tag> function_tagList, List<Member_task> member_taskList) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(
					"UPDATE task\n" + "SET title = ?, content = ?, importance = ?, updateDate = ?, deadLine = ?\n"
							+ "WHERE task_no = ?;");
			stmt.setString(1, task.getTitle());
			stmt.setString(2, task.getContent());
			stmt.setInt(3, task.getImportance());
			stmt.setTimestamp(4, task.getUpdateDate());
			stmt.setTimestamp(5, task.getDeadLine());
			stmt.setInt(6, task.getTask_no());
			stmt.executeUpdate();
			
			FeedbackFunction(conn, feedback);
			Function_TagFunction(conn, function_tagList);
			Member_taskFunction(conn, member_taskList);
			
			return "태스크 잘들어감";
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);

		}
		return "끝까지돌아가긴했음";
	}

	// 피드백이 없어도 돌아가게 만들었음
	public void FeedbackFunction(Connection conn, Feedback feedback) {
		PreparedStatement stmt = null;
		try {
			if (feedback.getFeedback_no() == 0) {
				stmt = conn.prepareStatement("INSERT INTO `feedback` (task_no, member_no, `comment`) VALUES(?,?,?)");
				stmt.setInt(1, feedback.getTask_no());
				stmt.setInt(2, feedback.getMember_no());
				stmt.setString(3, feedback.getComment());
				stmt.executeUpdate();
			} else if (feedback.getFeedback_no() != 0) {
				stmt = conn.prepareStatement(
						"UPDATE `feedback` SET feedback_no = ?, task_no = ?, member_no = ?, `comment` = ?\r\n"
								+ "WHERE task_no = ?");
				stmt.setInt(1, feedback.getFeedback_no());
				stmt.setInt(2, feedback.getTask_no());
				stmt.setInt(3, feedback.getMember_no());
				stmt.setString(4, feedback.getComment());
				stmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
		}
	}

	// 기능 태그가없어도 돌아감
	public void Function_TagFunction(Connection conn, List<Function_Tag> function_tagList) {
		PreparedStatement stmt = null;
		try {
			if (function_tagList.size() == 0) {
				// 태그가없으면 굳이안만듬
			} else if (function_tagList.size() != 0) {
				for (Function_Tag function_tag : function_tagList) {
					if (function_tag.getNo() == 0) {
						stmt = conn
								.prepareStatement("INSERT INTO `function_tag` (task_no, color, `text`) VALUES(?,?,?)");
						stmt.setInt(1, function_tag.getTask_no());
						stmt.setString(1, function_tag.getColor());
						stmt.setString(2, function_tag.getText());
						stmt.executeUpdate();
					} else if (function_tag.getNo() != 0) {
						stmt = conn.prepareStatement(
								"UPDATE `function_tag` SET color = ?, `comment` = ?\r\n" + "WHERE task_no = ?");
						stmt.setString(1, function_tag.getColor());
						stmt.setString(2, function_tag.getText());
						stmt.setInt(3, function_tag.getTask_no());
						stmt.executeUpdate();
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
		}
	}

	// 멤버태그가 없어도 돌아감
	public void Member_taskFunction(Connection conn, List<Member_task> member_taskList) {
		PreparedStatement stmt = null;
		try {
			if (member_taskList.size() == 0) {
				// 태그가없으면 굳이안만듬
			} else if (member_taskList.size() != 0) {
				for (Member_task member_task : member_taskList) {
					if (member_task.getPackage_no() == 0) {
						stmt = conn
								.prepareStatement("INSERT INTO `member_task` (`member_no`, `task_no`, `color`) "
										+ "VALUES(?,?,?)");
						stmt.setInt(1, member_task.getMember_no());
						stmt.setInt(2, member_task.getTask_no());
						stmt.setString(3, member_task.getColor());					
						stmt.executeUpdate();
					} else if (member_task.getPackage_no() != 0) {
						stmt = conn.prepareStatement(
								"UPDATE `member_task` SET `member_no` = ?, `color` = ?\r\n" + "WHERE `task_no` = ?");
						stmt.setInt(1, member_task.getMember_no());
						stmt.setString(2, member_task.getColor());	
						stmt.setInt(3, member_task.getTask_no());
						stmt.executeUpdate();
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
		}
	}
}
