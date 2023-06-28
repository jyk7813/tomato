package dbutil;

import java.time.LocalDateTime;
import java.util.List;

import tomatoPj.Member;
import tomatoPj.Project;
import tomatoPj.Task;

public class LoginMember {
	private Member member;
	private int member_no;
	private List<Project> pjList;
	private LocalDateTime loginTime;
	private int pjListSize;
	private List<Task> takeTaskList;
	
	public List<Task> getTakeTaskList() {
		return takeTaskList;
	}

	public void setTakeTaskList(List<Task> takeTaskList) {
		this.takeTaskList = takeTaskList;
	}

	public int getPjListSize() {
		return pjListSize;
	}

	public void setPjListSize(int pjListSize) {
		this.pjListSize = pjListSize;
	}

	public LoginMember(Member member, int member_no, List<Project> pjList, LocalDateTime loginTime) {
		super();
		this.member = member;
		this.member_no = member_no;
		this.pjList = pjList;
		this.loginTime = loginTime;
	}
	
	public LoginMember() {
	}

	@Override
	public String toString() {
		return "LoginMember [member=" + member + ", member_no=" + member_no + ", pjList=" + pjList + ", loginTime="
				+ loginTime + "]";
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public List<Project> getPjList() {
		return pjList;
	}

	public void setPjList(List<Project> pjList) {
		this.pjList = pjList;
	}

	public LocalDateTime getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(LocalDateTime loginTime) {
		this.loginTime = loginTime;
	}
	
}
