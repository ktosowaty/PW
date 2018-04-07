package stadion;

import java.util.LinkedList;

public class QueueManagement {
	
	private static int endOfQX = 600;
	private static int endOfQY = 250;
	private static int endOfTempQX = 540;
	private static int endOfTempQY = 350;
	public static LinkedList<Fan> fanQ = new LinkedList<Fan>();
	public static LinkedList<Fan> tempQ = new LinkedList<Fan>();
	public static LinkedList<Fan> control1;
	public static LinkedList<Fan> control2;
	public static LinkedList<Fan> control3;
	
	public QueueManagement() {
		
	}
	
	public static int getEndOfQX() {
		return endOfQX;
	}
	
	public static void setEndOfQX(int x) {
		endOfQX = x;
	}
	
	public static int getEndOfQY() {
		return endOfQY;
	}
	
	public static void setEndOfQY(int y) {
		endOfQY = y;
	}
	
	public static int getEndOfTempQX() {
		return endOfTempQX;
	}
	
	public static void setEndOfTempQX(int x) {
		endOfTempQX = x;
	}
	
	public static int getEndOfTempQY() {
		return endOfTempQY;
	}
	
	public static void setEndOfTempQY(int y) {
		endOfTempQY = y;
	}
	
}
