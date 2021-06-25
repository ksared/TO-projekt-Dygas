package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MainController {

	@FXML
	private StackPane mainStackPane;

	@FXML
	public void initialize() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/MenuScreen.fxml"));

		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MenuController menuController = loader.getController();
		menuController.setMainController(this);
		mainStackPane.getChildren().add(pane);
	}

	public void setScreen(Pane pane) {
		mainStackPane.getChildren().clear();
		mainStackPane.getChildren().add(pane);
	}
	
	public void startLoadingScreen() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/LoadingScreen.fxml"));

		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mainStackPane.getChildren().add(pane);
	}

	public void stopLoadingScreen() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/LoadingScreen.fxml"));

		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mainStackPane.getChildren().remove(1);
	}

}
