package view;

import java.util.Set;

import javafx.application.Platform;
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
import model.lemmings.contract.JoueurContract;
import model.lemmings.impl.JoueurImpl;
import model.lemmings.services.Joueur;
import model.lemmings.services.Lemming;
import model.lemmings.services.Level.Nature;
import view.Main.Images;

public class MainController {

	/* Enum qui permet de savoir quelle chase est sélectionnée (pour changer le type d'un lemming) */
	private enum SelectedType{
		DIGGER, CLIMBER, BUILDER, FLOATER, STOPPER, BASHER, BOMBER, MINER, NONE;
	}

	private static final long REFRESH_TIME = 400;

	private Pane exitPane;
	private Pane entrancePane;
	
	/* Booleens pour les types de lemmings */
	private SelectedType setLemming;

	@FXML private Button dimensionButton;
	@FXML private Button editingButton;
	@FXML private Button goPlayButton;
	@FXML private Button rejouerButton;
	@FXML private Button setEntranceButton;
	@FXML private Button setExitButton;
	@FXML private Button diggerButton;
	@FXML private Button climberButton;
	@FXML private Button builderButton;
	@FXML private Button floaterButton;
	@FXML private Button stopperButton;
	@FXML private Button basherButton;
	@FXML private Button bomberButton;
	@FXML private Button minerButton;
	@FXML private GridPane plateauGridPane;
	@FXML private HBox scoreHBox;
	@FXML private Label creesLabel;
	@FXML private Label mortsLabel;
	@FXML private Label sauvesLabel;
	@FXML private Label scoreLabel;
	@FXML private Label tourLabel;
	@FXML private TextField hauteurTextField;
	@FXML private TextField largeurTextField;
	@FXML private TextField sizeColonyTextField;
	@FXML private TextField spawnSpeedTextField;
	@FXML private VBox dimensionVBox;

	private Main main;
	

	public void setMainApp(Main main) {
		this.main = main;
	}

	private void initLevel() {
		String largeur = largeurTextField.getText();
		String hauteur = hauteurTextField.getText();
		String taille = sizeColonyTextField.getText();
		String vitesse = spawnSpeedTextField.getText();
		if (Outils.isNumber(largeur) && Outils.isNumber(hauteur)
				&& Outils.isNumber(taille) && Outils.isNumber(vitesse)) {
			int width = Integer.parseInt(largeur);
			int height = Integer.parseInt(hauteur);
			int sizeColony = Integer.parseInt(taille);
			int spawnSpeed = Integer.parseInt(vitesse);
			main.initGameEng(width, height, sizeColony, spawnSpeed);
			main.initialiserLevel(width, height);
		}
		else {
			System.err.println("sfsdf");
			//TODO alert
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
		goPlayButton.setDisable(false);
		//TODO attendtion platform runLater
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
	void handleMouseEventOnGrid(MouseEvent event) {
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

		/* mode editing */
		if (main.getGameEng().getLevel().isEditing()) {
			if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
				/* si on est en train de modifier une case normale */
				if (!main.getGameEng().getLevel().isEntrance(x, y) && !main.isSetEntranceClicked()
						&& !main.getGameEng().getLevel().isExit(x, y) && !main.isSetExitClicked() 
						&& main.isEditing()) {
					if (event.getButton() == MouseButton.PRIMARY){
						((Pane) pointNode.getNode()).setBackground(main.getBackground(Images.DIRT));
						main.getGameEng().getLevel().setNature(x, y, Nature.DIRT);
					}else if(event.getButton() == MouseButton.SECONDARY){
						((Pane) pointNode.getNode()).setBackground(main.getBackground(Images.METAL));
						main.getGameEng().getLevel().setNature(x, y, Nature.METAL);
					}else if(event.getButton() == MouseButton.MIDDLE){
						((Pane) pointNode.getNode()).setBackground(main.getBackground(Images.EMPTY));
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
							entrancePane.setBackground(main.getBackground(Images.EMPTY));
						((Pane) pointNode.getNode()).setBackground(main.getBackground(Images.ENTREE));
						entrancePane = (Pane) pointNode.getNode();
					} catch (Error error) {
						Outils.showAlert(AlertType.ERROR, "Set Entrance", "Emplacement case d'entree non valide, "
								, "La case d'entree doit etre situee sur une case vide et sous une case vide");
					} finally {
						main.setSetEntranceClicked(false);
					}
				}
				/* si on est en train de set une exit */
				else if(main.isSetExitClicked() && !main.getGameEng().getLevel().isEntrance(x, y)){
					try {
						main.getGameEng().getLevel().setExit(x, y);
						if (exitPane != null) 
							exitPane.setBackground(main.getBackground(Images.EMPTY));
						((Pane) pointNode.getNode()).setBackground(main.getBackground(Images.SORTIE));
						exitPane = (Pane) pointNode.getNode();
					} catch( Error error) {
						Outils.showAlert(AlertType.ERROR, "Set Exit", "Emplacement case de sortie non valide, "
								, "La case de sortie doit etre situee sur une case Metal et sous une case vide");
					} finally {
						main.setSetExitClicked(false);
					}
				}
				else {
					/* si on est en train de modifier une case normale */
					if (!main.getGameEng().getLevel().isEntrance(x, y) && !main.isSetEntranceClicked()
							&& !main.getGameEng().getLevel().isExit(x, y) && !main.isSetExitClicked()) {
						if (event.getButton() == MouseButton.PRIMARY){
							((Pane) pointNode.getNode()).setBackground(main.getBackground(Images.DIRT));
							main.getGameEng().getLevel().setNature(x, y, Nature.DIRT);
						}else if(event.getButton() == MouseButton.SECONDARY){
							((Pane) pointNode.getNode()).setBackground(main.getBackground(Images.METAL));
							main.getGameEng().getLevel().setNature(x, y, Nature.METAL);
						}else if(event.getButton() == MouseButton.MIDDLE){
							((Pane) pointNode.getNode()).setBackground(main.getBackground(Images.EMPTY));
							main.getGameEng().getLevel().setNature(x, y, Nature.EMPTY);
						}
					}
				}
			}
		}
		/* Mode play */
		else {
			//TODO reagir en temps reel
			System.err.println("Clic sur "+pointNode.toString());
			try{
				/* Si changement de type */
				Lemming l = main.getJoueur().select(x, y);

				/* Si la case était vide alors on annule */ 
				if (l != null){
					switch(setLemming){
					case DIGGER:
						main.getJoueur().changeClasse(l, "DIGGER");
						break;
					case CLIMBER:
						main.getJoueur().changeClasse(l, "CLIMBER");
						break;
					case BUILDER:
						main.getJoueur().changeClasse(l, "BUILDER");
						break;
					case BOMBER:
						main.getJoueur().changeClasse(l, "BOMBER");
						break;
					case STOPPER:
						main.getJoueur().changeClasse(l, "STOPPER");
						break;
					case FLOATER:
						main.getJoueur().changeClasse(l, "FLOATER");
						break;
					case BASHER:
						main.getJoueur().changeClasse(l, "BASHER");
						break;
					case MINER:
						main.getJoueur().changeClasse(l, "MINER");
						break;
					}
				}
			}catch(Error e){
				System.out.println(e.getMessage());
			}
		}
	}

	@FXML
	void goPlay(ActionEvent event) {
		try {
			main.getGameEng().getLevel().goPlay();
			
			/* Activer/Désactiver/Visible les boutons */
			goPlayButton.setDisable(true);
			diggerButton.setVisible(true);
			climberButton.setVisible(true);
			builderButton.setVisible(true);
			floaterButton.setVisible(true);
			bomberButton.setVisible(true);
			stopperButton.setVisible(true);
			basherButton.setVisible(true);
			minerButton.setVisible(true);

			/* setLemming à NONE (aucune case de type n'est sélectionné) */
			setLemming = SelectedType.NONE;

			//TODO masquer des zones de saisies ( colony, largeur hauteur) mais laisser qqch pour changer spawnspeed
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					while (!main.getGameEng().gameOver()) {
						main.getGameEng().step();
						updateGameInfo();
						//TODO ptet revoir contrat gameEng
						try {
							Thread.sleep(REFRESH_TIME);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}		
					}
					updateScore();
					goPlayButton.setDisable(false);
				}
			});
			t.start();
		} catch (Error e) {
			//TODO attention is editing est a true 
			Outils.showAlert(AlertType.ERROR, 
					"Erreur", 
					"Terrain de jeu non valide", e.getMessage());
			//							"La partie ne peut pas commencer : le bord du terrrain "
			//							+ "doit etre en metal, et le terrain doit avoir "
			//							+ "une entree et une sortie");
		}
	}

	@FXML
	void changeSpawnSpeed(ActionEvent event) {
		if (!main.getGameEng().gameOver()) {
			String vitesse = spawnSpeedTextField.getText();
			if (Outils.isNumber(vitesse)) {
				int spawnSpeed = Integer.parseInt(vitesse);
				main.getGameEng().setSpawnSpeed(spawnSpeed);
			}
		}
	}


	@FXML
	void goEditing(ActionEvent event) {
		// TODO reset proprement
	}

	@FXML
	void relancerNiveau(ActionEvent event) {

	}


	public void updateScore() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				scoreLabel.setText(""+main.getGameEng().score()*100+"%");
				scoreHBox.setVisible(true);
			}
		});
	}

	public void updateNbCrees() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (!main.getGameEng().gameOver())
					creesLabel.setText(""+main.getGameEng().getNombreCrees());
			}
		});
	}


	public void updateNbMorts() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (!main.getGameEng().gameOver())
					mortsLabel.setText(""+main.getGameEng().getNombreMorts());
			}
		});
	}

	public void updateNbSauves() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (!main.getGameEng().gameOver())
					sauvesLabel.setText(""+main.getGameEng().getNombreSauves());
			}
		});
	}

	public void updateTour() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (!main.getGameEng().gameOver())
					tourLabel.setText(""+main.getGameEng().getNombreTours());
			}
		});
	}

	public void updateGameInfo() {
		// TODO attention des fois valeurs incoherente a cause du runLater
		updateNbCrees();
		updateNbMorts();
		updateNbSauves();
		updateTour();
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
		System.err.println("getCoordonneesClic : Je ne dois jamais passer ici !");
		return null;
	}

	/* Permet d'obtenir le premier lemming actif que l'on rencontre sur une certaine case */
//	public Lemming getLemming(int x, int y){
//		GameEng g = main.getGameEng();
//		Set<Integer> set = g.getLemmingsActifs();
//		for(int i : set){
//			if(g.getLemming(i).getX() == x && g.getLemming(i).getY() == y)
//				return g.getLemming(i);
//		}
//		return null;
//	}

	@FXML
	void setDigger(ActionEvent event) {
		setLemming = SelectedType.DIGGER;
	}

	@FXML
	void setClimber(ActionEvent event) {
		setLemming = SelectedType.CLIMBER;
	}

	@FXML
	void setBuilder(ActionEvent event) {
		setLemming = SelectedType.BUILDER;
	}

	@FXML
	void setFloater(ActionEvent event) {
		setLemming = SelectedType.FLOATER;
	}

	@FXML
	void setBomber(ActionEvent event) {
		setLemming = SelectedType.BOMBER;
	}

	@FXML
	void setStopper(ActionEvent event) {
		setLemming = SelectedType.STOPPER;
	}

	@FXML
	void setBasher(ActionEvent event) {
		setLemming = SelectedType.BASHER;
	}

	@FXML
	void setMiner(ActionEvent event) {
		setLemming = SelectedType.MINER;
	}

}
