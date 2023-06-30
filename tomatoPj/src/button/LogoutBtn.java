package button;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import dbutil.LoginMember;
import dbutil.SelectProjectInfo;
import frame.MainFrame;
import utility.IconData;
import utility.Utility;

public class LogoutBtn extends JButton {
    
    private IconData iconData;
    private ImageIcon logoutBrightIcon;
    private ImageIcon logoutIcon;
    private Utility utility;
    
    public LogoutBtn(MainFrame mainFrame) {
        iconData = new IconData();
        
        logoutIcon = iconData.getImageIcon("logout_btn");
        logoutBrightIcon = iconData.getImageIcon("logout_btn_white");
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                LogoutBtn.this.setIcon(logoutBrightIcon); // LogoutBtn 객체를 참조
            }

            @Override
            public void mouseExited(MouseEvent e) {
                LogoutBtn.this.setIcon(logoutIcon); // LogoutBtn 객체를 참조
            }

			@Override
			public void mouseClicked(MouseEvent e) {
				mainFrame.getContentPane().removeAll();
				mainFrame.loginMember = new LoginMember();
		        mainFrame.pjInfo = new SelectProjectInfo();
		        mainFrame.addPnl();
				mainFrame.showCard("login");
				
			}
        });
        
        utility.setButtonProperties(this);
        
        // JButton에 아이콘을 설정합니다.
        this.setIcon(logoutIcon);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(150,70);
    }
}
