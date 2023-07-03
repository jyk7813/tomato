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
	public Member loginMember;

	public PrintPlannerList() {

	}

	// 페이지 전환 메소드 (전체 프로젝트 / 프로젝트 별) ---------------
	public List<PrintPlanner> setView(int member_no, Boolean toggleSwitch) {
		List<Project> pjOfUser = null;
		ppList = new ArrayList<>();
		try {
			loginMember = mr.searchByMemberNo(member_no);
			pjOfUser = mr.returnMemberPj(member_no);
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
								PrintPlanner pp = new PrintPlanner(m.getMember_no(), p.getTitle(), m.getName(),
										t.getTitle(), update, deadLine);
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
		} catch (SQLException e1) {
			System.out.println("투두창 오류-printplanner_class");
			e1.printStackTrace();
		}
		return ppList;
	}

	public List<PrintPlanner> getPrintList(List<PrintPlanner> list, boolean toggleSwitch) {
		List<PrintPlanner> getList = new ArrayList<>();
		int i = 1;
		int pkNum = 0;
		if (toggleSwitch) {
			List<PrintPlanner> ppList = new ArrayList<>();
			for (PrintPlanner p : list) {
				pkNum = p.getPk();
				if (pkNum == p.getPk()) {
					ppList.add(p);
				}
				PrintPlanner pp = new PrintPlanner(i, ppList);
				System.out.println("getPrintList 테스트 [전체]: " + pp);
				getList.add(pp);
				i++;
			}
			return getList;
		} else {
			List<PrintPlanner> ppList = new ArrayList<>();
			for (PrintPlanner p : list) {
				pkNum = p.getPk();
				if (pkNum == p.getPk()) {
					ppList.add(p);
				}
				PrintPlanner pp = new PrintPlanner(i, ppList);
				System.out.println("getPrintList 테스트 [프로젝트별]: " + pp);
				getList.add(pp);
				i++;
			}
			return getList;
		}

	}

	public void testPrint(List<PrintPlanner> ppList) {
		for (PrintPlanner pp : ppList) {
			System.out.println(pp);
		}
	}

}