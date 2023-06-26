package pnl;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class signUpPnl extends JPanel{
	private Image image;
	
	public signUpPnl(Image image) {
		this.image = image;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
	
//	public class ImagePanel extends JPanel {
//	    private Image image;
//
//	    public ImagePanel(Image image) {
//	        this.image = image;
//	    }
//
//	    @Override
//	    protected void paintComponent(Graphics g) {
//	        super.paintComponent(g);
//	        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
//	    }
//	}
}
