package pnl.commonpnl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import pnl.projectpnl.ProjectPnl;
import utility.FontData;
import utility.IconData;
import utility.Utility;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Component;

public class MemberListPnl extends JPanel {
	private IconData iconData;
	private Image image;
	private FontData fontData;
	private Utility utility;
	private JButton addMemberBtn;
	private JPanel memberAddPnl;
	private final static int MAX_MEMBER_SIZE = 4;
	

	/**
	 * Create the panel.
	 */
	public MemberListPnl() {
		iconData = new IconData();
		fontData = new FontData();
		utility = new Utility();
		this.image = iconData.getImageIcon("long_bar").getImage();
		setOpaque(false);
		
		setLayout(null);

		JPanel memberTitleLbl = new JPanel();
		memberTitleLbl.setBounds(0, 0, 130, 70);
		memberTitleLbl.setOpaque(false);
		add(memberTitleLbl);
		memberTitleLbl.setLayout(new BorderLayout(0, 0));

		JLabel memberLbl = new JLabel("Member", SwingConstants.CENTER);
		memberLbl.setBackground(new Color(0, 0, 0, 0));
		memberLbl.setFont(fontData.nanumFontBold(20));
		memberLbl.setForeground(new Color(112, 112, 112));
		memberTitleLbl.add(memberLbl, BorderLayout.CENTER);

		memberAddPnl = new JPanel();
	    memberAddPnl.setBounds(0, 69, 130, 458);
	    memberAddPnl.setBackground(new Color(0,0,0,0));
	    memberAddPnl.setOpaque(false);
	    memberAddPnl.setLayout(null);  // Use a null layout
	    add(memberAddPnl);

	    addMemberBtn = new JButton(iconData.getImageIcon("plus"));
	    addMemberBtn.setBounds(25, 10, 80, 80);  // Set initial bounds
	    utility.setButtonProperties(addMemberBtn);
	    addMemberBtn.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            addPanel();
	            
	        }
	    });
	    memberAddPnl.add(addMemberBtn);
	    
	}
	private void addPanel() {
		
	    MemberPnl memberPnl = new MemberPnl();
	    memberPnl.setBounds(25, addMemberBtn.getY() - 10, 80, 80); 
	    memberAddPnl.add(memberPnl);  
	    memberAddPnl.setComponentZOrder(memberPnl, 1);  // Make MemberPnl paint before addMemberBtn
	    addMemberBtn.setLocation(addMemberBtn.getX(), addMemberBtn.getY() + 90);
	    memberAddPnl.setPreferredSize(new Dimension(memberAddPnl.getWidth(), addMemberBtn.getY() + addMemberBtn.getHeight()));
	    utility.setButtonProperties(addMemberBtn);
	    memberAddPnl.validate();
	    memberAddPnl.repaint();
	}




	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}

}