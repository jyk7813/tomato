package pnl;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import frame.MainFrame;

public class LoginPnl extends JPanel{
	private Image image;
	
	public LoginPnl(Image image, MainFrame mainFrame) {
		this.image = image;
		setLayout(null);
		
		JButton loginButton = new JButton();
		JButton signUpBtn = new JButton();
		loginButton.setBounds(897, 659, 126, 41);
		signUpBtn.setBounds(927, 709, 66, 18);
		signUpBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.showCard("signUp");
			}
		});
		add(signUpBtn);
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
}
