package pnl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dbutil.DBUtil;
import frame.MainFrame;
import tomatoPj.MemberRepository;
import utility.FontData;
import utility.IconData;
import utility.Regex;
import utility.Utility;

public class SignUpPnl extends JPanel {
	private Image image;
	private FontData fontData;
	private MemberRepository mr;
	private JTextField idField;
	private Regex regex;
	private JButton btn;
	private JPasswordField passwordField;
	private JPasswordField checkPasswordField;
	private JTextField nameField;
	private JTextField emailField;
	private JButton backBtn;
	private Utility utility;
	private IconData iconData;
	private JLabel[] checkLbl;

	public SignUpPnl(Image image, MainFrame mainFrame) {
		regex = new Regex();
		mr = new MemberRepository();
		this.image = image;
		fontData = new FontData();
		utility = new Utility();
		iconData = new IconData();
		checkLbl = new JLabel[5];
		setLayout(null);

		idField = new JTextField("아이디");
		passwordField = new JPasswordField("비밀번호");
		checkPasswordField = new JPasswordField("비밀번호 확인");
		nameField = new JTextField("이름");
		emailField = new JTextField("이메일");
		btn = new JButton();
		btn.setBackground(Color.BLACK);
		backBtn = new JButton("뒤로갈래..");
		for (int i = 0; i < checkLbl.length; i++) {
			checkLbl[i] = new JLabel();
		}
		signUpActionListener(mainFrame);
		settingTextField();

		utility.setButtonProperties(idField);
		utility.setButtonProperties(passwordField);
		utility.setButtonProperties(checkPasswordField);
		utility.setButtonProperties(nameField);
		utility.setButtonProperties(emailField);

		btn.addMouseListener (new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent me) {
				btn.setIcon(iconData.getImageIcon("okbtnicondark"));
				btn.repaint();
			}

			public void mouseEntered(MouseEvent me) {
				System.out.println("마우스 들어감");
				btn.setIcon(iconData.getImageIcon("okbtniconbright"));
				btn.repaint();
			}

			public void mouseExited(MouseEvent me) {
				btn.setIcon(iconData.getImageIcon("okbtnicon"));
				btn.repaint();
			}
		});

		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.showCard("login");
				clearTextField();
			}
		});

		add(idField);
		add(passwordField);
		add(checkPasswordField);
		add(emailField);
		add(nameField);
		add(btn);
		add(backBtn);

		addComponentListener(new ComponentListener() {
			@Override
			public void componentShown(ComponentEvent e) {
				idField.requestFocusInWindow();
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
	}
	/*
	 * public JTextField getIdField() { return idField; }
	 * 
	 * public void setIdField(JTextField idField) { this.idField = idField; }
	 */

	public KeyListener enterKey() {
		return new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("발동");
					btn.doClick();
				}
			}
		};
	}

	private void clearTextField() {
		idField.setText("");
		passwordField.setText("");
		checkPasswordField.setText("");
		nameField.setText("");
		emailField.setText("");
	}

	private void settingTextField() {
		idField.addKeyListener(enterKey());
		passwordField.addKeyListener(enterKey());
		checkPasswordField.addKeyListener(enterKey());
		nameField.addKeyListener(enterKey());
		emailField.addKeyListener(enterKey());

		idField.setBounds(870, 363, 233, 41);
		passwordField.setBounds(870, 474, 233, 41);
		checkPasswordField.setBounds(870, 547, 233, 41);
		emailField.setBounds(870, 652, 233, 41);
		nameField.setBounds(870, 713, 233, 41);
		btn.setBounds(898, 826, 126, 41);
		backBtn.setBounds(761, 193, 399, 90);

		idField.setFont(fontData.nanumFont(16));
		passwordField.setFont(fontData.nanumFont(16));
		checkPasswordField.setFont(fontData.nanumFont(16));
		emailField.setFont(fontData.nanumFont(16));
		nameField.setFont(fontData.nanumFont(16));
		backBtn.setFont(fontData.nanumFont(16));

		utility.setButtonProperties(btn);
	}

	private void signUpActionListener(MainFrame mainFrame) {
		btn.addActionListener(new ActionListener() {
			Connection conn = null;

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (regex.regexPassword(regex.pwdToString(passwordField))
							&& regex.regexEmail(emailField.getText())) {
						if (regex.pwdToString(passwordField).equals(regex.pwdToString(checkPasswordField))) {
							conn = DBUtil.getConnection();
							if (mr.dupliIdCheck(conn, idField.getText())) {
								mr.signUp(conn, idField.getText(), regex.pwdToString(passwordField),
										emailField.getText(), nameField.getText(), null);
								System.out.println("회원가입성공");
								mainFrame.showCard("login");
								clearTextField();
							} else {
								System.out.println("아이디 중복");
							}
						} else {
							System.out.println("비밀번호 일치하지않음");
						}
					} else {
						System.out.println("이메일, 패스워드 regex통과불가");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}

//	public class ImagePanel extends JPanel {
//	    private Image image;
//
//	    public ImagePanel(Image image) {
//	        this.image = image;
//	    }
//
//	    @Override
//	    protected void paintComponent(Graphics g) {
//	        super.paintComponent(g);
//	        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
//	    }
//	}
}
