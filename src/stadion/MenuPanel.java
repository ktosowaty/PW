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

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage img;
	private JButton symulacja, opcje, about;
	
	public MenuPanel() {
		loadImage();	
		loadButtons();			
	}
	
	private void loadImage() {
		try {
			img = ImageIO.read(new File("C:/Users/Konrad/Desktop/java/Stadion/Images/menu.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);		
	}
	
	private void loadButtons() {
		int buttonWidth = 100;
		int buttonHeight = 30;
		symulacja = new JButton("Symulacja");
		symulacja.setVisible(false);
		symulacja.setBounds(MainStadion.getWidth()/2 - buttonWidth/2, MainStadion.getHeight()/2 - 100, buttonWidth, buttonHeight);
		symulacja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainStadion.setSimulationPoint(true);
				unseenButtons();
			}
		});
		opcje = new JButton("Opcje");
		opcje.setVisible(false);
		opcje.setBounds(MainStadion.getWidth()/2 - buttonWidth/2, MainStadion.getHeight()/2, buttonWidth, buttonHeight);
		opcje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainStadion.setOptionsPoint(true);
				unseenButtons();
			}	
		});
		about = new JButton("About");
		about.setVisible(false);
		about.setBounds(MainStadion.getWidth()/2 - buttonWidth/2, MainStadion.getHeight()/2 + 100, buttonWidth, buttonHeight);
		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainStadion.setAboutPoint(true);
				unseenButtons();
			}			
		});
		MainStadion.getWindow().add(symulacja);
		MainStadion.getWindow().add(opcje);
		MainStadion.getWindow().add(about);
	}
	
	public void seenButtons() {
		symulacja.setVisible(true);
		opcje.setVisible(true);
		about.setVisible(true);
	}
	
	private void unseenButtons() {
		symulacja.setVisible(false);
		opcje.setVisible(false);
		about.setVisible(false);
	}
	
}
