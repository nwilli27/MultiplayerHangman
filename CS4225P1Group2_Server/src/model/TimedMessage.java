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
	
	public void setTimeoutTask() {
		this.timer.schedule(new RemindTask(TimedTaskType.FinalTimeout, this.timer), this.seconds * 1000);
	}
	
	public void setNudgeTask() {
		this.timer.schedule(new RemindTask(TimedTaskType.Nudge, this.timer), this.seconds * 1000);
	}

	/**
	 * 
	 * @author csuser
	 *
	 */
    class RemindTask extends TimerTask {
        
    	private TimedTaskType type;
    	private Timer timer;
    	
    	public RemindTask(TimedTaskType taskType, Timer timer) {
    		this.type = taskType;
    		this.timer = timer;
    	}
    	
		public void run() {
            
			switch (this.type) {
				
				case Nudge:
					ClientManager.sendCurrentClientNudge();
					break;
					
				case FinalTimeout:
					ClientManager.disconnectCurrentClient();
					break;
					
			}
            this.timer.cancel();
        }

    }

}
