package pnl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import button.AddPictureBtn;
import button.ExitBtn;
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
	private Regex regex;
	private JButton btn;
	private MyTextField idField;
	private MyPwdField passwordField;
	private MyPwdField checkPasswordField;
	private MyTextField nameField;
	private MyTextField emailField;
	private JButton backBtn;
	private Utility utility;
	private IconData iconData;
	private JLabel[] checkLbl;
	private AddPictureBtn addPictureBtn;
	public JLabel testLbl;
	private ImageIcon possible;
	private ImageIcon impossible;
	
	private JLabel idcheckLbl;
	private JLabel pwdcheckLbl;
	private JLabel pwd2checkLbl;
	private JLabel emailcheckLbl;
	private JLabel namecheckLbl;
	
	private boolean idRegex=false;
	private boolean pwdRegex=false;
	private boolean pwdDuRegex=false;
	private boolean emailRegex=false;
	private boolean nameRegex=false;
	
	private ExitBtn exitBtn;
	
	public SignUpPnl(Image image, MainFrame mainFrame) {
		exitBtn = new ExitBtn(mainFrame);
		
		idcheckLbl = new JLabel();
		pwdcheckLbl = new JLabel();
		pwd2checkLbl = new JLabel();
		emailcheckLbl = new JLabel();
		namecheckLbl = new JLabel();
		
		exitBtn.setBounds(1649, 33, 150, 70);
		
		regex = new Regex();
		mr = new MemberRepository();
		this.image = image;
		fontData = new FontData();
		utility = new Utility();
		iconData = new IconData();
		checkLbl = new JLabel[5];
		setLayout(null);

		possible = iconData.getImageIcon("project_check");
		impossible = iconData.getImageIcon("delete_btn");
		btn = new JButton();
		idField = new MyTextField("아이디", btn);
		passwordField = new MyPwdField("비밀번호", btn);
		checkPasswordField = new MyPwdField("비밀번호확인", btn);
		nameField = new MyTextField("이름", btn);
		emailField = new MyTextField("이메일", btn);
		
		btn.setBackground(Color.BLACK);
		backBtn = new JButton(iconData.getImageIcon("login_logo"));
		addPictureBtn = new AddPictureBtn(this);
		testLbl = new JLabel();
		
		for (int i = 0; i < checkLbl.length; i++) {
			checkLbl[i] = new JLabel();
		} 
		addPictureBtn.setBounds(937, 773, 199, 41);
		
		
		testLbl = new JLabel();
		testLbl.setBounds(860, 763, 60, 60);
		add(testLbl);
		
		signUpActionListener(mainFrame);
		settingTextField();

		utility.setButtonProperties(idField);
		utility.setButtonProperties(passwordField);
		utility.setButtonProperties(checkPasswordField);
		utility.setButtonProperties(nameField);
		utility.setButtonProperties(emailField);
		utility.setButtonProperties(backBtn);
		
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
		
		backBtn.addMouseListener (new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent me) {
				backBtn.setIcon(iconData.getImageIcon("login_logo"));
				backBtn.repaint();
			}

			public void mouseEntered(MouseEvent me) {
				System.out.println("마우스 들어감");
				backBtn.setIcon(iconData.getImageIcon("login_logo"));
				backBtn.repaint();
			}

			public void mouseExited(MouseEvent me) {
				backBtn.setIcon(iconData.getImageIcon("login_logo"));
				backBtn.repaint();
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
		add(addPictureBtn);
		add(exitBtn);
		add(idcheckLbl);
		add(pwdcheckLbl);
		add(pwd2checkLbl);
		add(emailcheckLbl);
		add(namecheckLbl);
		addComponentListener(new ComponentAdapter() {
		
			@Override
			public void componentShown(ComponentEvent e) {
				idField.requestFocusInWindow();

			}

		});
		idField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            if(regex.regexId(idField.getText())) {
                				idcheckLbl.setIcon(possible);
                				idRegex = true;
                			} else {
                				idcheckLbl.setIcon(impossible);
                				idRegex = false;
                			}
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
		
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				 Thread thread = new Thread() {
	                    @Override
	                    public void run() {
	                        try {
	                            Thread.sleep(1000);
	                            if(regex.regexId(idField.getText())) {
	                				idcheckLbl.setIcon(possible);
	                				idRegex = true;
	                			} else {
	                				idcheckLbl.setIcon(impossible);
	                				idRegex = false;
	                			}
	                        } catch (InterruptedException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                };
	                thread.start();
			
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
		
			}
		});
		passwordField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				 Thread thread = new Thread() {
	                    @Override
	                    public void run() {
	                        try {
	                            Thread.sleep(1000);
	                            if (regex.regexPassword(regex.pwdToString(passwordField))) {
	                            	pwdcheckLbl.setIcon(possible);
	                            	pwdRegex = true;
	                            } else {
	                            	pwdcheckLbl.setIcon(impossible);
	                            	pwdRegex = false;
	                            }
	                        } catch (InterruptedException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                };
	                thread.start();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				 Thread thread = new Thread() {
	                    @Override
	                    public void run() {
	                        try {
	                            Thread.sleep(1000);
	                            if (regex.regexPassword(regex.pwdToString(passwordField))) {
	                            	pwdcheckLbl.setIcon(possible);
	                            	pwdRegex = true;
	                            } else {
	                            	pwdcheckLbl.setIcon(impossible);
	                            	pwdRegex = false;
	                            }
	                        } catch (InterruptedException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                };
	                thread.start();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				
			}
		});
		checkPasswordField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            if(regex.pwdToString(passwordField).equals((regex.pwdToString(checkPasswordField)))) {
                            	pwd2checkLbl.setIcon(possible);
                            	pwdDuRegex = true;
                            } else {
                            	pwd2checkLbl.setIcon(impossible);
                            	pwdDuRegex = false;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            if(regex.pwdToString(passwordField).equals((regex.pwdToString(checkPasswordField)))) {
                            	pwd2checkLbl.setIcon(possible);
                            	pwdDuRegex = true;
                            } else {
                            	pwd2checkLbl.setIcon(impossible);
                            	pwdDuRegex = false;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});
		emailField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            if(regex.regexEmail(emailField.getText())) {
                            	emailcheckLbl.setIcon(possible);
                            	emailRegex = true;
                            } else {
                            	emailcheckLbl.setIcon(impossible);
                            	emailRegex = false;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            if(regex.regexEmail(emailField.getText())) {
                            	emailcheckLbl.setIcon(possible);
                            	emailRegex = true;
                            } else {
                            	emailcheckLbl.setIcon(impossible);
                            	emailRegex = false;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});
		nameField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            if(regex.regexName(nameField.getText())) {
                            	namecheckLbl.setIcon(possible);
                            	nameRegex = true;
                            } else {
                            	namecheckLbl.setIcon(impossible);
                            	nameRegex = false;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            if(regex.regexName(nameField.getText())) {
                            	namecheckLbl.setIcon(possible);
                            	nameRegex = true;
                            } else {
                            	namecheckLbl.setIcon(impossible);
                            	nameRegex = false;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
				
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});
	}
	
	public KeyListener enterKey() {
		return new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
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
		
		idcheckLbl.setIcon(possible);
		pwdcheckLbl.setIcon(possible);
		pwd2checkLbl.setIcon(possible);
		emailcheckLbl.setIcon(possible);
		namecheckLbl.setIcon(possible);
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

		idcheckLbl.setBounds(1100, 360, 50, 50);
		pwdcheckLbl.setBounds(1100, 471, 50, 50);
		pwd2checkLbl.setBounds(1100, 544, 50, 50);
		emailcheckLbl.setBounds(1100, 649, 50, 50);
		namecheckLbl.setBounds(1100, 710, 50, 50);
		
		
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
					conn = DBUtil.getConnection();
					if (idRegex==true && pwdRegex==true && pwdDuRegex==true && emailRegex==true && nameRegex==true) {
						if (mr.dupliIdCheck(conn, idField.getText())) {
							mr.signUp(conn, idField.getText(), regex.pwdToString(passwordField),
									emailField.getText(), nameField.getText(), null, getImageBytesFromLabel(testLbl));
							System.out.println("회원가입성공");
							mainFrame.showCard("login");
							clearTextField();
						} else {
							System.out.println("아이디 중복");
						}
					} else {
						System.out.println("전부 true가 아님");
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

	public byte[] getImageBytesFromLabel(JLabel label) {
	    byte[] imageBytes = null;
	    try {
	        // Get the icon from the label
	        Icon icon = label.getIcon();
	        
	        // Convert the icon to a BufferedImage
	        BufferedImage bi = new BufferedImage(
	            icon.getIconWidth(),
	            icon.getIconHeight(),
	            BufferedImage.TYPE_INT_RGB);
	        Graphics g = bi.createGraphics();
	        icon.paintIcon(null, g, 0, 0);
	        g.dispose();
	        
	        // Convert the BufferedImage to a byte array
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(bi, "png", baos);  // Change "png" to the format of your image
	        imageBytes = baos.toByteArray();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (NullPointerException e2) {
	    	imageBytes = null;
	    }
	    
	    return imageBytes;
	}

}
