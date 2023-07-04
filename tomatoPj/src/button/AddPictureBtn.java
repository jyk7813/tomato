package button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import data_input_output.ImageInput;
import pnl.SignUpPnl;
import utility.IconData;
import utility.Utility;

public class AddPictureBtn extends JButton {
	private ImageInput imageInput;
	private IconData iconData;
	private Utility utility;
	private ImageIcon enterIcon;
	private ImageIcon exitIcon;
	
	public AddPictureBtn(SignUpPnl signUpPnl) {
		
		iconData = new IconData();
        utility = new Utility();
        
        enterIcon = iconData.getImageIcon("AddPicture2");
        exitIcon = iconData.getImageIcon("AddPicture");
        utility.setButtonProperties(this);
        addMouseListener(new MouseAdapter() {
        	 @Override
             public void mouseEntered(MouseEvent e) {
        		 AddPictureBtn.this.setIcon(enterIcon);
             }

             @Override
             public void mouseExited(MouseEvent e) {
            	 AddPictureBtn.this.setIcon(exitIcon);
             }

 			@Override
 			public void mousePressed(MouseEvent e) {
 			}
		});
		this.setIcon(exitIcon);
		
		
		
		imageInput = new ImageInput();
		
		addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
			byte[] image = imageInput.chooseImageAndConvertToByteArray();
			//System.out.println(image);
			ImageIcon imageIcon = new ImageIcon(image);
			signUpPnl.testLbl.setIcon(imageIcon);
			}
		});
	}
	public ImageIcon getImageIcon(byte[] resizedImages) {
		ImageIcon imageIcon = new ImageIcon(resizedImages);
		return imageIcon;
	}
	
	
}

