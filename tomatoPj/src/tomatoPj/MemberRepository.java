package tomatoPj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBUtil;

public class MemberRepository {
	
	
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
				list.add(new Member(member_no, id, pwd, e_mail, name, mbti, active));
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
			String query = "SELECT id FROM member WHERE id = ?";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			
			String a = null;
			rs.next();
			a = rs.getString("id");
			if(a.equals(null)) {
				return false;
			} else {
				return true;
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
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
	public int signUp(Connection conn, String id, String pwd, String e_mail, String name, String mbti) {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("INSERT INTO member (id, password, e-mail, name, mbti) VALUES (?, ?, ?, ?, ?)");
			stmt.setString(1, id);
			stmt.setString(2, pwd);
			stmt.setString(3, e_mail);
			stmt.setString(4, name);
			stmt.setString(5, mbti);
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
				if(inputId.equals(id) && inputPwd.equals(pwd)) {
					member = new Member(member_no, id, pwd, e_mail, name, mbti, active);
					break;
				}
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
		}
		return member;
	}
}
