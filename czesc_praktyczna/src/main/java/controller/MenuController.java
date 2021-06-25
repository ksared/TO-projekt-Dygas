package controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import model.Model;

import java.io.IOException;

public class MenuController {
	
	private MainController mainController;
	

	@FXML
	public void learningButtonClick() {
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
	
	@FXML
	public void przegladFiszekClick() {
		
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
	
	@FXML
	public void wyjscieClick() {
		System.exit(0);
	}
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	

}
