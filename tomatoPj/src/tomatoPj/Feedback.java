package tomatoPj;

public class Feedback {
	private int feedback_no;
	private int task_no;
	private int member_no;
	private String comment;
	// auto 인크리먼트를 제외한 생성자
	public Feedback(int task_no, int member_no,String comment) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + feedback_no;
		result = prime * result + member_no;
		result = prime * result + task_no;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Feedback))
			return false;
		Feedback other = (Feedback) obj;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (feedback_no != other.feedback_no)
			return false;
		if (member_no != other.member_no)
			return false;
		if (task_no != other.task_no)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Feedback [feedback_no=" + feedback_no + ", task_no=" + task_no + ", member_no=" + member_no
				+ ", comment=" + comment + "]";
	}
	
	
}
