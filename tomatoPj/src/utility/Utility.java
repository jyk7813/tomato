package utility;

import javax.swing.JButton;
import javax.swing.JToggleButton;

public class Utility {
	
	public void setButtonProperties(JButton button) {
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
	}
	public void setButtonProperties(JToggleButton tgbtn) {
		tgbtn.setOpaque(false);
		tgbtn.setContentAreaFilled(false);
		tgbtn.setBorderPainted(false);
		tgbtn.setFocusPainted(false);
	}
}
