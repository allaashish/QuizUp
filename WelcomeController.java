/**
 * 
 */
package FirstPart;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Welcoming to the game and selecting of 
 * topics are done in this class
 *
 * @author Alla
 *
 */
public class WelcomeController {

	@FXML
	private Button moviesButton;
	
	@FXML
	private Button gotButton;

	@FXML
	private Button pornButton;

	@FXML
	private Button morePornButton;

	@FXML
	private Label nameEntered;

	@FXML
	public void initialize(String name) {
		this.nameEntered.setText(name);
	}
	
	private void setQuestion(ActionEvent event, String topic) throws MyException{
		try {

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("QuestionsGUI.fxml"));

			Scene scene = new Scene(fxmlLoader.load(), 600, 400);
			
			QuestionsController questionsController = fxmlLoader.<QuestionsController>getController();
			questionsController.initialize(topic);

			Stage stage = new Stage();

			stage.setTitle("Questions Window");
			stage.setScene(scene);

			stage.show();

			((Node) (event.getSource())).getScene().getWindow().hide();

		} catch (IOException e) {
			Logger logger = Logger.getLogger(getClass().getName());
			logger.log(Level.SEVERE, "Failed to create question Window", e);
		}
		
	}
	
	@FXML
	private void moviesButtonAction(ActionEvent event) throws MyException {
		setQuestion(event, Constants.CATEGORY1);
	}
	
	@FXML
	private void gotButtonAction(ActionEvent event) throws MyException{
		setQuestion(event, Constants.CATEGORY2);
	}

	@FXML
	private void pornButtonAction(ActionEvent event) throws MyException {
		setQuestion(event, Constants.CATEGORY3);
	}
	
	@FXML
	private void morePornButtonAction(ActionEvent event) throws MyException{
		setQuestion(event, Constants.CATEGORY4);
	}

}
