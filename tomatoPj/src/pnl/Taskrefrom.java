package pnl;


import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneLayout;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import dbutil.DBUtil;
import frame.MainFrame;
import tomatoPj.Feedback;
import tomatoPj.Function_Tag;
import tomatoPj.Member;
import tomatoPj.Task;
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
	JPanel tagPnl;
	int CountTag;
	Image image;
	JToggleButton star;
	JToggleButton[] stars;
	SettingTask st;
	// 폰트 사이즈 조정
	int FontSize;
	// Task 에 줘야하는거
	String title;
	Timestamp updateDate;
	Timestamp deadLine;
	int returnImoportance;
	// 돌려주는 테스크.
	Task returnTask;
	// 캘린더 2
	CalendarPnl2 cal2;
	CalendarPnl cal;
	// 받는 정보들
	// 멤버목록 테스크 목록
	MainFrame MF;
	Task TakeTask;
	List<Member> memberList;
	List<Function_Tag> Function_Tag_List;

	CardLayout cardLayout;

	public Taskrefrom(Task task,MainFrame mainFrame) {
		Taskrefrom tr = this;
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
			public void mouseClicked(MouseEvent e) {
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
					returnTask = new Task(title, contentText.getText(), returnImoportance, updateDate, deadLine);
					System.out.println(returnTask);
					mainFrame.showCard("columnSelect");
				}
			}
		};

		// 프레임에 패널 외부 클릭 리스너 추가
		addMouseListener(outsideClickListener);

		// pnl에 대한 클릭 액션 추가
		pnl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 클릭 이벤트 발생 시 실행할 코드 작성
				// 예시: pnl이 클릭되었을 때의 동작 처리
				System.out.println("pnl이 클릭되었습니다.");
			}
		});

		// cal에 대한 클릭 액션 추가
		cal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 클릭 이벤트 발생 시 실행할 코드 작성
				// 예시: cal이 클릭되었을 때의 동작 처리
				System.out.println("cal이 클릭되었습니다.");
			}
		});

		// cal2에 대한 클릭 액션 추가
		cal2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 클릭 이벤트 발생 시 실행할 코드 작성
				// 예시: cal2가 클릭되었을 때의 동작 처리
				System.out.println("cal2가 클릭되었습니다.");
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
		try {
			TakeTask = taskListBypjNo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Feedback feedback = new Feedback(6, 27, 1, "대본수정");
		st = new SettingTask(TakeTask, this, feedback);
		IC = new IconData();
		FD = new FontData();
		util = new Utility();
		togglePanel();
		// 메인
//		TaskMain();
		TaskMainLbl();
		// 별 날짜
		StarAndDate();
		// 별세팅
		StarSet();
		st.SetStar();

		// 날짜 세팅

		SetUpdateLbl(st.setUpdataDate());
		UpdateMentLbl();

		// 끝나는 날짜

		DeadLineDate(st.setDeadDate());
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

	public void togglePanel() {
		boolean isVisible = pnl.isVisible();

		if (isVisible) {
			// 패널이 이미 켜져있을 때의 동작
			System.out.println("패널이 이미 켜져있습니다.");
		} else {
			// 패널을 켜는 동작
			pnl.setVisible(true);
			System.out.println("패널을 켭니다.");
		}
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
		StarAndDate.setPreferredSize(new Dimension(631, 134));
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
		proTitleAndMember.setSize(new Dimension(631, 120));
		proTitleAndMember.setLocation(0, 0);
		proTitleAndMember.setLayout(null);
		TaskUnderPanel.add(proTitleAndMember);
	}

	public void TaskTitle() {
		taskTitle = new JTextField(TakeTask.getTitle());
		taskTitle.setBorder(null);
		taskTitle.setFont(FD.nanumFontBold(20));
		taskTitle.setSize(200, 53);
		taskTitle.setLocation(60, -10);

		proTitleAndMember.add(taskTitle);
	}

	public void MemberPnl() {
		MemberPnl = new JPanel();
		MemberPnl.setOpaque(false);
		MemberPnl.setLayout(null);
		MemberPnl.setSize(499, 94);
		MemberPnl.setLocation(60, 30);
		proTitleAndMember.add(MemberPnl);
	}

	// 추후 수정 필요
	public void selectMember() {
		plus = new JLabel(IC.getImageIcon("plus"));
		plus.setSize(80, 80);
		plus.setLocation(0, 10);
		MemberPnl.add(plus);

		plus.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				JLabel memberIcon = new JLabel(IC.getImageIcon("user1"));

				memberIcon.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						MemberPnl.remove(memberIcon);
						MemberPnl.revalidate();
						MemberPnl.repaint();
						Count--;
						plus.setVisible(true);
						plus.setLocation(0 + ((Count * 1) * 100), 10);
					}
				});
				MemberPnl.add(memberIcon);
				memberIcon.setSize(80, 80);
				memberIcon.setLocation(0 + ((Count * 1) * 100), 10);
				plus.setLocation(0 + ((Count + 1 * 1) * 100), 10);

				// 아이콘들 간의 간격을 위한 빈 공간 컴포넌트 추가

				MemberPnl.revalidate();
				MemberPnl.repaint();
				Count++;

				if (Count >= 4) {
					plus.setVisible(false);
				} else {
				}
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
		JLabel detailFontLbl = new JLabel("FontSize");
		detailFontLbl.setFont(FD.nanumFontBold(12));
		detailFontLbl.setSize(80, 50);
		detailFontLbl.setLocation(5, 0);
		detail.add(detailFontLbl);
	}

	public void detailFontBox() {
		JTextField detailFontBox = new JTextField();
		detailFontBox.setFont(FD.nanumFontBold(13));
		detailFontBox.setDocument(new PlainDocument() {
			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				if (str == null) {
					return;
				}

				try {
					if (getLength() + str.length() <= 2) {
						FontSize = Integer.parseInt(str);
						super.insertString(offs, str, a);
					}
				} catch (NumberFormatException e) {
					// 입력된 값이 정수가 아닌 경우 처리할 로직을 작성하세요.
					// 예를 들어, 경고 메시지를 표시하거나 입력을 무시할 수 있습니다.
				}
			}
		});
		detailFontBox.setSize(30, 25);
		detailFontBox.setLocation(15, 35);
		detail.add(detailFontBox);

		JButton change = new JButton("버튼");
		change.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				contentText.setFont(FD.nanumFont(Integer.valueOf(detailFontBox.getText())));
			}
		});
		change.setSize(18, 18);
		change.setLocation(20, 60);
		detail.add(change);

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
		JLabel content = new JLabel(IC.getImageIcon("contentPanel_write"));

		JFrame toggleFrame = new JFrame("Toggle Window");
		toggleFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		toggleFrame.setSize(300, 200);
		toggleFrame.setLayout(null);

		contentText = new JTextArea(st.setContent());
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

		content.setFont(FD.nanumFont(1));
		content.add(detailScrollPane);
		content.setSize(500, 250);
//	    content.setLocation(0, 0);

		// contentText의 위치 조정
		detailScrollPane.setLocation(10, 40); // 원하는 위치로 조정

		detail.add(content);
	}

	public void TagPnl() {
		tagPnl = new JPanel();
		tagPnl.setSize(631, 70);
		tagPnl.setLocation(0, 355);
		tagPnl.setLayout(null);
		tagPnl.setOpaque(false);
//		TagPnl.setBackground(new Color(255,0,0));
		TaskUnderPanel.add(tagPnl);
	}

	public void TagSet() {
		JLabel plusTag = new JLabel(IC.getImageIcon("addMember_btn"));
		plusTag.setSize(30, 30);
		plusTag.setLocation(87, 10);
		plusTag.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				JLabel tagIcon = new JLabel(IC.getImageIcon("tag"));
				JLabel tagText = new JLabel("기획");
				tagText.setSize(70, 20);
				tagText.setLocation(20, 5);
				tagIcon.add(tagText);
				tagIcon.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						tagPnl.remove(tagIcon);
						tagPnl.revalidate();
						tagPnl.repaint();
						CountTag--;
						plusTag.setVisible(true);
						plusTag.setLocation(87 + ((CountTag * 1) * 100), 10);
					}
				});
				tagPnl.add(tagIcon);
				tagIcon.setSize(80, 30);
				tagIcon.setLocation(64 + ((CountTag * 1) * 90), 10);
				plusTag.setLocation(87 + ((CountTag + 1 * 1) * 100), 10);

				// 아이콘들 간의 간격을 위한 빈 공간 컴포넌트 추가

				tagPnl.revalidate();
				tagPnl.repaint();
				CountTag++;

				if (CountTag >= 5) {
					plusTag.setVisible(false);
				} else {
				}
			}
		});

		tagPnl.add(plusTag);

	}

	public void feedBack() {
		feedBack = new JPanel();
		feedBack.setSize(629, 172);
		feedBack.setLocation(0, 425);
//		feedBack.setBackground(new Color(0,255,0));
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

		JLabel feedBackLbl = new JLabel(IC.getImageIcon("contentPanel_write"));
		feedBackLbl.setLocation(60, 0);

		feedBackText = new JTextArea(st.setFeedback());
		feedBackText.setFont(FD.nanumFont(18));
		feedBackText.setSize(495, 128);
		feedBackText.setBorder(null); // 테두리 제거
		feedBackText.setOpaque(false);
		feedBackText.setLineWrap(true); // 줄바꿈 활성화

		feedBackscrollPane = new JScrollPane(feedBackText);
		feedBackscrollPane.setFont(FD.nanumFont(18));
		feedBackscrollPane.setSize(495, 128);
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
//	    content.setLocation(0, 0);

		// contentText의 위치 조정
		feedBackscrollPane.setLocation(10, 40); // 원하는 위치로 조정
		feedBackscrollPane.setFont(FD.nanumFont(18));
		feedBack.add(feedBackLbl);
	}

	public static Task taskListBypjNo() throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Task task = null;

		try {
			conn = DBUtil.getConnection();
			String query = "select * from task where task_no = 36";

			stmt = conn.prepareStatement(query);

			rs = stmt.executeQuery();

			if (rs.next()) {
				int task_no = rs.getInt("task_no");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int importance = rs.getInt("importance");
				Timestamp updateDate = rs.getTimestamp("updateDate");
				Timestamp deadLine = rs.getTimestamp("deadLine");
				int active = rs.getInt("active");
				task = new Task(task_no, title, content, importance, updateDate, deadLine, active);
				return task;
			}

		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return task;
	}

//	public static class MyFrame extends JFrame {
//		IconData IC;
//
//		public MyFrame() {
//			IC = new IconData();
//			MainFrame mainFrame;
//			Task testTask = null;
//			Taskrefrom task;
//			try {
//				testTask = taskListBypjNo();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			task = new Taskrefrom(testTask);
//
//			JLabel Background = new JLabel(IC.getImageIcon("selectTask(BG)"));
//
//			Background.setSize(1920, 1080);
//			Background.add(task);
//			add(Background);
//			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			setTitle("My Frame");
//			setSize(1920, 1080);
//			setBackground(new Color(255, 0, 0));
//			setLayout(null);
//			setVisible(true);
//		}
//	}
//
//	public static void main(String[] args) {
//		new MyFrame();
////		frame.add(task);
//
//	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
}
