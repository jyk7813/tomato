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
	private int i, j, pkNum, pkNum2;


	public PrintPlannerList() {

	}

	// 페이지 전환 메소드 (전체 프로젝트 / 프로젝트 별) ---------------
	public List<PrintPlanner> setView(Boolean isAll, int member_no) {
		List<Project> pjOfUser = null;
		ppList = new ArrayList<>();
		try {
			loginMember = mr.searchByMemberNo(member_no);
			pjOfUser = mr.returnMemberPj(member_no);
			if (isAll) {
				List<Task> tkOfPj;
				for (Project p : pjOfUser) {
					try {
						tkOfPj = tr.taskListBypjNo(p.getProject_no());
						for (Task t : tkOfPj) {
							String update = t.getUpdateDate().toString();
							String deadLine = t.getDeadLine().toString();
							PrintPlanner pp = new PrintPlanner(isAll, p.getProject_no(), p.getTitle(), t.getTitle(), update, deadLine);
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
								PrintPlanner pp = new PrintPlanner(isAll, m.getMember_no(), p.getTitle(), m.getName(),
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
	
	public List<PrintPlanner> getPrintTest(boolean isAll, List<Project> p, Member m){
		List<PrintPlanner> ppList = new ArrayList<>();
		if(isAll) {
			List<PrintPlanner> pp = null;
			List<Task> tkOfPj = null;
			for (Project pj : p) {
				try {
					tkOfPj = tr.taskListBypjNo(pj.getProject_no());
					for (Task t : tkOfPj) {
						String update = t.getUpdateDate().toString();
						String deadLine = t.getDeadLine().toString();
						PrintPlanner printplanner = new PrintPlanner(isAll, pj.getProject_no()
								, pj.getTitle(), t.getTitle(), update, deadLine);
						
						ppList.add(printplanner);
					}
				} catch (SQLException e) {
					System.out.println("투두오류1: 전체프로젝트 리스트 전달 실패");
					e.printStackTrace();
				}
			}
			return ppList;
		} else {
			List<Task> tkOfPj;
			List<Member> mOfTask;
			for (Project pj : p) {
				try {
					tkOfPj = tr.taskListBypjNo(pj.getProject_no());
					for (Task t : tkOfPj) {
						mOfTask = tr.searchMemberByTask_no(t.getTask_no());
						for (Member mem : mOfTask) {
							String update = t.getUpdateDate().toString();
							String deadLine = t.getDeadLine().toString();
							PrintPlanner printplanner = new PrintPlanner(isAll, m.getMember_no(), pj.getTitle(), mem.getName()
									, t.getTitle(), update, deadLine);
							ppList.add(printplanner);
						}
					}
				} catch (SQLException e) {
					System.out.println("투두오류1: 전체프로젝트 리스트 전달 실패");
					e.printStackTrace();
				}
			}
			return ppList;
		}
	}

	public List<PrintPlanner> getPrintList(boolean isAll, List<PrintPlanner> list) {
		List<PrintPlanner> getList = new ArrayList<>();
		i = 1;
		pkNum = 0;
		if (isAll) {
			List<PrintPlanner> ppList = new ArrayList<>();
			System.out.println("――――――――――――――――――――");
			for (PrintPlanner p : list) {
				System.out.println("[전체]겟프린트 프로젝트PK1: " + p.getPk() + p.getSelect() + p.getTitle());
				pkNum = p.getPk();
				for(PrintPlanner p2 : list) {
					if (pkNum == p2.getPk()) {
						ppList.add(p2);
						System.out.println("[전체]겟프린트 프로젝트PK2: " + p2.getPk()+ p.getSelect() + p.getTitle());
						System.out.println("--------------------");
					}
				}
				PrintPlanner pp = new PrintPlanner(isAll, i, ppList);
				System.out.println("[전체]*겟프린트*: " + pp);
				System.out.println("--------------------");
				getList.add(pp);
				i++;
			}
			System.out.println("――――――――――――――――――――");
			return getList;
		} else {
			j = 1;
			pkNum2 = 0;
			List<PrintPlanner> ppList = new ArrayList<>();
			System.out.println("――――――――――――――――――――");
			for (PrintPlanner p : list) {
				pkNum2 = p.getPk();
				System.out.println("[개별]겟프린트 프로젝트PK1: " + p.getPk() + p.getSelect() + p.getTitle());
				for(PrintPlanner p2 : list) {
					if (pkNum2 == p2.getPk()) {
						ppList.add(p2);
						System.out.println("[개별]겟프린트 프로젝트PK2: " + p2.getPk() + p.getSelect() + p.getTitle());
						System.out.println("--------------------");
					}
				}
				PrintPlanner pp = new PrintPlanner(isAll, j, ppList);
				System.out.println("[개별]*겟프린트*: " + pp);
				System.out.println("--------------------");
				getList.add(pp);
				j++;
			}
			System.out.println("――――――――――――――――――――");
			return getList;
		}

	}

	public void testPrint(List<PrintPlanner> ppList) {
		for (PrintPlanner pp : ppList) {
			System.out.println(pp);
		}
	}

}