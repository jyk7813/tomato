package button;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import utility.IconData;
import utility.Utility;

public class PlusBtn extends JButton{
	
	private IconData iconData;
	private Utility utility;
	private ImageIcon plusIcon;
	private ImageIcon plusWhiteIcon;
	
	public PlusBtn() {
		
		iconData = new IconData();
		utility = new Utility();
		
		plusIcon = iconData.getImageIcon("plusicon");
		plusWhiteIcon = iconData.getImageIcon("pluswhite");
		
		utility.setButtonProperties(this);
		
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				PlusBtn.this.setIcon(plusWhiteIcon);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				PlusBtn.this.setIcon(plusIcon);
			
			}
			
			
		});
		this.setIcon(plusIcon);
		
	}
}
