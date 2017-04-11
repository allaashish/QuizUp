/**
 * 
 */
package FirstPart;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Final controller class
 * which displays score and exits
 * the game
 *
 * @author Alla
 *
 */
public class FinalController {
	
	@FXML
	Label scoreLabel;
	
	@FXML
	Button exitButton;

	public void setScore(int score) {
		scoreLabel.setText( "" + score);	
	}
	
	@FXML
	private void exitButtonAction(ActionEvent event){
	    Stage stage = (Stage) exitButton.getScene().getWindow();
	    stage.close();
	}

}
