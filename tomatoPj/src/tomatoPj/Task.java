package tomatoPj;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Task {
	private int task_no;
	private String title;
	private String content;
	private int importance;
	private Timestamp updateDate;
	private Timestamp deadLine;
	private int active;
	private List<Member> list; // 이거말고 다른 멤버태그변수 만들어야함
	private Feedback feedBack;
	
	public Task(String title, String content, int importance, Timestamp updateDate, Timestamp deadLine) {
		super();
		this.title = title;
		this.content = content;
		this.importance = importance;
		this.updateDate = updateDate;
		this.deadLine = deadLine;
	}
	public Task(int task_no, String title, String content, int importance, Timestamp updateDate,
			Timestamp deadLine, int active) {
		super();
		this.task_no = task_no;
		this.title = title;
		this.content = content;
		this.importance = importance;
		this.updateDate = updateDate;
		this.deadLine = deadLine;
		this.active = active;
	}
	
	public int getTask_no() {
		return task_no;
	}
	public void setTask_no(int task_no) {
		this.task_no = task_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getImportance() {
		return importance;
	}
	public void setImportance(int importance) {
		this.importance = importance;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	public Timestamp getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(Timestamp deadLine) {
		this.deadLine = deadLine;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	
	public List<Member> getList() {
		return list;
	}
	public void setList(List<Member> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "Task [task_no=" + task_no + ", title=" + title + ", content=" + content + ", importance=" + importance
				+ ", updateDate=" + updateDate + ", deadLine=" + deadLine + ", active=" + active + ", list=" + list
				+ "]";
	}
	
}
