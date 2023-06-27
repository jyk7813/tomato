package frame;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import pnl.LoginPnl;
import pnl.ProjectSelectPnl;
import pnl.SignUpPnl;
import utility.IconData;

public class MainFrame extends JFrame {
	private IconData iconData;
	private JPanel signUpPnl;
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
		
		KeyboardFocusManager.getCurrentKeyboardFocusManager().clearFocusOwner();
		CardLayout c1 = (CardLayout)(getContentPane().getLayout());
		c1.show(getContentPane(), cardName);
		
	}

	public MainFrame() {
		iconData = new IconData();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 450, 300);
		setSize(1920,1080);
		setResizable(false);
		
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
		Image projectImage = iconData.projectBackGround().getImage();
		
		JPanel loginPnl = new LoginPnl(loginImage,this);
		JPanel signUpPnl = new SignUpPnl(signImage,this);
		JPanel projectPnl = new ProjectSelectPnl(projectImage, this);


		// frame의 타이틀 바를 숨깁니다.
		setUndecorated(true);

		getContentPane().setLayout(new CardLayout(0, 0));
		getContentPane().add(loginPnl,"login");
		getContentPane().add(signUpPnl,"signUp");
		getContentPane().add(projectPnl,"projectSelect");
		
		
	}

}
