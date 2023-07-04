package pnl;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneLayout;
import javax.swing.plaf.basic.BasicScrollBarUI;

import frame.MainFrame;
import tomatoPj.Column;
import tomatoPj.Feedback;
import tomatoPj.FeedbackRepository;
import tomatoPj.Function_Tag;
import tomatoPj.Member;
import tomatoPj.Member_task;
import tomatoPj.Task;
import tomatoPj.TaskRepository;
import tomatoPj.Task_Service_Repository;
import utility.FontData;
import utility.IconData;
import utility.Utility;

public class Taskrefrom extends JPanel {
	// 유틸 패키지
	IconData IC;
	FontData FD;
	Utility util;

	// 메인 패널
	JPanel pnl;
	JLabel TaskPnlMainLbl;
	// 별과 날자
	JPanel StarAndDate;
	JLabel timeManagementNavi;
	int Imoportance;
	// 별리셋용 Imoportance

	// 시작날짜
	JLabel StartDate;
	// 끝나는 날짜
	JLabel deadLineDate;
	// 언더
	JPanel TaskUnderPanel;
	// 프로필
	JPanel proTitleAndMember;
	JPanel MemberPnl;
	JLabel plus;
//	JLabel memberIcon;
	int Count;
	// 내용 패널
	JTextField taskTitle;
	JPanel detail;
	JScrollPane detailScrollPane;
	JTextArea contentText;
	JPopupMenu popupMenu;
	// 피드백 패널
	JPanel feedBack;
	JScrollPane feedBackscrollPane;
	JTextArea feedBackText;
	// 태그 패널
	List<String> tagTexts;
	JPanel tagPnl;
	int CountTag;
	Image image;
	JToggleButton star;
	JToggleButton[] stars;
	SettingTask st;
	// 폰트 사이즈 조정
	int FontSize;
	// Task 에 줘야하는거
	int task_Pk;
	String title;
	Timestamp updateDate;
	Timestamp deadLine;
	int returnImoportance;
	int Active;
	// 돌려주는 테스크.
	String text;
	Task returnTask;
	// 캘린더 2
	CalendarPnl2 cal2;
	CalendarPnl cal;
	// 받는 정보들
	Task TakeTask;
	Feedback TakeFeedBack;
	List<Function_Tag> Function_Tag_List;
	// 멤버목록 테스크 목록
	MainFrame MF;
	// 돌려주는 펑션태그 목록
	List<Function_Tag> return_Function_Tag_List;
	TaskRepository taskRepo;
	List<Member> memberList;
	List<Member_task> member_task_List;

	Column column;
	CardLayout cardLayout;

	int useingMemberNum;
	Feedback returnFeedBack;

	Taskrefrom tr;

	private JLabel content;

	JLabel calomnTitle;

	Taskrefrom ts;
	TagAddButton TAB;
	JLabel plusTag;
	List<Member> ProjectMember;
	
	public Taskrefrom(MainFrame mainFrame) {
		MF = mainFrame;
		tagTexts = new ArrayList<>();
		ts = this;
//		 태그 받아오는 db 함수 필요함s
// 		 피드백 도 받아 와야함.
		add(pnl());
		add(cal2 = new CalendarPnl2(this));
		cal2.setLocation(1305, 291);
		cal = new CalendarPnl(this, cal2);
		cal.setLocation(292, 292);
		add(cal);

		MouseAdapter outsideClickListener = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// 패널 외부 클릭 이벤트 발생 시 실행할 코드 작성

				if (e.getSource() == this) {
				} else {
					title = taskTitle.getText();
					String updateDateString = StartDate.getText();
					String deadLineString = deadLineDate.getText();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
					try {
						Date updateDatetoTimestamp = dateFormat.parse(updateDateString);
						Date deadLinetoTimestamp = dateFormat.parse(deadLineString);
						int result = updateDatetoTimestamp.compareTo(deadLinetoTimestamp);
						if (result <= 0) {

							updateDate = new Timestamp(updateDatetoTimestamp.getTime());
							deadLine = new Timestamp(deadLinetoTimestamp.getTime());
						} else {
							updateDate = new Timestamp(updateDatetoTimestamp.getTime());
							deadLine = null;
						}

						// 변환된 Timestamp 객체 사용
					} catch (ParseException e2) {
						// 날짜 포맷이 잘못된 경우 예외 처리
						e2.printStackTrace();
					}

					if (TakeTask == null) {

						returnTask = new Task(title, contentText.getText(), returnImoportance, updateDate, deadLine);
						if (returnImoportance == 0) {
							returnTask = new Task(title, contentText.getText(), 1, updateDate, deadLine);
						}
					} else {
						returnTask = new Task(task_Pk, title, contentText.getText(), returnImoportance, updateDate,
								deadLine, Active);

					}

					if (TakeFeedBack != null) {
						if (TakeFeedBack.getMember_no() == 0) {
							TakeFeedBack.setMember_no(useingMemberNum);
						}
						if (TakeFeedBack.getTask_no() == 0) {
							if (TakeTask != null) {
								TakeFeedBack.setTask_no(TakeTask.getTask_no());
							} else {
								TakeFeedBack.setTask_no(0);
							}
						}
						if (TakeFeedBack.getComment().equals("")) {
//						TakeFeedBack.setComment(text);

						}
					}

					returnFeedBack = TakeFeedBack;

					Task_Service_Repository TSR = new Task_Service_Repository();
					
					
					int newTask_num = TSR.updateTask(returnTask,column.getColumn_no());
					
					if(TakeFeedBack !=null && TakeFeedBack.getTask_no() ==0) {
						if(TakeTask ==null) {
						TSR.FeedbackFunction(TakeFeedBack, newTask_num);
						}else if(TakeTask !=null) {
							TSR.FeedbackFunction(TakeFeedBack, returnTask.getTask_no());
						}
					}
					
					if(returnTask.getTask_no()==0 && Function_Tag_List!= null) {
						TSR.Function_TagFunction(Function_Tag_List,newTask_num);						
					}else if(returnTask.getTask_no()!=0 && Function_Tag_List!= null){
						TSR.Function_TagFunction(Function_Tag_List,returnTask.getTask_no());
					}
					
					
					if(returnTask.getTask_no()==0 && member_task_List!= null) {
						TSR.Member_taskFunction(member_task_List,newTask_num);
					}else if(returnTask.getTask_no()!=0 && member_task_List!= null) {
						TSR.Member_taskFunction(member_task_List,returnTask.getTask_no());						
					}
					st.reset();
					CountTag = 0;
					mainFrame.getContentPane().removeAll();
					mainFrame.addPnl();

					if (mainFrame.todoBtnActive == false) {
						mainFrame.showCard("columnSelect");
					} else if (mainFrame.todoBtnActive == true) {
						mainFrame.showCard("todo");
					}

					mainFrame.columnActive = true;
					mainFrame.boradPnl.panel_2.columnSelectPnl.columnSetting();

				}

			}
		};

		// 프레임에 패널 외부 클릭 리스너 추가
		addMouseListener(outsideClickListener);

		// pnl에 대한 클릭 액션 추가
		pnl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		// cal에 대한 클릭 액션 추가
		cal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		// cal2에 대한 클릭 액션 추가
		cal2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		setSize(1920, 1080);
		setLocation(0, 0);
		setLayout(null);
		setVisible(true);
		setOpaque(false);

	}

	public JPanel pnl() {

		pnl = new JPanel();
		// 달력작업
		// 모든 정보를 뭉쳐서 task 객체로 반환
		// 팝업창에 아이디 입력으로 추가.
		// 멤버 추가 로직고민

		IC = new IconData();
		FD = new FontData();
		util = new Utility();

		// 메인

		TaskMainLbl();
		// 별 날짜
		StarAndDate();
		// 별세팅
		StarSet();

		// 날짜 세팅

		SetUpdateLbl("");
		UpdateMentLbl();

		// 끝나는 날짜

		DeadLineDate("");
		DeadLineDateAdd();
		// 기간 바
		TimeMangementBar();
		TimeMangerMentNavi();
		// 언더
		TaskUnderPnl();
		// 타이틀 멤버 아이콘 패널
		ProTitleAndMember();
		MemberPnl();
		selectMember();
		TaskTitle();
		CalomnTitle();
		// 내용 패널
		detailPnl();
		detailTextFiled();
		scrollPaneSetLayout();
		detailFontLbl();
		detailFontBox();
		// tag
		TagPnl();

		TagSet();

		// feedback
		feedBack();
		feedBackTextFiled();
		feedBackscrollPaneSetLayout();

		TaskPnlMainLbl.add(StarAndDate);
		TaskPnlMainLbl.add(TaskUnderPanel);

		pnl.add(TaskPnlMainLbl);

		pnl.setBounds(645, 293, 631, 725);
		pnl.setOpaque(false);
		return pnl;
	}

	public void settingTask(Taskrefrom myUpPnl, Task task, Column column) {

		Feedback feedback = null;
		FeedbackRepository FRT = new FeedbackRepository();
		int i = 0;

		if (task != null) {
			i = task.getTask_no();
		}
		;
		if (FRT.searchFeedbackBytask_no(i) != null) {
			feedback = FRT.searchFeedbackBytask_no(i);
		} else {
			feedback = null;
		}



		st = new SettingTask(MF, myUpPnl, task, column, feedback);

		st.setPJ_Mem();
		
		st.settingPKAndAc();

		st.setUsingMemberNum();

		// 별세팅
		st.SetStar();
		// 시작 날짜 세팅
		st.setUpdataDate();
		// 끝나는 날짜 세팅
		st.setDeadDate();
		// 칼럼 제목 세팅
		st.setColTitle();
		// 제목 세팅
		st.setTitle();
		// 내용세팅
		st.setContent();
		// 피드백 세팅
		st.setFeedback();
		
		// 멤버 세팅
		st.setMemberlist();
		st.setMember_Task();
		// 멤버 아이콘 세ㅐ팅
		st.settingMemberIcon();
		// 태그 세팅
		st.setTaglist();

		st.TagAddButton(Function_Tag_List);


	}

	public void TaskMainLbl() {
		TaskPnlMainLbl = new JLabel(IC.getImageIcon("contentPanel"));
		TaskPnlMainLbl.setSize(631, 725);
		TaskPnlMainLbl.setLocation(645, 290);
		TaskPnlMainLbl.setLayout(new BoxLayout(TaskPnlMainLbl, BoxLayout.Y_AXIS));
		TaskPnlMainLbl.setOpaque(false);
	}

	/**
	 * @param 이호재
	 * @return 별과 기간 패널 반환
	 */
	public JPanel StarAndDate() {
		StarAndDate = new JPanel();
		StarAndDate.setPreferredSize(new Dimension(631, 140));
		StarAndDate.setOpaque(false);
		StarAndDate.setLayout(null);
		return StarAndDate;
	}

	/**
	 * @author 이호재
	 * @param IC
	 * @param StarAndDate
	 * @param util
	 * 
	 */
	public void StarSet() {
		stars = new JToggleButton[5];

		for (int i = 0; i < stars.length; i++) {
			star = new JToggleButton(IC.getImageIcon("starGray"));
			stars[i] = star;
			star.setSize(20, 21);
			star.setLocation(225 + ((i + 1) * 25), 28);
			MouseAdapter ma = new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					for (int i = 0; i < stars.length; i++) {
						stars[i].setIcon(IC.getImageIcon("starGray"));
					}
					int index = 0;

					for (JToggleButton Star : stars) {
						JToggleButton SelectedStar = (JToggleButton) e.getSource();
						if (SelectedStar.equals(Star)) {
							Imoportance = index;
							returnImoportance = index + 1;
						}
						index++;
					}
					for (int j = 0; j <= Imoportance; j++) {
						stars[j].setIcon(IC.getImageIcon("starRed"));

					}

				}

			};

			star.addMouseListener(ma);
			util.setButtonProperties(star);
			StarAndDate.add(stars[i]);

		}
	}

	/**
	 * @author 이호재
	 * @param FD
	 * @param date JLabel :날짜 정보
	 * @return JLabel
	 */
	public void SetUpdateLbl(String date) {

		StartDate = new JLabel(date);

		// Update date 라벨

		StartDate.setBorder(null); // 테두리 제거
		StartDate.setSize(98, 16);
		StartDate.setLocation(65, 68);
		StartDate.setForeground(new Color(36, 161, 138));
		StartDate.setFont(FD.nanumFont(16));
		StarAndDate.add(StartDate);

	}

	/**
	 * @author 이호재
	 * @param FD
	 * @return JLabel
	 */
	public void UpdateMentLbl() {
		JLabel StartDateAdd = new JLabel("부터");
		StartDateAdd.setSize(31, 16);
		StartDateAdd.setLocation(165, 68);
		StartDateAdd.setFont(FD.nanumFont(16));
		StarAndDate.add(StartDateAdd);

	}

	/**
	 * @author 이호재
	 * @param FD
	 * @param String 마감기한의 String 값
	 * @return JLabel
	 */
	public void DeadLineDate(String deadDate) {
		deadLineDate = new JLabel(deadDate);
		deadLineDate.setSize(98, 16);
		deadLineDate.setLocation(435, 68);
		deadLineDate.setForeground(new Color(235, 105, 97));
		deadLineDate.setFont(FD.nanumFont(16));
		StarAndDate.add(deadLineDate);
	}

	/**
	 * 
	 * @param FD
	 * @return "까지" 라벨
	 */
	public void DeadLineDateAdd() {
		JLabel DeadLineDateAdd = new JLabel("까지");
		DeadLineDateAdd.setSize(31, 16);
		DeadLineDateAdd.setLocation(535, 68);
		DeadLineDateAdd.setFont(FD.nanumFont(16));
		StarAndDate.add(DeadLineDateAdd);
	}

	/**
	 * 
	 * @param IC
	 * @return 타임라인 선
	 */
	public void TimeMangementBar() {
		JLabel TimeMangementBar = new JLabel(IC.getImageIcon("timeManagementBar"));
		TimeMangementBar.setSize(504, 22);
		TimeMangementBar.setLocation(63, 88);
		StarAndDate.add(TimeMangementBar);
	}

	/**
	 * @apiNote 시간 단위로 이동하는 기능 필요.
	 * @param IC
	 * @return 타임라인 네비
	 */
	public void TimeMangerMentNavi() {
		timeManagementNavi = new JLabel(IC.getImageIcon("timeManagementNavi"));
		timeManagementNavi.setSize(16, 14);
		timeManagementNavi.setLocation(63, 109);
		StarAndDate.add(timeManagementNavi);
	}

	public void TaskUnderPnl() {
		TaskUnderPanel = new JPanel();
		TaskUnderPanel.setPreferredSize(new Dimension(625, 597));
		TaskUnderPanel.setLayout(null);
		TaskUnderPanel.setOpaque(false);

	}

	public void ProTitleAndMember() {
		proTitleAndMember = new JPanel();
		proTitleAndMember.setOpaque(false);
//		proTitleAndMember.setBackground(new Color(255,0,0));
		proTitleAndMember.setSize(new Dimension(631, 140));
		proTitleAndMember.setLocation(0, 0);
		proTitleAndMember.setLayout(null);
		TaskUnderPanel.add(proTitleAndMember);
	}

	public void TaskTitle() {

		taskTitle = new JTextField("타이틀");

		JLabel taskTitleLbl = new JLabel(IC.getImageIcon("taskTitle"));
		taskTitleLbl.setSize(290, 62);
		taskTitleLbl.setLocation(275, -3);

		taskTitleLbl.add(taskTitle);
		taskTitle.setBorder(null);
		taskTitle.setFont(FD.nanumFontBold(18));
		taskTitle.setOpaque(false);
		taskTitle.setSize(200, 53);
		taskTitle.setLocation(15, 17);
		;

		proTitleAndMember.add(taskTitleLbl);

	}

	public void CalomnTitle() {
		JLabel CalomnTitleLbl = new JLabel(IC.getImageIcon("columnTitle"));
		calomnTitle = new JLabel("칼럼 제목 들어가야함");

		calomnTitle.setBorder(null);
		calomnTitle.setFont(FD.nanumFontBold(18));
		calomnTitle.setOpaque(false);
		calomnTitle.setSize(200, 53);
		calomnTitle.setLocation(15, 17);

		CalomnTitleLbl.setSize(200, 62);
		CalomnTitleLbl.setLocation(60, -3);
		CalomnTitleLbl.add(calomnTitle);

		proTitleAndMember.add(CalomnTitleLbl);

	}

	public void MemberPnl() {
		MemberPnl = new JPanel();
		MemberPnl.setOpaque(false);
		MemberPnl.setLayout(new BoxLayout(MemberPnl, BoxLayout.X_AXIS));
		MemberPnl.setSize(499, 94);
		MemberPnl.setLocation(60, 50);
		proTitleAndMember.add(MemberPnl);
	}

	// 추후 수정 필요
	public void selectMember() {
		plus = new JLabel(IC.getImageIcon("plus"));
		plus.setSize(80, 80);
		plus.setLocation(0, 15);
		MemberPnl.add(plus);

		plus.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				JDialog  memAdd =  new TaskMemberAdd(MF);
				memAdd.setLocation(700,550);
//				ImageIcon back = IC.getImageIcon("addmember(BG)");
//				String input = (String) JOptionPane.showInputDialog(null, "Enter something", "Input Dialog", JOptionPane.PLAIN_MESSAGE, back, null, null);
//				JLabel memberIcon = new JLabel();
//				for(int i = 0; i<memberList.size();i++) {
//				if(input.equals(memberList.get(i).getId())) {
//					if(memberList.get(i).getImage()!=null) {
//					ImageIcon icon = new ImageIcon(memberList.get(i).getImage());
//					memberIcon.setIcon(icon);
//					}else {
//					memberIcon.setIcon(IC.getImageIcon("user1"));
//					}
//					
//					memberIcon.addMouseListener(new MouseAdapter() {
//						public void mousePressed(MouseEvent e) {
//							
//							// showInputDialog 메서드를 사용하여 입력 다이얼로그를 표시하고 이미지 아이콘을 설정합니다.
//							MemberPnl.remove(memberIcon);
//							MemberPnl.revalidate();
//							MemberPnl.repaint();
//							Count--;
//							plus.setVisible(true);
//							
//							plus.setLocation(0 + ((Count * 1) * 100), 15);
//						}
//					});
//					MemberPnl.add(memberIcon);
//					memberIcon.setSize(60, 60);
//					memberIcon.setLocation(0 + ((Count * 1) * 100), 20);
//					plus.setLocation(0 + ((Count + 1 * 1) * 100), 15);
//					
//					// 아이콘들 간의 간격을 위한 빈 공간 컴포넌트 추가
//					
//					MemberPnl.revalidate();
//					MemberPnl.repaint();
//					Count++;
//				}
//				}
//
//				if (Count >= 4) {
//					plus.setVisible(false);
//				} else {
//				}
			}
		});
	}

	public void detailPnl() {
		detail = new JPanel();
		detail.setOpaque(false);
		detail.setSize(new Dimension(631, 235));
		detail.setLocation(0, 121);
		detail.setLayout(null);
		TaskUnderPanel.add(detail);
	}

	public void detailFontLbl() {
		JLabel detailFontLbl = new JLabel(IC.getImageIcon("font"));
		detailFontLbl.setFont(FD.nanumFontBold(12));
		detailFontLbl.setSize(16, 10);
		detailFontLbl.setLocation(400, 35);
		content.add(detailFontLbl);
	}

	public void detailFontBox() {

		JLabel Increment = new JLabel(IC.getImageIcon("fontBig"));
		Increment.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				FontSize++;
				detailScrollPane.setFont(FD.nanumFont(FontSize));
				contentText.setFont(FD.nanumFont(FontSize));

			}
		});
		Increment.setSize(20, 20);
		Increment.setLocation(450, 30);
		content.add(Increment);

		JLabel dincrement = new JLabel(IC.getImageIcon("fontSmall"));
		dincrement.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (FontSize >= 5) {
					FontSize--;
				}
				detailScrollPane.setFont(FD.nanumFont(FontSize));
				contentText.setFont(FD.nanumFont(FontSize));

			}
		});
		dincrement.setSize(20, 20);
		dincrement.setLocation(430, 30);
		content.add(dincrement);

	}

	private void scrollPaneSetLayout() {
		detailScrollPane.setLayout(new ScrollPaneLayout() {
			@Override
			public void layoutContainer(Container parent) {
				JScrollPane scrollPane = (JScrollPane) parent;

				Rectangle availR = scrollPane.getBounds();
				availR.x = availR.y = 0;

				Insets insets = parent.getInsets();
				availR.x = insets.left;
				availR.y = insets.top;
				availR.width -= insets.left + insets.right;
				availR.height -= insets.top + insets.bottom;

				Rectangle vsbR = new Rectangle();
				vsbR.width = 12;
				vsbR.height = availR.height;
				vsbR.x = availR.x + availR.width - vsbR.width;
				vsbR.y = availR.y;

				if (viewport != null) {
					viewport.setBounds(availR);
				}
				if (vsb != null) {
					vsb.setVisible(false); // 스크롤바를 보이지 않게 설정
					vsb.setOpaque(false);

					vsb.setBounds(vsbR);
				}
			}
		});
	}

	public void detailTextFiled() {
		content = new JLabel(IC.getImageIcon("contentPanel_write"));

		JFrame toggleFrame = new JFrame("Toggle Window");
		toggleFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		toggleFrame.setSize(300, 200);
		toggleFrame.setLayout(null);

		contentText = new JTextArea("내용을 입력해주세요");

		contentText.setFont(FD.nanumFont(18));
		content.setLocation(60, -10);

		if (TakeTask != null) {
			contentText.setText(TakeTask.getContent());
		}
//	    
		contentText.setSize(495, 160);
		contentText.setBorder(null); // 테두리 제거
		contentText.setOpaque(false);
		contentText.setLineWrap(true); // 줄바꿈 활성화

		detailScrollPane = new JScrollPane(contentText);
		detailScrollPane.setSize(495, 160);
		detailScrollPane.setBorder(null);
		detailScrollPane.setOpaque(false);
		detailScrollPane.getViewport().setOpaque(false); // 뷰포트 투명화

		// 수직 스크롤바 커스텀
		JScrollBar verticalScrollBar = detailScrollPane.getVerticalScrollBar();
		verticalScrollBar.setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				// 스크롤바의 배경색을 투명하게 설정
				this.trackColor = new Color(0, 0, 0, 0);
			}
		});

		// 수평 스크롤바 커스텀
		JScrollBar horizontalScrollBar = detailScrollPane.getHorizontalScrollBar();
		horizontalScrollBar.setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				// 스크롤바의 배경색을 투명하게 설정
				this.trackColor = new Color(0, 0, 0, 0);
			}
		});
		detailScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		detailScrollPane.addMouseWheelListener(new MouseAdapter() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				int units = e.getWheelRotation();
				JScrollBar scrollBar = detailScrollPane.getVerticalScrollBar();
				int scrollAmount = scrollBar.getUnitIncrement() * units;
				int speed = 20; // 스크롤 속도 조절 (값을 조정하여 원하는 속도로 설정)
				int newValue = scrollBar.getValue() + (scrollAmount * speed);
				scrollBar.setValue(newValue);
			}
		});
		FontSize = 18;
		content.setFont(FD.nanumFont(FontSize));
		content.add(detailScrollPane);
		content.setSize(500, 250);

		// contentText의 위치 조정
		detailScrollPane.setLocation(10, 40); // 원하는 위치로 조정

		detail.add(content);
	}

	public void TagPnl() {
		tagPnl = new JPanel();
		tagPnl.setSize(500, 56);
		tagPnl.setLocation(60, 355);
		tagPnl.setLayout(new BoxLayout(tagPnl, BoxLayout.X_AXIS));
		tagPnl.setOpaque(false);

		TaskUnderPanel.add(tagPnl);
	}

	public void TagSet() {

		plusTag = new JLabel(IC.getImageIcon("addMember_btn"));
		plusTag.setSize(30, 30);
		plusTag.setLocation(0, 10);
		tagPnl.add(plusTag);
		plusTag.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				TAB = new TagAddButton(MF, plusTag, tagPnl, ts, TakeTask);
				JDialog TagAdd = TAB;
				TagAdd.setLocation(plusTag.getX() + 650, plus.getY() + 820);
				tagPnl.revalidate();
				tagPnl.repaint();
			}
		});
	}

	public void feedBack() {
		feedBack = new JPanel();
		feedBack.setSize(629, 172);
		feedBack.setLocation(0, 425);
		feedBack.setOpaque(false);
		TaskUnderPanel.add(feedBack);
	}

	private void feedBackscrollPaneSetLayout() {
		feedBackscrollPane.setLayout(new ScrollPaneLayout() {
			@Override
			public void layoutContainer(Container parent) {
				JScrollPane scrollPane = (JScrollPane) parent;

				Rectangle availR = scrollPane.getBounds();
				availR.x = availR.y = 0;

				Insets insets = parent.getInsets();
				availR.x = insets.left;
				availR.y = insets.top;
				availR.width -= insets.left + insets.right;
				availR.height -= insets.top + insets.bottom;

				Rectangle vsbR = new Rectangle();
				vsbR.width = 12;
				vsbR.height = availR.height;
				vsbR.x = availR.x + availR.width - vsbR.width;
				vsbR.y = availR.y;

				if (viewport != null) {
					viewport.setBounds(availR);
				}
				if (vsb != null) {
					vsb.setVisible(false); // 스크롤바를 보이지 않게 설정
					vsb.setOpaque(false);

					vsb.setBounds(vsbR);
				}
			}
		});
	}

	public void feedBackTextFiled() {

		JLabel feedBackLbl = new JLabel(IC.getImageIcon("feedback"));
		feedBackLbl.setLocation(60, 0);

		feedBackLbl.add(feedbackRun());

		feedBackText = new JTextArea("피드백을 입력해주세요");
		feedBackText.setFont(FD.nanumFont(20));
		feedBackText.setSize(350, 40);
		feedBackText.setBorder(null); // 테두리 제거
		feedBackText.setOpaque(false);
		feedBackText.setLineWrap(true); // 줄바꿈 활성화

		feedBackText.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				// 텍스트 필드의 내용이 변경될 때 호출됨
				if (TakeFeedBack != null) {
					text = feedBackText.getText();
					TakeFeedBack.setComment(text);
				} else if (TakeTask != null && TakeFeedBack == null) {
				
					TakeFeedBack = new Feedback(TakeTask.getTask_no(), useingMemberNum, text);
				}
				if (TakeTask == null && TakeFeedBack == null) {
					TakeFeedBack = new Feedback(0, useingMemberNum, text);
				}

			}
		});

		feedBackscrollPane = new JScrollPane(feedBackText);
		feedBackscrollPane.setFont(FD.nanumFont(20));
		feedBackscrollPane.setSize(350, 40);
		feedBackscrollPane.setBorder(null);
		feedBackscrollPane.setOpaque(false);
		feedBackscrollPane.getViewport().setOpaque(false); // 뷰포트 투명화

		// 수직 스크롤바 커스텀
		JScrollBar verticalScrollBar = feedBackscrollPane.getVerticalScrollBar();
		verticalScrollBar.setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				// 스크롤바의 배경색을 투명하게 설정
				this.trackColor = new Color(0, 0, 0, 0);
			}
		});

		// 수평 스크롤바 커스텀
		JScrollBar horizontalScrollBar = feedBackscrollPane.getHorizontalScrollBar();
		horizontalScrollBar.setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				// 스크롤바의 배경색을 투명하게 설정
				this.trackColor = new Color(0, 0, 0, 0);
			}
		});
		feedBackscrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		feedBackscrollPane.addMouseWheelListener(new MouseAdapter() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				int units = e.getWheelRotation();
				JScrollBar scrollBar = feedBackscrollPane.getVerticalScrollBar();
				int scrollAmount = scrollBar.getUnitIncrement() * units;
				int speed = 20; // 스크롤 속도 조절 (값을 조정하여 원하는 속도로 설정)
				int newValue = scrollBar.getValue() + (scrollAmount * speed);
				scrollBar.setValue(newValue);
			}
		});
		feedBackLbl.setFont(FD.nanumFont(1));

		feedBackLbl.setSize(482, 72);
		feedBackLbl.add(feedBackscrollPane);
		// contentText의 위치 조정
		feedBackscrollPane.setLocation(63, 47); // 원하는 위치로 조정
		feedBackscrollPane.setFont(FD.nanumFont(18));
		feedBack.add(feedBackLbl);
	}

	public JLabel feedbackRun() {
		JLabel feedbackbtn = new JLabel();

		return feedbackbtn;

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}

}