package tomatoPj;

import java.time.LocalDateTime;
import java.util.List;

public class Task {
	private int task_no;
	private String title;
	private String content;
	private int importance;
	private LocalDateTime updateDate;
	private LocalDateTime deadLine;
	private int active;
	private List<Member>list;
	public Task(String title, String content, int importance, LocalDateTime updateDate, LocalDateTime deadLine) {
		super();
		this.title = title;
		this.content = content;
		this.importance = importance;
		this.updateDate = updateDate;
		this.deadLine = deadLine;
	}
	public Task(int task_no, String title, String content, int importance, LocalDateTime updateDate,
			LocalDateTime deadLine, int active) {
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
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
	public LocalDateTime getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(LocalDateTime deadLine) {
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
	
}
