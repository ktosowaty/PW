package stadion;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class Fan {
	
	BufferedImage fan1, fan2;
	private int x;
	private int y;
	private int qX;
	private int qY;
	private String team;
	private int status = 0;
	Random rand = new Random();
	
	public Fan(int xCoor, int yCoor) {
		this.x = xCoor;
		this.y = yCoor;
		if(rand.nextInt(2) == 0) {
			this.team = "blue";
		} else {
			this.team = "red";
		}
		this.qX = QueueManagement.getEndOfQX();
		this.qY = QueueManagement.getEndOfQY();
		QueueManagement.setEndOfQX(QueueManagement.getEndOfQX()+30); 
		loadImage();
	}
	
	public int getQX() {
		return qX;
	}
	
	public void setQX(int newQX) {
		this.qX = newQX;
	}
	
	public int getQY() {
		return qY;
	}
	
	public void setQY(int newQY) {
		this.qY = newQY;
	}
		
	public String getTeam() {
		return team;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int newStatus) {
		this.status = newStatus;
	}
	
	private void loadImage() {
		try {
			fan1 = ImageIO.read(new File("C:/Users/Konrad/Desktop/java/Stadion/Images/fan1.jpg"));
			fan2 = ImageIO.read(new File("C:/Users/Konrad/Desktop/java/Stadion/Images/fan2.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void drawFan(Graphics g) {
		if(this.team == "blue") {
			g.drawImage(fan1, x, y, null);
		} else if(this.team == "red") {
			g.drawImage(fan2, x, y, null);
		}
	}
	
	public void move() {
		if(x>qX) {
			x--;
		} else if(x<qX) {
			x++;
		}
		if(y>qY) {
			y--;
		} else if(y<qY) {
			y++;
		}
		if(x==qX && y==qY && status==0) {
			status = 1;
		} else if(x==qX && y==qY && status==2){ 
			status = 3;
		}
	}
	
}
