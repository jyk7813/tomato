package pnl;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.time.LocalDateTime;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.JViewport;
import javax.swing.ScrollPaneLayout;
import javax.swing.plaf.basic.BasicScrollBarUI;

import tomatoPj.Task;
import utility.FontData;
import utility.IconData;
import utility.Utility;

public class Taskrefrom extends JPanel{
	// 중요도
	private int Imoportance;
	// 유틸 패키지
	IconData IC;
	FontData FD ;
	Utility util;
	
	// 메인 패널
	JLabel TaskPnlMainLbl;
	// 별과 날자
	JPanel StarAndDate;
	JLabel timeManagementNavi;
	//시작날짜
	JLabel StartDate;
	//끝나는 날짜
	JLabel deadLineDate;
	//언더
	JPanel TaskUnderPanel;
	// 프로필
	JPanel proTitleAndMember;
	JPanel MemberPnl;
	JLabel plus;
//	JLabel memberIcon;
	int Count;
	//내용 패널
	JPanel detail;
	JScrollPane detailScrollPane;
	JTextArea contentText;
	
	// 피드백 패널
	JPanel feedBack;
	JScrollPane feedBackscrollPane;
	JTextArea feedBackText;
	// 태그 패널
	JPanel tagPnl;
	int CountTag;
	



	public Taskrefrom() {
		IC = new IconData();
		FD = new FontData();
		util = new Utility();
		//메인
//		TaskMain();
		TaskMainLbl();
		//별 날짜
		StarAndDate();
		// 별세팅
		StarSet();
		// 날짜 세팅
		String date = "2023.06.23";
		SetUpdateLbl(date);
		UpdateMentLbl();
		
		//끝나는 날짜
		String deadDate = "2023.07.03";
		DeadLineDate(deadDate);
		DeadLineDateAdd();
		// 기간 바
		TimeMangementBar();
		TimeMangerMentNavi();
		//언더
		TaskUnderPnl();
		// 타이틀 멤버 아이콘 패널
		ProTitleAndMember();
		MemberPnl();
		selectMember();
		// 내용 패널
		detailPnl();
		detailTextFiled();
		scrollPaneSetLayout();

		// tag
		TagPnl();
		TagSet();
		//feedback
		feedBack();
		feedBackTextFiled();
		feedBackscrollPaneSetLayout();
		
		
		TaskPnlMainLbl.add(StarAndDate);
		TaskPnlMainLbl.add(TaskUnderPanel);

		
		add(TaskPnlMainLbl);


		setVisible(true);
		setSize(631,725);
		setLocation(645,290);
		setOpaque(false);
		
	}
	public Taskrefrom(Task task) {
	
		
		
//		task = new Task(1,"프로젝트 계획 수립","계획을 수립하세요",1,,);
		IC = new IconData();
		FD = new FontData();
		util = new Utility();
		//메인
//		TaskMain();
		TaskMainLbl();
		//별 날짜
		StarAndDate();
		// 별세팅
		StarSet();
		// 날짜 세팅
		String date = "2023.06.23";
		SetUpdateLbl(date);
		UpdateMentLbl();
		
		//끝나는 날짜
		String deadDate = "2023.07.03";
		DeadLineDate(deadDate);
		DeadLineDateAdd();
		// 기간 바
		TimeMangementBar();
		TimeMangerMentNavi();
		//언더
		TaskUnderPnl();
		// 타이틀 멤버 아이콘 패널
		ProTitleAndMember();
		MemberPnl();
		selectMember();
		// 내용 패널
		detailPnl();
		detailTextFiled();
		scrollPaneSetLayout();

		// tag
		TagPnl();
		TagSet();
		//feedback
		feedBack();
		feedBackTextFiled();
		feedBackscrollPaneSetLayout();
		
		
		TaskPnlMainLbl.add(StarAndDate);
		TaskPnlMainLbl.add(TaskUnderPanel);

		
		add(TaskPnlMainLbl);


		setVisible(true);
		setSize(631,725);
		setLocation(645,290);
		setOpaque(false);
		
	}
	
	
	public void TaskMainLbl() {
		TaskPnlMainLbl = new JLabel(IC.getImageIcon("contentPanel"));
		TaskPnlMainLbl.setSize(631,725);
		TaskPnlMainLbl.setLocation(645,290);
		TaskPnlMainLbl.setLayout(new BoxLayout(TaskPnlMainLbl, BoxLayout.Y_AXIS));
//		TaskPnlMainLbl.setOpaque(false);
	}
	/**
	 * @param 이호재
	 * @return 별과 기간 패널 반환
	 */
	public JPanel StarAndDate() {
		StarAndDate = new JPanel();
		StarAndDate.setPreferredSize(new Dimension(631,134));
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
		//별
		JToggleButton [] Stars = new JToggleButton[5];
		for (int i = 0; i < Stars.length; i++) {
			JToggleButton Star = new JToggleButton(IC.getImageIcon("starGray"));
			Stars[i] = Star;
			Star.setSize(20,21);
			Star.setLocation(225+((i+1)*25), 28);
			MouseAdapter ma = new MouseAdapter() {
				 @Override
				    public void mousePressed(MouseEvent e) {
						for (int i = 0; i < Stars.length; i++) {
							Stars[i].setIcon(IC.getImageIcon("starGray"));
						}
						int index = 0;

						 for (JToggleButton Star : Stars) {
							 JToggleButton SelectedStar = (JToggleButton)e.getSource();
							 if(SelectedStar.equals(Star)) {
								 Imoportance = index;
							 }
							 index++;
						 }
						 System.out.println(Imoportance+1);
						 for(int i = 0;i<=Imoportance;i++)
							 Stars[i].setIcon(IC.getImageIcon("starRed"));
					   }
			
			};
			Star.addMouseListener(ma);
			util.setButtonProperties(Star);
			StarAndDate.add(Stars[i]);
		}
	}
	/**
	 * @author 이호재
	 * @param FD
	 * @param date JLabel :날짜 정보
	 * @return JLabel
	 */
	public void SetUpdateLbl(String date) {
		//Update date 라벨
		StartDate = new JLabel(date);
		StartDate.setSize(98,16);
		StartDate.setLocation(65,68);
		StartDate.setForeground(new Color(36,161,138));
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
		StartDateAdd.setSize(31,16);
		StartDateAdd.setLocation(165,68);
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
		deadLineDate.setSize(98,16);
		deadLineDate.setLocation(435,68);
		deadLineDate.setForeground(new Color(235,105,97));
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
		DeadLineDateAdd.setSize(31,16);
		DeadLineDateAdd.setLocation(535,68);
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
		timeManagementNavi.setSize(16,14);
		timeManagementNavi.setLocation(63,109);
		StarAndDate.add(timeManagementNavi);
	}
	

	public void TaskUnderPnl() {
		TaskUnderPanel = new JPanel();
		TaskUnderPanel.setPreferredSize(new Dimension(625,597));
		TaskUnderPanel.setLayout(null);
		TaskUnderPanel.setOpaque(false);
		
	}	
	public void ProTitleAndMember() {
		proTitleAndMember = new JPanel();
		proTitleAndMember.setOpaque(false);
//		proTitleAndMember.setBackground(new Color(255,0,0));
		proTitleAndMember.setSize(new Dimension(631,120));
		proTitleAndMember.setLocation(0,0);
		proTitleAndMember.setLayout(null);
		TaskUnderPanel.add(proTitleAndMember);
	}
	public void MemberPnl() {
		MemberPnl = new JPanel();
		MemberPnl.setOpaque(false);
		MemberPnl.setLayout(null);
		MemberPnl.setSize(499,94);
		MemberPnl.setLocation(60,20);	
		proTitleAndMember.add(MemberPnl);
	}
	// 추후 수정 필요
	public void selectMember() {
		   plus = new JLabel(IC.getImageIcon("plus"));
		    plus.setSize(80, 80);
		    plus.setLocation(0,10);
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
		             plus.setLocation(0+((Count*1)*100),10);
		   			}
					});
		            MemberPnl.add(memberIcon);
		    	    memberIcon.setSize(80,80);
		            memberIcon.setLocation(0+((Count*1)*100),10);
		            plus.setLocation(0+((Count+1*1)*100),10);

		            // 아이콘들 간의 간격을 위한 빈 공간 컴포넌트 추가
		            
		            MemberPnl.revalidate();
		            MemberPnl.repaint();
		            Count++;
		            
		            if(Count >=4) {
		            	plus.setVisible(false);
		            }else {
		            }
		        }
		    });
		}
	public void detailPnl() {
		detail = new JPanel();
		detail.setOpaque(false);
		detail.setSize(new Dimension(631,235));
//		detail.setBackground(new Color(255,0,0));
		detail.setLocation(0,121);
		detail.setLayout(null);
		TaskUnderPanel.add(detail);
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
	    content.setLocation(60,-10);

	    contentText = new JTextArea();
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
                int speed = 20;  // 스크롤 속도 조절 (값을 조정하여 원하는 속도로 설정)
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
	public void TagPnl(){
		tagPnl = new JPanel();
		tagPnl.setSize(631,70);
		tagPnl.setLocation(0,355);
		tagPnl.setLayout(null);
		tagPnl.setOpaque(false);
//		TagPnl.setBackground(new Color(255,0,0));
		TaskUnderPanel.add(tagPnl);
	}
	public void TagSet() {
		JLabel plusTag = new JLabel(IC.getImageIcon("addMember_btn"));
		plusTag.setSize(30,30);
		plusTag.setLocation(87,10);
		plusTag.addMouseListener(new MouseAdapter() {
			  public void mousePressed(MouseEvent e) {
		        	JLabel tagIcon = new JLabel(IC.getImageIcon("tag"));
		        	JLabel tagText = new JLabel("기획");
		        	tagText.setSize(70,20);
		        	tagText.setLocation(20,5);
		        	tagIcon.add(tagText);		           		            
		        	tagIcon.addMouseListener(new MouseAdapter() {
		            	public void mousePressed(MouseEvent e) {
		            		tagPnl.remove(tagIcon);
		            		tagPnl.revalidate();
		            		tagPnl.repaint();
		                    CountTag--;
		                    plusTag.setVisible(true);
		                    plusTag.setLocation(87+((CountTag*1)*100),10);
		   			}
					});
		        	tagPnl.add(tagIcon);
		        	tagIcon.setSize(80,30);
		        	tagIcon.setLocation(64+((CountTag*1)*90),10);
		            plusTag.setLocation(87+((CountTag+1*1)*100),10);

		            // 아이콘들 간의 간격을 위한 빈 공간 컴포넌트 추가
		            
		            tagPnl.revalidate();
		            tagPnl.repaint();
		            CountTag++;
		            
		            if(CountTag >=5) {
		            	plusTag.setVisible(false);
		            }else {
		            }
		        }
		    });
		
		tagPnl.add(plusTag);
		
	}
	public void feedBack() {
		feedBack = new JPanel();
		feedBack.setSize(629,172);
		feedBack.setLocation(0,425);
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
	    feedBackLbl.setLocation(60,0);

	    feedBackText = new JTextArea();
	    feedBackText.setSize(495, 128);
	    feedBackText.setBorder(null); // 테두리 제거
	    feedBackText.setOpaque(false);
	    feedBackText.setLineWrap(true); // 줄바꿈 활성화

	    feedBackscrollPane = new JScrollPane(feedBackText);
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
                int speed = 20;  // 스크롤 속도 조절 (값을 조정하여 원하는 속도로 설정)
                int newValue = scrollBar.getValue() + (scrollAmount * speed);
                scrollBar.setValue(newValue);
            }
        });
	    feedBackLbl.setFont(FD.nanumFont(1));
	    feedBackLbl.add(feedBackscrollPane);
	    feedBackLbl.setSize(482,72 );
//	    content.setLocation(0, 0);

	    // contentText의 위치 조정
	    feedBackscrollPane.setLocation(10, 40); // 원하는 위치로 조정

	    feedBack.add(feedBackLbl);
	}


	public static class MyFrame extends JFrame {
		IconData IC;
	    public MyFrame() {
	    	IC = new IconData();
	    	Taskrefrom task = new Taskrefrom();
	    	
	    	
	        JLabel Background =new JLabel(IC.getImageIcon("selectTask(BG)"));
	        Background.setSize(1920, 1080);
	        Background.add(task);
	        add(Background);
	    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setTitle("My Frame");
	        setSize(1920, 1080);
//	        setBackground(new Color(255,0,0));
	        setUndecorated(true);
	        setLocationRelativeTo(null); //중앙정렬
	        setVisible(true);
	    }
	}
	public static void main(String[] args) {
		new MyFrame();
//		frame.add(task);
		
	}
	
}

