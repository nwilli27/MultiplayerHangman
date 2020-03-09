package view;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import javafx.scene.text.Text;

public class MainPageCodeBehind {

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

	private Controller controller;

	@FXML
	void initialize() {
		this.controller = new Controller();
		this.hideAll();
	}

	@FXML
	void handleGuessButtonClicked(MouseEvent event) {
		this.controller.handleGuess(this.guessTextField.getText());
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
