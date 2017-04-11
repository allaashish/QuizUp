package FirstPart;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class to start the application.
 * 
 * @author Alla
 */
public class QuizUp extends Application {

	@Override
	public void start(Stage primaryStage) throws MyException {
		primaryStage.setTitle(Constants.STAGE_TITLE1);
		
		try {
			FXMLLoader loader = new FXMLLoader(QuizUp.class.getResource("OpeningGUI.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Scene scene = new Scene(page);
			
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			Logger logger = Logger.getLogger(getClass().getName());
			logger.log(Level.SEVERE, "Failed to create main Window", e);
			
			throw new MyException("Exception occured while creating main window", e);

		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}