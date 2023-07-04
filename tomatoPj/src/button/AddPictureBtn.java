package button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import data_input_output.ImageInput;
import pnl.SignUpPnl;
import utility.IconData;

public class AddPictureBtn extends JButton {
	private ImageInput imageInput;
	public AddPictureBtn(SignUpPnl signUpPnl) {
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

