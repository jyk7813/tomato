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

	@Override
	public String toString() {
		return "Project [project_no=" + project_no + ", title=" + title + ", member_no=" + member_no + ", active="
				+ active + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + active;
		result = prime * result + member_no;
		result = prime * result + project_no;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (active != other.active)
			return false;
		if (member_no != other.member_no)
			return false;
		if (project_no != other.project_no)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
}
