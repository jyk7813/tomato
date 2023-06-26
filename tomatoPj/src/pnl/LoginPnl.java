package pnl;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dbutil.DBUtil;
import frame.MainFrame;
import tomatoPj.Member;
import tomatoPj.MemberRepository;
import utility.FontData;
import utility.Utility;

public class LoginPnl extends JPanel {
	private Image image;
	private Utility utility;
	private JButton signUpBtn;
	private JButton loginButton;
	private FontData fontData;
	private Member member;
	private MemberRepository mr;
	private JTextField idField;
	private JPasswordField passwordField;
	
	public LoginPnl(Image image, MainFrame mainFrame) {
		mr = new MemberRepository();
		this.image = image;
		fontData = new FontData();
		setLayout(null);

		utility = new Utility();
		loginButton = new JButton();
		signUpBtn = new JButton();

		idField = new JTextField();
		passwordField = new JPasswordField();

		loginButton.setBounds(897, 659, 126, 41);
		signUpBtn.setBounds(927, 709, 66, 18);

		idField.setBounds(842, 536, 255, 41);
		passwordField.setBounds(842, 597, 255, 41);
		
		idField.setFont(fontData.nanumFont(16));
		passwordField.setFont(fontData.nanumFont(16));
		
		
		signUpActionListener(mainFrame);
		
		loginActionListener(mainFrame);

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

	private void loginActionListener(MainFrame mainFrame) {
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Connection conn = null;
				try {
					conn = DBUtil.getConnection();
					member = mr.logIn(conn, idField.getText(), passwordField.getText());
					System.out.println("로그인성공");
					mainFrame.showCard("projectSelect");
				} catch (SQLException e1) {
					e1.printStackTrace();
					System.out.println("로그인실패");
				} finally {
					DBUtil.close(conn);
				}
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
}
