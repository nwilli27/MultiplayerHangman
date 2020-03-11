package model;

import java.util.Timer;
import java.util.TimerTask;

import enums.TimedTaskType;

/**
 * Class holds functionality for a timed task.
 * @author Nolan W, Carsen B, Tristen R
 */
class RemindTask extends TimerTask {

	private TimedTaskType type;
	private Timer timer;
	private String username;

	/**
	 * Creates a RemindTask object with the given timer, username, and task type
	 * 
	 * @param taskType The TaskType
	 * @param timer    The Timer
	 * @param username The username
	 */
	RemindTask(TimedTaskType taskType, Timer timer, String username) {
		this.type = taskType;
		this.timer = timer;
		this.username = username;
	}

	/**
	 * Determines the type of task and runs the execution.
	 * @precondition: none
	 * @postcondition none
	 */
	public void run() {

		switch (this.type) {

			case Nudge:
				ClientManager.sendCurrentClientNudge(this.username);
				break;

			case FinalTimeout:
				ClientManager.disconnectCurrentClient(this.username);
				break;

			default:
				break;
		}
		this.timer.cancel();
	}

}