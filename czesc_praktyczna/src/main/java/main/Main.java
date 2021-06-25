package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Model;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/MainScreen.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Testing");
		primaryStage.show();
	}
	
	
	
	
	
	
	
	/*
	List<String> list = new ArrayList<String>();
	list.add("lesson");
	list.add("class");
	Model.createPolishWord("lekcja", list);
	list.clear();
	list.add("graæ");
	list.add("bawiæ siê");
	Model.createEnglishWord("play", list);
	list.clear();
	list.add("castle");
	list.add("zipper");
	Model.updatePolishWord("lekcja", "zamek", list);
	list.clear();
	list.add("poganin");
	list.add("bezbo¿nik");
	Model.updateEnglishWord("play", "heathen", list);
	//Model.deletePolishWord("zamek");
	//Model.deleteEnglishWord("heathen");
	Model.closeEntityManager();

	
		
		
DROP TABLE IF EXISTS `angielskie_slowa`;
DROP TABLE IF EXISTS `hibernate_sequence`;
DROP TABLE IF EXISTS `polskie_slowa`;
DROP TABLE IF EXISTS `t³uamczenia`;
		
		
	List<EnglishWord> translations = new ArrayList<EnglishWord>();
	
	
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("manager");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	
	
	
	entityManager.getTransaction().begin();
	EnglishWord e = new EnglishWord("change", new ArrayList<PolishWord>());
	entityManager.persist(e);
	translations.add(e);
	PolishWord p = new PolishWord("zmieniaæ", new ArrayList<EnglishWord>());
	p.addTranslation(e);
	String query = "SELECT W FROM EnglishWord W WHERE W.word = " + "'" + "change" + "'";
	TypedQuery<EnglishWord> typedQuery = entityManager.createQuery(query, EnglishWord.class);
	EnglishWord k =  typedQuery.getResultList().get(0);
	System.out.println(k.getId());
	entityManager.persist(p);
	
	entityManager.getTransaction().commit();
	
	System.out.println(k.getTranslations().get(0).getWord());
	
	*/

}
