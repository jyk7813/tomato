package tomatoPj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import dbutil.DBUtil;

public class Task_Service_Repository {

	/**
	 * @explain db에 선택한태스크가 있다는걸 기준으로 돌아감 -> 새로운 태스크판넬을 만들었을때는 taskRepository안에있는
	 *          insertTask함수를사용해 db에 태스크데이터를 올리고 이기능을 사용할것 -> 위의 주의사항을 따랐다면 태스크 바깥쪽에
	 *          업데이트되는 부분에서 이함수를 사용하면된다. --> Feedback, Function_Tag, Member_task객체에
	 *          pk는 새로만들었으면 반드시 0으로 세팅해줘야한다. --> 위의 모든 객체는 테이블과같은 생성자를 사용하여야한다. -->
	 *          멤버, 기능, 피드백은 작성한게없어도 돌아감 --> 작성한게없다면 null값을 보내거나 --> 아무것도 들어있지않은
	 *          List<Generic> list = new ArrayList<>(); list를 파라미터에 보내주면된다.
	 * @param Task
	 * @param Feedback
	 * @param List<Function_Tag>
	 * @param List<Member_task>
	 */
	public int updateTask(Task task, /*Feedback feedback, List<Function_Tag> function_tagList,
			List<Member_task> member_taskList, */int column_no) {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		PreparedStatement stmt3 = null;
		ResultSet rs = null;
		int task_no = 0;
		try {
			
			if (task != null) {
				conn = DBUtil.getConnection();
				if (task.getTask_no() == 0) {
					String sql = "INSERT INTO tomato_copy.task (title, content, importance, updateDate, deadLine)"
							+ " VALUES (?,?,?,?,?)";
					stmt = conn.prepareStatement(sql);
					stmt.setString(1, task.getTitle());
					stmt.setString(2, task.getContent());
					stmt.setInt(3, task.getImportance());
					stmt.setTimestamp(4, task.getUpdateDate());
					stmt.setTimestamp(5, task.getDeadLine());
					stmt.executeUpdate();

					stmt2 = conn.prepareStatement("SELECT `task_no` FROM `task` ORDER BY `task_no` DESC");
					rs = stmt2.executeQuery();
					rs.next();
					task_no = rs.getInt("task_no");

					stmt3 = conn.prepareStatement("INSERT INTO `column_task` (`column_no`, `task_no`) VALUES (?,?)");
					stmt3.setInt(1, column_no);
					stmt3.setInt(2, task_no);
					stmt3.executeUpdate();

//					FeedbackFunction(conn, feedback, task_no);
//					Function_TagFunction(conn, function_tagList, task_no);
//					Member_taskFunction(conn, member_taskList, task_no);
					return task_no;
				} else if (task.getTask_no() != 0) {

					stmt = conn.prepareStatement("UPDATE task\n"
							+ "SET title = ?, content = ?, importance = ?, updateDate = ?, deadLine = ?\n"
							+ "WHERE task_no = ?;");
					stmt.setString(1, task.getTitle());
					stmt.setString(2, task.getContent());
					stmt.setInt(3, task.getImportance());
					stmt.setTimestamp(4, task.getUpdateDate());
					stmt.setTimestamp(5, task.getDeadLine());
					stmt.setInt(6, task.getTask_no());
					stmt.executeUpdate();

//					FeedbackFunction(conn, feedback, task.getTask_no());
//					Function_TagFunction(conn, function_tagList, task.getTask_no());
//					Member_taskFunction(conn, member_taskList, task.getTask_no());
				
					return task_no;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(stmt2);
			DBUtil.close(conn);

		}
		return task_no;
	}

	// 피드백이 없어도 돌아가게 만들었음
	public void FeedbackFunction(Feedback feedback, int task_no) {
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			if (feedback != null) {
				if (feedback.getFeedback_no() == 0) {
					stmt = conn
							.prepareStatement("INSERT INTO `feedback` (task_no, member_no, `comment`) VALUES(?,?,?)");
					stmt.setInt(1, task_no);
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
					stmt.setInt(5, feedback.getTask_no());
					stmt.executeUpdate();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}

	// 기능 태그가없어도 돌아감
	public void Function_TagFunction(List<Function_Tag> function_tagList, int task_no) {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("DELETE FROM `function_tag` WHERE `task_no` = ?");
			stmt.setInt(1, task_no);
			stmt.executeUpdate();
			for (Function_Tag function_tag : function_tagList) {

				stmt2 = conn.prepareStatement("INSERT INTO `function_tag` (task_no, color, `text`) VALUES(?,?,?)");
				stmt2.setInt(1, task_no);
				stmt2.setString(2, function_tag.getColor());
				
				stmt2.setString(3, function_tag.getText());
				stmt2.executeUpdate();
		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(stmt2);
			DBUtil.close(conn);
		}
	}

	// 멤버태그가 없어도 돌아감
	public void Member_taskFunction(List<Member_task> member_taskList, int task_no) {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		System.out.println("서비스 레포지토리");
		System.out.println(member_taskList);
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("DELETE FROM `member_task` WHERE `task_no` = ?");
			stmt.setInt(1, task_no);
			stmt.executeUpdate();
			if(member_taskList.size() != 0) {
			for (Member_task member_task : member_taskList) {
				stmt2 = conn.prepareStatement(
						"INSERT INTO `member_task` (`member_no`, `task_no`, `color`) " + "VALUES(?,?,?)");
				stmt2.setInt(1, member_task.getMember_no());
				stmt2.setInt(2, member_task.getTask_no());
				stmt2.setString(3, member_task.getColor());
				stmt2.executeUpdate();
			}
			}
		}catch(SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		}
			DBUtil.close(conn);
			DBUtil.close(stmt);
			DBUtil.close(stmt2);
		}
	
}
