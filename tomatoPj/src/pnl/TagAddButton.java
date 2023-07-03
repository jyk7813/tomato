package pnl;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import button.AddMemberBtn;
import frame.MainFrame;
import utility.IconData;
import utility.Utility;

public class TagAddButton extends JDialog {
	JTextField idInsertTextField;
	JButton addMemberBtn;
	MainFrame mainFrame;
	Utility utility;
	int CountTag;
	private IconData iconData = new IconData();
	

	public TagAddButton(MainFrame mainFrame,JLabel plus,JPanel tagpnl,Taskrefrom ts) {
		
		IconData IC = new IconData();
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
		addMemberBtn.addActionListener(new ActionListener() {
			
			int tagX =0;
			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel tag = new JLabel(IC.getImageIcon("tag"));
			    tag.setSize(80, 30);
			    tag.setLocation(plus.getX() + tagX, plus.getY());
			    ts.tagTexts.add(idInsertTextField.getText());
			    JLabel tagText = new JLabel(idInsertTextField.getText());
			    System.out.println(ts.tagTexts);
			    tagText.setSize(70, 30);
			    tagText.setLocation(10, 0); // tag 내부에서의 상대적인 위치로 설정
			    tag.add(tagText);
			    CountTag ++;
			    System.out.println(CountTag);
			    tag.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					tagpnl.removeAll();
	        	        CountTag--;
	        	        plus.setLocation(plus.getX() - 100, plus.getY());
	        	        // 아이콘들 왼쪽 정렬
	        	        for(int i = 0;i< ts.tagTexts.size();i++) {
	        	        	if(ts.tagTexts.get(i).equals(tagText.getText()))
	        	        	ts.tagTexts.remove(i);
	        	        }
	        	        for(int i = 0; i<ts.tagTexts.size();i++) {
	        	        	JLabel tag = new JLabel(IC.getImageIcon("tag"));
	        	        	tag.setSize(80, 30);
	        	        	tag.setLocation(60+(i*80),15);
	        	        	JLabel tagText = new JLabel(ts.tagTexts.get(i));
	        	        	tagText.setSize(70,20);
	        	        	tagText.setLocation(10,0);
	        	        	tag.add(tagText);
	        	        	tagpnl.add(tag);
	        	        	plus.setLocation(60+(ts.tagTexts.size()*80),15);
	        	        	tagpnl.add(plus);
	        	        }
	        	        
	        	        tagpnl.revalidate();
	        	        tagpnl.repaint();
				}
			    });
			    plus.setLocation(plus.getX() + 100, plus.getY());
			    plus.setVisible(true);
			    tagpnl.add(tag);
			    tagX += 30;
			    System.out.println( ts.tagTexts.size());
			    dispose();
			}
		});
		panelWithBackground.add(addMemberBtn);
		setSize(346, 174);
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0));
		setVisible(true);
		if(CountTag>=4) {
			plus.setVisible(false);
		}
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