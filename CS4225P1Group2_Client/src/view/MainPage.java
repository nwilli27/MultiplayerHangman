package view;

import java.util.ArrayList;


import controller.MainPageController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class MainPage {

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
	
	private ArrayList<BodyPart> bodyParts;
	
	private MainPageController controller;

	@FXML
	void initialize() {
		this.hideAll();
		this.bodyParts = new ArrayList<BodyPart>();
		this.addBodyParts();
		this.guessButton.setDisable(true);
		this.controller = new MainPageController(this.messageFromServerText, this.guessedLettersTextArea, this.guessButton, this.wordGuessBox, this.gameStatusText, this.bodyParts);
	}

	private void addBodyParts() {
		var head = new BodyPart();
		head.addPart(this.leftEye1);
		head.addPart(this.leftEye2);
		head.addPart(this.rightEye1);
		head.addPart(this.rightEye2);
		head.addPart(this.mouthUpper);
		head.addPart(this.mouthLower);
		head.addPart(this.head);
		var body = new BodyPart();
		body.addPart(this.body);
		var leftArm = new BodyPart();
		leftArm.addPart(this.leftArm);
		var rightArm = new BodyPart();
		rightArm.addPart(this.rightArm);
		var leftLeg = new BodyPart();
		leftLeg.addPart(this.leftLeg);
		var rightLeg = new BodyPart();
		rightLeg.addPart(this.rightLeg);
		
		
		this.bodyParts.add(head);
		this.bodyParts.add(body);
		this.bodyParts.add(leftArm);
		this.bodyParts.add(rightArm);
		this.bodyParts.add(leftLeg);
		this.bodyParts.add(rightLeg);
	}

	@FXML
	void handleGuessButtonClicked(MouseEvent event) {

		if (!this.guessTextField.getText().isEmpty()) {

			String guess = this.guessTextField.getText();
			if (guess.length() == 1) {
				if (this.guessedLettersTextArea.getText().contains(guess)) {

					this.errorTextField.setText(guess + " has already been guessed.");

				} else {

					this.errorTextField.setText("");
					this.guessedLettersTextArea.appendText(guess + System.lineSeparator());
					this.controller.handleGuess(guess);
					this.guessButton.setDisable(true);
				}
			} else {
				this.errorTextField.setText("");
				// SEND WORD GUESS
				System.out.println("word guess");
			}
		}

		this.guessTextField.setText("");
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
