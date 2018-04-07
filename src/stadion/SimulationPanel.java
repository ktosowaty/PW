package stadion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class SimulationPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private BufferedImage img, img2, img3, img4;
	private JButton menu, opcje, reset;
	private static boolean isSimulationGoing = false;
	private static int numberOfFansInStadium = 0;
	private static Integer maxNumberOfFansInStadium = 100;
	private static Integer minTimeOfControl = 5000;
	private static Integer maxTimeOfControl = 10000;
	private static Integer minTimeBetweenSpawningFans = 1000; 
	private static Integer maxTimeBetweenSpawningFans = 2000;
	public static Lock lock1 = new ReentrantLock();
	public static Lock lock2 = new ReentrantLock();
	Police police = new Police();
	
	public SimulationPanel() {
		loadImages();
		loadButtons();
	}

	public static boolean getIsSimulationGoing() {
		return isSimulationGoing;
	}
	
	public static void setIsSimulationGoing(boolean newBool) {
		isSimulationGoing = newBool;
	}
	
	public static int getNumberOfFansInStadium() {
		return numberOfFansInStadium;
	}
	
	public static void setNumberOfFansInStadium(int newNumber) {
		numberOfFansInStadium = newNumber;
	}
	
	public static Integer getMaxNumberOfFansInStadium() {
		return maxNumberOfFansInStadium;
	}
	
	public static void setMaxNumberOfFansInStadium(int newNumber) {
		maxNumberOfFansInStadium = newNumber;
	}
	
	public static Integer getMinTimeOfControl() {
		return minTimeOfControl;
	}

	public static void setMinTimeOfControl(int minTime) {
		minTimeOfControl = minTime;
	}

	public static Integer getMaxTimeOfControl() {
		return maxTimeOfControl;
	}

	public static void setMaxTimeOfControl(int maxTime) {
		maxTimeOfControl = maxTime;
	}

	public static Integer getMinTimeBetweenSpawningFans() {
		return minTimeBetweenSpawningFans;
	}

	public static void setMinTimeBetweenSpawningFans(int minTime) {
		minTimeBetweenSpawningFans = minTime;
	}

	public static Integer getMaxTimeBetweenSpawningFans() {
		return maxTimeBetweenSpawningFans;
	}

	public static void setMaxTimeBetweenSpawningFans(int maxTime) {
		maxTimeBetweenSpawningFans = maxTime;
	}
	
	private void loadImages() {
		try {
			img = ImageIO.read(new File("C:/Users/Konrad/Desktop/java/Stadion/Images/stadion.jpg"));
			img2 = ImageIO.read(new File("C:/Users/Konrad/Desktop/java/Stadion/Images/powrotDoMenu.jpg"));
			img3 = ImageIO.read(new File("C:/Users/Konrad/Desktop/java/Stadion/Images/opcje.jpg"));
			img4 = ImageIO.read(new File("C:/Users/Konrad/Desktop/java/Stadion/Images/restart.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, -600, 0, null);
		g.drawImage(img2, 110, 100, null);
		g.drawImage(img3, 110, 200, null);
		g.drawImage(img4, 110, 300, null);
		police.drawPolice(g);
		g.setFont(new Font("Tahoma", Font.PLAIN, 20));
		g.setColor(Color.YELLOW);
		g.drawString("Liczba kibiców na stadionie: " + numberOfFansInStadium, 50, 50);
		g.drawString("Maksymalna liczba kibiców", 50, 400);
		g.drawString("na stadionie: " + maxNumberOfFansInStadium, 85, 450);
		for(int i=0; i<QueueManagement.fanQ.size(); i++) {
			QueueManagement.fanQ.get(i).drawFan(g);
		}
		for(int i=0; i<QueueManagement.control1.size(); i++) {
			QueueManagement.control1.get(i).drawFan(g);
		}
		for(int i=0; i<QueueManagement.control2.size(); i++) {
			QueueManagement.control2.get(i).drawFan(g);
		}
		for(int i=0; i<QueueManagement.control3.size(); i++) {
			QueueManagement.control3.get(i).drawFan(g);
		}
		for(int i=0; i<QueueManagement.tempQ.size(); i++) {
			QueueManagement.tempQ.get(i).drawFan(g);
		}
	}
	
	public void move() {
		for(int i=0; i<QueueManagement.fanQ.size(); i++) {
			QueueManagement.fanQ.get(i).move();
		}
		for(int i=0; i<QueueManagement.control1.size(); i++) {
			QueueManagement.control1.get(i).move();
		}
		for(int i=0; i<QueueManagement.control2.size(); i++) {
			QueueManagement.control2.get(i).move();
		}
		for(int i=0; i<QueueManagement.control3.size(); i++) {
			QueueManagement.control3.get(i).move();
		}
		for(int i=0; i<QueueManagement.tempQ.size(); i++) {
			QueueManagement.tempQ.get(i).move();
		}
	}
	
	public static void reset() {
		isSimulationGoing = false;
		QueueManagement.fanQ.clear();
		QueueManagement.control1.clear();
		QueueManagement.control2.clear();
		QueueManagement.control3.clear();
		QueueManagement.tempQ.clear();
		QueueManagement.setEndOfTempQX(550);
		QueueManagement.setEndOfTempQY(350);
		QueueManagement.setEndOfQX(600);
		QueueManagement.setEndOfQY(250);
		setNumberOfFansInStadium(0);
	}
	
	private void loadButtons() {
		int buttonWidth = 150;
		int buttonHeight = 30;
		menu = new JButton("Powrót do menu");
		menu.setVisible(false);
		menu.setBounds(110, 100, buttonWidth, buttonHeight);
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainStadion.setMenuPoint(true);
				reset();
				unseenButtons();
				MainStadion.getTimer().stop();
			}		
		});
		opcje = new JButton("Opcje");
		opcje.setVisible(false);
		opcje.setBounds(110, 200, buttonWidth, buttonHeight);
		opcje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainStadion.setOptionsPoint(true);
				reset();
				unseenButtons();
				MainStadion.getTimer().stop();
			}	
		});
		reset = new JButton("Restart");
		reset.setVisible(false);
		reset.setBounds(110, 300, buttonWidth, buttonHeight);
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
				isSimulationGoing = true;
			}		
		});
		MainStadion.getWindow().add(menu);
		MainStadion.getWindow().add(opcje);
		MainStadion.getWindow().add(reset);
	}
	
	public void seenButtons() {
		menu.setVisible(true);
		opcje.setVisible(true);
		reset.setVisible(true);
	}
	
	private void unseenButtons() {
		menu.setVisible(false);
		opcje.setVisible(false);
		reset.setVisible(false);
	}
	
}
