package tomatoPj;

public class Member_Tag_Package {
	private int mt_no;
	private int project_no;
	private int member_no;
	private String color;
	public Member_Tag_Package(int mt_no, int project_no, int member_no, String color) {
		super();
		this.mt_no = mt_no;
		this.project_no = project_no;
		this.member_no = member_no;
		this.color = color;
	}
	public int getMt_no() {
		return mt_no;
	}
	public void setMt_no(int mt_no) {
		this.mt_no = mt_no;
	}
	public int getProject_no() {
		return project_no;
	}
	public void setProject_no(int project_no) {
		this.project_no = project_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + member_no;
		result = prime * result + mt_no;
		result = prime * result + project_no;
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
		Member_Tag_Package other = (Member_Tag_Package) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (member_no != other.member_no)
			return false;
		if (mt_no != other.mt_no)
			return false;
		if (project_no != other.project_no)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Member_Tag_Package [mt_no=" + mt_no + ", project_no=" + project_no + ", member_no=" + member_no
				+ ", color=" + color + "]";
	}
	
}
