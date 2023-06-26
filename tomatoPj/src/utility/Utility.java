package utility;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JTextField;
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
	public void setButtonProperties(JTextField jtxf) {
		jtxf.setOpaque(false);
		jtxf.setBorder(null);
		jtxf.setBackground(new Color(0, 0, 0, 0));
		
	}
}
