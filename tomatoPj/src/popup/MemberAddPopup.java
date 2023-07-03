package popup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import button.AddMemberBtn;
import frame.MainFrame;
import utility.IconData;
import utility.Utility;

public class MemberAddPopup extends JDialog {
	public JTextField idInsertTextField;
	public JButton addMemberBtn;
	MainFrame mainFrame;
	Utility utility;
	private IconData iconData = new IconData();
	private int num;

	public MemberAddPopup() {
		utility = new Utility();
		ImageIcon imageIcon = iconData.getImageIcon("addmember(BG)");

		JPanel panelWithBackground = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				// Draw the image on the panel
				g.drawImage(imageIcon.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		// Optional: set the layout manager of the panel
		panelWithBackground.setLayout(null);

		// Add any other components to the panelWithBackground
		panelWithBackground.setOpaque(false);
		// Add the panel to the JDialog
		this.getContentPane().add(panelWithBackground);
		
		idInsertTextField = new JTextField();
		idInsertTextField.setBounds(34, 57, 276, 41);
		utility.setButtonProperties(idInsertTextField);
		panelWithBackground.add(idInsertTextField);
		addMemberBtn = new AddMemberBtn(mainFrame);
		addMemberBtn.setBounds(110, 119, 126, 41);
		panelWithBackground.add(addMemberBtn);
		setSize(346, 174);
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0));
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		

		// WindowFocusListener를 추가합니다.
		this.addWindowFocusListener(new WindowFocusListener() {
			@Override
			public void windowGainedFocus(WindowEvent e) {
				// 팝업 창이 포커스를 얻었을 때의 동작을 정의합니다.
			}

			@Override
			public void windowLostFocus(WindowEvent e) {
				// 팝업 창이 포커스를 잃었을 때의 동작을 정의합니다.
				// 여기서는 팝업 창을 닫습니다.
				dispose();
			}
		});
	}

	
}
