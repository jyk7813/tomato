package pnl.commonpnl;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import button.CanBanBtn;
import button.HomeBtn;
import button.LogoutBtn;
import frame.MainFrame;
import utility.IconData;

public class TopTodoSelectedPnl extends JPanel {
    private Image backgroundImage; // Use Image class
    private IconData iconData;

    public TopTodoSelectedPnl(MainFrame mainFrame, TopMainPnl topMainPnl) {
    	iconData = new IconData();
        setLayout(null);
        
		HomeBtn homeBtn = new HomeBtn(mainFrame);
		homeBtn.setBounds(100, 45, 201, 45);
		add(homeBtn);
        
        CanBanBtn canBanBtn = new CanBanBtn(mainFrame, topMainPnl);
        canBanBtn.setBounds(813, 38, 102, 60);
        add(canBanBtn);
        
        LogoutBtn logoutBtn = new LogoutBtn(mainFrame);
        logoutBtn.setBounds(1649, 33, 150, 70);
        add(logoutBtn);
        
        // Load the image (this assumes you have a "image.jpg" file in the same directory)
        backgroundImage = iconData.getImageIcon("todoselectedre").getImage();
        setOpaque(false);
    }

    // Override the paintComponent method
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, this);
    }
}
