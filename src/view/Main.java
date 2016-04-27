package view;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
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
	
	private Image dirtImage;
	private Image metalImage;
	private Image emptyImage;
	private Image entreeImage;
	private Image sortieImage;
	private Background dirtBg;
	private Background metalBg;
	private Background emptyBg;
	private Background entreeBg;
	private Background sortieBg;
	
	/* Variables */
	boolean settingEntrance;
	boolean settingExit;
	private Pane entrancePane;
	private Pane exitPane;
	private boolean isEditing;

	/* FX Nodes */
	private Button dimensionButton;
	private Button setExitButton;
	private Button setEntranceButton;
	private TextField largeurTextField;
	private TextField hauteurTextField;


	@Override
	public void start(Stage primaryStage) {
		root = new AnchorPane();
		isEditing = true;
		initRootLayout(primaryStage);
	}

	public void initInternalNodes() {
		if (root != null) {
			largeurTextField = (TextField) root.lookup("#largeurTextField");
			hauteurTextField = (TextField) root.lookup("#hauteurTextField");
			dimensionButton = (Button) root.lookup("#dimensionButton");
			setExitButton = (Button) root.lookup("#setExitButton");
			setEntranceButton = (Button) root.lookup("#setEntranceButton");
		}
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
		gameEng.init(10, 5);
	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout(Stage primaryStage) {
		try {
			game = (BorderPane) FXMLLoader.load(getClass().getResource(GAME_UI));
			root.getChildren().add(game);
			plateauGridPane = (GridPane) game.lookup("#plateauGridPane");
			loadImages();
			initInternalNodes();
			initEventHandler();

			// Show the scene containing the root layout.
			Scene scene = new Scene(root);			
			primaryStage.setTitle("Lemmings");
			primaryStage.setScene(scene);
			initEditingEventHandler();

			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initEventHandler() {
		dimensionButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				System.out.println("JE PASSE ICI");
				//TODO check si c'est bien un int
				int largeur = Integer.parseInt(largeurTextField.getText());
				int hauteur = Integer.parseInt(hauteurTextField.getText());
				initGame(largeur, hauteur);
				initialiserPlateauGrid(largeur, hauteur);
			}
		});

		setEntranceButton.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				settingEntrance = true;
				settingExit = false;
				System.out.println("Setting Entrance"+settingEntrance);
			}
		});

		setExitButton.addEventFilter(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				settingExit = true;
				settingEntrance = false;
				System.out.println("Setting Exit ="+settingExit);
			}
		});

	}

	public void initialiserPlateauGrid(int width, int height) {

		/* Remise à false des booléens setEntrance et setExit */
		settingEntrance = false;
		settingExit = false;

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
		initEditingEventHandler();
	}

	private void initEditingEventHandler() {
		plateauGridPane.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				for(Node node: plateauGridPane.getChildren()) {
					if(node instanceof Pane) {
						if( node.getBoundsInParent().contains(e.getX(), e.getY())) {
							int y = GridPane.getRowIndex(node);
							int x = GridPane.getColumnIndex(node);
							/* si on est en train de set une entrance */
							if (!gameEng.getLevel().isEntrance(x, y) && !settingEntrance
									&& !gameEng.getLevel().isExit(x, y) && !settingExit && isEditing) {
								if (e.getButton() == MouseButton.PRIMARY){
									((Pane) node).setBackground(dirtBg);
									gameEng.getLevel().setNature(x, y, Nature.DIRT);
								}else if(e.getButton() == MouseButton.SECONDARY){
									((Pane) node).setBackground(metalBg);
									gameEng.getLevel().setNature(x, y, Nature.METAL);
								}else if(e.getButton() == MouseButton.MIDDLE){
									((Pane) node).setBackground(emptyBg);
									gameEng.getLevel().setNature(x, y, Nature.EMPTY);
								}
							}
							break;
						}
					}
				}
			}
		});

		plateauGridPane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				for(Node node: plateauGridPane.getChildren()) {
					if(node instanceof Pane) {
						if( node.getBoundsInParent().contains(e.getX(), e.getY())) {
							int y = GridPane.getRowIndex(node);
							int x = GridPane.getColumnIndex(node);
							/* si on est en train de set une entrance */
							if (!gameEng.getLevel().isEntrance(x, y) && !settingEntrance
									&& !gameEng.getLevel().isExit(x, y) && !settingExit) {
								if (e.getButton() == MouseButton.PRIMARY){
									((Pane) node).setBackground(dirtBg);
									gameEng.getLevel().setNature(x, y, Nature.DIRT);
								}else if(e.getButton() == MouseButton.SECONDARY){
									((Pane) node).setBackground(metalBg);
									gameEng.getLevel().setNature(x, y, Nature.METAL);
								}else if(e.getButton() == MouseButton.MIDDLE){
									((Pane) node).setBackground(emptyBg);
									gameEng.getLevel().setNature(x, y, Nature.EMPTY);
								}
							}
							break;
						}
					}
				}
			}
		});

		plateauGridPane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {

				for(Node node: plateauGridPane.getChildren()) {
					if(node instanceof Pane) {
						if( node.getBoundsInParent().contains(e.getX(), e.getY())) {
							int y = GridPane.getRowIndex(node);
							int x = GridPane.getColumnIndex(node);
							/* si on est en train de set une entrance */
							if(settingEntrance && !gameEng.getLevel().isExit(x, y)){
								try {
									gameEng.getLevel().setEntrance(x, y);
									if (entrancePane != null) 
										entrancePane.setStyle("-fx-background-color: #000000;");
									((Pane) node).setBackground(entreeBg);
									entrancePane = (Pane) node;
									settingEntrance = false;
									System.out.println("Je passe dans settingEntrance");
								} catch (Error error) {
									Alert alert = new Alert(AlertType.ERROR);
									alert.setTitle("Set Entrance");
									alert.setHeaderText("Error");
									alert.setContentText("Emplacement non valide");

									alert.showAndWait();
								} finally {
									settingEntrance = false;
								}
							}
							/* si on est en train de set une exit */
							else if(settingExit && !gameEng.getLevel().isEntrance(x, y)){
								try {
									gameEng.getLevel().setExit(x, y);
									if (exitPane != null) 
										exitPane.setStyle("-fx-background-color: #000000;");
									((Pane) node).setBackground(sortieBg);
									exitPane = (Pane) node;
									System.out.println("Je sort dans settingExit");
								} catch( Error error) {
									Alert alert = new Alert(AlertType.ERROR);
									alert.setTitle("Set Exit");
									alert.setHeaderText("Error");
									alert.setContentText("Emplacement non valide");
									alert.show();
								}finally {
									settingExit = false;
								}
							}
							break;
						}
					}
				}
			}
		});

	}

	/**
	 * Returns the main stage.
	 * @return
	 */
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}

