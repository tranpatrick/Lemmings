package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainController {

    @FXML
    private Label scoreLabel;

    @FXML
    private TextField hauteurTextField;

    @FXML
    private Label creesLabel;

    @FXML
    private Label mortsLabel;

    @FXML
    private Label sauvesLabel;

    @FXML
    private TextField largeurTextField;

    @FXML
    private VBox dimensionVBox;

    @FXML
    private HBox scoreHBox;

    @FXML
    private GridPane plateauGridPane;

    @FXML
    private Label tourLabel;

    @FXML
    private Button dimensionButton;

}
