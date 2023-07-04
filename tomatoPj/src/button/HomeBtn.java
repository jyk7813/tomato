package button;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import dbutil.LoginMember;
import dbutil.SelectProjectInfo;
import frame.MainFrame;
import utility.IconData;
import utility.Utility;

public class HomeBtn extends JButton{
	private IconData iconData;
	private Utility utility;
	private ImageIcon enterIcon;
	private ImageIcon exitIcon;
	
	public HomeBtn(MainFrame mainFrame) {
		iconData = new IconData();
        utility = new Utility();
        
        enterIcon = iconData.getImageIconGif("tomatoLogo");
        exitIcon = iconData.getImageIcon("tomatoLogoStop");
        utility.setButtonProperties(this);
        addMouseListener(new MouseAdapter() {
        	 @Override
             public void mouseEntered(MouseEvent e) {
        		 HomeBtn.this.setIcon(enterIcon);
             }

             @Override
             public void mouseExited(MouseEvent e) {
            	 HomeBtn.this.setIcon(exitIcon);
             }

 			@Override
 			public void mousePressed(MouseEvent e) {
 				mainFrame.getContentPane().removeAll();
 		        mainFrame.addPnl();
 				mainFrame.showCard("projectSelect");
 				
 			}
		});
        this.setIcon(exitIcon);
	}
	
}
