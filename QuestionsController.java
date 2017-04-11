/**
 * 
 */
package FirstPart;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * This class is responsible for generating questions and its options.
 *
 * @author Alla
 */

public class QuestionsController {

	private static String topic;
	private int counter = 0;
	private int score = 0;

	private Set<Integer> questionSet = new LinkedHashSet<Integer>();
	private Object[] questionSetArray;

	private String[] recordedAnswers = new String[Constants.MAX_QUESTIONS];
	private String[] correctAnswers = new String[Constants.MAX_QUESTIONS];
	
	
	@FXML
	private Label questionLabel;

	@FXML
	private CheckBox firstOption;

	@FXML
	private CheckBox secondOption;

	@FXML
	private CheckBox thirdOption;

	@FXML
	private CheckBox fourthOption;

	@FXML
	private Button previousQuestion;

	@FXML
	private Button nextQuestion;


	@FXML
	private void previousButtonAction(ActionEvent event) throws MyException{
		
		if(counter > 0 ){
			System.out.println(" this is previous button and counter value is : " + counter);
			Object[] questionSetArray =  questionSet.toArray();
			
			int questionNumber = (int) questionSetArray[--counter];
			generateQuestion(questionNumber);
			
		}
		
	}

	@FXML
	private void nextButtonAction(ActionEvent event) throws MyException{

		System.out.println(" this is next button and counter value before is : " + counter);
		recordAnswer(counter++);
		System.out.println(" this is next button and counter value after is : " + counter);

		if( counter < Constants.MAX_QUESTIONS){
			
			generateQuestion((int)questionSetArray[counter]);
		}
		else{
			try {

				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ScoreCardGUI.fxml"));

				Scene scene = new Scene(fxmlLoader.load(), 600, 400);
				
				FinalController finalController = fxmlLoader.<FinalController>getController();
				
				calculateScore();
				
				finalController.setScore(score);

				Stage stage = new Stage();

				stage.setTitle(Constants.STAGE_TITLE4);
				stage.setScene(scene);

				stage.show();

				((Node) (event.getSource())).getScene().getWindow().hide();

			} catch (IOException e) {
				Logger logger = Logger.getLogger(getClass().getName());
				logger.log(Level.SEVERE, "Failed to create question Window", e);
			}

		}
	}
	
	/**
	 * This method is for calculation
	 * of score
	 * +3 for correct answer
	 * -1 for wrong answer
	 */
	private void calculateScore() {

		for(int questionNumber = 0; questionNumber < Constants.MAX_QUESTIONS; questionNumber++){
			String recordedAnswer = recordedAnswers[questionNumber];
			String correctAnswer = correctAnswers[questionNumber];
			
			if(correctAnswer.equalsIgnoreCase(recordedAnswer)){
				score = score + 3;
			}
			else if(recordedAnswer!=null){
				score = score - 1;
			}
		}
	}

	@FXML
	public void initialize(String topic) throws MyException {

		this.topic = topic;
		Random randomGenerator = new Random();

		while (questionSet.size() < Constants.MAX_QUESTIONS) {
			questionSet.add(1 + randomGenerator.nextInt(Constants.TOTAL_QUESTIONS));
		}	

		System.out.println(questionSet);	
		
		questionSetArray = questionSet.toArray();
		
		System.out.println( (int)questionSetArray[counter] );
		generateQuestion((int)questionSetArray[counter]);

	}


	/**
	 * This method is for generating
	 * questions
	 *
	 * @param questionNumber
	 * @throws MyException
	 */
	private void generateQuestion(int questionNumber) throws MyException {
		
		System.out.println(" this is question number : " + questionNumber);

		try {
			String questionPath = "./SecondPart/Questions/" + topic + "/" + questionNumber + ".txt";

			BufferedReader inputReader = new BufferedReader(new FileReader(questionPath));
			String question;

			while ((question = inputReader.readLine()) != null) {
				questionLabel.setText(question);
				break;
			}

			firstOption.setText(inputReader.readLine());
			secondOption.setText(inputReader.readLine());
			thirdOption.setText(inputReader.readLine());
			fourthOption.setText(inputReader.readLine());
			
			correctAnswers[counter] = inputReader.readLine();

			inputReader.close();
						
		} catch (IOException e) {
			throw new MyException("IOException occured while generating the question", e);
		}
	}

	/**
	 * Method for recording the 
	 * resonses given by the user
	 *
	 * @param counter 
	 *
	 */
	private void recordAnswer(int counter) {

		if ( firstOption.isSelected() ) {
			recordedAnswers[counter] = "A";
		}
		
		if ( secondOption.isSelected() ) {
			recordedAnswers[counter] = "B";
		}

		if ( thirdOption.isSelected() ) {
			recordedAnswers[counter] = "C";
		}
		
		if ( fourthOption.isSelected() ) {
			recordedAnswers[counter] = "D";
		}
		
		//Resetting all checkboxes
		firstOption.setSelected(false);
		secondOption.setSelected(false);
		thirdOption.setSelected(false);
		fourthOption.setSelected(false);

	}
}
