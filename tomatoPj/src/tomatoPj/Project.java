package tomatoPj;

public class Project {
	private int project_no;
	private String title;
	private int member_no;
	private int active;
	
	public Project(String title) {
		super();
		this.title = title;
	}

	public Project(int project_no, String title, int member_no, int active) {
		super();
		this.project_no = project_no;
		this.title = title;
		this.member_no = member_no;
		this.active = active;
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

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	
}
