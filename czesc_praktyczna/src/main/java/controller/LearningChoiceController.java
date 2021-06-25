package controller;

import java.io.IOException;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import model.Model;

public class LearningChoiceController {
	private MainController mainController;

	@FXML
	void polishAllClick() {
		mainController.startLoadingScreen();
		Task<Pane> task = new Task<Pane>() {
            @Override
            public Pane call() throws Exception {
            	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/LearnFiszkiScreen.fxml"));

        		LearnFiszkiController learnFiszkiController = new LearnFiszkiController();
        		learnFiszkiController.setMainController(mainController);
        		learnFiszkiController.setWordList(Model.getAllPolishWordsString());
        		
        		loader.setController(learnFiszkiController);
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
	void polish7Click() {
		mainController.startLoadingScreen();
		Task<Pane> task = new Task<Pane>() {
            @Override
            public Pane call() throws Exception {
            	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/LearnFiszkiScreen.fxml"));

        		LearnFiszkiController learnFiszkiController = new LearnFiszkiController();
        		learnFiszkiController.setMainController(mainController);
        		learnFiszkiController.setWordList(Model.getAllPolishWordsFrom7Days());
        		
        		loader.setController(learnFiszkiController);
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
	void polish30Click() {
		mainController.startLoadingScreen();
		Task<Pane> task = new Task<Pane>() {
            @Override
            public Pane call() throws Exception {
            	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/LearnFiszkiScreen.fxml"));

        		LearnFiszkiController learnFiszkiController = new LearnFiszkiController();
        		learnFiszkiController.setMainController(mainController);
        		learnFiszkiController.setWordList(Model.getAllPolishWordsFrom30Days());
        		
        		loader.setController(learnFiszkiController);
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
	void englishAllClick() {
		
		Task<Pane> task = new Task<Pane>() {
            @Override
            public Pane call() throws Exception {
            	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/LearnFiszkiScreen.fxml"));

        		LearnFiszkiController learnFiszkiController = new LearnFiszkiController();
        		learnFiszkiController.setMainController(mainController);
        		learnFiszkiController.setWordList(Model.getAllEnglishWordsString());
        		
        		loader.setController(learnFiszkiController);
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
	void english7Click() {
		mainController.startLoadingScreen();
		Task<Pane> task = new Task<Pane>() {
            @Override
            public Pane call() throws Exception {
            	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/LearnFiszkiScreen.fxml"));

        		LearnFiszkiController learnFiszkiController = new LearnFiszkiController();
        		learnFiszkiController.setMainController(mainController);
        		learnFiszkiController.setWordList(Model.getAllEnglishWordsFrom7Days());
        		
        		loader.setController(learnFiszkiController);
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
	void english30Click() {
		mainController.startLoadingScreen();
		Task<Pane> task = new Task<Pane>() {
            @Override
            public Pane call() throws Exception {
            	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/LearnFiszkiScreen.fxml"));

        		LearnFiszkiController learnFiszkiController = new LearnFiszkiController();
        		learnFiszkiController.setMainController(mainController);
        		learnFiszkiController.setWordList(Model.getAllEnglishWordsFrom30Days());
        		
        		loader.setController(learnFiszkiController);
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
	public void WsteczButtonClick() {
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
}
