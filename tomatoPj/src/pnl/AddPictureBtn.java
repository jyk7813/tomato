package pnl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class AddPictureBtn extends JButton {
	
	public AddPictureBtn() {
		
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
}

//imageBtn.addActionListener(new ActionListener() {
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		JFileChooser fileChooser = new JFileChooser();
//		FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files",
//				ImageIO.getReaderFileSuffixes());
//		fileChooser.setFileFilter(filter);
//		int returnValue = fileChooser.showOpenDialog(null);
//		if (returnValue == JFileChooser.APPROVE_OPTION) {
//			File selectedFile = fileChooser.getSelectedFile();
//			double bytes = selectedFile.length();
//			double kilobytes = bytes / 1024;
//			double megabytes = kilobytes / 1024;
//
//			BufferedImage originalImage;
//			try {
//				originalImage = ImageIO.read(selectedFile);
//
//				int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
//
//				BufferedImage resizedImage = resizeImage(originalImage, type);
//				ImageIcon imageIcon = new ImageIcon(resizedImage);
//
//				registeredImage.setIcon(imageIcon);
//
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//
//			imageRoot.setText(selectedFile.getAbsolutePath());
//		}
//	}
//
//});