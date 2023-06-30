package pnl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utility.FontData;
import utility.IconData;

public class CalendarPnl2 extends JPanel {
	IconData IC;
	FontData FD;
	 JPanel cal2;
	 int monthInt;
	 JPanel dayOfMonth;
	 JLabel dayJLabel;
	Calendar calendar;
	JLabel [] dayJLabels;
	int dayOfWeek;
	int dayOfWeek2;
	int daysInMonth;
	int Update;
	int deadLine;
	Taskrefrom tr;
	MouseListener ma;
	MouseListener  beforeMouse;
	JLabel before;
	JLabel month;
	public CalendarPnl2(Taskrefrom tr) {
		this.tr = tr;
		IC = new IconData();
		FD = new FontData();
		JLabel cal2Lbl = new JLabel(IC.getImageIcon("calendarRight"));
		cal2Lbl.setSize(326, 268);
		cal2Lbl.setLocation(605, 293);

		monthInt = 6;
		month = new JLabel(monthInt + "월");

		
		
//	cal2lbl.setOpaque(false);
		month.setFont(FD.nanumFontBold(20));
		month.setForeground(new Color(235, 105, 97));
		month.setSize(50, 30);
		month.setLocation(160, 30);

		before = new JLabel(IC.getImageIcon("calendarRed_back"));
		before.setSize(18, 18);
		before.setLocation(125, 35);
		beforeMouse = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				monthInt--;

				if (monthInt < 1) {
					monthInt = 12;
				}
				month.setText(monthInt + "월");
				showPreviousMonth(monthInt);
			}
		};

		before.addMouseListener(beforeMouse);

		JLabel after = new JLabel(IC.getImageIcon("calendarRed_next"));
		after.setSize(18, 18);
		after.setLocation(205, 35);
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
		weekPnl.setLocation(65, 70);

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
			week[0].setForeground(new Color(235, 105, 97));
			week[0].setLocation(week[0].getX(), 0);
			// 일정 거리씩 띄우기
			if (i > 0) {
				week[i].setLocation(week[i - 1].getX() + 35, 0);
				week[i].setFont(FD.nanumFontBold(18));
				week[i].setForeground(new Color(235, 105, 97));	
				
			}
		}
		settingCal();
		
		dayOfMonth.setOpaque(false);
		cal2Lbl.add(month);
		cal2Lbl.add(before);
		cal2Lbl.add(after);
		cal2Lbl.add(weekPnl);
		cal2Lbl.add(dayOfMonth);
		add(cal2Lbl);
		setSize(326, 268);
		setLocation(1010, -2);
		setBackground(new Color(255,0,0));
		setOpaque(false);
		setVisible(true);
	}


	public void showMonth(int monthInt) {
			dayOfMonth.removeAll();
	        dayJLabels = new JLabel[42]; // dayJLabels 배열 재생성

	    for (int i = 0; i < 42; i++) {
	            JLabel dayJLabel = new JLabel();
	            dayJLabels[i] = dayJLabel;
	            dayJLabels[i].setFont(FD.nanumFont(16));
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
			 
			        String updateDateString = tr.StartDate.getText();
			        String deadLineString = 2023+"."+setMonth+"."+labelText;
			        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

			        try {
						Date updateDatetoTimestamp = dateFormat.parse(updateDateString);
						Date deadLinetoTimestamp = dateFormat.parse(deadLineString);
						
						 int result = updateDatetoTimestamp.compareTo(deadLinetoTimestamp);
						 if(result==-1) {
	
							 tr.deadLineDate.setText(deadLineString);        		    
	            		    }else {
	            		 
	            		    }
			        } catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					
			    }
			    }
			    }
			});
		}
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
		ma = new MouseAdapter() {
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
		       
		       
		        String updateDateString = tr.StartDate.getText();
		        String deadLineString = 2023+"."+setMonth+"."+labelText;
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");

		        try {
					Date updateDatetoTimestamp = dateFormat.parse(updateDateString);
					Date deadLinetoTimestamp = dateFormat.parse(deadLineString);
					
					 int result = updateDatetoTimestamp.compareTo(deadLinetoTimestamp);
					 if(result==-1) {
						 tr.deadLineDate.setText(deadLineString);        		    
            		    }else {
            		    	
            		    }
		        } catch (ParseException e1) {

					e1.printStackTrace();
				
		    }
		    }else{
		    	System.out.println("널임");
		    	}	
		    }
		};
		for(int i = 0; i <dayJLabels.length; i++) {
			dayJLabels[i].addMouseListener(ma);
		}
		dayOfMonth.setSize(250, 160);
		dayOfMonth.setLocation(65,100);
	
	}


}
