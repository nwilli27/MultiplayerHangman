package model;

import java.util.Timer;
import java.util.TimerTask;

import enums.TimedTaskType;

public class TimedMessage {

	private Timer timer;
	private int seconds;
	
	/**
	 * 
	 * @param seconds
	 */
	public TimedMessage(int seconds) {
        this.timer = new Timer();
        this.seconds = seconds;
	}
	
	public void setTimeoutTask() {
		//this.timer.schedule(new RemindTask(), this.seconds * 1000);
	}

	/**
	 * 
	 * @author csuser
	 *
	 */
    class RemindTask extends TimerTask {
        
    	public RemindTask(TimedTaskType taskType) {
    		
    	}
    	
		public void run() {
            
            timer.cancel();
        }
    }

}
