package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainController {

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
        System.out.println("dsfldsflfldksf");
    }
    
    @FXML
    void testMethode(ActionEvent event) {
		System.out.println("JE PASSE ICI");
		//TODO check si c'est bien un int
		int largeur = Integer.parseInt(largeurTextField.getText());
		int hauteur = Integer.parseInt(hauteurTextField.getText());
		main.initGame(largeur, hauteur);
		main.initialiserPlateauGrid(largeur, hauteur);
    }
    
}
