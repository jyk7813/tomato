package pnl.commonpnl;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowStateListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dbutil.LoginMember;
import frame.MainFrame;
import utility.IconData;

public class MyInfoPnl extends JPanel {
	private IconData iconData;
	private Image image;
	private byte[] imageBytes;
	private MainFrame mainFrame;
	public JLabel myInfoLbl;
	/**
	 * Create the panel.
	 */
	public MyInfoPnl(MainFrame mainFrame) {
		System.out.println("여기가 생성되어버렸다고!!!");
		this.mainFrame = mainFrame;
		iconData = new IconData();
		this.image = iconData.getImageIcon("mini_bar").getImage();
		
		setOpaque(false);
		
	}
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); 
	}
	
	public void settingMyInfopnl() {
		imageBytes = mainFrame.loginMember.getMember().getImage();
		System.out.println(mainFrame.loginMember);
		System.out.println(imageBytes);
		if (imageBytes == null) { // 기본이미지
			myInfoLbl = new JLabel(iconData.getImageIcon("user1"));
			myInfoLbl.setBounds(25, 30, 60, 60);
			add(myInfoLbl);
			revalidate();
			repaint();
		}
		if (imageBytes != null) { // 넣어둔이미지
			ImageIcon imageIcon = new ImageIcon(imageBytes);
			JLabel myInfoLbl = new JLabel(imageIcon);
			myInfoLbl.setBounds(25, 30, 60, 60);
			add(myInfoLbl);
			revalidate();
			repaint();
		}
	}
}