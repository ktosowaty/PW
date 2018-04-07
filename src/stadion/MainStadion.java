package stadion;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;

public class MainStadion {

	private static JFrame window;
	private static final int width = 1200; 
	private static final int height = 630;
	private static boolean simulationPoint = false; 
	private static boolean optionsPoint = false;
	private static boolean menuPoint = false;
	private static boolean aboutPoint = false;
	private static Timer timer;
	private static Random rand = new Random();
	
	public MainStadion() {
		window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(width, height);
		window.setLocationRelativeTo(null);
		window.setTitle("Stadion");
		window.setResizable(false);
	}
	
	public static JFrame getWindow() {
		return window;
	}
	
	public static int getWidth() {
		return width;
	}
	
	public static int getHeight() {
		return height;
	}
	
	public static Timer getTimer() {
		return timer;
	}
	
	public static void setSimulationPoint(boolean sp) {
		simulationPoint = sp;
	}
	
	public static void setOptionsPoint(boolean op) {
		optionsPoint = op;
	}
	
	public static void setMenuPoint(boolean menp) {
		menuPoint = menp;
	}
	
	public static void setAboutPoint(boolean ap) {
		aboutPoint = ap;
	}
	
	private void rendering() {
		MenuPanel mp = new MenuPanel();	
		SimulationPanel sp = new SimulationPanel();
		OptionsPanel op = new OptionsPanel();
		AboutPanel ap = new AboutPanel();
		timer = new Timer(20, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sp.repaint();
				sp.move();
			}
		});
		Container c = mp;
		window.add(c);
		mp.seenButtons();
		window.setVisible(true);
		while(true) {
			while(MainStadion.simulationPoint==false && MainStadion.optionsPoint==false 
					&& MainStadion.aboutPoint==false && MainStadion.menuPoint == false) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			c.setVisible(false);
			if(MainStadion.simulationPoint==true) {
				c = sp;
				window.add(c);			
				sp.seenButtons();
				MainStadion.simulationPoint = false;
			} else if(MainStadion.optionsPoint==true) {
				c = op;
				window.add(c);			
				op.seenButtons();
				MainStadion.optionsPoint = false;
			} else if(MainStadion.aboutPoint==true) {
				c = ap;
				window.add(c);
				ap.seenButtons();
				MainStadion.aboutPoint = false;
			} else if(MainStadion.menuPoint==true) {
				c = mp;
				window.add(mp);	
				mp.seenButtons();
				MainStadion.menuPoint = false;
			}
			c.setVisible(true);
			if(c == sp) {
				timer.start();
				SimulationPanel.setIsSimulationGoing(true);
			} else {
				timer.stop();
			}
		}		
	}
	
	public static void main(String[] args) {
		MainStadion stadion = new MainStadion();
		Thread fans = new Thread(new Runnable() {
			public void run() {
				while(true) {
					if(SimulationPanel.getIsSimulationGoing() == true) {
						try {
							Thread.sleep(rand.nextInt(SimulationPanel.getMaxTimeBetweenSpawningFans()-
									SimulationPanel.getMinTimeBetweenSpawningFans())+SimulationPanel.getMinTimeBetweenSpawningFans());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						QueueManagement.fanQ.add(new Fan(1200, 250));
					} else {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}			
		});
		fans.start();
		Thread cp1 = new Thread(new ControlPoint(375, 150, 1));
		Thread cp2 = new Thread(new ControlPoint(375, 350, 2));
		Thread cp3 = new Thread(new ControlPoint(375, 550, 3));
		cp1.start();
		cp2.start();
		cp3.start();
		stadion.rendering();
	}

}
