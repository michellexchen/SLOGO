package view;

import java.io.IOException;
import java.util.Observer;
import exception.SLogoException;
import javafx.collections.ObservableList;


/*
 * CODE MASTERPIECE
 * 
 * I chose this View class because it's one of the two fundamental APIs for this program.
 * It defines and sets out a contract of methods that other class can access when they
 * need to communicate with SLogoView.
 * 
 * API Design should keep generality in mind. Previously, generics were not used for these
 * interfaces. However, I felt that the frontman interfaces needed to keep themselves as 
 * general as possible by using Java Generics.This was also discussed during lecture.
 * 
 *  * In addition to this change, this class along with View has been refactored and modified
 * numerous times over the duration of the project, so I felt that this was appropriate
 * for code masterpiece.
 * 
 * Some of the code in SLogoWorkspace and SLogoGUIController has also been modified.
 * 
 */




/**
 * View interface - A contract for Model
 * View in MVC Design Pattern: Model can only call methods
 * defined in this interface
 * 
 * @author Hunter
 * @param <T>
 */
public interface View<T> {

    SLogoVisualizer getCurrentVisualizer();

    void addVisualizer() throws SLogoException, IOException;

    void switchVisualizer(int index);

    Observer getObserver();
    
    String getLanguage();

    void updateCustoms (ObservableList<T> customs);
    
    void updateVariables (ObservableList<T> variables);

    void updateDisplayData ();
    
}