package view;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class MainPageCodeBehind {

	
	public MainPageCodeBehind() {
		
	}
	
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
	    
	    private Controller controller;

	    @FXML
	    void initialize() {
	        this.controller = new Controller();

	    }
	    
	    @FXML
	    void handleGuessButtonClicked(MouseEvent event) {
	    	this.controller.handleGuess(this.guessTextField.getText());

	    }
}
