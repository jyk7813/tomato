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

public class ExitBtn extends JButton {
    
    private IconData iconData;
    private ImageIcon logoutBrightIcon;
    private ImageIcon logoutIcon;
    private Utility utility;
    
    public ExitBtn(MainFrame mainFrame) {
        iconData = new IconData();
        utility = new Utility();
        
        logoutIcon = iconData.getImageIcon("exit");
        logoutBrightIcon = iconData.getImageIcon("exit2");
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	ExitBtn.this.setIcon(logoutBrightIcon); // LogoutBtn 객체를 참조
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	ExitBtn.this.setIcon(logoutIcon); // LogoutBtn 객체를 참조
            }

			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
				
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
