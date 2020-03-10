package application;
	

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class Main extends Application {
	
	public static final String LOGIN_PAGE_TITLE = "HangMan";
	public static final String LOGIN_PAGE_VIEW = "../view/LoginPage.fxml";
	public static final String MAIN_PAGE_VIEW = "../view/MainPage.fxml";
	
	
	@Override
	public void start(Stage primaryStage) {
	try {
		FXMLLoader loader = new FXMLLoader(); 
		loader.setLocation(Main.class.getResource(LOGIN_PAGE_VIEW));
		loader.load();
		Scene scene = new Scene(loader.getRoot());
		primaryStage.setTitle(LOGIN_PAGE_TITLE);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	public static void main(String[] args) {
		launch(args);
	}


}
