package pnl;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import frame.MainFrame;
import utility.FontData;
import utility.Utility;

public class LoginPnl extends JPanel {
	private Image image;
	private Utility utility;
	private JButton signUpBtn;
	private JButton loginButton;
	private FontData fontData;
	public LoginPnl(Image image, MainFrame mainFrame) {
		this.image = image;
		fontData = new FontData();
		setLayout(null);

		utility = new Utility();

		loginButton = new JButton();
		signUpBtn = new JButton();

		JTextField idField = new JTextField();
		JPasswordField passwordField = new JPasswordField();
		loginButton.setBounds(897, 659, 126, 41);
		signUpBtn.setBounds(927, 709, 66, 18);

		idField.setBounds(842, 536, 255, 41);
		passwordField.setBounds(842, 597, 255, 41);
		
		idField.setFont(fontData.nanumFont(16));
		passwordField.setFont(fontData.nanumFont(16));
		
		
		signUpActionListener(mainFrame);
		
		loginActionListener();

		utility.setButtonProperties(idField);
		utility.setButtonProperties(passwordField);

		add(loginButton);
		add(signUpBtn);
		add(idField);
		add(passwordField);

	}
	
	

	private void signUpActionListener(MainFrame mainFrame) {
		signUpBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.showCard("signUp");
			}
		});
	}

	private void loginActionListener() {
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
}
