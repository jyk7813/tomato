package utility;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbutil.LoginMember;
import frame.MainFrame;
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
	public LoginMember loginMember;
	
	public PrintPlannerList() {
	}
	
	// 페이지 전환 메소드 (전체 프로젝트 / 프로젝트 별) ---------------
	public List<PrintPlanner> getPrintPlannerList(LoginMember loginMember,Boolean toggleSwitch) {
		List<Project> pjOfUser = null;
		ppList = new ArrayList<>();
		pjOfUser = loginMember.getPjList();
		if (toggleSwitch) {
			List<Task> tkOfPj;
			for (Project p : pjOfUser) {
				try {
					tkOfPj = tr.taskListBypjNo(p.getProject_no());
					for (Task t : tkOfPj) {
						String update = t.getUpdateDate().toString();
						String deadLine = t.getDeadLine().toString();
						PrintPlanner pp = new PrintPlanner(p.getProject_no(), p.getTitle(), t.getTitle(), update,
								deadLine);
						ppList.add(pp);
					}
				} catch (SQLException e) {
					System.out.println("투두오류1: 전체프로젝트 리스트 전달 실패");
					e.printStackTrace();
				}
			}
			return ppList;
			// 프로젝트 별
		} else {
			List<Task> tkOfPj2;
			List<Member> mOfTask;
			for (Project p : pjOfUser) {
				try {
					tkOfPj2 = tr.taskListBypjNo(p.getProject_no());
					for (Task t : tkOfPj2) {
						mOfTask = tr.searchMemberByTask_no(t.getTask_no());
						for (Member m : mOfTask) {
							String update = t.getUpdateDate().toString();
							String deadLine = t.getDeadLine().toString();
							PrintPlanner pp = new PrintPlanner(m.getMember_no(), p.getTitle(), m.getName(), t.getTitle(), update,
									deadLine);
							ppList.add(pp);
						}
					}
				} catch (SQLException e) {
					System.out.println("투두오류2: 전체프로젝트 리스트 전달 실패");
					e.printStackTrace();
				}
			}
			return ppList;
		}
	}

	public void testPrint(List<PrintPlanner> list) {
		List<PrintPlanner> currentList = ppList;
		for (PrintPlanner pp : currentList) {
			System.out.println(pp);
		}
	}

}
