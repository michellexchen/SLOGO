package deprecated_to_be_deleted;

import java.util.Collection;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
@Deprecated
/**
 * @author Hunter Lee
 * @param <E>
 *
 */
public interface HistoryViewer<E> {

	public Region getPane();

	public <E> void getHistory(List<E> history);

	public String getClickedCommand(ActionEvent e);
}