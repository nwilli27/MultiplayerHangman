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
	private int currentGuessCount;

	/**
	 * Creates a RemindTask object with the given timer, username, and task type
	 * 
	 * @param taskType The TaskType
	 * @param timer    The Timer
	 * @param username The username
	 * @param currentGuessCount of the user
	 */
	RemindTask(TimedTaskType taskType, Timer timer, String username, int currentGuessCount) {
		this.type = taskType;
		this.timer = timer;
		this.username = username;
		this.currentGuessCount = currentGuessCount;
	}

	/**
	 * Determines the type of task and runs the execution.
	 * @precondition: none
	 * @postcondition none
	 */
	public void run() {

		switch (this.type) {

			case Nudge:
				ClientManager.sendCurrentClientNudge(this.username, this.currentGuessCount);
				break;

			case FinalTimeout:
				ClientManager.disconnectCurrentClient(this.username, this.currentGuessCount);
				break;

			default:
				break;
		}
		this.timer.cancel();
	}

}