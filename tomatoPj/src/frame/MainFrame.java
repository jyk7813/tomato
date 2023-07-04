package frame;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import dbutil.LoginMember;
import dbutil.SelectProjectInfo;
import pnl.BoradPnl;
import pnl.LoginPnl;
import pnl.ProjectSelectPnl;
import pnl.SignUpPnl;
import pnl.TaskBackgroundPnl;
import pnl.Taskrefrom;
import pnl.TestTodoPnl;
import tomatoPj.Column;
import tomatoPj.Feedback;
import tomatoPj.Member;
import tomatoPj.MemberRepository;
import tomatoPj.Task;
import utility.IconData;

public class MainFrame extends JFrame {
	private IconData iconData;
	public LoginMember loginMember;
	public SelectProjectInfo pjInfo;
	public SelectProjectInfo tempInfo;
	private Image loginImage;
	private Image signImage;
	private Image projectImage;
	private Image boradImage;
	private Image taskImage;
	public boolean columnActive = false;
	public TaskBackgroundPnl TBP;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	public String selectedProjectTitle;
	public ProjectSelectPnl projectPnl;
	
	public MemberRepository mr = new MemberRepository();

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
		CardLayout c1 = (CardLayout) (getContentPane().getLayout());
		c1.show(getContentPane(), cardName);

	}

	public MainFrame() {
		pjInfo = new SelectProjectInfo();
		loginMember = new LoginMember();
		iconData = new IconData();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 450, 300);
		setSize(1920, 1080);
		setResizable(false);

		KeyStroke escKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);

		InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = getRootPane().getActionMap();

		inputMap.put(escKeyStroke, "ESCAPE");

		actionMap.put("ESCAPE", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// frame의 타이틀 바를 숨깁니다.
		setUndecorated(true);

		loginImage = iconData.getImageIcon("login(BG)remake3").getImage();
		signImage = iconData.getImageIcon("signup(BG)remake2").getImage();
		projectImage = iconData.getImageIcon("projectSelect(BG)remake").getImage();
		boradImage = iconData.getImageIcon("Background").getImage();
		taskImage = iconData.getImageIcon("selectTask(BG)").getImage();
//		Image todoImage = iconData.getImageIcon("Background").getImage();
		addPnl();

	}

	public void addPnl() {
		TBP = new TaskBackgroundPnl(taskImage, this);

		JPanel loginPnl = new LoginPnl(loginImage, this);
		JPanel signUpPnl = new SignUpPnl(signImage, this);
		projectPnl = new ProjectSelectPnl(projectImage, this);
		JPanel boradPnl = new BoradPnl(boradImage, this);
		JPanel taskPnl = TBP;
		JPanel todoPnl = new TestTodoPnl(this);

		getContentPane().setLayout(new CardLayout(0, 0));
		getContentPane().add(loginPnl, "login");
		getContentPane().add(signUpPnl, "signUp");
		getContentPane().add(projectPnl, "projectSelect");
		getContentPane().add(boradPnl, "columnSelect");
		getContentPane().add(taskPnl, "task");
		getContentPane().add(todoPnl, "todo");

	}

	public void setTask(Task task, Column column) {
		Taskrefrom myUpPnl = TBP.taskrefrom;
		System.out.println("메인에서 확인한다 대답.");
		TBP.taskrefrom.settingTask(myUpPnl, task, column);
	}

	public boolean isPanelVisible(JPanel panel) {
		// 현재 보여지는 카드 패널 가져오기
		Component currentPanel = this.getComponent(0);

		// 현재 보여지는 카드 패널과 인자로 전달된 패널이 동일한지 확인
		return currentPanel == panel;
	}

	public String getSelectedProjectTitle() {
		return selectedProjectTitle;
	}

	public void setSelectedProjectTitle(String newValue) {
		String oldValue = this.selectedProjectTitle;
		this.selectedProjectTitle = newValue;
		pcs.firePropertyChange("selectedProjectTitle", oldValue, newValue);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
}
