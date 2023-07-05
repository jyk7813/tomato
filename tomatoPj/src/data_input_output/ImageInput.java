package data_input_output;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifIFD0Directory;

public class ImageInput {
	
	
	
	
	public byte[] chooseImageAndConvertToByteArray() throws IOException, ImageProcessingException, MetadataException {
	    byte[] imageBytes = null;

	    JFileChooser fileChooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
	    fileChooser.setFileFilter(filter);
	    int returnValue = fileChooser.showOpenDialog(null);
	    if (returnValue == JFileChooser.APPROVE_OPTION) {
	        File selectedFile = fileChooser.getSelectedFile();

	        
	            BufferedImage originalImage = ImageIO.read(selectedFile);

	            // First, we scale down the image.
	            originalImage = resizeImageToTenPercent(originalImage);

	            // Then, we rotate the image if needed.
	            originalImage = rotateImageIfRequired(selectedFile, originalImage);

	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            ImageIO.write(originalImage, "png", baos);
	            imageBytes = baos.toByteArray();
	            imageBytes = resizeImage(imageBytes);
	            imageBytes = cropImageToSquare(imageBytes, 60);
	            imageBytes = cropImageToCircle(imageBytes);
	        
	    }

	    return imageBytes;
	}

	private BufferedImage rotateImageIfRequired(File selectedFile, BufferedImage originalImage) throws IOException, ImageProcessingException, MetadataException {
	    int orientation = getOrientation(selectedFile);

	    switch (orientation) {
	        case 1:
	            return originalImage;
	        case 3:
	            return rotateImage(originalImage, 180);
	        case 6:
	            return rotateImage(originalImage, 90);
	        case 8:
	            return rotateImage(originalImage, -90);
	        default:
	            return originalImage;
	    }
	}


	public BufferedImage resizeImageToTenPercent(BufferedImage originalImage) {
	    // Calculate the new size
	    int scaledWidth = originalImage.getWidth() / 10;
	    int scaledHeight = originalImage.getHeight() / 10;

	    // Create new size image
	    Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
	    BufferedImage bufferedScaledImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);

	    // Create a graphics object from the image
	    Graphics2D g = bufferedScaledImage.createGraphics();

	    // Draw the image
	    g.drawImage(scaledImage, 0, 0, null);

	    g.dispose();

	    return bufferedScaledImage;
	}

	private static int getOrientation(File imgFile) throws IOException, ImageProcessingException, MetadataException {
	    Metadata metadata = ImageMetadataReader.readMetadata(imgFile);
	    Directory directory = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
	    int orientation = 1;
	    if (directory != null && directory.containsTag(ExifIFD0Directory.TAG_ORIENTATION)) {
	        orientation = directory.getInt(ExifIFD0Directory.TAG_ORIENTATION);
	    }
	    return orientation;
	}

	private static BufferedImage rotateImage(BufferedImage originalImage, double degrees) {
	    AffineTransform transform = new AffineTransform();
	    transform.rotate(Math.toRadians(degrees), originalImage.getWidth() / 2, originalImage.getHeight() / 2);

	    AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
	    return op.filter(originalImage, null);
	}

	
	public byte[] resizeImage(byte[] originalImageBytes) {
	    byte[] resizedImageBytes = null;

	    try {
	        // Convert byte array to BufferedImage
	        ByteArrayInputStream bais = new ByteArrayInputStream(originalImageBytes);
	        BufferedImage originalImage = ImageIO.read(bais);

	        // Create a square canvas larger than the original image
	        int maxDimension = Math.max(originalImage.getWidth(), originalImage.getHeight());
	        BufferedImage squareImage = new BufferedImage(maxDimension, maxDimension, BufferedImage.TYPE_INT_ARGB);

	        // Calculate the position of the original image on the canvas (to center it)
	        int x = (maxDimension - originalImage.getWidth()) / 2;
	        int y = (maxDimension - originalImage.getHeight()) / 2;

	        // Draw the original image onto the canvas
	        Graphics2D g = squareImage.createGraphics();
	        g.drawImage(originalImage, x, y, null);

	        // Create a circular clip
	        g.setClip(new Ellipse2D.Float(0, 0, maxDimension, maxDimension));

	        // Draw the circular clip onto the image
	        g.drawImage(squareImage, 0, 0, maxDimension, maxDimension, null);
	        g.dispose();

	        // Resize the image to 60x60 while maintaining the aspect ratio
	        Image scaledImage = squareImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	        BufferedImage bufferedScaledImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
	        bufferedScaledImage.getGraphics().drawImage(scaledImage, 0, 0, null);

	        // Convert BufferedImage back to byte array
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(bufferedScaledImage, "png", baos);
	        resizedImageBytes = baos.toByteArray();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return resizedImageBytes;
	}

	public byte[] cropImageToSquare(byte[] originalImageBytes, int size) {
	    byte[] croppedImageBytes = null;

	    try {
	        // Convert byte array to BufferedImage
	        ByteArrayInputStream bais = new ByteArrayInputStream(originalImageBytes);
	        BufferedImage originalImage = ImageIO.read(bais);

	        // Calculate the position of the square to crop
	        int x = (originalImage.getWidth() - size) / 2;
	        int y = (originalImage.getHeight() - size) / 2;

	        // Crop the image
	        BufferedImage croppedImage = originalImage.getSubimage(x, y, size, size);

	        // Convert BufferedImage back to byte array
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(croppedImage, "png", baos);
	        croppedImageBytes = baos.toByteArray();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return croppedImageBytes;
	}
	public byte[] cropImageToCircle(byte[] originalImageBytes) {
	    byte[] circleImageBytes = null;

	    try {
	        // Convert byte array to BufferedImage
	        ByteArrayInputStream bais = new ByteArrayInputStream(originalImageBytes);
	        BufferedImage originalImage = ImageIO.read(bais);

	        // Create a square canvas larger than the original image
	        int maxDimension = Math.max(originalImage.getWidth(), originalImage.getHeight());
	        BufferedImage squareImage = new BufferedImage(maxDimension, maxDimension, BufferedImage.TYPE_INT_ARGB);

	        // Calculate the position of the original image on the canvas (to center it)
	        int x = (maxDimension - originalImage.getWidth()) / 2;
	        int y = (maxDimension - originalImage.getHeight()) / 2;

	        // Draw the original image onto the canvas
	        Graphics2D g = squareImage.createGraphics();
	        g.drawImage(originalImage, x, y, null);

	        // Create a circular clip
	        g.setClip(new Ellipse2D.Float(x, y, originalImage.getWidth(), originalImage.getHeight()));

	        // Create a new image to fit the circular clip
	        BufferedImage circleImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2 = circleImage.createGraphics();

	        // Apply the clip
	        g2.setClip(new Ellipse2D.Float(0, 0, originalImage.getWidth(), originalImage.getHeight()));
	        g2.drawImage(squareImage, 0, 0, originalImage.getWidth(), originalImage.getHeight(), x, y, x + originalImage.getWidth(), y + originalImage.getHeight(), null);
	        g2.dispose();

	        // Convert BufferedImage back to byte array
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(circleImage, "png", baos);
	        circleImageBytes = baos.toByteArray();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return circleImageBytes;
	}




}
