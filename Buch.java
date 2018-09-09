import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Buch {

	static int		coverWidth			= 5;
	static int		coverHeight			= 7;

	static int		width				= coverWidth+3;
	static int		height				= coverHeight+6;

	static Color	pageColor			= new Color(226, 207, 167);
	Color			background, foreground, bookmarkColor;

	boolean			bookmark;

	int[][]			cover				= new int[coverHeight][coverWidth];
	static double	foregroundChance	= 0.5;
	static double	bookmarkChance		= 0.3;
	static double mirrorChance = 0.7;

	public Buch() {
		randomCover();
		randomMirror();
		randomBookmark();
		randomColors();
	}
	
	public void randomColors() {
		background = randomColor();
		do {
		foreground = randomColor();
		} while (similarColor(background,foreground));
	}
	
	//Checks if two colors are similar
	public boolean similarColor(Color a, Color b) {
	    double distance = Math.pow(a.getRed()-b.getRed(),2)+Math.pow(a.getGreen()-b.getGreen(),2)+Math.pow(a.getBlue()-b.getBlue(),2);
	    System.out.println(distance);
		return distance<5000;
	}

	//Creates random array with 0s and 1s
	public void randomCover() {
		for (int i = 0; i<cover.length; i++) {
			for (int j = 0; j<cover[i].length; j++) {
				cover[i][j] = Math.random()<foregroundChance ? 1 : 0;
			}
		}
	}
	
	public void randomMirror() {
		if (Math.random()<mirrorChance)
		mirrorVertically();
		if (Math.random()<mirrorChance)
		mirrorHorizontally();
	}
	
	public void mirrorVertically() {
		for (int i = 0; i<cover.length;i++) {
			for (int j = 0; j<cover[i].length/2;j++) {
				cover[i][j]=cover[i][cover[i].length-1-j];
			}
		}
	}
	public void mirrorHorizontally() {
		for (int i = 0; i<cover.length/2;i++) {
			for (int j = 0; j<cover[i].length;j++) {
				cover[i][j]=cover[cover.length-1-i][j];
			}
		}
	}

	public void randomBookmark() {
		if (Math.random()<bookmarkChance) {
			bookmark = true;
			bookmarkColor = randomColor();
		}
	}
	
	public Color randomColor() {
		return new Color((int)(Math.random() * 0x1000000));
	}

	public BufferedImage getImage() {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();

		// Rücken
		g.setColor(background.darker().darker());
		g.drawLine(0, 1, 0, coverHeight+3);

		// Rahmen
		g.setColor(background.darker());
		g.drawRect(1, 0, coverWidth+1, coverHeight+1);
		g.drawLine(1, coverHeight+4, width, coverHeight+4);

		// Seiten
		g.setColor(pageColor.darker());
		g.drawLine(1, coverHeight+2, width, coverHeight+2);
		g.setColor(pageColor);
		g.drawLine(1, coverHeight+3, width, coverHeight+3);

		// Bookmark
		if (bookmark) {
			g.setColor(bookmarkColor);
			g.drawLine(2, coverHeight+3, 2, coverHeight+6);
		}

		// Cover
		for (int i = 0; i<cover.length; i++) {
			for (int j = 0; j<cover[i].length; j++) {
				int rgb;
				if (cover[i][j]==1) {
					rgb = foreground.getRGB();
				} else {
					rgb = background.getRGB();
				}
				image.setRGB(2+j, 1+i, rgb);
			}
		}

		return image;
	}
}
