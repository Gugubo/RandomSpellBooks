import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panel extends JPanel {


	int scale = 10;    // Factor by which to scale books
	int w = 12;    // Number of columns of books
	int h = 12;    // Number of rows of books
	int margin = 5;    // Number of pixels between each book (will be scaled)

	BufferedImage bufferImage;
	Graphics2D bufferGraphics;

	public Panel() {
		final int frameWidth = (Buch.width + margin) * scale * w;
		final int frameHeight = (Buch.height + margin) * scale * h;
		setPreferredSize(new Dimension(frameWidth, frameHeight));
		bufferImage = new BufferedImage(frameWidth, frameHeight, BufferedImage.TYPE_INT_ARGB);
		bufferGraphics = bufferImage.createGraphics();
	}

	//Draw all the books
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.fillRect(0, 0, getWidth(), getHeight());

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				buchZeichnen(margin * scale / 2 + ((Buch.width + margin) * scale) * j, margin * scale / 2 + ((Buch.height + margin) * scale) * i);
			}
		}
		exportToFile(g2);
	}

	private void exportToFile(Graphics2D g2){
		g2.drawImage(bufferImage, null, 0, 0);
		try {
			ImageIO.write(bufferImage, "png", new File("frameImage.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//Draw a book
	public void buchZeichnen(int x, int y) {
		Buch buch = new Buch();
		bufferGraphics.drawImage(buch.getImage(), x, y, x + Buch.width * scale, y + Buch.height * scale, 0, 0, Buch.width, Buch.height, null);

	}

}
