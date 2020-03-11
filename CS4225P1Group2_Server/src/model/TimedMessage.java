package model;

import java.util.Timer;
import enums.TimedTaskType;

/**
 * Class holds functionality for a timed message.
 * 
 * @author Nolan W, Carson B, Tristen R
 *
 */
public class TimedMessage {

	private Timer timer;
	private int seconds;

	/**
	 * Creates a TimedMessage object with the given seconds and initializes the
	 * timer
	 * 
	 * @param seconds Number of seconds
	 */
	public TimedMessage(int seconds) {
		this.timer = new Timer();
		this.seconds = seconds;
	}

	/**
	 * Sets the task to time out a user if they haven't acted in a given amount of
	 * time
	 * 
	 * @param username Username of player to be timed out
	 * @param currentGuessCount the guess count
	 */
	public void setTimeoutTask(String username, int currentGuessCount) {
		this.timer.schedule(new RemindTask(TimedTaskType.FinalTimeout, this.timer, username, currentGuessCount), this.seconds * 1000);
	}

	/**
	 * Sets the task to nudge a user if they haven't acted in a given amount of time
	 * to alert them to act
	 * 
	 * @param username Username of player to be nudged
	 * @param currentGuessCount the guess count 
	 */
	public void setNudgeTask(String username, int currentGuessCount) { 
		this.timer.schedule(new RemindTask(TimedTaskType.Nudge, this.timer, username, currentGuessCount), this.seconds * 1000); 
    }

}
