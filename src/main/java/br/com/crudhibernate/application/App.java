package br.com.crudhibernate.application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

	private static Scene mainScene;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/crudhibernate/view/View.fxml"));
			
			
			mainScene = new Scene(loader.load());
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Crud Hibernate c/ JPA");
			primaryStage.show();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

	public static void main(String[] args) {
		launch(args);

	}

}
