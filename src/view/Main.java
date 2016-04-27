package view;

import java.awt.Point;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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


	/* FX Nodes */
	private Button dimensionButton;
	private TextField largeurTextField;
	private TextField hauteurTextField;

	@Override
	public void start(Stage primaryStage) {
		root = new AnchorPane();
		initRootLayout(primaryStage);
	}

	public void initInternalNodes() {
		if (root != null) {
			largeurTextField = (TextField) root.lookup("#largeurTextField");
			hauteurTextField = (TextField) root.lookup("#hauteurTextField");
			dimensionButton = (Button) root.lookup("#dimensionButton");
		}
	}

	public void initGame(int width, int height){
		Point entrance = new Point(5,2);
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

	}

	public void initialiserPlateauGrid(int width, int height) {
		if (plateauGridPane != null) {
			plateauGridPane.setGridLinesVisible(false);
			plateauGridPane.getChildren().clear();
			plateauGridPane.getColumnConstraints().clear();
			plateauGridPane.getRowConstraints().clear();
		}
		
		plateauGridPane.setMinWidth(800);
		plateauGridPane.setMinHeight(800);
		for (int i = 0; i < height; i++) {
			ColumnConstraints columnConstraints = new ColumnConstraints();
			columnConstraints.setHgrow(Priority.SOMETIMES);
			plateauGridPane.getColumnConstraints().add(columnConstraints);
		}
		for(int i = 0; i < width; i++) {
			RowConstraints rowConstraints = new RowConstraints();
			rowConstraints.setVgrow(Priority.SOMETIMES);
			plateauGridPane.getRowConstraints().add(rowConstraints);
		}

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Pane label = new Pane();
//				label.setStyle("-fx-background-color: #000000;");
				GridPane.setRowIndex(label, i);
				GridPane.setColumnIndex(label, j);
				plateauGridPane.getChildren().add(label);
			}
		}
		plateauGridPane.setGridLinesVisible(true);
		initEditingEventHandler();
	}

	private void initEditingEventHandler() {
		plateauGridPane.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				for( Node node: plateauGridPane.getChildren()) {
					if( node instanceof Pane) {
						if( node.getBoundsInParent().contains(e.getX(),  e.getY())) {
							int x = GridPane.getRowIndex(node);
							int y = GridPane.getColumnIndex(node);
							if (e.getButton() == MouseButton.PRIMARY){
								((Pane) node).setStyle("-fx-background-color: #61210B;"); //TODO chercher image
								gameEng.getLevel().setNature(x, y, Nature.DIRT);
							}else if(e.getButton() == MouseButton.SECONDARY){
								((Pane) node).setStyle("-fx-background-color: #6E6E6E;");
								gameEng.getLevel().setNature(x, y, Nature.METAL);
							}else if(e.getButton() == MouseButton.MIDDLE){
								((Pane) node).setStyle("-fx-background-color: #000000;");
								gameEng.getLevel().setNature(x, y, Nature.EMPTY);
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
		System.out.println("sffdsf");
	}
}
