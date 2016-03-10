/**
 * 
 */
package exception;

import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;

/**
 * SLogo's Exception class that extends Exception abstract class in the Java
 * library
 * 
 * @author Hunter Lee
 *
 */
public class SLogoException extends Exception {

	private Optional<String> unresolvedException;

	/**
	 * @param arg0
	 */
	public SLogoException(String arg0) {
		super(arg0);
		Dialog alert = new Dialog();
		alert.setTitle("SLOGO EXCEPTION");
		alert.setHeaderText("ERROR: " + arg0);
		ButtonType buttonTypeOk = new ButtonType("Okay");
		alert.getDialogPane().getButtonTypes().add(buttonTypeOk);

		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("myDialog.css").toExternalForm());
		dialogPane.getStyleClass().add("myDialog");
		alert.showAndWait();
	}

	/**
	 * @param instruction, numParams
	 * Resource file 
	 */
	public SLogoException(String instruction, int numParams){	
		super(instruction);
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Instructions");
		dialog.setHeaderText(instruction);
		dialog.setContentText("Please enter new argument(s) (separated by space if more than one):");
		unresolvedException = dialog.showAndWait();
	}

	public Optional<String> getNewInput(){
		return unresolvedException;
	}
	
}
