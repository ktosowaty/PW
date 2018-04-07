package stadion;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Police {
	
	BufferedImage[] police = new BufferedImage[7];
	
	public Police() {
		loadImages();
	}

	private void loadImages() {
		for(int i=0; i<police.length; i++) {
			String temp = "C:/Users/Konrad/Desktop/java/Stadion/Images/policeman" + (i+1) + ".jpg";
			try {
				police[i] = ImageIO.read(new File(temp));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void drawPolice(Graphics g) {
		for(int i=0; i<3; i++) {
			g.drawImage(police[i], 375, 50*i, null);
		}
		g.drawImage(police[3], 375, 250, null);
		g.drawImage(police[4], 375, 300, null);
		g.drawImage(police[5], 375, 450, null);
		g.drawImage(police[6], 375, 500, null);
	}
	
}
