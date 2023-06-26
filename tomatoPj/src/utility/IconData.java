package utility;

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

	public ImageIcon kanbanBtnIcon() {
		return new ImageIcon(getClass().getClassLoader().getResource("navi_board.png"));
	}

	public ImageIcon kanbanCBtnIcon() {
		return new ImageIcon(getClass().getClassLoader().getResource("navi_board_c.png"));
	}
	
	public ImageIcon todoBtnIcon() {
		return new ImageIcon(getClass().getClassLoader().getResource("navi_todo.png"));
	}

	public ImageIcon todoCBtnIcon() {
		return new ImageIcon(getClass().getClassLoader().getResource("navi_todo_c.png"));
	}
	
	public ImageIcon plannerBtnIcon() {
		return new ImageIcon(getClass().getClassLoader().getResource("navi_planner.png"));
	}

	public ImageIcon plannerCBtnIcon() {
		return new ImageIcon(getClass().getClassLoader().getResource("navi_planner_c.png"));
	}
	
	public ImageIcon getImageIcon(String iconName) {
		return new ImageIcon(getClass().getClassLoader().getResource(iconName + ".png"));
	}
	
}
