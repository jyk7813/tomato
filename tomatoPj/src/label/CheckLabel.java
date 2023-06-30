package label;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import utility.IconData;
import utility.Regex;
import utility.Utility;


public class CheckLabel extends JLabel {
	private IconData iconData;
	private ImageIcon checkIcon;
	private Regex regex;
	
	public CheckLabel() {
		iconData = new IconData();
		
		checkIcon = iconData.getImageIcon("checkIcon");
		setOpaque(false);
		this.setIcon(checkIcon);
		
	}
	private void passwordCheck() {
		
	}
	private void emailCheck() {
		
	}
}
