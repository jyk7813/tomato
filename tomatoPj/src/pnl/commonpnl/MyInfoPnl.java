package pnl.commonpnl;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import frame.MainFrame;
import utility.IconData;

public class MyInfoPnl extends JPanel {
	private IconData iconData;
	private Image image;
	private byte[] imageBytes;
	private MainFrame mainFrame;
	private JLayeredPane layeredPane;
	public JLabel myInfoLbl;
	public JLabel anotherLabel;
	/**
	 * Create the panel.
	 */
	public MyInfoPnl(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		iconData = new IconData();
		this.image = iconData.getImageIcon("mini_bar").getImage();
		layeredPane = new JLayeredPane();
		setOpaque(false);
		add(layeredPane);
		
	}
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); 
	}
	
	public void settingMyInfopnl() {
		imageBytes = mainFrame.loginMember.getMember().getImage();
		if (imageBytes == null) { // 기본이미지
			myInfoLbl = new JLabel(iconData.getImageIcon("user1"));
			myInfoLbl.setBounds(35, 40, 60, 60);
			layeredPane.add(myInfoLbl, new Integer(2));
			revalidate();
			repaint();
		}
		if (imageBytes != null) { // 넣어둔이미지
			ImageIcon imageIcon = new ImageIcon(imageBytes);
			JLabel myInfoLbl = new JLabel(imageIcon);
			myInfoLbl.setBounds(35, 40, 60, 60);
			layeredPane.add(myInfoLbl, new Integer(2));
			revalidate();
			repaint();
		}
		 anotherLabel = new JLabel(iconData.getImageIcon("myinfo2"));
	        anotherLabel.setBounds(0, 0, 130, 140); // Set bounds so it overlaps myInfoLbl
	        layeredPane.add(anotherLabel,new Integer(3));

	        revalidate();
	        repaint();
	}
}