package tomatoPj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import dbutil.DBUtil;

public class Member_Tag_Package_Repository {
	private MemberRepository memberRepo;
	
	public Member_Tag_Package_Repository() {
		memberRepo = new MemberRepository();
	}
	
	// 프로젝트 넘버를 날리면 해당 프로젝트에 참가하고있는 멤버리턴
	public HashSet<Member> returnMemberByPj_no(int project_no) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		HashSet<Member> list = new HashSet<>();
		try {
			conn = DBUtil.getConnection();
			String query = "SELECT * FROM `member` AS a\r\n"
					+ "JOIN (\r\n"
					+ "SELECT * FROM `member_tag` WHERE `project_no` = ?) AS b\r\n"
					+ "ON a.member_no = b.member_no;";
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
				
				//Member member = memberRepo.searchByMemberNo(member_no);
				
				list.add(new Member(member_no, id, pwd, e_mail, name, mbti, active, image));
			}
			
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return list;
	}
	
	public int containMemberCnt(int project_no) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int count;
		try {
			conn = DBUtil.getConnection();
			String query = "SELECT COUNT(*) AS cnt FROM `member_tag` WHERE `project_no` = ?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, project_no);
			rs = stmt.executeQuery();

			rs.next();
			count = rs.getInt("cnt");
			
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return count;
	}
	
	
}
