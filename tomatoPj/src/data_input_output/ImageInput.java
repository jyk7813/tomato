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
	
	
	
	
	public byte[] chooseImageAndConvertToByteArray() {
	    byte[] imageBytes = null;

	    JFileChooser fileChooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
	    fileChooser.setFileFilter(filter);
	    int returnValue = fileChooser.showOpenDialog(null);

	    if (returnValue == JFileChooser.APPROVE_OPTION) {
	        File selectedFile = fileChooser.getSelectedFile();

	        try {
	            BufferedImage originalImage = rotateImageIfRequired(selectedFile);
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            ImageIO.write(originalImage, "png", baos); 
	            imageBytes = baos.toByteArray();
	        } catch (IOException | ImageProcessingException e) {
	            e.printStackTrace();
	        } catch (MetadataException e) {
				e.printStackTrace();
			}
	    }

	    return imageBytes;
	}

	private static BufferedImage rotateImageIfRequired(File imgFile) throws IOException, ImageProcessingException, MetadataException {
	    BufferedImage originalImage = ImageIO.read(imgFile);
	    int orientation = getOrientation(imgFile);

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
	        Image scaledImage = squareImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
	        BufferedImage bufferedScaledImage = new BufferedImage(60, 60, BufferedImage.TYPE_INT_ARGB);
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
