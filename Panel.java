import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Panel extends JPanel {

	int	scale	= 10;
	int	w		= 4, h = 3, margin = 5;

	public Panel() {
		setPreferredSize(new Dimension((Buch.width+margin)*scale*w, (Buch.height+margin)*scale*h));
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(new Color(108,83,83));
		g.fillRect(0, 0, getWidth(), getHeight());
		for (int i = 0; i<h; i++) {
			for (int j = 0; j<w; j++) {
				buchZeichnen(margin*scale/2+((Buch.width+margin)*scale)*j, margin*scale/2+((Buch.height+margin)*scale)*i, g);
			}
		}
	}

	public void buchZeichnen(int x, int y, Graphics g) {
		Buch buch = new Buch();
		g.drawImage(buch.getImage(), x, y, x+Buch.width*scale, y+Buch.height*scale, 0, 0, Buch.width, Buch.height, null);
	}

}
