package pnl.commonpnl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import button.CanBanBtn;
import button.LogoutBtn;
import button.TodoBtn;
import frame.MainFrame;

public class TopCanbanSelectedPnl extends JPanel {
	
	public TopCanbanSelectedPnl(MainFrame mainFrame, TopMainPnl topMainPnl) {
		setLayout(null);
		
		TodoBtn todo = new TodoBtn(mainFrame, topMainPnl);
		todo.setBounds(753, 56, 102, 60);
		add(todo);
		
		
		LogoutBtn logoutBtn = new LogoutBtn(mainFrame);
		logoutBtn.setBounds(1649, 33, 150, 70);
		add(logoutBtn);
		
	}
}
