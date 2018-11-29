package principal;

import conexaoHSQLDB.CNXHSQLDB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application{
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = FXMLLoader.load(getClass().getResource("/ui/FXMLCarro.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Carros");
			primaryStage.show();
			
			primaryStage.setOnCloseRequest(
					  event -> {
					  Alert alert = new Alert(AlertType.INFORMATION);
				    	alert.setTitle("Sair");
				    	alert.setHeaderText("Obrigado por utilizar esse software!");
				    	alert.setContentText("=)");
				    	alert.show();
					  }
				);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
//		System.out.println(new CNXHSQLDB().conectar());
	}

}
