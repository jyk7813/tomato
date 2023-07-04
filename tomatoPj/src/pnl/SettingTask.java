package pnl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dbutil.DBUtil;
import dbutil.LoginMember;
import frame.MainFrame;
import tomatoPj.Column;
import tomatoPj.Feedback;
import tomatoPj.Function_Tag;
import tomatoPj.Member;
import tomatoPj.MemberRepository;
import tomatoPj.Member_task;
import tomatoPj.Task;
import tomatoPj.TaskRepository;
import utility.FontData;
import utility.IconData;

public class SettingTask {
	DBUtil dbutil;
	Task task;
	Taskrefrom ts;
	IconData IC;
	Feedback feedback;
	Column Takecolumn;
	MainFrame MF;
	TaskRepository TR;
	TagAddButton TAB;
	MemberRepository MR;

	public SettingTask(MainFrame MF, Taskrefrom ts, Task task, Column column, Feedback feedback) {

		this.MF = MF;
		dbutil = new DBUtil();
		IC = new IconData();
		this.task = task;
		ts.TakeTask = task;
		this.ts = ts;
		Takecolumn = column;
		ts.column = column;
		this.feedback = feedback;
		ts.TakeFeedBack = feedback;
		MR = new MemberRepository();
		TR = new TaskRepository();
		ts.member_task_List = new ArrayList<>();
	}

	public void reset() {
		task = null;
		ts.TakeTask = null;
		this.feedback = null;
		ts.useingMemberNum = 0;
		ts.task_Pk = 0;
		ts.Active = 0;

		String updateDate = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Timestamp tt = new Timestamp(System.currentTimeMillis());
		updateDate = dateFormat.format(tt);
		ts.StartDate.setText(updateDate);

		String updateDate2 = "";

		Timestamp tt2 = new Timestamp(System.currentTimeMillis());
		updateDate2 = dateFormat.format(tt2);
		ts.deadLineDate.setText(updateDate2);

		for (int i = 0; i < 5; i++) {
			ts.returnImoportance = 0;
			ts.stars[i].setIcon(IC.getImageIcon("starGray"));
		}
		ts.TakeFeedBack = null;
		ts.taskTitle.setText("제목을 입력해주세요");
		ts.contentText.setText("내용을 입력해주세요");
		ts.feedBackText.setText("피드백을 입력해주세요");
		ts.memberList = new ArrayList<>();
		ts.Function_Tag_List = new ArrayList<>();
		ts.tagPnl.removeAll();
		ts.TagSet();
		ts.tagPnl.add(ts.plusTag);

	}

	public void setPJ_Mem() {
		ts.ProjectMember = new ArrayList<Member>();
		LoginMember LM = MF.loginMember;
		try {
			ts.ProjectMember = MR.getMemberBypj_no(LM.getMember_no());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void settingMemberIcon() {
		for(int i = 0; i<ts.memberList.size();i++) {
			 JLabel memberIcon = new JLabel();
			JLabel hidenText = new JLabel();
			hidenText.setPreferredSize(new Dimension(60,60));
			hidenText.setForeground(new Color(0,0,0,0));
			hidenText.add(memberIcon);
			 if (ts.memberList.get(i).getImage() != null) {
                 ImageIcon icon = new ImageIcon(ts.memberList.get(i).getImage());
                 memberIcon.setIcon(icon);
                 hidenText.setText(""+ts.memberList.get(i).getMember_no());			 
			 } else {
                 memberIcon.setIcon(IC.getImageIcon("user1"));
                 hidenText.setText(""+ts.memberList.get(i).getMember_no());
             }
			 memberIcon.addMouseListener(new MouseAdapter() {
                 public void mousePressed(MouseEvent e) {

                     // showInputDialog 메서드를 사용하여 입력 다이얼로그를 표시하고 이미지 아이콘을 설정합니다.
                     ts.MemberPnl.remove(memberIcon);
                     ts.MemberPnl.revalidate();
                     ts.MemberPnl.repaint();
                     ts.Count--;
                     ts.plus.setVisible(true);
                     int MemberNum = Integer.valueOf(hidenText.getText());
                     for(int i =0; i<ts.member_task_List.size();i++) {
                    	 if(ts.member_task_List.get(i).getMember_no() ==MemberNum) {
                    		 ts.member_task_List.remove(i);
                    	 }
                     }
                     
                 }
             });
			 
			
			 ts.MemberPnl.add(memberIcon);
             memberIcon.setSize(new Dimension(60,60));
		}
	}

	public void setMember_Task() {

		if (task != null) {

			for (int i = 0; i < ts.memberList.size(); i++) {
				Member_task MT = new Member_task(0, ts.memberList.get(i).getMember_no(), task.getTask_no(), "확인용");
				ts.member_task_List.add(MT);
			}

		} else {
			Member_task MT = new Member_task(0, MF.loginMember.getMember_no(), 1, "확인용");
			ts.member_task_List.add(MT);
		}

	}

	public void setMemberlist() {
		if (task != null) {
			try {
				ts.memberList = TR.searchMemberByTask_no(task.getTask_no());

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setTaglist() {
		if (task != null) {
			try {

				ts.Function_Tag_List = TR.searchFunction_tagByTask_no(task.getTask_no());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			ts.Function_Tag_List = new ArrayList<>();
		}
	}

	public void setUsingMemberNum() {
		ts.useingMemberNum = MF.loginMember.getMember().getMember_no();

	}

	public void settingPKAndAc() {
		if (task != null) {
			ts.task_Pk = task.getTask_no();
			ts.Active = task.getActive();
		}
	}

	public void TagAddButton(List<Function_Tag> list) {

		ts.tagPnl.add(ts.plusTag);
		IconData IC = new IconData();

		ts.return_Function_Tag_List = list;

		for (int i = 0; i < list.size(); i++) {
			JLabel box = new JLabel();
//			box.setSize();

			JLabel tag = new JLabel(IC.getImageIcon("tag"));
			JLabel tagText = new JLabel();

			tag.setLayout(null);
			tagText.setForeground(Color.BLACK);

			FontData FD = new FontData();
			tagText.setSize(80, 30);
			tagText.setLocation(20, 0);
			tagText.setFont(FD.nanumFont(12));
			ts.tagTexts.add(list.get(i).getText());
			tagText.setText(list.get(i).getText());
			tag.add(tagText);
			tagText.setVisible(true);
			ts.CountTag = list.size();
			tag.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					ts.CountTag--;
					ts.tagPnl.remove(tag);
					// 아이콘들 왼쪽 정렬
					for (int i = 0; i < ts.tagTexts.size(); i++) {
						if (ts.tagTexts.get(i).equals(tagText.getText()))
							ts.tagTexts.remove(i);

					}
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getText().equals(tagText.getText())) {
							list.remove(i);
						}
					}
					ts.plusTag.setVisible(true);
					ts.tagPnl.revalidate();
					ts.tagPnl.repaint();
				}

			});
			ts.tagPnl.add(tag);
			ts.tagPnl.revalidate();
			ts.tagPnl.repaint();

		}
		if (ts.CountTag > 4) {
			ts.plusTag.setVisible(false);
		}
	}

	public String setUpdataDate() {
		String updateDate = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Timestamp tt = new Timestamp(System.currentTimeMillis());

		updateDate = dateFormat.format(tt);
		ts.StartDate.setText(updateDate);
		if (task != null) {

			updateDate = dateFormat.format(task.getUpdateDate());
			ts.StartDate.setText(updateDate);
			return updateDate;
		}
		return updateDate;
	}

	public String setDeadDate() {
		String updateDate = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Timestamp tt = new Timestamp(System.currentTimeMillis());

		updateDate = dateFormat.format(tt);
		ts.deadLineDate.setText(updateDate);
		if (task != null) {

			updateDate = dateFormat.format(task.getDeadLine());
			ts.deadLineDate.setText(updateDate);
			return updateDate;
		}
		return updateDate;
	}

	public void SetStar() {
		if (task != null) {
			for (int i = 0; i < task.getImportance(); i++) {
				ts.returnImoportance = task.getImportance();
				ts.stars[i].setIcon(IC.getImageIcon("starRed"));
			}
		}
	}

	public void setTitle() {

		if (task != null) {
			ts.taskTitle.setText(task.getTitle());
		} else if (task == null) {
			ts.taskTitle.setText("제목을 입력해주세요");
		}
	}

	public String setContent() {

		String content = "내용을 입력해주세요!";
		if (task != null) {
			content = task.getContent();
			ts.contentText.setText(content);
		}
		return content;
	}

	public void setColTitle() {
		String calomnTitle = Takecolumn.getTitle();
		ts.calomnTitle.setText(calomnTitle);

	}

	public void setFeedback() {

		if (feedback != null && task != null) {
			ts.TakeFeedBack = feedback;
			ts.feedBackText.setText(feedback.getComment());

		} else {
			ts.feedBackText.setText("피드백을 입력해주세요");
		}
	}

}
