package workspace;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class UIScrollPane extends Node {

	private String myTitle;
	private Label myTitleLabel;
	private VBox myVBox;
	private ScrollPane myScrollPane;
	
	public UIScrollPane(String title, int width, int height) {
		myVBox = new VBox();
		myScrollPane = new ScrollPane();
		myScrollPane.setPrefWidth(width);
		myScrollPane.setPrefHeight(height);
		
		myTitle = title;
		myTitleLabel = new Label(myTitle);
		
		myVBox.getChildren().addAll(myTitleLabel, myScrollPane);
	}

	@Override
	protected NGNode impl_createPeer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseBounds impl_computeGeomBounds(BaseBounds bounds, BaseTransform tx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean impl_computeContains(double localX, double localY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object impl_processMXNode(MXNodeAlgorithm alg, MXNodeAlgorithmContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}

}
