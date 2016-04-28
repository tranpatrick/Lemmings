package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Outils {
	
	public static boolean isNumber(String string) {
		return string.matches("\\d+");
	}
	
	public static void showAlert(AlertType alertType, String title, 
			String headerText, String content) {
		Alert alert = new Alert(alertType);
		if (title != null)
			alert.setTitle(title);
		if (headerText!= null)
			alert.setHeaderText(headerText);
		if (content!= null)
			alert.setContentText(content);
		alert.show();
	}
	

}
