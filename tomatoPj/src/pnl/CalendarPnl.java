package pnl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utility.FontData;
import utility.IconData;

public class CalendarPnl extends JPanel {
	IconData IC;
	FontData FD;
	 JPanel cal2;
	 int monthInt;
	 JPanel dayOfMonth;
	 JLabel dayJLabel;
	private Calendar calendar;
	private JLabel [] dayJLabels;
	private int dayOfWeek;
	private int dayOfWeek2;
	private int daysInMonth;

	public CalendarPnl(Taskrefrom tr) {
		System.out.println(1);
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
				calendar.set(2023, monthInt - 1, 1);
				dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
				dayOfWeek2 = calendar.get(Calendar.DAY_OF_WEEK);
				daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				for(int a = 0; a<dayJLabels.length;a++) {
					dayJLabels[a].setText(null);
				}
				for(int i=1; i<=daysInMonth; i++) {
					
					if(i==1) {
						for(int j=1; j<dayOfWeek; j++) {
							System.out.println(dayOfWeek);
							dayJLabels[j].setText(null);
						}
					}		
				}
				for(int i = 1; i<=daysInMonth; i++) {
					if(i<10) {
						dayJLabels[dayOfWeek2].setText(" "+i);
						dayOfWeek2++;
					}
					else if(dayOfWeek2<=daysInMonth+dayOfWeek2) {
						
						dayJLabels[dayOfWeek2-1].setText(""+i);
						dayOfWeek2++;

					}			
				}		
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

	public JPanel CalendarPnl2() {
		cal2 = new JPanel();
		JLabel cal2lbl = new JLabel(IC.getImageIcon("calendarRight"));
//	cal2lbl.setOpaque(false);
		cal2.add(cal2lbl);
		cal2.setSize(326, 268);
		cal2.setLocation(1010, -2);
//		cal2.setBackground(new Color(255,0,0));
		cal2.setOpaque(false);
		cal2.setVisible(true);
		return cal2;
	}


	public void settingCal() {
		calendar = Calendar.getInstance();
		dayJLabels = new JLabel[35];
		dayOfMonth = new JPanel(new GridLayout(0,7));
//		dayOfMonth.setOpaque(false);
		
		dayOfMonth.setBackground(new Color(255,0,0));
		calendar.set(2023, monthInt - 1, 1);
		dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		
		dayOfWeek2 = calendar.get(Calendar.DAY_OF_WEEK);
		
		daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		for (int i = 0; i < 35; i++) {
			dayJLabel = new JLabel();
			
			dayJLabels[i] =  dayJLabel;
			dayJLabels[i].setPreferredSize(new Dimension(15,15));
			dayJLabels[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					
				}
			});
			dayOfMonth.add(dayJLabels[i]);
			
		}
		
		for(int i=1; i<=daysInMonth; i++) {
			if(i==1) {
				for(int j=1; j<dayOfWeek; j++) {
					dayJLabels[j].setText(null);
				}
			}		
		}
		for(int i = 1; i<=daysInMonth; i++) {
			if(dayOfWeek2<=daysInMonth+dayOfWeek2) {
				
			dayJLabels[dayOfWeek2-1].setText(""+i);
			dayOfWeek2++;
			}			
		}

		dayOfMonth.setSize(250, 160);
		dayOfMonth.setLocation(30,100);
	
	}


}
