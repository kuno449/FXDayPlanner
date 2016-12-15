package app;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
		
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ui/Interface.fxml"));
			Scene scene = new Scene(root,580,820);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("ui/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("FXDayPlanner");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	public static void main(String[] args)  {
		launch(args);
  	}

}