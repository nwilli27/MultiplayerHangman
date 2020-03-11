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
		this.timer.schedule(new RemindTask(TimedTaskType.FinalTimeout), this.seconds * 1000);
	}
	
	public void setNudgeTask() {
		this.timer.schedule(new RemindTask(TimedTaskType.Nudge), this.seconds * 1000);
	}

	/**
	 * 
	 * @author csuser
	 *
	 */
    class RemindTask extends TimerTask {
        
    	private TimedTaskType type;
    	
    	public RemindTask(TimedTaskType taskType) {
    		this.type = taskType;
    	}
    	
		public void run() {
            
			switch (this.type) {
				
				case Nudge:
					
					
				case FinalTimeout:
					
			}
            timer.cancel();
        }

    }

}
