package model;

import java.io.IOException;

import exception.SLogoException;
import javafx.collections.ObservableList;

/*
 * CODE MASTERPIECE
 * 
 * I chose this Model class because it's one of the two fundamental APIs for this program.
 * It defines and sets out a contract of methods that other class can access when they
 * need to communicate with SLogoModel.
 * 
 * API Design should keep generality in mind. Previously, generics were not used for these
 * interfaces. However, I felt that the frontman interfaces needed to keep themselves as 
 * general as possible by using Java Generics. This was also discussed during lecture.
 * 
 * In addition to this change, this class along with View has been refactored and modified
 * numerous times over the duration of the project, so I felt that this was appropriate
 * for code masterpiece.
 * 
 * Some of the code in SLogoWorkspace and SLogoGUIController has also been modified.
 * 
 */



/**
 * Model interface - A contract for View Model in MVC Design Pattern: View can
 * only call methods defined in this interface
 * 
 * @author Hunter
 * @param <E>
 *
 */
public interface Model<E> {

    void readCommand(String command) throws SLogoException;

    void addWorkspace() throws SLogoException, IOException;

    SLogoWorkspace getCurrentWorkspace();

    ObservableList<E> getObservableDataList();

    void switchWorkspace(int index) throws SLogoException;

}