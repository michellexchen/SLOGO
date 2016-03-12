package view;

import java.io.IOException;
import java.util.Observer;
import exception.SLogoException;
import javafx.collections.ObservableList;

/**
 * View interface - A contract for Model
 * View in MVC Design Pattern: Model can only call methods
 * defined in this interface
 * 
 * @author Hunter
 * @param <E>
 * @param <T>
 */
public interface View<T> {

    public SLogoVisualizer getCurrentVisualizer();

    public void addVisualizer() throws SLogoException, IOException;

    public void switchVisualizer(int index);

    public Observer getObserver();
    
    public String getLanguage();

    public void updateVariable (ObservableList<T> variables);

    public void updateDisplayData ();
    
}