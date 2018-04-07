package stadion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OptionsPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private BufferedImage img;
	private JButton menu, symulacja;
	private JTextField maxNumberOfFansInStadium, minTimeOfControl, maxTimeOfControl,
	minTimeBetweenSpawningFans, maxTimeBetweenSpawningFans;
	
	public OptionsPanel() {
		loadImage();
		loadButtons();
	}
	
	private void loadImage() {
		try {
			img = ImageIO.read(new File("C:/Users/Konrad/Desktop/java/Stadion/Images/stadion.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);		
		g.setFont(new Font("Tahoma", Font.BOLD, 30));
		g.setColor(Color.BLACK);
		g.drawString("Opcje", 1028, 25);
		g.setFont(new Font("Tahoma", Font.PLAIN, 20));
		g.drawString("Maksymalna liczba", 998, 70);
		g.drawString("kibiców na stadionie", 990, 90);
		g.drawString("5 <=", 1000, 120);
		g.drawString("<= 10000", 1100, 120);		
		g.drawString("Minimalny czas", 1003, 170);
		g.drawString("kontroli kibica (ms)", 990, 190);
		g.drawString("500 <=", 978, 220);
		g.drawString(" < 60000", 1100, 220);
		g.drawString("Maksymalny czas", 998, 270);
		g.drawString("kontroli kibica (ms)", 990, 290);
		g.drawString(" 500 <", 978, 320);
		g.drawString("<= 60000", 1100, 320);
		g.drawString("Co ile pojawiaj¹ siê", 993, 370);
		g.drawString("kibice - minimum (ms)", 980, 390);
		g.drawString("500 <=", 978, 420);
		g.drawString(" < 60000", 1100, 420);
		g.drawString("Co ile pojawiaj¹ siê", 993, 470);
		g.drawString("kibice - maksimum (ms)", 972, 490);
		g.drawString(" 500 <", 978, 520);
		g.drawString("<= 60000", 1100, 520);
		g.setFont(new Font("Tahoma", Font.PLAIN, 11));
		g.drawString("min czas kontroli < max czas kontroli", 994, 550);
		g.drawString("min czas pojawiania < max czas pojawiania", 978, 565);
		g.drawString("Po wprowadzeniu danych naciœnij Enter", 988, 580);
	}
	
	private boolean isNumeric(String str) {
		for(char c : str.toCharArray()) {
			if(Character.isDigit(c)==false) {
				return false;
			}
		}
		return true;
	}
	
	private void loadButtons() {
		int buttonWidth = 100;
		int buttonHeight = 30;
		symulacja = new JButton("Symulacja");
		symulacja.setVisible(false);
		symulacja.setBounds(325, 200, buttonWidth, buttonHeight);
		symulacja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainStadion.setSimulationPoint(true);
				unseenButtons();
			}		
		});
		menu = new JButton("Menu");
		menu.setVisible(false);
		menu.setBounds(525, 200, buttonWidth, buttonHeight);
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainStadion.setMenuPoint(true);
				unseenButtons();
			}			
		});
		maxNumberOfFansInStadium = new JTextField(SimulationPanel.getMaxNumberOfFansInStadium().toString());
		maxNumberOfFansInStadium.setVisible(false);
		maxNumberOfFansInStadium.setBounds(1050, 100, buttonWidth/2, buttonHeight);
		maxNumberOfFansInStadium.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(isNumeric(maxNumberOfFansInStadium.getText())==true && Integer.parseInt(maxNumberOfFansInStadium.getText())>=5 
						&& Integer.parseInt(maxNumberOfFansInStadium.getText())<=10000) {
					SimulationPanel.setMaxNumberOfFansInStadium(Integer.parseInt(maxNumberOfFansInStadium.getText()));
				} else {
					maxNumberOfFansInStadium.setText(SimulationPanel.getMaxNumberOfFansInStadium().toString());
				}
			}
		});
		minTimeOfControl = new JTextField(SimulationPanel.getMinTimeOfControl().toString());
		minTimeOfControl.setVisible(false);
		minTimeOfControl.setBounds(1050, 200, buttonWidth/2, buttonHeight);
		minTimeOfControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isNumeric(minTimeOfControl.getText())==true && Integer.parseInt(minTimeOfControl.getText())>=500
						&& Integer.parseInt(minTimeOfControl.getText())<SimulationPanel.getMaxTimeOfControl()) {
					SimulationPanel.setMinTimeOfControl(Integer.parseInt(minTimeOfControl.getText()));
				} else {
					minTimeOfControl.setText(SimulationPanel.getMinTimeOfControl().toString());
				}
			}	
		});
		maxTimeOfControl = new JTextField(SimulationPanel.getMaxTimeOfControl().toString());
		maxTimeOfControl.setVisible(false);
		maxTimeOfControl.setBounds(1050, 300, buttonWidth/2, buttonHeight);
		maxTimeOfControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isNumeric(maxTimeOfControl.getText())==true && Integer.parseInt(maxTimeOfControl.getText())<=60000
						&& Integer.parseInt(maxTimeOfControl.getText())>SimulationPanel.getMinTimeOfControl()) {
					SimulationPanel.setMaxTimeOfControl(Integer.parseInt(maxTimeOfControl.getText()));
				} else {
					maxTimeOfControl.setText(SimulationPanel.getMaxTimeOfControl().toString());
				}
			}	
		});
		minTimeBetweenSpawningFans = new JTextField(SimulationPanel.getMinTimeBetweenSpawningFans().toString());
		minTimeBetweenSpawningFans.setVisible(false);
		minTimeBetweenSpawningFans.setBounds(1050, 400, buttonWidth/2, buttonHeight);
		minTimeBetweenSpawningFans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isNumeric(minTimeBetweenSpawningFans.getText())==true && Integer.parseInt(minTimeBetweenSpawningFans.getText())>=500
						&& Integer.parseInt(minTimeBetweenSpawningFans.getText())<SimulationPanel.getMaxTimeBetweenSpawningFans()) {
					SimulationPanel.setMinTimeBetweenSpawningFans(Integer.parseInt(minTimeBetweenSpawningFans.getText()));
				} else {
					minTimeBetweenSpawningFans.setText(SimulationPanel.getMinTimeBetweenSpawningFans().toString());
				}
			}	
		});
		maxTimeBetweenSpawningFans = new JTextField(SimulationPanel.getMaxTimeBetweenSpawningFans().toString());
		maxTimeBetweenSpawningFans.setVisible(false);
		maxTimeBetweenSpawningFans.setBounds(1050, 500, buttonWidth/2, buttonHeight);
		maxTimeBetweenSpawningFans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isNumeric(maxTimeBetweenSpawningFans.getText())==true && Integer.parseInt(maxTimeBetweenSpawningFans.getText())<=60000
						&& Integer.parseInt(maxTimeBetweenSpawningFans.getText())>SimulationPanel.getMinTimeBetweenSpawningFans()) {
					SimulationPanel.setMaxTimeBetweenSpawningFans(Integer.parseInt(maxTimeBetweenSpawningFans.getText()));
				} else {
					maxTimeBetweenSpawningFans.setText(SimulationPanel.getMaxTimeBetweenSpawningFans().toString());
				}
			}	
		});
		MainStadion.getWindow().add(symulacja);
		MainStadion.getWindow().add(menu);
		MainStadion.getWindow().add(maxNumberOfFansInStadium);
		MainStadion.getWindow().add(minTimeOfControl);
		MainStadion.getWindow().add(maxTimeOfControl);
		MainStadion.getWindow().add(minTimeBetweenSpawningFans);
		MainStadion.getWindow().add(maxTimeBetweenSpawningFans);
	}
	
	public void seenButtons() {
		symulacja.setVisible(true);
		menu.setVisible(true);
		maxNumberOfFansInStadium.setVisible(true);
		minTimeOfControl.setVisible(true);
		maxTimeOfControl.setVisible(true);
		minTimeBetweenSpawningFans.setVisible(true);
		maxTimeBetweenSpawningFans.setVisible(true);
	}
	
	private void unseenButtons() {
		symulacja.setVisible(false);
		menu.setVisible(false);
		maxNumberOfFansInStadium.setVisible(false);
		minTimeOfControl.setVisible(false);
		maxTimeOfControl.setVisible(false);
		minTimeBetweenSpawningFans.setVisible(false);
		maxTimeBetweenSpawningFans.setVisible(false);
	}	
	
}
