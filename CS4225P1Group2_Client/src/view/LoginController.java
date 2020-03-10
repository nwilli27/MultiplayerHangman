package view;

import java.io.IOException;

import application.Main;
import controller.Controller;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
    
    private Scene scene;
    
    private static LoginController instance;
    
    public static MainPageController controller;
    

   public LoginController() {
	   instance = this;
   }
   
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
			Parent window = (Pane) fxmlLoader.load();
			controller = fxmlLoader.<MainPageController>getController();
			Listener reader = new Listener(username, controller);
			Thread thread = new Thread(reader);
			thread.start();
			this.scene = new Scene(window);
		}
		
    }
    
  
    
    public void showScene() {
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
}
