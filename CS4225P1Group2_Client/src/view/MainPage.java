package view;

import java.io.IOException;
import java.util.ArrayList;

import application.Main;
import controller.MainPageController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
		this.controller = new MainPageController(this.messageFromServerText, this.guessedLettersTextArea,
				this.guessButton, this.wordGuessBox, this.gameStatusText, this.bodyParts);
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
			this.checkIfLetterOrWordGuess(guess);
		}

		this.guessTextField.setText("");
	}

	@FXML
	void handleLogOut(MouseEvent event) {

		MainPageController.handleLogOut();
		System.exit(0);
	}

	private void checkIfLetterOrWordGuess(String guess) {
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
			this.controller.handleGuess(guess);
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

	private void hideHead() {
		this.leftEye1.setVisible(false);
		this.leftEye2.setVisible(false);
		this.rightEye1.setVisible(false);
		this.rightEye2.setVisible(false);
		this.mouthUpper.setVisible(false);
		this.mouthLower.setVisible(false);
		this.head.setVisible(false);

	}

	private void hideLeftArm() {
		this.leftArm.setVisible(false);
	}

	private void hideRightArm() {
		this.rightArm.setVisible(false);
	}

	private void hideLeftLeg() {
		this.leftLeg.setVisible(false);
	}

	private void hideRightLeg() {
		this.rightLeg.setVisible(false);
	}

	private void hideBody() {
		this.body.setVisible(false);
	}

}
