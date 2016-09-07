package imageComparison.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import imageComparison.exception.DifferentSizeException;
import imageComparison.exception.NotAPictureException;

public class ImageUtil {
	
	public ImageUtil() {}
	
	public BufferedImage checkImage(File file) throws NotAPictureException {
		BufferedImage image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException ex) {
			throw new NotAPictureException(file.getName() + " not a picture");
		}
		return image;
	}
	
	public boolean isTheSameSize(BufferedImage img1, BufferedImage img2) throws DifferentSizeException {
		int differenceWidth = img1.getWidth() - img2.getWidth();
		int differenceHeigth = img1.getHeight() - img2.getHeight();
		if (differenceWidth != 0 && differenceHeigth != 0) {
			throw new DifferentSizeException("Pictures have different sizes");
		}
		return true;
	}

}
