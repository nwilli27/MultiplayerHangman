package view;

import java.io.IOException;

import application.Main;
import controller.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * Controller for the login page
 * 
 * @author Carson Bedrosian, Tristen Rivera, Nolan Williams
 *
 */
public class LoginPage {

	@FXML
	private TextField usernameTextField;

	@FXML
	private Text welcomeText;

	@FXML
	private Text errorText;

	@FXML
	private Text createUsernameText;

	@FXML
	private Button loginButton;

	@FXML
	private Text userNameTakenText;
	
	private LoginController controller;

	@FXML
	void initialize() {
		this.controller = new LoginController(this.errorText, this.userNameTakenText);
		
	}

	@FXML
	void handleLogin(MouseEvent event) throws IOException {

		String username = this.usernameTextField.getText();
		if (username.isEmpty() || username.isBlank()) {
			this.errorText.setVisible(true);
			return;
		} else {
			if(this.controller.handleLogin(this.usernameTextField.getText())) {
				Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource(Main.MAIN_PAGE_VIEW));
				loader.load();
				Scene sceneToNavigateTo = new Scene(loader.getRoot());
				currentStage.setScene(sceneToNavigateTo);
			}

		}

	}
	
	/**
	 * Shows a message from the server on the gui
	 * @param message the message to be shown
	 */
	public void showServerMessage(String message) { //TODO Get rid of this
		this.userNameTakenText.setText(message);

	}
}
