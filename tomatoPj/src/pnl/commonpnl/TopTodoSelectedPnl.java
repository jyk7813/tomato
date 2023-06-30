package pnl.commonpnl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import button.CanBanBtn;
import button.LogoutBtn;
import button.TodoBtn;
import frame.MainFrame;

public class TopTodoSelectedPnl extends JPanel {
	
	public TopTodoSelectedPnl(MainFrame mainFrame,TopMainPnl topMainPnl) {
		setLayout(null);
		
		JButton todo = new JButton("todo");
		todo.setBounds(753, 56, 102, 60);
		add(todo);
		
		TodoBtn canBanBtn = new TodoBtn(mainFrame, topMainPnl);
		
		LogoutBtn logoutBtn = new LogoutBtn(mainFrame);
		logoutBtn.setBounds(1649, 33, 150, 70);
		add(logoutBtn);
		
	}
}
