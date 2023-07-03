package tomatoPj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBUtil;

public class MemberRepository {
	
	// 프로젝트 넘버를 넘기면 해당 프로젝트에 참여된 모든 멤버 리스트 리턴
	// 06/30 지윤씨 여기에요///////////////////////////
	public List<Member> getMemberBypj_no(int project_no) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Member> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			String query = "SELECT * FROM `member` AS a\r\n" + 
					"JOIN (\r\n" + 
					"SELECT * FROM `member_tag` WHERE `project_no` = ? ) AS b\r\n" + 
					"ON a.member_no = b.member_no";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, project_no);
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
	
	
	public List<Member> selectAll(Connection conn) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Member> list = new ArrayList<>();

		try {
			String query = "SELECT * FROM member";
			stmt = conn.prepareStatement(query);
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
				list.add(new Member(member_no, id, pwd, e_mail, name, mbti, active,image));
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
		}
		return list;
	}
	
	/**
	 * @author 임태경
	 * @explain 중복아이디체크
	 * @param conn
	 * @param id
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean dupliIdCheck (Connection conn, String id) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			String query = "SELECT `id` FROM `member` WHERE id = ?";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			
			String a = null;
			while(rs.next()) {
				a = rs.getString("id");
				if(a.equals(null) || id.equals(a)) {
					return false;
				}
			}
			return true;
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
		}
	}
	
	// 특정프로젝트에 함께하는 멤버추가 
	// 07/03 하태씨 이거쓰면됨
	public void addProjectMember(int project_no, String id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		
		
		try {
			System.out.println("실행됨");
			conn = DBUtil.getConnection();
			if(!dupliIdCheck(conn, id)) {
				String sql = "SELECT `member_no` FROM `member` WHERE id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, id);
				rs = stmt.executeQuery();
			}
			rs.next(); 
			int member_no = rs.getInt("member_no");
			String sql2 = "INSERT INTO `member_tag` (`project_no`, `member_no`)\r\n" + 
					"VALUES (?,?)";
			stmt2 = conn.prepareStatement(sql2);
			stmt2.setInt(1, project_no);
			stmt2.setInt(2, member_no);
			stmt2.executeUpdate();
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(stmt2);
			DBUtil.close(conn);
		}
	}
	
	/**
	 * @author 임태경
	 * @explain 회원가입
	 * @param conn
	 * @param id
	 * @param pwd
	 * @param e_mail
	 * @param name
	 * @param mbti
	 * @return
	 */
	public int signUp(Connection conn, String id, String pwd, String e_mail, String name, String mbti, byte[] image) {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("INSERT INTO `member` (id, pwd, `e-mail`, name, mbti, image) VALUES (?, ?, ?, ?, ?, ?)");
			stmt.setString(1, id);
			stmt.setString(2, pwd);
			stmt.setString(3, e_mail);
			stmt.setString(4, name);
			stmt.setString(5, mbti);
			stmt.setBytes(6, image);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
		}
		return 0;
	}
	
	/**
	 * @author 임태경
	 * @explain 로그인하면 Member를 리턴
	 * @param conn
	 * @param inputId
	 * @param inputPwd
	 * @return Member
	 * @throws SQLException
	 */
	public Member logIn(Connection conn, String inputId, String inputPwd) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Member member = null;
		try {
			String query = "SELECT * FROM member";
			stmt = conn.prepareStatement(query);
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
				if(inputId.equals(id) && inputPwd.equals(pwd)) {
					member = new Member(member_no, id, pwd, e_mail, name, mbti, active, image);
					break;
				}									
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
		}
		return member;
	}
	/**
	 * @author 임태경
	 * @param member_no
	 * @return List<Project>
	 * @throws SQLException
	 */
	public List<Project> returnMemberPj(int member_no) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Project> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			String query = "SELECT * FROM `project` WHERE member_no = ?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, member_no);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int project_no = rs.getInt("project_no");
				String title = rs.getString("title");
				int member_noParse = rs.getInt("member_no");
				int active = rs.getInt("active");
				list.add(new Project(project_no, title, member_noParse, active));
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return list;
	}
	
	public Member searchByMemberNo(int member_no) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Member member = null;
		try {
			conn = DBUtil.getConnection();
			String query = "SELECT * FROM `member` WHERE member_no = ?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, member_no);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int member_noParse = rs.getInt("member_no");
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String e_mail = rs.getString("e-mail");
				String name = rs.getString("name");
				String mbti = rs.getString("mbti");
				int active = rs.getInt("active");
				byte[] image = rs.getBytes("image");
				
				
				member = new Member(member_noParse, id, pwd, e_mail, name, mbti, active, image);
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return member;
	}
}
