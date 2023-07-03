package button;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import frame.MainFrame;
import utility.IconData;
import utility.Utility;

public class AddMemberBtn extends JButton {
	private IconData iconData;
	private Utility utility;
	private ImageIcon enterIcon;
	private ImageIcon exitIcon;
	
	public AddMemberBtn(MainFrame mainFrame) {
		iconData = new IconData();
        utility = new Utility();
        
        enterIcon = iconData.getImageIcon("addmemberwhite");
        exitIcon = iconData.getImageIcon("addmember");
        utility.setButtonProperties(this);
        addMouseListener(new MouseAdapter() {
        	 @Override
             public void mouseEntered(MouseEvent e) {
        		 AddMemberBtn.this.setIcon(enterIcon);
             }

             @Override
             public void mouseExited(MouseEvent e) {
            	 AddMemberBtn.this.setIcon(exitIcon);
             }

		});
        this.setIcon(exitIcon);
	}
	
}
