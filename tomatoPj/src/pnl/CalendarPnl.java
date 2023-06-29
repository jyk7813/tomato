package pnl;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;

import utility.FontData;
import utility.IconData;

public class CalendarPnl extends JPanel {
	IconData IC;
	FontData FD;
	 int monthInt;
	 JPanel dayOfMonth;
	 JLabel dayJLabel;
	private Calendar calendar;
	private JLabel [] dayJLabels;
	private int dayOfWeek;
	private int dayOfWeek2;
	private int daysInMonth;
	Taskrefrom tr;
	CalendarPnl2 cal2;
	private int settinJLbl;
	public CalendarPnl(Taskrefrom tr,CalendarPnl2 cal2) {
		 this.tr = tr;
		 this.cal2 =cal2;
		IC = new IconData();
		FD = new FontData();
		JLabel cal1Lbl = new JLabel(IC.getImageIcon("calendarLeft"));
		cal1Lbl.setSize(326, 268);
		cal1Lbl.setLocation(0, 0);
		cal1Lbl.setOpaque(false);
		monthInt = 6;
		JLabel month = new JLabel(monthInt + "월");

		month.setFont(FD.nanumFontBold(20));
		month.setForeground(new Color(36, 161, 138));
		month.setSize(50, 30);
		month.setLocation(135, 30);

		JLabel before = new JLabel("전달");
		before.setSize(50, 50);
		before.setLocation(95, 20);
		before.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				monthInt--;

				if (monthInt < 1) {
					monthInt = 12;
				}
				month.setText(monthInt + "월");
				showPreviousMonth(monthInt);
			}
		});

		JLabel after = new JLabel("다음달");
		after.setSize(50, 50);
		after.setLocation(190, 20);
		after.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				monthInt++;

				if (monthInt > 12) {
					monthInt = 1;
				}
				month.setText(monthInt + "월");
				showNextMonth(monthInt);
			}
		});

		JPanel weekPnl = new JPanel();
		weekPnl.setOpaque(false);
		weekPnl.setLayout(null);
		weekPnl.setSize(241, 20);
		weekPnl.setLocation(30, 70);

		JLabel[] week = new JLabel[7];
		String Strweek = "";
		for (int i = 0; i < week.length; i++) {
			if (i == 0) {
				Strweek = "일";
			} else if (i == 1) {
				Strweek = "월";
			} else if (i == 2) {
				Strweek = "화";
			} else if (i == 3) {
				Strweek = "수";
			} else if (i == 4) {
				Strweek = "목";
			} else if (i == 5) {
				Strweek = "금";
			} else if (i == 6) {
				Strweek = "토";
			}
			JLabel Stringweek = new JLabel(Strweek);
			week[i] = Stringweek;

			week[i].setSize(18, 18);

			weekPnl.add(week[i]);
			week[0].setFont(FD.nanumFontBold(18));
			week[0].setForeground(new Color(36, 161, 138));	
			week[0].setLocation(week[0].getX(), 0);
			// 일정 거리씩 띄우기
			if (i > 0) {
				week[i].setLocation(week[i - 1].getX() + 35, 0);
				week[i].setFont(FD.nanumFontBold(18));
				week[i].setForeground(new Color(36, 161, 138));		
				
			}
		}
		settingCal();
		dayOfMonth.setOpaque(false);
		
		cal1Lbl.add(month);
		cal1Lbl.add(before);
		cal1Lbl.add(after);
		cal1Lbl.add(weekPnl);
		cal1Lbl.add(dayOfMonth);
		add(cal1Lbl);

		setSize(326, 268);
		setLocation(0, 0);
		setOpaque(false);
		setLayout(null);
		setVisible(true);
	}


	public void showMonth(int monthInt) {
			dayOfMonth.removeAll();
	        dayJLabels = new JLabel[42]; // dayJLabels 배열 재생성

	    for (int i = 0; i < 42; i++) {
	            JLabel dayJLabel = new JLabel();
	            dayJLabels[i] = dayJLabel;
//	            dayJLabels[i].setFont(FD.nanumFont(20));
	            dayOfMonth.add(dayJLabels[i]);
	        }
	    for (JLabel label : dayJLabels) {
	        label.setText(null);
	        dayOfMonth.add(label);
	    }

	    Calendar calendar = Calendar.getInstance();
	    calendar.set(2023, monthInt - 1, 1);

	    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
	    int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

	    int index = dayOfWeek - 1;
	    for (int i = 1; i <= daysInMonth; i++) {
	        dayJLabels[index].setText(String.valueOf(i));
            dayJLabels[index].setFont(FD.nanumFont(20));
	        index++;
	    }
	    for(int i = 0; i <dayJLabels.length; i++) {
	    	
			dayJLabels[i].addMouseListener(new MouseAdapter() {
			    @Override
			    public void mousePressed(MouseEvent e) {
			    	JLabel clickedLabel = (JLabel) e.getSource();
			        String labelText = clickedLabel.getText();
			        
			        String setMonth = String.valueOf(monthInt);
			        if(labelText != null) {
			        if(monthInt <10) {
			        	setMonth = "0"+monthInt;
			        }else {
			        }
			        if(Integer.valueOf(labelText)<10) {
			        	labelText = "0"+clickedLabel.getText();
			        }
			        tr.StartDate.setText(2023+"."+setMonth+"."+labelText);
			    }else{
			    	System.out.println("널임");
			    }
			    }
			});
			}
	    dayOfMonth.setSize(250, 160);
		dayOfMonth.setLocation(65,100);
	}
	   public void showPreviousMonth(int monthInt) {
	        showMonth(monthInt);
	    }
	   public void showNextMonth(int monthInt) {
		   showMonth(monthInt);
	   }
	public void settingCal() {
		calendar = Calendar.getInstance();
		dayJLabels = new JLabel[42];
		dayOfMonth = new JPanel(new GridLayout(0,7));
		dayOfMonth.setOpaque(false);
		
		dayOfMonth.setBackground(new Color(255,0,0));
		calendar.set(2023, monthInt - 1, 1);
		dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		
		dayOfWeek2 = calendar.get(Calendar.DAY_OF_WEEK);
		
		daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		for (int i = 0; i < 42; i++) {
			dayJLabel = new JLabel();
			dayJLabels[i] =  dayJLabel;
			dayJLabels[i].setFont(FD.nanumFont(16));

			dayOfMonth.add(dayJLabels[i]);
			
		}
		
		for(int i=1; i<=daysInMonth; i++) {
			if(i==1) {
				for(int j=1; j<dayOfWeek; j++) {
					dayJLabels[j].setText(null);
				}
			if(i==daysInMonth+dayOfWeek) {
				for(int p =daysInMonth+dayOfWeek; p<dayJLabels.length;p++) {
					dayJLabels[p].setText(null);
				}
			}
			}		
		}
		for(int i = 1; i<=daysInMonth; i++) {
			if(dayOfWeek2<=daysInMonth+dayOfWeek2) {
				
			dayJLabels[dayOfWeek2-1].setText(""+i);
			dayOfWeek2++;
			}			
		}
		for(int i = 0; i <dayJLabels.length; i++) {
	
		dayJLabels[i].addMouseListener(new MouseAdapter() {
		    @Override
		    public void mousePressed(MouseEvent e) {
		    	JLabel clickedLabel = (JLabel) e.getSource();
		        String labelText = clickedLabel.getText();
		        
		        String setMonth = String.valueOf(monthInt);
		        if(labelText != null) {
		        if(monthInt <10) {
		        	setMonth = "0"+monthInt;
		        }else {
		        }
		        if(Integer.valueOf(labelText)<10) {
		        	labelText = "0"+clickedLabel.getText();
		        }
		        tr.StartDate.setText(2023+"."+setMonth+"."+labelText);
		        tr.deadLineDate.setText(2023+"."+setMonth+"."+labelText);
		        
		    }
		    }
		});
		}
		dayOfMonth.setSize(250, 160);
		dayOfMonth.setLocation(30,100);
	}

}
