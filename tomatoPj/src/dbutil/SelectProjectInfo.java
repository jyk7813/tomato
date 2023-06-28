package dbutil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import tomatoPj.Column;
import tomatoPj.Member;
import tomatoPj.Task;

public class SelectProjectInfo {
	
	private int project_no;
	private String title;
	private List<Column> col;
	private List<Task> task;
	private List<Member> memberList;
	
	public SelectProjectInfo() {
		this.project_no = 0;
		this.title = null;
		this.col = null;
		this.task = null;
		this.memberList = null;
	}
	
	public SelectProjectInfo(int project_no, String title, List<Column> col, List<Task> task) {
		super();
		this.project_no = project_no;
		this.title = title;
		this.col = col;
		this.task = task;
		this.memberList = new ArrayList<>();
	}
	public List<Member> getMemberList() {
		return memberList;
	}
	public void setMemberList(List<Member> memberList) {
		this.memberList = memberList;
	}
	public int getProject_no() {
		return project_no;
	}
	public void setProject_no(int project_no) {
		this.project_no = project_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Column> getCol() {
		return col;
	}
	public void setCol(List<Column> col) {
		this.col = col;
	}
	public List<Task> getTask() {
		return task;
	}
	public void setTask(List<Task> task) {
		this.task = task;
	}
	@Override
	public String toString() {
		return "SelectProjectInfo [project_no=" + project_no + ", title=" + title + ", col=" + col + ", task=" + task
				+ "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(col, project_no, task, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SelectProjectInfo other = (SelectProjectInfo) obj;
		return Objects.equals(col, other.col) && project_no == other.project_no && Objects.equals(task, other.task)
				&& Objects.equals(title, other.title);
	}
	
}
