package FirstPart;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController{

	@FXML
	private Button enteringButton;

	@FXML
	private TextField userName;

	@FXML
	private void enteringButtonAction(ActionEvent event) throws MyException {
		try {

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WelcomeGUI.fxml"));
						
			Scene scene = new Scene(fxmlLoader.load(), 600, 400);
			Stage stage = new Stage();
			
			WelcomeController welcomeController = fxmlLoader.<WelcomeController>getController();
			welcomeController.initialize(userName.getText().toString());

			stage.setTitle(Constants.STAGE_TITLE2);
			stage.setScene(scene);
			stage.show();

			((Node) (event.getSource())).getScene().getWindow().hide();

		} catch (IOException e) {
			
			throw new MyException("IOException occured while creating main window",e);

		}

	}

	@FXML
	public void initialize() {
		enteringButton.setDisable(true);
	}
	
	@FXML
	private void handleEnterPressed(){
		enteringButton.setDisable(false);
	}

}
