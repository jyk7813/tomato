package tomatoPj;

import java.util.Objects;

public class Member_task {
	private int package_no;
	private int member_no;
	private int task_no;
	private String color;
	
	public Member_task(int package_no, int member_no, int task_no, String color) {
		super();
		this.package_no = package_no;
		this.member_no = member_no;
		this.task_no = task_no;
		this.color = color;
	}
	public int getPackage_no() {
		return package_no;
	}
	public void setPackage_no(int package_no) {
		this.package_no = package_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public int getTask_no() {
		return task_no;
	}
	public void setTask_no(int task_no) {
		this.task_no = task_no;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public int hashCode() {
		return Objects.hash(color, member_no, package_no, task_no);
	}
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null || getClass() != obj.getClass())
	        return false;
	    Member_task other = (Member_task) obj;
	    return member_no == other.member_no;
	}
	@Override
	public String toString() {
		return "Member_task [package_no=" + package_no + ", member_no=" + member_no + ", task_no=" + task_no
				+ ", color=" + color + "]";
	}
}
