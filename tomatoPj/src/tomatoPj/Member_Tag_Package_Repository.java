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
	
	public HashSet<Member> returnMemberByPj_no(int project_no) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		HashSet<Member> list = new HashSet<>();
		try {
			conn = DBUtil.getConnection();
			String query = "SELECT * FROM `member_tag` WHERE `project_no` = ?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, project_no);
			rs = stmt.executeQuery();

			while (rs.next()) {
				int mt_no = rs.getInt("mt_no");
				int project_noParse = rs.getInt("project_no");
				int member_no = rs.getInt("member_no");
				String color = rs.getString("color");
				Member member = memberRepo.searchByMemberNo(member_no);
				list.add(member);
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
