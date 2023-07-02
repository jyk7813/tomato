package pnl.commonpnl;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frame.MainFrame;
import tomatoPj.Member;
import utility.IconData;

public class MemberPnl extends JPanel {
	private IconData iconData;
	private Image image;
	private MainFrame mainFrame;
	private byte[] imageBytes;
	private Member member;
	
	/**
	 * Create the panel.
	 */
	public MemberPnl() {
		iconData = new IconData();
		this.image = iconData.getImageIcon("user1").getImage();
		
		setOpaque(false);
	}
	public MemberPnl(Member member,MainFrame mainFrame) {
		this.member = member;
		this.mainFrame = mainFrame;
		iconData = new IconData();
		System.out.println(member);
		settingMyInfopnl();
	
		
		setOpaque(false);
	}
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); 
	}
	public void settingMyInfopnl() {
		imageBytes = member.getImage();
		System.out.println(member.getName());
		System.out.println(imageBytes);
		if (imageBytes == null) {
			this.image = iconData.getImageIcon("user1").getImage();
			this.setBounds(35, 30, 90, 90);
			mainFrame.revalidate();
			mainFrame.repaint();
		}
		if (imageBytes != null) {
			ImageIcon imageIcon = new ImageIcon(imageBytes);
			this.image = imageIcon.getImage();
			setBounds(35, 30, 90, 90);
			mainFrame.revalidate();
			mainFrame.repaint();
		}
	}
}