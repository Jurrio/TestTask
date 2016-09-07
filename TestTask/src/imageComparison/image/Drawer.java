package imageComparison.image;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import imageComparison.model.Circle;

public class Drawer {
	
	private Image image;

	public Drawer(Image image) {
		this.image = image;
	}

	public Image getImage() {
		return image;
	}
	
	public void fillDifferents(List<Circle> circles) throws IOException {
		BufferedImage bufImg = (BufferedImage) image;
		Graphics2D g2d = bufImg.createGraphics();
		System.out.println(circles.size() + " different pixels");
		for (Circle circle : circles) {
			g2d.setColor(new Color(255, 0, 0, 5));
			g2d.fillOval(circle.getCenter().getX() - circle.getRadius()/2, circle.getCenter().getY() - circle.getRadius()/2, circle.getRadius(), circle.getRadius());
		}
		ImageIO.write(bufImg, "PNG", new File("test.png"));
		g2d.dispose();
	}

}
