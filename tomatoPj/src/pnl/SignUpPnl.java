package pnl;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import frame.MainFrame;
import utility.FontData;

public class SignUpPnl extends JPanel{
	private Image image;
	private FontData fontData;
	
	public SignUpPnl(Image image,MainFrame mainFrame) {
		this.image = image;
		fontData = new FontData();
		setLayout(null);
		
		JTextField idField = new JTextField();
		JPasswordField passwordField = new JPasswordField();
		JPasswordField checkPasswordField = new JPasswordField();
		JTextField nameField = new JTextField();
		JTextField emailField = new JTextField();
		
		idField.setBounds(842, 333, 233, 41);
		passwordField.setBounds(842, 415, 233, 41);
		checkPasswordField.setBounds(842, 498, 233, 41);
		nameField.setBounds(842, 582, 233, 41);
		emailField.setBounds(842, 643, 233, 41);
		
		idField.setFont(fontData.nanumFont(16));
		passwordField.setFont(fontData.nanumFont(16));
		checkPasswordField.setFont(fontData.nanumFont(16));
		nameField.setFont(fontData.nanumFont(16));
		emailField.setFont(fontData.nanumFont(16));
		
		add(idField);
		add(passwordField);
		add(checkPasswordField);
		add(nameField);
		add(emailField);
		
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
