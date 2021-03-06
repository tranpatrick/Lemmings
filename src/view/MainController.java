package view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.lemmings.services.Lemming;
import model.lemmings.services.Level.Nature;
import view.Main.Images;

public class MainController {

	/* Enum qui permet de savoir quelle chase est sélectionnée (pour changer le type d'un lemming) */
	private enum SelectedType{
		DIGGER, CLIMBER, BUILDER, FLOATER, STOPPER, BASHER, BOMBER, MINER, NONE;
	}

	private static long REFRESH_TIME = 300;

	private Pane exitPane;
	private Pane entrancePane;

	/* Booleens pour les types de lemmings */
	private SelectedType setLemming;
	private boolean stop = false;
	private Thread t;

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
	@FXML private Button annihilationButton;
	@FXML private GridPane plateauGridPane;
	@FXML private HBox scoreHBox;
	@FXML private HBox vitesseHBox;
	@FXML private Label creesLabel;
	@FXML private Label mortsLabel;
	@FXML private Label sauvesLabel;
	@FXML private Label scoreLabel;
	@FXML private Label tourLabel;
	@FXML private Label labelDigger;
	@FXML private Label labelClimber;
	@FXML private Label labelBuilder;
	@FXML private Label labelFloater;
	@FXML private Label labelStopper;
	@FXML private Label labelBomber;
	@FXML private Label labelBasher;
	@FXML private Label labelMiner;
	@FXML private TextField hauteurTextField;
	@FXML private TextField largeurTextField;
	@FXML private TextField sizeColonyTextField;
	@FXML private TextField spawnSpeedTextField;
	@FXML private TextField jetonsTextField;
	@FXML private TextField vitesseTextField;
	@FXML private VBox dimensionVBox;

	private Main main;
	
	private int nbJetons;

	public void setMainApp(Main main) {
		this.main = main;
	}

	private void initLevel() {
		String largeur = largeurTextField.getText();
		String hauteur = hauteurTextField.getText();
		String taille = sizeColonyTextField.getText();
		String vitesse = spawnSpeedTextField.getText();
		String jetons = jetonsTextField.getText();
		if (Outils.isNumber(largeur) && Outils.isNumber(hauteur)
				&& Outils.isNumber(taille) && Outils.isNumber(vitesse)
				&& Outils.isNumber(jetons)) {
			int width = Integer.parseInt(largeur);
			int height = Integer.parseInt(hauteur);
			int sizeColony = Integer.parseInt(taille);
			int spawnSpeed = Integer.parseInt(vitesse);
			nbJetons = Integer.parseInt(jetons);
			if ( width > 0 && height > 0 && sizeColony > 0 && spawnSpeed > 0) { 
				main.initGameEng(width, height, sizeColony, spawnSpeed, nbJetons);
				main.initialiserLevel(width, height);				
			}
			else {
				Outils.showAlert(AlertType.ERROR, "Probleme", 
						"Parametres incorrects", "Hauteur, largeur, spawnSpeed"
								+ " et sizeColony doivent strictement positifs");
			}
		}
		else {
			Outils.showAlert(AlertType.ERROR, "Probleme", 
					"Parametres incorrects", "Hauteur, largeur, spawnSpeed"
							+ " et sizeColony doivent etre des nombres ");
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

	/* Permet de mettre en place la gestion de scroll sur la spawnSpeedTextField */
	void initScrollOnSpawnSpeed(){
		spawnSpeedTextField.addEventFilter(ScrollEvent.SCROLL, new EventHandler<ScrollEvent>() {
			@Override
			public void handle(ScrollEvent se) {
				try{
					if(!main.getGameEng().gameOver()){
						String s;
						int ss;
						s = spawnSpeedTextField.getText();
						if(Outils.isNumber(s)){
							ss = Integer.parseInt(s);
							if(se.getDeltaY() > 0){
								main.getJoueur().changeSpawnSpeed(ss+1);
								spawnSpeedTextField.setText(""+(ss+1));
							}
							else if(se.getDeltaY() < 0){
								if(ss == 1){
									main.getJoueur().changeSpawnSpeed(1);
									spawnSpeedTextField.setText(""+1);
								}else{
									main.getJoueur().changeSpawnSpeed(ss-1);
									spawnSpeedTextField.setText(""+(ss-1));
								}
							}
						}
					}
				}catch(Error e){
					System.err.println(e.getMessage());
				}
			}
		});
	}

	@FXML
	void updateSpeed(ScrollEvent event) {
		try{
			if(!main.getGameEng().gameOver()){
					if(event.getDeltaY() > 0){
						if (REFRESH_TIME < 1000) {
						REFRESH_TIME += 50;
						//vitesseTextField.setText(""+(double) REFRESH_TIME/1000+"s");
						}
					}
					else if(event.getDeltaY() < 0){
						if (REFRESH_TIME > 50) {
							REFRESH_TIME -= 50;
							//vitesseTextField.setText(""+(double) REFRESH_TIME/1000+"s");
						}
					}
					vitesseTextField.setText(""+(double) REFRESH_TIME/1000+"s");
			}
		}catch(Error e){
			System.err.println(e.getMessage());
		}
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
						Outils.showAlert(AlertType.ERROR, "Set Entrance", "Emplacement case d'entrée non valide, "
								, "La case d'entrée doit être située sur une case vide et sous une case vide");
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
								, "La case de sortie doit être située sur une case metal et sous une case vide");
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
					updateJetons(setLemming);
				}
			}catch(Error e){
				System.out.println(e.getMessage());
			}
		}
	}

	@FXML
	void goPlay(ActionEvent event) {
		try {
			/* On récupère le spawnSpeed */
			String ss = spawnSpeedTextField.getText();
			int speed;
			if(Outils.isNumber(ss)){
				speed = Integer.parseInt(ss);
				main.getJoueur().changeSpawnSpeed(speed);

				main.getGameEng().getLevel().goPlay();

				/* Activer/Désactiver/Visible les boutons */
				hauteurTextField.setDisable(true);
				largeurTextField.setDisable(true);
				sizeColonyTextField.setDisable(true);
				setEntranceButton.setDisable(true);
				setExitButton.setDisable(true);
				goPlayButton.setDisable(true);
				annihilationButton.setDisable(false);
				rejouerButton.setDisable(false);
				diggerButton.setVisible(true);
				climberButton.setVisible(true);
				builderButton.setVisible(true);
				floaterButton.setVisible(true);
				bomberButton.setVisible(true);
				stopperButton.setVisible(true);
				basherButton.setVisible(true);
				minerButton.setVisible(true);
				dimensionButton.setDisable(true);
				jetonsTextField.setDisable(true);

				/* setLemming à NONE (aucune case de type n'est sélectionné) */
				setLemming = SelectedType.NONE;

				/* Affichage du nombre de jetons pour chaque type */
				labelDigger.setText(""+main.getJoueur().getNbJetons("DIGGER"));
				labelClimber.setText(""+main.getJoueur().getNbJetons("CLIMBER"));
				labelBuilder.setText(""+main.getJoueur().getNbJetons("BUILDER"));
				labelFloater.setText(""+main.getJoueur().getNbJetons("FLOATER"));
				labelBomber.setText(""+main.getJoueur().getNbJetons("BOMBER"));
				labelStopper.setText(""+main.getJoueur().getNbJetons("STOPPER"));
				labelBasher.setText(""+main.getJoueur().getNbJetons("BASHER"));
				labelMiner.setText(""+main.getJoueur().getNbJetons("MINER"));

				/* Mise en place du handler pour le scroll sur le SpawnSpeedTextField */
				initScrollOnSpawnSpeed();
				vitesseHBox.setVisible(true);
				t = new Thread(new Runnable() {
					@Override
					public void run() {
						while (!main.getGameEng().gameOver() && !stop) {
							main.getGameEng().step();
							updateGameInfo();
							try {
								Thread.sleep(REFRESH_TIME);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						updateScore();
						//goPlayButton.setDisable(false);
						vitesseHBox.setVisible(false);
					}
				});
				t.start();
			}else{
				Outils.showAlert(Alert.AlertType.ERROR, "Spawn Speed", "Spawn speed invalide", "Spawn speed doit être un nombre");
			}
		} catch (Error e) {
			main.getGameEng().getLevel().goEditing();
			Outils.showAlert(AlertType.ERROR, 
					"Erreur", 
					"Terrain de jeu non valide", 
					"La partie ne peut pas commencer : le bord du terrrain "
							+ "doit etre en metal, et le terrain doit avoir "
							+ "une entree et une sortie");
		}
	}


	@FXML
	void relancerNiveau(ActionEvent event) {
		stop = true;
		
		/* Désactiver le bouton goPlay, activer le bouton annihilation*/
		goPlayButton.setDisable(true);
		annihilationButton.setDisable(false);
		
		
		main.getGameEng().getLevel().reset();
		spawnSpeedTextField.setText("10");
		main.getGameEng().init(main.getGameEng().getSizeColony(), 10);
		main.getJoueur().init(nbJetons);
		for(SelectedType s : SelectedType.values())
			updateJetons(s);
		stop = false;
		if (!t.isAlive()) {
			scoreHBox.setVisible(false);
			vitesseHBox.setVisible(true);
			t = new Thread(new Runnable() {
				@Override
				public void run() {
					while (!main.getGameEng().gameOver() && !stop) {
						main.getGameEng().step();
						updateGameInfo();
						try {
							Thread.sleep(REFRESH_TIME);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}		
					}
					vitesseHBox.setVisible(false);
					updateScore();
					//goPlayButton.setDisable(false);
				}
			});
			t.start();
		}
	}

	@FXML
	void annihilation(ActionEvent event){
		try{
			main.getJoueur().annihilation();
			annihilationButton.setDisable(true);			
		}catch(Error e){
			System.out.println(e.getMessage());
		}
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
				creesLabel.setText(""+main.getGameEng().getNombreCrees());
			}
		});
	}


	public void updateNbMorts() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				mortsLabel.setText(""+main.getGameEng().getNombreMorts());
			}
		});
	}

	public void updateNbSauves() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				sauvesLabel.setText(""+main.getGameEng().getNombreSauves());
			}
		});
	}

	public void updateTour() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				tourLabel.setText(""+main.getGameEng().getNombreTours());
			}
		});
	}

	public void updateGameInfo() {
		updateNbCrees();
		updateNbMorts();
		updateNbSauves();
		updateTour();
	}

	public void updateJetons(SelectedType sl){
		switch(sl){
		case DIGGER:
			labelDigger.setText(""+main.getJoueur().getNbJetons(sl.toString()));
			break;
		case CLIMBER:
			labelClimber.setText(""+main.getJoueur().getNbJetons(sl.toString()));
			break;
		case BUILDER:
			labelBuilder.setText(""+main.getJoueur().getNbJetons(sl.toString()));
			break;
		case FLOATER:
			labelFloater.setText(""+main.getJoueur().getNbJetons(sl.toString()));
			break;
		case STOPPER:
			labelStopper.setText(""+main.getJoueur().getNbJetons(sl.toString()));
			break;
		case BOMBER:
			labelBomber.setText(""+main.getJoueur().getNbJetons(sl.toString()));
			break;
		case BASHER:
			labelBasher.setText(""+main.getJoueur().getNbJetons(sl.toString()));
			break;
		case MINER:
			labelMiner.setText(""+main.getJoueur().getNbJetons(sl.toString()));
			break;
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
		return null;
	}

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
