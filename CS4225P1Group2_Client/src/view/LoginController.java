package view;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Listener;

/**
 * Controller for the login page
 * 
 * @author Carson Bedrosian, Tristen Rivera, Nolan Williams
 *
 */
public class LoginController {

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

	private Scene scene;

	private static LoginController instance;

	private static MainPageController controller;

	/**
	 * Creates a login controller object and initialized instance
	 */
	public LoginController() {
		instance = this;
	}

	/**
	 * Gets the instance of the controller
	 * @return the instance of the controller
	 */
	public static LoginController getInstance() {
		return instance;
	}

	@FXML
	void handleLogin(MouseEvent event) throws IOException {

		String username = this.usernameTextField.getText();
		if (username.isEmpty() || username.isBlank()) {
			this.errorText.setVisible(true);
			return;
		} else {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
			@SuppressWarnings("unused")
			Parent window = (Pane) fxmlLoader.load();
			controller = fxmlLoader.<MainPageController>getController();
			Listener reader = new Listener(username, controller);
			Thread thread = new Thread(reader);
			thread.start();

		}

	}

	/**
	 * Shows the main page scene
	 * @throws IOException exception 
	 */
	public void showScene() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
		Parent window = (Pane) fxmlLoader.load();
		this.scene = new Scene(window);
		Platform.runLater(() -> {
			Stage stage = (Stage) this.createUsernameText.getScene().getWindow();
//            stage.setResizable(true);
//            stage.setWidth(800);
//            stage.setHeight(600);

			stage.setOnCloseRequest((WindowEvent e) -> {
				Platform.exit();
				System.exit(0);
			});
			stage.setScene(this.scene);
//            stage.setMinWidth(800);
//            stage.setMinHeight(300);
//            stage.centerOnScreen();
		});
	}

	/**
	 * Shows a message from the server on the gui
	 * @param message the message to be shown
	 */
	public void showServerMessage(String message) {
		this.userNameTakenText.setText(message);

	}
}
