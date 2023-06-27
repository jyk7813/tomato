package pnl;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dbutil.DBUtil;
import dbutil.LoginMember;
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

	private void setLoginMember(MainFrame mainFrame, Member member) {
		LocalDateTime now = LocalDateTime.now();
		try {
			mainFrame.loginMember = new LoginMember(member, member.getMember_no(), mr.returnMemberPj(member.getMember_no()), now);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public KeyListener enterKey() {
		return new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					loginButton.doClick();
				}
			}
		};
	}

	private void clearTextField() {
		idField.setText("");
		passwordField.setText("");
	}
	
	public LoginPnl(Image image, MainFrame mainFrame) {
		mr = new MemberRepository();
		this.image = image;
		fontData = new FontData();
		setLayout(null);
		
		utility = new Utility();
		loginButton = new JButton("로그인");
		signUpBtn = new JButton("회원가입");

		idField = new JTextField("testid");
		passwordField = new JPasswordField("test1234");
		idField.addKeyListener(enterKey());
		passwordField.addKeyListener(enterKey());

		loginButton.setBounds(897, 659, 126, 41);
		signUpBtn.setBounds(927, 709, 126, 41);

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

		addComponentListener(new ComponentListener() {

			@Override
			public void componentShown(ComponentEvent e) {
				idField.requestFocusInWindow();
				clearTextField();
			}
			@Override
			public void componentResized(ComponentEvent e) {}
			@Override
			public void componentMoved(ComponentEvent e) {}
			@Override
			public void componentHidden(ComponentEvent e) {}
		});
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
					if (member != null) {
						System.out.println("로그인성공");
						setLoginMember(mainFrame, member);
						//System.out.println(mainFrame.loginMember.getMember());
						System.out.println(mainFrame.loginMember.getPjList());
						mainFrame.showCard("projectSelect");
					} else {
						System.out.println("로그인실패");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();

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
