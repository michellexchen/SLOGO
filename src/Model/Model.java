package Model;

import Controller.SLogoException;
import View.View;

public interface Model {

	public void readCommand(String command) throws SLogoException;

	public void setView(View myView);

	public void createBackend();

}