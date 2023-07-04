package utility;

import java.awt.image.BufferedImage;

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
	public ImageIcon getImageIconGif(String iconName) {
		return new ImageIcon(getClass().getClassLoader().getResource(iconName + ".gif"));
	}

	public ImageIcon getRollImageIcon(String iconName) {
		return new ImageIcon(getClass().getClassLoader().getResource(iconName + "_c.png"));
	}
	
	//아이콘 객체를 보내면 동그란 아이콘 객체를 반환
	public static ImageIcon getCircleImage(ImageIcon icon) {
	    // 이미지의 너비와 높이를 가져옵니다.
	    int width = icon.getIconWidth();
	    int height = icon.getIconHeight();

	    // 이미지의 가운데에 있는 원형의 너비와 높이를 계산합니다.
	    int circleRadius = Math.min(width, height) / 2;

	    // 이미지의 가운데에 있는 원형을 잘라냅니다.
	    BufferedImage circleImage = ((BufferedImage) icon.getImage()).getSubimage((width - circleRadius) / 2, (height - circleRadius) / 2, circleRadius * 2, circleRadius * 2);

	    // 이미지를 ImageIcon 객체로 만듭니다.
	    ImageIcon newIcon = new ImageIcon(circleImage);

	    // ImageIcon 객체를 반환합니다.
	    return newIcon;
	  }
	
}
