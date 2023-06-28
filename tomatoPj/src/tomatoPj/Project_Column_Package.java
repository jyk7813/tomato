package tomatoPj;

public class Project_Column_Package {
	private int package_no;
	private int project_no;
	private int column_no;
	
	public Project_Column_Package(int package_no, int project_no, int column_no) {
		super();
		this.package_no = package_no;
		this.project_no = project_no;
		this.column_no = column_no;
	}

	public int getPackage_no() {
		return package_no;
	}

	public void setPackage_no(int package_no) {
		this.package_no = package_no;
	}

	public int getProject_no() {
		return project_no;
	}

	public void setProject_no(int project_no) {
		this.project_no = project_no;
	}

	public int getColumn_no() {
		return column_no;
	}

	public void setColumn_no(int column_no) {
		this.column_no = column_no;
	}

	@Override
	public String toString() {
		return "Project_Column_Package [package_no=" + package_no + ", project_no=" + project_no + ", column_no="
				+ column_no + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column_no;
		result = prime * result + package_no;
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
		Project_Column_Package other = (Project_Column_Package) obj;
		if (column_no != other.column_no)
			return false;
		if (package_no != other.package_no)
			return false;
		if (project_no != other.project_no)
			return false;
		return true;
	}
	
	
}
