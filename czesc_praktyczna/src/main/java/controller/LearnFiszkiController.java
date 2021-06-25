package controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.Word;

public class LearnFiszkiController {

	@FXML
	private Label wordLabel;

	@FXML
	private Label translationsLabel;

	@FXML
	private Label score;

	@FXML
	private Label pytanieLabel;

	@FXML
	private Button nieButton;

	@FXML
	private Button takButton;

	@FXML
	private Button sprawdzButton;

	private MainController mainController;

	private List<Word> wordList;

	private int totalAttempts = 0;
	private int correctAttempts = 0;
	Random rand;

	@FXML
	public void initialize() {
		rand = new Random();
		Word currentWord = getNextWord();
		if(currentWord != null) {
			wordLabel.setText(currentWord.getWord());
			translationsLabel.setText(convertTranslations(currentWord.getTranslations()));
		}	
	}

	@FXML
	void sprawdzClick() {
		pytanieLabel.setVisible(true);
		takButton.setVisible(true);
		nieButton.setVisible(true);
		sprawdzButton.setVisible(false);
		translationsLabel.setVisible(true);
	}

	@FXML
	void nieClick() {
		totalAttempts++;
		score.setText(correctAttempts + "/" + totalAttempts);
		pytanieLabel.setVisible(false);
		takButton.setVisible(false);
		nieButton.setVisible(false);
		Word currentWord = getNextWord();
		if(currentWord != null) {
			wordLabel.setText(currentWord.getWord());
			translationsLabel.setText(convertTranslations(currentWord.getTranslations()));
			translationsLabel.setVisible(false);
			sprawdzButton.setVisible(true);
		}
	}

	@FXML
	void takClick() {
		totalAttempts++;
		correctAttempts++;
		score.setText(correctAttempts + "/" + totalAttempts);
		pytanieLabel.setVisible(false);
		takButton.setVisible(false);
		nieButton.setVisible(false);
		Word currentWord = getNextWord();
		if(currentWord != null) {
			wordLabel.setText(currentWord.getWord());
			translationsLabel.setText(convertTranslations(currentWord.getTranslations()));
			translationsLabel.setVisible(false);
			sprawdzButton.setVisible(true);
		}
	}

	@FXML
	void wsteczClick() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/LearningChoiceScreen.fxml"));

		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LearningChoiceController learningChoiceController = loader.getController();
		learningChoiceController.setMainController(mainController);
		mainController.setScreen(pane);
	}

	private Word getNextWord() {
		if (wordList.size() == 0) {
			wordLabel.setVisible(false);
			translationsLabel.setText("Wszystkie fiszki zosta³y przerobione");
			sprawdzButton.setVisible(false);
			takButton.setVisible(false);
			nieButton.setVisible(false);
			return null;
		}
		int index = rand.nextInt(wordList.size());
		Word next = wordList.get(index);
		wordList.remove(index);
		return next;
	}

	private String convertTranslations(List<String> translations) {
		String translationsString = "";

		for (String translation : translations) {
			translationsString += translation + ", ";
		}
		translationsString = translationsString.substring(0, translationsString.lastIndexOf(","));

		return translationsString;
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	public void setWordList(List<Word> wordList) {
		this.wordList = wordList;
	}

}
