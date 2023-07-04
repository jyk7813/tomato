package utility;

import java.security.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import tomatoPj.Member;
import tomatoPj.Project;

public class PrintPlanner implements Comparable<PrintPlanner> {
	private int pk;
	private int pjPk; 
	private int taskPk; 
	private int memPk;
	private int colorPk = 0;
	private String PjName; // 선택한 프로젝트 이름
	private String select; // 선택 패널 출력 문자열 : 프로젝트이름 / 멤버이름
	private String taskName; // 프로젝트의 태스크명 / 멤버의 태스크명
	private String memberName; // 멤버 이름
	private LocalDate up; // 태스크 시작일자
	private LocalDate dead; // 태스크 종료일자
	
//   private List<PrintPlanner> printList;
//   private boolean isAll; // 전체/개별 리스트  여부
	private CalendarData cd = new CalendarData();

	public PrintPlanner(int pk, int pjPk, int taskPk, int memPk, String upDate, String deadLine) {
		super();
		this.pk = pk;
		this.pjPk = pjPk;
		this.taskPk = taskPk;
		this.memPk = memPk;
		this.up = cd.getLocalDate(upDate);
		this.dead = cd.getLocalDate(deadLine);
	}

	public PrintPlanner(int pk, int pjPk, int taskPk, int memPk, String pjName, String taskName,
			String memberName, String upDate, String deadLine) {
		super();
		this.pk = pk;
		this.pjPk = pjPk;
		this.taskPk = taskPk;
		this.memPk = memPk;
		this.PjName = pjName;
		this.taskName = taskName;
		this.memberName = memberName;
		this.up = cd.getLocalDate(upDate);
		this.dead = cd.getLocalDate(deadLine);
	}

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public int getPjPk() {
		return pjPk;
	}

	public void setPjPk(int pjPk) {
		this.pjPk = pjPk;
	}

	public int getTaskPk() {
		return taskPk;
	}

	public void setTaskPk(int taskPk) {
		this.taskPk = taskPk;
	}

	public int getMemPk() {
		return memPk;
	}

	public void setMemPk(int memPk) {
		this.memPk = memPk;
	}

	public int getColorPk() {
		return colorPk;
	}

	public void setColorPk(int colorPk) {
		this.colorPk = colorPk;
	}

	public String getPjName() {
		return PjName;
	}

	public void setPjName(String pjName) {
		PjName = pjName;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public LocalDate getUp() {
		return up;
	}

	public void setUp(LocalDate up) {
		this.up = up;
	}

	public LocalDate getDead() {
		return dead;
	}

	public void setDead(LocalDate dead) {
		this.dead = dead;
	}

	@Override
	public String toString() {
		return "PrintPlanner [pk=" + pk + ", pjPk=" + pjPk + ", taskPk=" + taskPk + ", memPk=" + memPk + ", colorPk="
				+ colorPk + ", PjName=" + PjName + ", select=" + select + ", taskName=" + taskName + ", up=" + up
				+ ", dead=" + dead + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((PjName == null) ? 0 : PjName.hashCode());
		result = prime * result + colorPk;
		result = prime * result + ((dead == null) ? 0 : dead.hashCode());
		result = prime * result + memPk;
		result = prime * result + pjPk;
		result = prime * result + pk;
		result = prime * result + ((select == null) ? 0 : select.hashCode());
		result = prime * result + ((taskName == null) ? 0 : taskName.hashCode());
		result = prime * result + taskPk;
		result = prime * result + ((up == null) ? 0 : up.hashCode());
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
		PrintPlanner other = (PrintPlanner) obj;
		if (PjName == null) {
			if (other.PjName != null)
				return false;
		} else if (!PjName.equals(other.PjName))
			return false;
		if (colorPk != other.colorPk)
			return false;
		if (dead == null) {
			if (other.dead != null)
				return false;
		} else if (!dead.equals(other.dead))
			return false;
		if (memPk != other.memPk)
			return false;
		if (pjPk != other.pjPk)
			return false;
		if (pk != other.pk)
			return false;
		if (select == null) {
			if (other.select != null)
				return false;
		} else if (!select.equals(other.select))
			return false;
		if (taskName == null) {
			if (other.taskName != null)
				return false;
		} else if (!taskName.equals(other.taskName))
			return false;
		if (taskPk != other.taskPk)
			return false;
		if (up == null) {
			if (other.up != null)
				return false;
		} else if (!up.equals(other.up))
			return false;
		return true;
	}

	@Override
	public int compareTo(PrintPlanner p) {
		if (p.pk < pk) {
			return 1;
		} else if (p.pk > pk) {
			return -1;
		}
		return 0;
	}
}