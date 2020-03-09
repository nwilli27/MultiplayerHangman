package view;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginCodeBehind {

	@FXML
    private TextField usernameTextField;

    @FXML
    private Text welcomeText;

    @FXML
    private Text createUsernameText;

    @FXML
    private Button loginButton;

    @FXML
    void handleLogin(MouseEvent event) throws IOException {
    	//this.handleMouseClickToNavigateToDifferentPage(event, Main.MAIN_PAGE_VIEW);
    	Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource(Main.MAIN_PAGE_VIEW));
		loader.load();
		Scene sceneToNavigateTo = new Scene(loader.getRoot());
		currentStage.setScene(sceneToNavigateTo);
    }

    @FXML
    void initialize() {
        

    }
	
}
