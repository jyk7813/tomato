package button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.MetadataException;

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
        
        enterIcon = iconData.getImageIcon("addPicture2");
        exitIcon = iconData.getImageIcon("addPicture");
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
			byte[] image = null;
			try {
				image = imageInput.chooseImageAndConvertToByteArray();
			} catch (ImageProcessingException e1) {
				e1.printStackTrace();
			} catch (MetadataException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
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

