package pnl.commonpnl;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import button.LogoutBtn;
import frame.MainFrame;

import javax.swing.JButton;

public class topPnl extends JPanel {

	public topPnl(MainFrame mainFrame) {
		setOpaque(false);
		setLayout(null);
		
		JButton todo = new JButton("todo");
		todo.setBounds(753, 56, 102, 60);
		add(todo);
		
		LogoutBtn logoutBtn = new LogoutBtn(mainFrame);
		logoutBtn.setBounds(1649, 33, 150, 70);
		add(logoutBtn);
		
		todo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.showCard("todo");
			}
		});
	}

	@Override
	public Dimension getPreferredSize() {
		
		return new Dimension(1920,135);
	}
}
