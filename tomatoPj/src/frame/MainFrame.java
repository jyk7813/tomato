package frame;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import pnl.LoginPnl;
import pnl.SignUpPnl;
import utility.IconData;

public class MainFrame extends JFrame {
	private IconData iconData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void showCard(String cardName) {
		CardLayout c1 = (CardLayout)(getContentPane().getLayout());
		c1.show(getContentPane(), cardName);
	}

	/**
	 * Create the frame.
	 */

	public MainFrame() {
		iconData = new IconData();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		KeyStroke escKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0,false);
		
		InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getRootPane().getActionMap();

        inputMap.put(escKeyStroke, "ESCAPE");
        
        actionMap.put("ESCAPE", new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
        
		Image loginImage = iconData.loginBackGround().getImage();
		Image signImage = iconData.signUpBackGround().getImage();
		Image projectImage = iconData.getImageIcon("selectProject(BG)").getImage();
		
		JPanel loginPnl = new LoginPnl(loginImage,this);
		JPanel signUpPnl = new SignUpPnl(signImage,this);
		
		
		
		
		// 현재 그래픽 환경을 얻습니다.
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		// 현재 그래픽 디바이스(일반적으로 모니터)를 얻습니다.
		GraphicsDevice gs = ge.getDefaultScreenDevice();

		// frame의 타이틀 바를 숨깁니다.
		setUndecorated(true);

		// 전체화면으로 설정합니다.
		gs.setFullScreenWindow(this);
		getContentPane().setLayout(new CardLayout(0, 0));
		getContentPane().add(loginPnl,"login");
		getContentPane().add(signUpPnl,"signUp");
		
		
	}

}
