package pnl.commonpnl;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frame.MainFrame;
import tomatoPj.Member;
import tomatoPj.MemberRepository;
import utility.IconData;

public class MemberPnl extends JPanel {
	private IconData iconData;
	private Image image;
	private MainFrame mainFrame;
	private byte[] imageBytes;
	private Member member;
	private JButton jButton;
	private MemberRepository memberRepository;
	
	
	/**
	 * Create the panel.
	 */
	public MemberPnl() {
		iconData = new IconData();
		this.image = iconData.getImageIcon("user1").getImage();
		
		setOpaque(false);
	}
	public MemberPnl(Member member,MainFrame mainFrame) {
		jButton = new JButton();
		this.member = member;
		this.mainFrame = mainFrame;
		iconData = new IconData();
		System.out.println(member);
		memberRepository = new MemberRepository();
		settingMyInfopnl();
		
		jButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					memberRepository.deletePjMember(mainFrame.pjInfo.getProject_no(), member.getMember_no());
				} catch (SQLException e1) {
					e1.printStackTrace();
				} finally {
					mainFrame.projectPnl.westPnl.projectMemberPnl.memberPnl.updateMember(mainFrame);
				}
			}
		});
		add(jButton);
		setOpaque(false);
	}
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Get the dimensions of the panel and the image
        int panelWidth = this.getWidth();
        int panelHeight = this.getHeight();
        int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);

        // Calculate the position of the image's top-left corner
        // in order to center the image within the panel
        int x = (panelWidth - imageWidth) / 2;
        int y = (panelHeight - imageHeight) / 2;

        // Draw the image at the calculated position
        g.drawImage(image, x, y, this);
    }
	public void settingMyInfopnl() {
		imageBytes = member.getImage();
		System.out.println(member.getName());
		System.out.println(imageBytes);
		if (imageBytes == null) {
			this.image = iconData.getImageIcon("user1").getImage();
			this.setBounds(35, 30, 60, 60);
			mainFrame.revalidate();
			mainFrame.repaint();
		}
		if (imageBytes != null) {
			ImageIcon imageIcon = new ImageIcon(imageBytes);
			this.image = imageIcon.getImage();
			setBounds(35, 30, 60, 60);
			mainFrame.revalidate();
			mainFrame.repaint();
		}
	}
}