package tomatoPj;

public class Function_Tag {
	private int no;
	private int task_no;
	private String color;
	private String text;
	public Function_Tag(int no, int task_no, String color, String text) {
		super();
		this.no = no;
		this.task_no = task_no;
		this.color = color;
		this.text = text;
	}
	public Function_Tag(int task_no, String color, String text) {
		super();
		this.task_no = task_no;
		this.color = color;
		this.text = text;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + no;
		result = prime * result + task_no;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
	        return true;
	    if (obj == null || getClass() != obj.getClass())
	        return false;
	    Function_Tag other = (Function_Tag) obj;
	    return text.equals(other.text);
	}
	@Override
	public String toString() {
		return "Function_Tag [no=" + no + ", task_no=" + task_no + ", color=" + color + ", text=" + text + "]";
	}
}
