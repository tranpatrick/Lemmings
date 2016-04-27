package view;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.lemmings.services.Level.Nature;

public class MainController {

	private Pane exitPane;
	private Pane entrancePane;

	@FXML
	private Button dimensionButton;

	@FXML
	private TextField hauteurTextField;

	@FXML
	private Label mortsLabel;

	@FXML
	private Label sauvesLabel;

	@FXML
	private HBox scoreHBox;

	@FXML
	private GridPane plateauGridPane;

	@FXML
	private Label scoreLabel;

	@FXML
	private Button setEntranceButton;

	@FXML
	private Label creesLabel;

	@FXML
	private TextField largeurTextField;

	@FXML
	private VBox dimensionVBox;

	@FXML
	private Button setExitButton;

	@FXML
	private Label tourLabel;

	private Main main;

	public void setMainApp(Main main) {
		this.main = main;
	}

	private void initLevel() {
		String largeur = largeurTextField.getText();
		String hauteur = hauteurTextField.getText();
		if (Outils.isNumber(largeur) && Outils.isNumber(hauteur)) {
			int width = Integer.parseInt(largeurTextField.getText());
			int height = Integer.parseInt(hauteurTextField.getText());
			main.initGame(width, height);
			main.initialiserPlateauGrid(width, height);
		}
		else {
			System.err.println("sfsdf");
		}
	}

	@FXML
	void initLevelEvent(Event event) {
		if (event instanceof ActionEvent) {
			initLevel();
		}
		else if (event instanceof KeyEvent) {
			KeyEvent new_name = (KeyEvent) event;
			if (new_name.getCode() == KeyCode.ENTER) {
				initLevel();
			}
		}
	}

	@FXML
	void handleSetEntranceButton(ActionEvent event) {
		main.setSetEntranceClicked(true);
		main.setSetExitClicked(false);
	}

	@FXML
	void handleSetExitButton(ActionEvent event) {
		main.setSetEntranceClicked(false);
		main.setSetExitClicked(true);
	}

	@FXML
	void buildLevel(MouseEvent event) {
		/* Si je deborde du plateau, je ne fais rien */
		if (event.getX() >= plateauGridPane.getWidth() || event.getX() < 0
				|| event.getY() >= plateauGridPane.getHeight() || event.getY() < 0){
			return;
		}
		/* On recupere les coordonnees du clic, ainsi que le Node correspondant
		 * dans un objet pointNode 
		 */
		PointNode pointNode = getCoordonneesClic(event);
		if (pointNode == null) {
			/* Cas ou la grille n'est pas encore cree */
			return;
		}
		int x = pointNode.x, y = pointNode.y;

		if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			/* si on est en train de set une entrance */
			if (!main.getGameEng().getLevel().isEntrance(x, y) && !main.isSetEntranceClicked()
					&& !main.getGameEng().getLevel().isExit(x, y) && !main.isSetExitClicked() 
					&& main.isEditing()) {
				if (event.getButton() == MouseButton.PRIMARY){
					((Pane) pointNode.getNode()).setBackground(main.getDirtBg());
					main.getGameEng().getLevel().setNature(x, y, Nature.DIRT);
				}else if(event.getButton() == MouseButton.SECONDARY){
					((Pane) pointNode.getNode()).setBackground(main.getMetalBg());
					main.getGameEng().getLevel().setNature(x, y, Nature.METAL);
				}else if(event.getButton() == MouseButton.MIDDLE){
					((Pane) pointNode.getNode()).setBackground(main.getEmptyBg());
					main.getGameEng().getLevel().setNature(x, y, Nature.EMPTY);
				}
			}
		}
		else if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			/* si on est en train de set une entrance */
			if(main.isSetEntranceClicked() && !main.getGameEng().getLevel().isExit(x, y)){
				try {
					main.getGameEng().getLevel().setEntrance(x, y);
					if (entrancePane != null) 
						entrancePane.setBackground(main.getEmptyBg());
					((Pane) pointNode.getNode()).setBackground(main.getEntreeBg());
					entrancePane = (Pane) pointNode.getNode();
				} catch (Error error) {
					Outils.showAlert(AlertType.ERROR, "Set Entrance", "Error", "Emplacement non valide");
				} finally {
					main.setSetEntranceClicked(false);
				}
			}
			/* si on est en train de set une exit */
			else if(main.isSetExitClicked() && !main.getGameEng().getLevel().isEntrance(x, y)){
				try {
					main.getGameEng().getLevel().setExit(x, y);
					if (exitPane != null) 
						exitPane.setBackground(main.getEmptyBg());
					((Pane) pointNode.getNode()).setBackground(main.getEntreeBg());
					exitPane = (Pane) pointNode.getNode();
				} catch( Error error) {
					Outils.showAlert(AlertType.ERROR, "Set Exit", "Error", "Emplacement non valide");
				} finally {
					main.setSetExitClicked(false);
				}
			}
			else {
				if (!main.getGameEng().getLevel().isEntrance(x, y) && !main.isSetEntranceClicked()
						&& !main.getGameEng().getLevel().isExit(x, y) && !main.isSetExitClicked()) {
					if (event.getButton() == MouseButton.PRIMARY){
						((Pane) pointNode.getNode()).setBackground(main.getDirtBg());
						main.getGameEng().getLevel().setNature(x, y, Nature.DIRT);
					}else if(event.getButton() == MouseButton.SECONDARY){
						((Pane) pointNode.getNode()).setBackground(main.getMetalBg());
						main.getGameEng().getLevel().setNature(x, y, Nature.METAL);
					}else if(event.getButton() == MouseButton.MIDDLE){
						((Pane) pointNode.getNode()).setBackground(main.getEmptyBg());
						main.getGameEng().getLevel().setNature(x, y, Nature.EMPTY);
					}
				}
			}
		}
	}


	public PointNode getCoordonneesClic(MouseEvent event) {
		if (plateauGridPane.getChildren().size() == 0) {
			return null;
		}
		for(Node node: plateauGridPane.getChildren()) {
			if(node instanceof Pane) {
				if( node.getBoundsInParent().contains(event.getX(), event.getY())) {
					int y = GridPane.getRowIndex(node);
					int x = GridPane.getColumnIndex(node);
					return new PointNode(x,y,node);
				}
			}
		}
		System.err.println("getCoordonneesClic : Je ne dois jamais passer ici");
		return null;
	}
}
