package utility;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class IconData {
	
	public ImageIcon topLogo() {
		return new ImageIcon(getClass().getClassLoader().getResource("topLogo.png"));
	}
	
	public ImageIcon topLogoWhite() {
		return new ImageIcon(getClass().getClassLoader().getResource("topLogo_white.png"));
	}
	
	public ImageIcon loginBackGround() {
		return new ImageIcon(getClass().getClassLoader().getResource("login(BG)remake.png"));
	}

	public ImageIcon signUpBackGround() {
		return new ImageIcon(getClass().getClassLoader().getResource("signUp(BG).png"));
	}

	public ImageIcon loginBtnIcon() {
		return new ImageIcon(getClass().getClassLoader().getResource("login_btn.png"));
	}
	
	public ImageIcon projectBackGround() {
		return new ImageIcon(getClass().getClassLoader().getResource("selectProject(BG).png"));
	}

	public ImageIcon getImageIcon(String iconName) {
		return new ImageIcon(getClass().getClassLoader().getResource(iconName + ".png"));
	}

	public ImageIcon getRollImageIcon(String iconName) {
		return new ImageIcon(getClass().getClassLoader().getResource(iconName + "_c.png"));
	}
}
