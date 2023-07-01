package pnl;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dbutil.DBUtil;
import dbutil.LoginMember;
import frame.MainFrame;
import tomatoPj.Member;
import tomatoPj.MemberRepository;
import tomatoPj.Project;
import utility.FontData;
import utility.IconData;
import utility.Utility;

public class LoginPnl extends JPanel {
	private Image image;
	private Utility utility;
	private IconData iconData;
	private JButton signUpBtn;
	private JButton loginButton;
	private FontData fontData;
	private Member member;
	private MemberRepository mr;
	private JTextField idField;
	private JPasswordField passwordField;
	private ImageIcon loginIcon;
	private ImageIcon loginDarkIcon;
	private ImageIcon loginbrightIcon;
	private ImageIcon signUpIcon;
	private ImageIcon signUpDarkIcon;
	private ImageIcon loginErrorIcon;
	private JLabel loginErrorLbl;

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
		iconData = new IconData();
		Image loginImg = iconData.getImageIcon("login_btn").getImage(); // 로그인 버튼 이미지 경로
		Image loginDarkImg = iconData.getImageIcon("login_btn(clicked)2").getImage(); // 어두운 버전의 로그인 버튼 이미지 경로
		Image loginBrightImg = iconData.getImageIcon("login_btn(enter)").getImage(); // 어두운 버전의 로그인 버튼 이미지 경로
		
		Image signUpImg = iconData.getImageIcon("signUp_btn").getImage();
		Image signUpDarkImg = iconData.getImageIcon("signUp_btn3").getImage();
		
		
		Image loginErrorImg = iconData.getImageIcon("loginError").getImage();
		
		idField = new JTextField("testid");
		passwordField = new JPasswordField("test1234");
		
		idField = new JTextField();
		passwordField = new JPasswordField();


		loginIcon = new ImageIcon(loginImg);
		loginDarkIcon = new ImageIcon(loginDarkImg);
		loginbrightIcon = new ImageIcon(loginBrightImg);
		signUpIcon = new ImageIcon(signUpImg);
		signUpDarkIcon = new ImageIcon(signUpDarkImg);

		loginErrorIcon = new ImageIcon(loginErrorImg);
		
		loginButton = new JButton(loginIcon);
		signUpBtn = new JButton(signUpIcon);
		loginErrorLbl = new JLabel(loginErrorIcon);
		

		idField.addKeyListener(enterKey());
		passwordField.addKeyListener(enterKey());

		loginButton.setBounds(896, 622, 126, 41);
		signUpBtn.setBounds(896, 663, 126, 41);
		idField.setBounds(870, 509, 255, 41);
		passwordField.setBounds(870, 570, 255, 41);


		idField.setFont(fontData.nanumFont(16));
		passwordField.setFont(fontData.nanumFont(16));

		utility.setButtonProperties(loginButton);
		utility.setButtonProperties(signUpBtn);

		signUpActionListener(mainFrame);
		loginActionListener(mainFrame);
		
		utility.setButtonProperties(idField);
		utility.setButtonProperties(passwordField);

		add(loginButton);
		add(signUpBtn);
		add(idField);
		add(passwordField);
		
		add(loginErrorLbl);
		
		addComponentListener(new ComponentListener() {

			@Override
			public void componentShown(ComponentEvent e) {
				idField.requestFocusInWindow();
				clearTextField();
			}

			@Override
			public void componentResized(ComponentEvent e) {
			}

			@Override
			public void componentMoved(ComponentEvent e) {
			}

			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});
		
		loginButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent me) {
				loginButton.setIcon(loginDarkIcon);
				loginButton.repaint();
			}
			public void mouseEntered(MouseEvent me) {
				System.out.println("마우스 들어감");
				loginButton.setIcon(loginbrightIcon);
				loginButton.repaint();
			}
			public void mouseExited(MouseEvent me) {
				loginButton.setIcon(loginIcon);
				loginButton.repaint();
			}
		});
		
		signUpBtn.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent me) {
				signUpBtn.setIcon(signUpDarkIcon);
				signUpBtn.repaint();
			}
			public void mouseEntered(MouseEvent me) {
				System.out.println("마우스 들어감");
				signUpBtn.setIcon(signUpDarkIcon);
				signUpBtn.repaint();
			}
			public void mouseExited(MouseEvent me) {
				signUpBtn.setIcon(signUpIcon);
				signUpBtn.repaint();
			}
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

						System.out.println("로그인한계정 가지고있는 프로젝트 목록");
						for(Project p : mainFrame.loginMember.getPjList()) {
							System.out.println(p);
						}
						mainFrame.showCard("projectSelect");
					} else {
						System.out.println("로그인실패");
						loginErrorLbl.setBounds(831, 724, 258, 50);
					}
					mainFrame.loginMember.setPjListSize(mainFrame.loginMember.getPjList().size());
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
