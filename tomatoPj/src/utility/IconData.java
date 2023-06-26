package utility;

import javax.swing.ImageIcon;

public class IconData {
	public ImageIcon loginBackGround() {
		return new ImageIcon(getClass().getClassLoader().getResource("login(BG)remake.png"));
	}
	public ImageIcon signUpBackGround() {
		return new ImageIcon(getClass().getClassLoader().getResource("signUp(BG).png"));
	}
	public ImageIcon loginBtnIcon() {
		return new ImageIcon(getClass().getClassLoader().getResource("login_btn.png"));
	}
	public ImageIcon getImageIcon(String iconName) {
		return new ImageIcon(getClass().getClassLoader().getResource(iconName));
	}
 }
