package tomatoPj;

import java.util.List;

public class Column {
	private int column_no;
	private String title;
	private int column_index;
	private int active;
	private List<Task> tasks;
	
	// auto 인크리먼트를 제외한 생성자
	public Column(String title, int column_index) {
		this.title = title;
		this.column_index = column_index;
	}
	public Column(int column_no, String title, int column_index, int active) {
		this.column_no = column_no;
		this.title = title;
		this.column_index = column_index;
		this.active = active;
	}
	
	public Column() {
		
	}
	
	public int getColumn_no() {
		return column_no;
	}
	public void setColumn_no(int column_no) {
		this.column_no = column_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getColumn_index() {
		return column_index;
	}
	public void setColumn_index(int column_index) {
		this.column_index = column_index;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "Column [column_no=" + column_no + ", title=" + title + ", column_index=" + column_index + ", active="
				+ active + "]";
	}
	
}
