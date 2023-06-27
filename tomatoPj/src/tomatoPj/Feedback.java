package tomatoPj;

public class Feedback {
	private int feedback_no;
	private int task_no;
	private int member_no;
	private String comment;
	// auto 인크리먼트를 제외한 생성자
	public Feedback(String comment) {
		super();
		this.comment = comment;
	}

	public Feedback(int feedback_no, int task_no, int member_no, String comment) {
		super();
		this.feedback_no = feedback_no;
		this.task_no = task_no;
		this.member_no = member_no;
		this.comment = comment;
	}

	public int getFeedback_no() {
		return feedback_no;
	}

	public void setFeedback_no(int feedback_no) {
		this.feedback_no = feedback_no;
	}

	public int getTask_no() {
		return task_no;
	}

	public void setTask_no(int task_no) {
		this.task_no = task_no;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
