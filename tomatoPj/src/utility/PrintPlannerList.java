package utility;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tomatoPj.Member;
import tomatoPj.MemberRepository;
import tomatoPj.Project;
import tomatoPj.ProjectRepository;
import tomatoPj.Task;
import tomatoPj.TaskRepository;

public class PrintPlannerList {
	private TaskRepository tr = new TaskRepository();
	private ProjectRepository pr = new ProjectRepository();
	private MemberRepository mr = new MemberRepository();
	public List<Project> pjOfUser;
	public List<PrintPlanner> ppList;
	public List<PrintPlanner> printList;
	public Member loginMember;
	private int pk;

	public PrintPlannerList() {

	}
	
	// 투두리스트 출력 객체 반환
	public List<PrintPlanner> getPrintPlannerList(int member_no) {
		printList = new ArrayList<>();
		List<Project> pjOfUser = null;
		List<Task> tkOfPj = null;
		try {
			loginMember = mr.searchByMemberNo(member_no);
			pjOfUser = mr.returnMemberPj(member_no);
			pk = 1;
			for (Project p : pjOfUser) {
				tkOfPj = tr.taskListBypjNo(p.getProject_no());
				for (Task t : tkOfPj) {
					String update = t.getUpdateDate().toString();
					String deadLine = t.getDeadLine().toString();
					PrintPlanner pp = new PrintPlanner(pk, p.getProject_no(), t.getTask_no(), member_no, update,
							deadLine);
					printList.add(pp);
					pk++;
				}
			} return printList;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return printList;
	}
	
	public List<PrintPlanner> getAllPrintPlannerList(int member_no) {
		printList = new ArrayList<>();
		List<Project> pjOfUser = null;
		List<Task> tkOfPj = null;
		List<Member> memOfTask = null;
		try {
			loginMember = mr.searchByMemberNo(member_no);
			pjOfUser = mr.returnMemberPj(member_no);
			pk = 1;
			for (Project p : pjOfUser) {
				tkOfPj = tr.taskListBypjNo(p.getProject_no());
				for (Task t : tkOfPj) {
					memOfTask = tr.searchMemberByTask_no(t.getTask_no());
					for(Member m : memOfTask) {
						String update = t.getUpdateDate().toString();
						String deadLine = t.getDeadLine().toString();
						PrintPlanner pp = new PrintPlanner(pk, p.getProject_no(), t.getTask_no(), m.getMember_no(), p.getTitle(), t.getTitle(), m.getName(), update,
								deadLine);
						printList.add(pp);
					}
				}
				pk++;
			} 
			return printList;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return printList;
	}
}