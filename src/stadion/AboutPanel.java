package stadion;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AboutPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private BufferedImage img;
	private JButton menu;
	
	public AboutPanel() {
		loadImage();
		loadButtons();
	}

	private void loadImage() {
		try {
			img = ImageIO.read(new File("C:/Users/Konrad/Desktop/java/Stadion/Images/about.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
	}
	
	private void loadButtons() {
		int buttonWidth = 150;
		int buttonHeight = 30;
		menu = new JButton("Powrót do menu");
		menu.setVisible(false);
		menu.setBounds(200, 460, buttonWidth, buttonHeight);
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainStadion.setMenuPoint(true);
				unseenButtons();
			}		
		});
		MainStadion.getWindow().add(menu);
	}
	
	public void seenButtons() {
		menu.setVisible(true);
	}
	
	private void unseenButtons() {
		menu.setVisible(false);
	}
	
}
