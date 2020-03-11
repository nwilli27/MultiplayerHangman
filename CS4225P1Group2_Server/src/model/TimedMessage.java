package model;

import java.util.Timer;
import java.util.TimerTask;
import enums.TimedTaskType;

/**
 * Class holds functionality for a timed message.
 * @author Nolan W, Carsen B, Tristen R
 *
 */
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
	
	public void setTimeoutTask(String username) {
		this.timer.schedule(new RemindTask(TimedTaskType.FinalTimeout, this.timer, username), this.seconds * 1000);
	}
	
	public void setNudgeTask(String username) {
		this.timer.schedule(new RemindTask(TimedTaskType.Nudge, this.timer, username), this.seconds * 1000);
	}

	/**
	 * 
	 * @author csuser
	 *
	 */
    class RemindTask extends TimerTask {
        
    	private TimedTaskType type;
    	private Timer timer;
    	private String username;
    	
    	public RemindTask(TimedTaskType taskType, Timer timer, String username) {
    		this.type = taskType;
    		this.timer = timer;
    		this.username = username;
    	}
    	
		public void run() {
            
			switch (this.type) {
				
				case Nudge:
					ClientManager.sendCurrentClientNudge(this.username);
					break;
					
				case FinalTimeout:
					ClientManager.disconnectCurrentClient(this.username);
					break;
					
			}
            this.timer.cancel();
        }

    }

}
