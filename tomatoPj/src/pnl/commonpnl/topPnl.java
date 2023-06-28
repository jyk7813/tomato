package pnl.commonpnl;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import frame.MainFrame;

import javax.swing.JButton;

public class topPnl extends JPanel {

	public topPnl(MainFrame mainFrame) {
		setOpaque(false);
		
		JButton btnNewButton = new JButton("New button");
		add(btnNewButton);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.showCard("task");
			}
		});
		
	}

	@Override
	public Dimension getPreferredSize() {
		
		return new Dimension(1920,135);
	}

}
