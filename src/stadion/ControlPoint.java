package stadion;

import java.util.LinkedList;
import java.util.Random;

public class ControlPoint implements Runnable {
	
	private int x, y, controlNumber;
	private LinkedList<Fan> control = new LinkedList<Fan>();
	Random rand = new Random();
	
	public ControlPoint(int x, int y, int controlNumb) {
		this.x = x;
		this.y = y;
		this.controlNumber = controlNumb;
		if(this.controlNumber==1) {
			QueueManagement.control1 = control;
		} else if(this.controlNumber==2) {
			QueueManagement.control2 = control;
		} else if(this.controlNumber==3) {
			QueueManagement.control3 = control;
		}
	}
	
	public void run() {
		while(true) {
			while(SimulationPanel.getIsSimulationGoing()==true && ((SimulationPanel.getNumberOfFansInStadium() 
					+ QueueManagement.tempQ.size() + QueueManagement.control1.size() + QueueManagement.control2.size() 
					+ QueueManagement.control3.size()) < SimulationPanel.getMaxNumberOfFansInStadium()
					|| QueueManagement.tempQ.isEmpty()==false)) {		
				SimulationPanel.lock1.lock();
				enter();
				SimulationPanel.lock1.unlock();
				while(control.isEmpty()==false && control.getLast().getStatus()!=3) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				try {
					Thread.sleep(rand.nextInt(SimulationPanel.getMaxTimeOfControl()-SimulationPanel.getMinTimeOfControl())
							+SimulationPanel.getMinTimeOfControl());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				SimulationPanel.lock2.lock();
				exit();
				SimulationPanel.lock2.unlock();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}				
		}		
	}
	
	private void enter() {		
		String tempTeam;
		int i = 0;
		int j = 3;
		int help = 0;
		boolean wasTempQEmpty;
		while(QueueManagement.tempQ.isEmpty()==true && (QueueManagement.fanQ.isEmpty()==true
				|| QueueManagement.fanQ.getFirst().getStatus()==0)) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(QueueManagement.tempQ.isEmpty()==true) {
			tempTeam = QueueManagement.fanQ.getFirst().getTeam();
			wasTempQEmpty = true;
		} else {
			tempTeam = QueueManagement.tempQ.getFirst().getTeam();
			wasTempQEmpty = false;
		}
		
		if(wasTempQEmpty==false && QueueManagement.tempQ.size()>=3) {
			j = 3;
		} else {
			if(wasTempQEmpty==false && QueueManagement.tempQ.size()<3) {
				j = QueueManagement.tempQ.size();
				help = 0;
			} else if(wasTempQEmpty==true) {
				j = 1;
				help = 1;
			}
			while(j<3 && QueueManagement.fanQ.isEmpty()==false && (SimulationPanel.getNumberOfFansInStadium() 
			+ QueueManagement.tempQ.size() + QueueManagement.control1.size() + QueueManagement.control2.size() 
			+ QueueManagement.control3.size() + help) < SimulationPanel.getMaxNumberOfFansInStadium() && help<QueueManagement.fanQ.size()) {
				if(tempTeam==QueueManagement.fanQ.get(help).getTeam()) {
					j++;
				}
				help++;
			}
		}
		
		while(i<j && (QueueManagement.fanQ.getFirst().getStatus()==1 || QueueManagement.tempQ.isEmpty()==false))  {
			if(QueueManagement.tempQ.isEmpty()==false && wasTempQEmpty==false) {
				shiftingTempQ();
				control.add(QueueManagement.tempQ.removeFirst());
				control.get(i).setStatus(2);
				control.get(i).setQX(x+i*30);
				control.get(i).setQY(y);
				i++;
			} else {
				wasTempQEmpty = true;
				if(QueueManagement.fanQ.getFirst().getStatus()==1) {
					if(QueueManagement.fanQ.getFirst().getTeam()==tempTeam) {
						shiftingFanQ();
						control.add(QueueManagement.fanQ.removeFirst());
						control.get(i).setStatus(2);
						control.get(i).setQX(x+i*30);
						control.get(i).setQY(y);
						i++;
					} else {
						shiftingFanQ();
						QueueManagement.tempQ.add(QueueManagement.fanQ.removeFirst());
						QueueManagement.tempQ.getLast().setQX(QueueManagement.getEndOfTempQX());
						QueueManagement.tempQ.getLast().setQY(QueueManagement.getEndOfTempQY());
						QueueManagement.setEndOfTempQX(QueueManagement.getEndOfTempQX()+30);
					}
				} else {
					break;
				}		
			}
		}
	}
	
	private void shiftingFanQ() {
		for(int i=QueueManagement.fanQ.size()-1; i>=1; i--) {
			QueueManagement.fanQ.get(i).setQX(QueueManagement.fanQ.get(i-1).getQX());
			QueueManagement.fanQ.get(i).setQY(QueueManagement.fanQ.get(i-1).getQY());
		}
		QueueManagement.setEndOfQX(QueueManagement.getEndOfQX()-30);
	}
	
	private void shiftingTempQ() {
		for(int i=QueueManagement.tempQ.size()-1; i>=1; i--) {
			QueueManagement.tempQ.get(i).setQX(QueueManagement.tempQ.get(i-1).getQX());
			QueueManagement.tempQ.get(i).setQY(QueueManagement.tempQ.get(i-1).getQY());
		}
		QueueManagement.setEndOfTempQX(QueueManagement.getEndOfTempQX()-30);
	} 
	
	private void exit() {
		SimulationPanel.setNumberOfFansInStadium(SimulationPanel.getNumberOfFansInStadium()+control.size());
		control.clear();
	}
	
}
