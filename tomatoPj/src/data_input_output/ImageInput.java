package data_input_output;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageInput {
	
	
	
	
	public byte[] chooseImageAndConvertToByteArray() {
	    byte[] imageBytes = null;

	    JFileChooser fileChooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
	    fileChooser.setFileFilter(filter);
	    int returnValue = fileChooser.showOpenDialog(null);

	    if (returnValue == JFileChooser.APPROVE_OPTION) {
	        File selectedFile = fileChooser.getSelectedFile();

	        try {
	            // Convert the image to PNG format
	            convertImageToPNG(selectedFile);

	            BufferedImage originalImage = ImageIO.read(selectedFile);
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            ImageIO.write(originalImage, "png", baos); // If your image is in .png format, change "jpg" to "png"
	            imageBytes = baos.toByteArray();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    return imageBytes;
	}
	
	public byte[] resizeImage(byte[] originalImageBytes) {
	    byte[] resizedImageBytes = null;

	    try {
	        // 바이트 배열을 BufferedImage로 변환
	        ByteArrayInputStream bais = new ByteArrayInputStream(originalImageBytes);
	        BufferedImage originalImage = ImageIO.read(bais);

	        // 이미지를 60x60 사이즈로 리사이징
	        BufferedImage resizedImage = new BufferedImage(60, 60, originalImage.getType());
	        Graphics2D g2 = resizedImage.createGraphics();
	        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        g2.drawImage(originalImage, 0, 0, 60, 60, null);
	        g2.dispose();

	        // 원형으로 자르기
	        BufferedImage circleBuffer = new BufferedImage(60, 60, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2d = circleBuffer.createGraphics();
	        g2d.setClip(new Ellipse2D.Float(0, 0, 60, 60));
	        g2d.drawImage(resizedImage, 0, 0, 60, 60, null);
	        g2d.dispose();

	        // BufferedImage를 바이트 배열로 다시 변환
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(circleBuffer, "png", baos);  // If your image is in .jpg format, change "png" to "jpg"
	        resizedImageBytes = baos.toByteArray();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return resizedImageBytes;
	}
	public void convertImageToPNG(File imageFile) {
	    try {
	        BufferedImage image = ImageIO.read(imageFile);

	        // Create new file with the same path but with ".png" extension
	        File pngFile = new File(imageFile.getAbsolutePath().replaceAll("\\.[^.]*$", "") + ".png");

	        // Write the image to the new file as PNG
	        ImageIO.write(image, "png", pngFile);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
