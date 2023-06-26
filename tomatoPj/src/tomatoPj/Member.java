package tomatoPj;

import java.util.ArrayList;
import java.util.List;

public class Member {
	private int member_no;
	private String id;
	private String pwd;
	private String e_mail;
	private String name;
	private String mbti;
	private int active;
	private List<Project> project;
	private List<Feedback> feedback;
	
	public Member(int member_no, String id, String pwd, String e_mail, String name, String mbti, int active) {
		super();
		this.member_no = member_no;
		this.id = id;
		this.pwd = pwd;
		this.e_mail = e_mail;
		this.name = name;
		this.mbti = mbti;
		this.active = active;
		this.project = new ArrayList<>();
		this.feedback = new ArrayList<>();
	}
	public Member(String id, String pwd, String e_mail, String name, String mbti, List<Project> project,
			List<Feedback> feedback) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.e_mail = e_mail;
		this.name = name;
		this.mbti = mbti;
		this.project = new ArrayList<>();
		this.feedback = new ArrayList<>();
	}
	@Override
	public String toString() {
		return "Member [member_no=" + member_no + ", id=" + id + ", pwd=" + pwd + ", e_mail=" + e_mail + ", name="
				+ name + ", mbti=" + mbti + ", active=" + active + ", project=" + project + ", feedback=" + feedback
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + active;
		result = prime * result + ((e_mail == null) ? 0 : e_mail.hashCode());
		result = prime * result + ((feedback == null) ? 0 : feedback.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mbti == null) ? 0 : mbti.hashCode());
		result = prime * result + member_no;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
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
		Member other = (Member) obj;
		if (active != other.active)
			return false;
		if (e_mail == null) {
			if (other.e_mail != null)
				return false;
		} else if (!e_mail.equals(other.e_mail))
			return false;
		if (feedback == null) {
			if (other.feedback != null)
				return false;
		} else if (!feedback.equals(other.feedback))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mbti == null) {
			if (other.mbti != null)
				return false;
		} else if (!mbti.equals(other.mbti))
			return false;
		if (member_no != other.member_no)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		return true;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getE_mail() {
		return e_mail;
	}
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMbti() {
		return mbti;
	}
	public void setMbti(String mbti) {
		this.mbti = mbti;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public List<Project> getProject() {
		return project;
	}
	public void setProject(List<Project> project) {
		this.project = project;
	}
	public List<Feedback> getFeedback() {
		return feedback;
	}
	public void setFeedback(List<Feedback> feedback) {
		this.feedback = feedback;
	}
}