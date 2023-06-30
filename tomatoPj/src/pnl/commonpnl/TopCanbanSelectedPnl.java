package pnl.commonpnl;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import button.CanBanBtn;
import button.LogoutBtn;
import button.TodoBtn;
import frame.MainFrame;
import utility.IconData;

public class TopCanbanSelectedPnl extends JPanel {
	private Image backgroundImage;
	private IconData iconData;

	public TopCanbanSelectedPnl(MainFrame mainFrame, TopMainPnl topMainPnl) {
		iconData = new IconData();
		setLayout(null);

		TodoBtn todo = new TodoBtn(mainFrame, topMainPnl);
		todo.setBounds(1025, 38, 102, 60);
		add(todo);

		LogoutBtn logoutBtn = new LogoutBtn(mainFrame);
		logoutBtn.setBounds(1649, 33, 150, 70);
		add(logoutBtn);
		backgroundImage = iconData.getImageIcon("canbanselectedre").getImage();
		setOpaque(false);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Draw the background image
		g.drawImage(backgroundImage, 0, 0, this);
	}
}
