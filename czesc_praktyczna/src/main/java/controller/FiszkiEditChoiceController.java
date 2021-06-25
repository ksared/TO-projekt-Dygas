package controller;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.Model;
import model.Word;

public class FiszkiEditChoiceController {

	private MainController mainController;

	@FXML
	private TableView<Word> polishTableView;

	@FXML
	private TableView<Word> englishTableView;

	@FXML
	private TableColumn<Word, String> polishWordCol;

	@FXML
	private TableColumn<Word, List<String>> polishTranslationsCol;

	@FXML
	private TableColumn<Word, String> englishWordCol;

	@FXML
	private TableColumn<Word, List<String>> englishTranslationsCol;

	@FXML
	private TextField polishSearchTextField;

	@FXML
	private TextField englishSearchTextField;

	ObservableList<Word> polishOblist = FXCollections.observableArrayList();
	ObservableList<Word> englishOblist = FXCollections.observableArrayList();

	@FXML
	public void initialize() {
		polishWordCol.setCellValueFactory(new PropertyValueFactory<>("Word"));
		polishTranslationsCol.setCellValueFactory(new PropertyValueFactory<>("Translations"));
		englishWordCol.setCellValueFactory(new PropertyValueFactory<>("Word"));
		englishTranslationsCol.setCellValueFactory(new PropertyValueFactory<>("Translations"));

		polishTableView.setItems(polishOblist);
		englishTableView.setItems(englishOblist);
	}

	@FXML
	public void polishSearchClick() {
		polishOblist.clear();
		mainController.startLoadingScreen();	
		Task<List<Word>> task = new Task<List<Word>>() {
            @Override
            public List<Word> call() throws Exception {	
                return Model.getAllPolishWordsLike(polishSearchTextField.getText());
            }
        };
        new Thread(task).start();

        task.setOnSucceeded(event -> {
        	polishOblist.addAll(task.getValue());
        	mainController.stopLoadingScreen(); 
        });
	}

	@FXML
	public void polishDodajClick() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/CreateFiszkiScreen.fxml"));

		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CreateFiszkiController createFiszkiController = loader.getController();
		createFiszkiController.setMainController(mainController);
		createFiszkiController.setLanguage(WordLanguage.POLISH);
		mainController.setScreen(pane);
	}

	@FXML
	public void polishEdytujClick() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/UpdateFiszkiScreen.fxml"));

		UpdateFiszkiController updateFiszkiController = new UpdateFiszkiController();
		updateFiszkiController.setMainController(mainController);
		updateFiszkiController.setLanguage(WordLanguage.POLISH);
		updateFiszkiController.setCurrentWord(polishTableView.getSelectionModel().getSelectedItem());	
		
		loader.setController(updateFiszkiController);
		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mainController.setScreen(pane);
	}

	@FXML
	public void polishUsuñClick() {
		if(polishTableView.getSelectionModel().getSelectedItem() != null) {
			mainController.startLoadingScreen();	
			Task<Void> task = new Task<Void>() {
	            @Override
	            public Void call() throws Exception {	
	            	Model.deletePolishWord(polishTableView.getSelectionModel().getSelectedItem().getWord());
	        		polishOblist.clear();
	        		polishOblist.addAll(Model.getAllPolishWordsString());
	        		englishOblist.clear();
	        		englishOblist.addAll(Model.getAllEnglishWordsString());
	        		return null;
	            }
	        };
	        new Thread(task).start();
	
	        task.setOnSucceeded(event -> {
	        	mainController.stopLoadingScreen();
	        });
		}
	}

	@FXML
	public void polishWsteczClick() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/MenuScreen.fxml"));

		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MenuController menuController = loader.getController();
		menuController.setMainController(mainController);
		mainController.setScreen(pane);
	}

	@FXML
	public void englishSearchClick() {
		englishOblist.clear();
		englishOblist.addAll(Model.getAllEnglishWordsLike(englishSearchTextField.getText()));
	}

	@FXML
	public void englishDodajClick() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/CreateFiszkiScreen.fxml"));

		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CreateFiszkiController createFiszkiController = loader.getController();
		createFiszkiController.setMainController(mainController);
		createFiszkiController.setLanguage(WordLanguage.ENGLISH);
		mainController.setScreen(pane);
	}

	@FXML
	public void englishEdytujClick() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/UpdateFiszkiScreen.fxml"));

		UpdateFiszkiController updateFiszkiController = new UpdateFiszkiController();
		updateFiszkiController.setMainController(mainController);
		updateFiszkiController.setLanguage(WordLanguage.ENGLISH);
		updateFiszkiController.setCurrentWord(englishTableView.getSelectionModel().getSelectedItem());	
		
		loader.setController(updateFiszkiController);
		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mainController.setScreen(pane);
	}

	@FXML
	public void englishUsuñClick() {
		if(englishTableView.getSelectionModel().getSelectedItem() != null) {
			mainController.startLoadingScreen();	
			Task<Void> task = new Task<Void>() {
	            @Override
	            public Void call() throws Exception {	
	            	Model.deleteEnglishWord(englishTableView.getSelectionModel().getSelectedItem().getWord());
	        		polishOblist.clear();
	        		polishOblist.addAll(Model.getAllPolishWordsString());
	        		englishOblist.clear();
	        		englishOblist.addAll(Model.getAllEnglishWordsString());
	        		return null;
	            }
	        };
	        new Thread(task).start();
	
	        task.setOnSucceeded(event -> {
	        	mainController.stopLoadingScreen();
	        });
		}
	}

	@FXML
	public void englishWsteczClick() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/MenuScreen.fxml"));

		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MenuController menuController = loader.getController();
		menuController.setMainController(mainController);
		mainController.setScreen(pane);
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	
	public void setLists(List<Word> list1, List<Word> list2) {
		polishOblist.addAll(list1);
		englishOblist.addAll(list2);
	}
}
