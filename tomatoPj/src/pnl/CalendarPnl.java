package pnl;

import java.awt.Color;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pnl.Taskrefrom.MyFrame;
import tomatoPj.Task;
import utility.IconData;

public class CalendarPnl extends JPanel{
	IconData IC;
private JPanel cal2;

public CalendarPnl() {
	IC = new IconData();
	JLabel cal1Lbl = new JLabel(IC.getImageIcon("calendarLeft"));
	add(cal1Lbl);
	setSize(326,268);
	setOpaque(false);
	setLocation(290, 291);	
	setVisible(true);
}
public JPanel CalendarPnl2() {
	cal2 = new JPanel();
	JLabel cal2lbl = new JLabel(IC.getImageIcon("calendarRight"));
	cal2lbl.setOpaque(false);
	cal2.add(cal2lbl);
	cal2.setSize(326,268);
	cal2.setLocation(1305, 291);
	cal2.setOpaque(false);
	cal2.setVisible(true);
	return cal2;
}

public static class MyCalFrame extends JFrame {
	IconData IC;
    public MyCalFrame() {
    	CalendarPnl cal = new CalendarPnl();
    	IC = new IconData();
    	JLabel Background =new JLabel(IC.getImageIcon("selectTask(BG)"));
        Background.setSize(1920, 1080);
        Background.add(cal);
        Background.add(cal.CalendarPnl2());
        add(Background);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("My Frame");
        setSize(1920, 1080);
        setBackground(new Color(255,0,0));
        setLayout(null);
        setVisible(true);
    }
}
		public static void main(String[] args) {
			new MyCalFrame();
		//	frame.add(task);
			
		}

}

