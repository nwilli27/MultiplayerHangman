package view;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import javafx.scene.text.Text;

public class MainPageController {

	@FXML
	private Text serverLabel;

	@FXML
	private TextArea guessedLettersTextArea;

	@FXML
	private Line hmTallLine;

	@FXML
	private Line hmHorizontalLine;

	@FXML
	private Line hmShortLine;

	@FXML
	private Line hmBaseLine;

	@FXML
	private TextField guessTextField;

	@FXML
	private Button guessButton;

	@FXML
	private Text guessText;
	
	@FXML
	private Text gameStatusText;
	
	@FXML
	private Text errorTextField;

	@FXML
	private Circle head;

	@FXML
	private Line body;

	@FXML
	private Line leftLeg;

	@FXML
	private Line rightLeg;

	@FXML
	private Line leftArm;

	@FXML
	private Line rightArm;

	@FXML
	private Line leftEye1;

	@FXML
	private Line leftEye2;

	@FXML
	private Line rightEye1;

	@FXML
	private Line rightEye2;

	@FXML
	private QuadCurve mouthUpper;

	@FXML
	private Line mouthLower;

	@FXML
	private Text wordGuessBox;

	@FXML
    private TextArea messageFromServerText;	

	private ArrayList<String> lettersGuessed;

	@FXML
	void initialize() {
		this.hideAll();
		this.lettersGuessed = new ArrayList<String>();
	}

	@FXML
	void handleGuessButtonClicked(MouseEvent event) {

		if (!this.guessTextField.getText().isEmpty()) {

			String guess = this.guessTextField.getText();
			if (guess.length() == 1) {
				if (this.lettersGuessed.contains(guess)) {

					this.errorTextField.setText(guess + " has already been guessed.");

				} else {

					this.errorTextField.setText("");
					this.lettersGuessed.add(guess);
					this.setGuessedLettersextArea();
					// SEND LETTER GUESS
					System.out.println("letter guess");
				}
			} else {
				this.errorTextField.setText("");
				// SEND WORD GUESS
				System.out.println("word guess");
			}
		}

		this.guessTextField.setText("");
	}

	private void setGuessedLettersextArea() {
		this.guessedLettersTextArea.setText("");
		for (var letter : this.lettersGuessed) {
			this.guessedLettersTextArea.appendText(letter + System.lineSeparator());
		}
	}

	private void hideAll() {
		this.hideHead();
		this.hideLeftArm();
		this.hideRightArm();
		this.hideLeftLeg();
		this.hideRightLeg();
		this.hideBody();
	}

	private void showAll() {
		this.showHead();
		this.showLeftArm();
		this.showRightArm();
		this.showLeftLeg();
		this.showRightLeg();
		this.showBody();
	}

	/**
	 * Shows message from server on gui
	 * 
	 * @param message Message from server
	 */
	public void showServerMessage(String message) {

	}

	/**
	 * Updates the gui after a wrong guess
	 */
	public void handleNextWrongGuess() {
		// TODO Auto-generated method stub

	}

	/**
	 * Updates the gui to reflect guessed characters
	 */
	public void updateGuessedCharacters() {
		// TODO Auto-generated method stub

	}

	/**
	 * Updates the hangman word with the guessed and the blank characters
	 * 
	 * @param word The word to update with the new value
	 */
	public void updateWordBox(String word) {
		this.wordGuessBox.setText(word);
	}

	public void setWrongGuesses(int amount) {

		if (amount == 0) {
			this.hideAll();
		} else if (amount == 1) {
			this.showHead();
		} else if (amount == 2) {
			this.showHead();
			this.showBody();
		} else if (amount == 3) {
			this.showHead();
			this.showBody();
			this.showLeftArm();
		} else if (amount == 4) {
			this.showHead();
			this.showBody();
			this.showLeftArm();
			this.showRightArm();
		} else if (amount == 5) {
			this.showHead();
			this.showBody();
			this.showLeftArm();
			this.showRightArm();
			this.showLeftLeg();
		} else if (amount == 6) {
			this.showHead();
			this.showBody();
			this.showLeftArm();
			this.showRightArm();
			this.showLeftLeg();
			this.showRightLeg();
			this.guessTextField.setDisable(true);
			this.guessButton.setDisable(true);
			this.gameStatusText.setText("GAME OVER");
			this.gameStatusText.setVisible(true);
		}
	}

	private void showHead() {
		this.leftEye1.setVisible(true);
		this.leftEye2.setVisible(true);
		this.rightEye1.setVisible(true);
		this.rightEye2.setVisible(true);
		this.mouthUpper.setVisible(true);
		this.mouthLower.setVisible(true);
		this.head.setVisible(true);
	}

	private void hideHead() {
		this.leftEye1.setVisible(false);
		this.leftEye2.setVisible(false);
		this.rightEye1.setVisible(false);
		this.rightEye2.setVisible(false);
		this.mouthUpper.setVisible(false);
		this.mouthLower.setVisible(false);
		this.head.setVisible(false);

	}

	private void showLeftArm() {
		this.leftArm.setVisible(true);
	}

	private void hideLeftArm() {
		this.leftArm.setVisible(false);
	}

	private void showRightArm() {
		this.rightArm.setVisible(true);
	}

	private void hideRightArm() {
		this.rightArm.setVisible(false);
	}

	private void showLeftLeg() {
		this.leftLeg.setVisible(true);
	}

	private void hideLeftLeg() {
		this.leftLeg.setVisible(false);
	}

	private void showRightLeg() {
		this.rightLeg.setVisible(true);
	}

	private void hideRightLeg() {
		this.rightLeg.setVisible(false);
	}

	private void showBody() {
		this.body.setVisible(true);
	}

	private void hideBody() {
		this.body.setVisible(false);
	}

}
