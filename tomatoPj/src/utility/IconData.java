package utility;

import javax.swing.ImageIcon;

public class IconData {
	public ImageIcon loginBackGround() {
		return new ImageIcon(getClass().getClassLoader().getResource("login(BG).png"));
	}
	public ImageIcon signUpBackGround() {
		return new ImageIcon(getClass().getClassLoader().getResource("signUp(BG).png"));
	}
 }
