/**
 * 
 */
package exception;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

/**
 * SLogo's Exception class that extends Exception abstract class in the Java
 * library
 * 
 * @author Hunter Lee
 *
 */
public class SLogoException extends Exception {

	
	
	public SLogoException() {
		
	}

	/**
	 * @param arg0
	 */
	public SLogoException(String arg0) {
		super(arg0);
		Dialog alert = new Dialog();
		alert.setTitle("SLOGO EXCEPTIOM");
		alert.setHeaderText("ERROR: " + arg0);
		ButtonType buttonTypeOk = new ButtonType("Okay");
		alert.getDialogPane().getButtonTypes().add(buttonTypeOk);
		
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("myDialog.css").toExternalForm());
		dialogPane.getStyleClass().add("myDialog");
		alert.showAndWait();
	}

	/**
	 * @param arg0
	 */
	public SLogoException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public SLogoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public SLogoException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
