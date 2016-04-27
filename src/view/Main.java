package view;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import model.lemmings.contract.GameEngContract;
import model.lemmings.contract.LevelContract;
import model.lemmings.impl.GameEngImpl;
import model.lemmings.impl.LevelImpl;
import model.lemmings.services.GameEng;
import model.lemmings.services.Level;
import model.lemmings.services.Level.Nature;


public class Main extends Application {

	public static final String GAME_UI = "Game.fxml";
	private Stage primaryStage;
	private BorderPane game;
	private AnchorPane root;
	private GridPane plateauGridPane;
	private GameEng gameEng;

	private static final String DIRT = "images/dirt.png";
	private static final String METAL = "images/metal.png";
	private static final String EMPTY = "images/empty.png";
	private static final String ENTREE = "images/entree.png";
	private static final String SORTIE = "images/sortie.png";
	
	/* Images */
	private Image dirtImage, metalImage, emptyImage, entreeImage, sortieImage;
	private Background dirtBg, metalBg, emptyBg, entreeBg, sortieBg;
	
	/* Variables */
	private boolean isSetEntranceClicked;
	private boolean isSetExitClicked;
	private boolean isEditing;

	/* FX Nodes */
	// empty at the moment


	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		root = new AnchorPane();
		isEditing = true;
		initRootLayout(primaryStage);
	}
	
	private void loadImages() {
		dirtImage = new Image(new File(DIRT).toURI().toString());
		metalImage = new Image(new File(METAL).toURI().toString());
		emptyImage = new Image(new File(EMPTY).toURI().toString());
		entreeImage = new Image(new File(ENTREE).toURI().toString());
		sortieImage = new Image(new File(SORTIE).toURI().toString());
		dirtBg = new Background(new BackgroundImage(dirtImage, null, null, null, null));
		metalBg = new Background(new BackgroundImage(metalImage, null, null, null, null));
		emptyBg = new Background(new BackgroundImage(emptyImage, null, null, null, null));
		entreeBg = new Background(new BackgroundImage(entreeImage, null, null, null, null));
		sortieBg = new Background(new BackgroundImage(sortieImage, null, null, null, null));
	}

	public void initGame(int width, int height){
		Level levelImpl = new LevelImpl();
		Level levelContract = new LevelContract(levelImpl);
		GameEngImpl gameEngImpl = new GameEngImpl();
		gameEng = new GameEngContract(gameEngImpl);
		levelContract.init(width, height);
		gameEngImpl.bindLevelService(levelContract);
		//TODO recup les valeur d'init de gameEng sur l'UI
		gameEng.init(10, 5);
	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource(GAME_UI));
			game = (BorderPane) loader.load();
			root.getChildren().add(game);
			plateauGridPane = (GridPane) game.lookup("#plateauGridPane");
			loadImages();
			MainController controller = loader.getController();
	        controller.setMainApp(this);
			// Show the scene containing the root layout.
			Scene scene = new Scene(root);			
			primaryStage.setTitle("Lemmings");
			primaryStage.setScene(scene);

			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialiserPlateauGrid(int width, int height) {

		/* Remise à false des booléens setEntrance et setExit */
		isSetEntranceClicked = false;
		isSetExitClicked = false;

		if (plateauGridPane != null) {
//			plateauGridPane.setGridLinesVisible(false);
			plateauGridPane.getChildren().clear();
			plateauGridPane.getColumnConstraints().clear();
			plateauGridPane.getRowConstraints().clear();
		}

		plateauGridPane.setMinWidth(800);
		plateauGridPane.setMinHeight(800);
		for (int i = 0; i < width; i++) {
			ColumnConstraints columnConstraints = new ColumnConstraints();
			columnConstraints.setHgrow(Priority.SOMETIMES);
			plateauGridPane.getColumnConstraints().add(columnConstraints);
		}
		for(int i = 0; i < height; i++) {
			RowConstraints rowConstraints = new RowConstraints();
			rowConstraints.setVgrow(Priority.SOMETIMES);
			plateauGridPane.getRowConstraints().add(rowConstraints);
		}
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Pane pane = new Pane();
				pane.setBackground(emptyBg);
				gameEng.getLevel().setNature(i, j, Nature.EMPTY);
				GridPane.setRowIndex(pane, j);
				GridPane.setColumnIndex(pane, i);
				plateauGridPane.getChildren().add(pane);
			}
		}
//		plateauGridPane.setGridLinesVisible(true);
	}

	public boolean isSetExitClicked() {
		return isSetExitClicked;
	}

	public void setSetExitClicked(boolean isSetExitClicked) {
		this.isSetExitClicked = isSetExitClicked;
	}

	public boolean isSetEntranceClicked() {
		return isSetEntranceClicked;
	}

	public void setSetEntranceClicked(boolean isSetEntranceClicked) {
		this.isSetEntranceClicked = isSetEntranceClicked;
	}

	public boolean isEditing() {
		return isEditing;
	}

	public void setEditing(boolean isEditing) {
		this.isEditing = isEditing;
	}

	public Background getDirtBg() {
		return dirtBg;
	}

	public Background getMetalBg() {
		return metalBg;
	}

	public Background getEmptyBg() {
		return emptyBg;
	}

	public Background getEntreeBg() {
		return entreeBg;
	}

	public Background getSortieBg() {
		return sortieBg;
	}

	public GameEng getGameEng() {
		return gameEng;
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

}

