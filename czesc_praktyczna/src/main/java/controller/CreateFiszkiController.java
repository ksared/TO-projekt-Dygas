package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.Model;

public class CreateFiszkiController {

	@FXML
	private TextArea translationsArea;

	@FXML
	private TextField wordField;

	@FXML
	private Label wordErrorLabel;

	@FXML
	private Label translationsErrorLabel;

	private MainController mainController;

	private WordLanguage language;

	@FXML
	void DodajClick() {
		wordErrorLabel.setVisible(false);
		translationsErrorLabel.setVisible(false);
		String word = getWord();
		List<String> translations = getTranslations();
		boolean check = true;

		if (word.equals("")) {
			check = false;
			wordErrorLabel.setVisible(true);
		}
		if (translations.equals(new ArrayList<String>())) {
			check = false;
			translationsErrorLabel.setVisible(true);
		}

		if (check) {
			if (language == WordLanguage.POLISH) {
				mainController.startLoadingScreen();
				Task<Pane> task = new Task<Pane>() {
					@Override
					public Pane call() throws Exception {
						Model.createPolishWord(word, translations);
						FXMLLoader loader = new FXMLLoader(
								this.getClass().getResource("/View/FiszkiEditChoiceScreen.fxml"));

						FiszkiEditChoiceController fiszkiEditChoiceController = new FiszkiEditChoiceController();
						fiszkiEditChoiceController.setMainController(mainController);
						fiszkiEditChoiceController.setLists(Model.getAllPolishWordsString(),
								Model.getAllEnglishWordsString());

						loader.setController(fiszkiEditChoiceController);

						Pane pane = null;
						try {
							pane = loader.load();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return pane;
					}
				};
				new Thread(task).start();

				task.setOnSucceeded(event -> {
					mainController.setScreen(task.getValue());
				});

			} else if (language == WordLanguage.ENGLISH) {
				mainController.startLoadingScreen();
				Task<Pane> task = new Task<Pane>() {
					@Override
					public Pane call() throws Exception {
						Model.createEnglishWord(word, translations);
						FXMLLoader loader = new FXMLLoader(
								this.getClass().getResource("/View/FiszkiEditChoiceScreen.fxml"));

						FiszkiEditChoiceController fiszkiEditChoiceController = new FiszkiEditChoiceController();
						fiszkiEditChoiceController.setMainController(mainController);
						fiszkiEditChoiceController.setLists(Model.getAllPolishWordsString(),
								Model.getAllEnglishWordsString());

						loader.setController(fiszkiEditChoiceController);

						Pane pane = null;
						try {
							pane = loader.load();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return pane;
					}
				};
				new Thread(task).start();

				task.setOnSucceeded(event -> {
					mainController.setScreen(task.getValue());
				});

			}
		}

	}

	@FXML
	void wsteczClick() {
		mainController.startLoadingScreen();
		Task<Pane> task = new Task<Pane>() {
			@Override
			public Pane call() throws Exception {
				FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/FiszkiEditChoiceScreen.fxml"));

				FiszkiEditChoiceController fiszkiEditChoiceController = new FiszkiEditChoiceController();
				fiszkiEditChoiceController.setMainController(mainController);
				fiszkiEditChoiceController.setLists(Model.getAllPolishWordsString(), Model.getAllEnglishWordsString());

				loader.setController(fiszkiEditChoiceController);

				Pane pane = null;
				try {
					pane = loader.load();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return pane;
			}
		};
		new Thread(task).start();

		task.setOnSucceeded(event -> {
			mainController.setScreen(task.getValue());
		});
	}

	private String getWord() {
		String currentWord = wordField.getText();
		if (language == WordLanguage.POLISH) {
			if (Model.getPolishWord(currentWord) == null) {
				return currentWord;
			} else {
				return "";
			}
		} else if (language == WordLanguage.ENGLISH) {
			if (Model.getEnglishWord(currentWord) == null) {
				return currentWord;
			} else {
				return "";
			}
		} else {
			System.out.println("getWord() Error!");
			return "";
		}
	}

	private List<String> getTranslations() {
		String fullText = translationsArea.getText();
		if (fullText.isEmpty())
			return new ArrayList<String>();

		List<String> list = new ArrayList<String>();

		while (fullText.indexOf(',') > 0) {
			String translation = fullText.substring(0, fullText.indexOf(','));
			fullText = fullText.substring(fullText.indexOf(',') + 2);
			list.add(translation);
		}
		list.add(fullText);

		return list;

	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	public void setLanguage(WordLanguage language) {
		this.language = language;
	}

}
