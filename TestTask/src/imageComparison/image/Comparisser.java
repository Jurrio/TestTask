package imageComparison.image;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import imageComparison.model.Circle;
import imageComparison.model.Point;

public class Comparisser {

	BufferedImage bufImage1;
	BufferedImage bufImage2;

	public Comparisser(Image img1, Image img2) {
		this.bufImage1 = (BufferedImage) img1;
		this.bufImage2 = (BufferedImage) img2;
	}
	
	public List<Circle> compareImages() {
		int h1 = bufImage1.getHeight();
		int h2 = bufImage2.getHeight();
		int height = h1 < h2 ? h1 : h2;

		int w1 = bufImage1.getWidth();
		int w2 = bufImage2.getWidth();
		int width = w1 < w2 ? w1 : w2;

		List<Circle> circles = new ArrayList<>();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (!theSameColors(getPixelColor(bufImage1, x, y), getPixelColor(bufImage2, x, y))) {
					circles.add(new Circle(new Point(x, y), 10));
				}
			}
		}
		return circles;		
	}

	private boolean theSameColors(Color pixelColor, Color pixelColor2) {
		return pixelColor.equals(pixelColor2);
	}

	private Color getPixelColor(BufferedImage image, int x, int y) {
		Object colorData = image.getRaster().getDataElements(x, y, null);
		int argb = image.getColorModel().getRGB(colorData);
		return new Color(argb, true);
	}
}
