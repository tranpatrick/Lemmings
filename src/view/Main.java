package view;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
import model.lemmings.services.Lemming;
import model.lemmings.services.Lemming.Direction;
import model.lemmings.services.Level;
import model.lemmings.services.Level.Nature;

//TODO rename Main et Main Controller
public class Main extends Application implements IObserver{

	public static final String GAME_UI = "Game.fxml";
	private Stage primaryStage;
	private BorderPane game;
	private AnchorPane root;
	private GridPane plateauGridPane;
	private GameEng gameEng = null;

	/* Enumeration pour charger automatiquement les images/Background dans une
	 * HashMap, utiliser le getter getBackground(Images image) pour avoir
	 * le background
	 * 
	 * Noms de fichers : pour enum DIRT, placer dirt.png dans le dossier Images
	 */
	enum Images {
		/* Cases */
		DIRT,METAL,EMPTY,ENTREE,SORTIE,
		//		/* Lemmings droitiers */
		MARCHEUR_D,
		TOMBEUR,
		//		CREUSEUR_D,
		//		GRIMPEUR_D,
		//		BUILDER_D,
		//		FLOTTEUR_D,
		//		EXPLOSEUR_D,
		//		STOPPEUR_D,
		//		BASHER_D,
		//		MINER_D,
		//		/* Lemmings gauchers */
		MARCHEUR_G, 
		//		CREUSEUR_G,
		//		GRIMPEUR_G,
		//		BUILDER_G,
		//		FLOTTEUR_G,
		//		EXPLOSEUR_G,
		//		STOPPEUR_G,
		//		BASHER_G,
		//		MINER_G,
	}


	/* Images */
	private HashMap<Images, Background> backgrounds;
	private static int cptImage = 0;

	/* Variables */
	private boolean isSetEntranceClicked;
	private boolean isSetExitClicked;

	/* FX Nodes */
	// empty at the moment car plus besoin de charger les truc ici, tout est fait dans le controller

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		root = new AnchorPane();
		backgrounds = new HashMap<>();
		initRootLayout(primaryStage);
	}

	/* Charge les images et cree les background avec la bonne taille */
	private void loadImages() {
		BackgroundSize backgroundSize = new BackgroundSize(
				plateauGridPane.getWidth()/gameEng.getLevel().getWidth(), 
				plateauGridPane.getHeight()/(gameEng.getLevel().getHeight()), 
				false, false, false, false);
		for (Images v : Images.values()) {
			String filename = "";
			int cpt = (cptImage%4)+1;
			if (v == Images.DIRT || v == Images.METAL || v == Images.EMPTY 
					|| v == Images.ENTREE|| v == Images.SORTIE) {
				filename = "images/"+v.toString().toLowerCase()+".png";
			}
			else { 
				filename = "images/"+v.toString().toLowerCase()+cpt+".png";
			}
			System.out.println(filename);
			Image tmpImage = new Image(new File(filename).toURI().toString());
			Background tmpBackground = null;
			if (v == Images.DIRT || v == Images.METAL || v == Images.EMPTY) {
				tmpBackground = new Background(
						new BackgroundImage(tmpImage, 
								BackgroundRepeat.REPEAT, 
								BackgroundRepeat.REPEAT, 
								BackgroundPosition.CENTER, 
								backgroundSize));
			}
			else { 
				tmpBackground = new Background(
						new BackgroundImage(tmpImage, 
								BackgroundRepeat.NO_REPEAT, 
								BackgroundRepeat.NO_REPEAT, 
								BackgroundPosition.CENTER, 
								backgroundSize));
			}
			backgrounds.put(v, tmpBackground);
		}
		cptImage++;
	}

	public void initGameEng(int width, int height, int sizeColony, int spawnSpeed){
		if (gameEng != null) {
			gameEng.deleteObserver(this);
		}
		Level levelImpl = new LevelImpl();
		Level levelContract = new LevelContract(levelImpl);
		GameEngImpl gameEngImpl = new GameEngImpl();
		gameEng = new GameEngContract(gameEngImpl);
		levelContract.init(width, height);
		gameEngImpl.bindLevelService(levelContract);
		loadImages();
		//TODO recup les valeur d'init de gameEng sur l'UI
		gameEng.init(sizeColony, spawnSpeed);
		gameEng.addObserver(this);
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

	public void initialiserLevel(int width, int height) {

		/* Remise a false des booleens isSetEntranceClicked et isSetExitClicked */
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
				if (i == 0 || i == width - 1 || j == 0 || j == height - 1) {
					gameEng.getLevel().setNature(i, j, Nature.METAL);
					pane.setBackground(getBackground(Images.METAL));
				}
				else if (i > 5 && j > 5 && j < height - 10) {
					gameEng.getLevel().setNature(i, j, Nature.DIRT);
					pane.setBackground(getBackground(Images.DIRT));
				}
				else {
					gameEng.getLevel().setNature(i, j, Nature.EMPTY);
					pane.setBackground(getBackground(Images.EMPTY));
				}
				GridPane.setRowIndex(pane, j);
				GridPane.setColumnIndex(pane, i);
				plateauGridPane.getChildren().add(pane);
			}
		}
		//		plateauGridPane.setGridLinesVisible(true);
	}

	/* Fonction appelee par Observer, dans game engine, appeler a la fin de step */
	//TODO implementer Observer
	@Override
	public void update() {
		//TODO resoudre probleme freeze interface

		System.err.println("Mise a jour interface");
		loadImages();
		if (plateauGridPane != null) {
			//	plateauGridPane.setGridLinesVisible(false);
			plateauGridPane.getChildren().clear();
		}
		int width = gameEng.getLevel().getWidth();
		int height = gameEng.getLevel().getHeight();

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Pane pane = new Pane();
				Lemming lem = null;
				lem = getLemmingWithPosition(i, j);

				if (gameEng.getLevel().isEntrance(i, j)) {
					pane.setBackground(getBackground(Images.ENTREE));
				}
				else if (gameEng.getLevel().isExit(i, j)) {
					pane.setBackground(getBackground(Images.SORTIE));
				}
				else if (lem != null) {
					if (lem.getDirection() == Direction.DROITIER)
						switch (lem.getType()) {
						case TOMBEUR:
							pane.setBackground(getBackground(Images.TOMBEUR));
							break;
						case MARCHEUR:
							pane.setBackground(getBackground(Images.MARCHEUR_D));
							break;
						default:
							break;
						}
					else {
						switch (lem.getType()) {
						case TOMBEUR:
							pane.setBackground(getBackground(Images.TOMBEUR));
							break;
						case MARCHEUR:
							pane.setBackground(getBackground(Images.MARCHEUR_G));
							break;
						default:
							break;
						}
					}
				}	
				else {
					Nature nature = gameEng.getLevel().getNature(i, j); 
					pane.setBackground(getBackground(Images.valueOf(nature.toString())));
				}

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
		return gameEng.getLevel().isEditing();
	}

	public Lemming getLemmingWithPosition(int x, int y) {
		if (gameEng.gameOver())
			return null;
		for (int id : gameEng.getLemmingsActifs()) {
			Lemming lem = gameEng.getLemming(id);
			if (lem.getX() == x && lem.getY() == y) {
				return lem;
			}
		}
		return null;
	}

	public Background getBackground(Images image) {
		Background bg = backgrounds.get(image);
		if (bg == null) {
			System.err.println("Main::getBackground, je ne dois jamais passer ici");
		}
		return bg;	
	}

	public GameEng getGameEng() {
		return gameEng;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

}

