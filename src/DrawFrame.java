import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JFrame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
 * @author Robyn Pollock
 * @version 12/1/2019
 * 
 * Project 5: For CS-2334
 * Creates a gui that allows the user to select a hamming distance.
 * 	Finds the stations that fall within that hamming distance.
 */
public class DrawFrame extends Application {
	
	
	protected ControlPanel controlPanel;
	//TODO: change to custom panel;
	protected CustomPanel customPanel;

	/*
	 * Creates frame and panel.
	 */
	public DrawFrame() throws IOException {
		//sets title
		Stage stage = new Stage();
		stage.setTitle("Hamming Distance");
		
		//Create Components
		controlPanel = new ControlPanel();
		//TODO: change to custom panel;
		customPanel = new CustomPanel(controlPanel);
		
		//sets the layout manager
		GridPane pane = new GridPane();
		Scene scene = new Scene(pane, 800, 800);
		//TODO: Add components
		pane.add(controlPanel, 0, 0);
		pane.add(customPanel, 1, 0);
		
		//setup frame
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	/*
	 * Main method
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		//sets title
		Stage stage = new Stage();
		stage.setTitle("Hamming Distance");
		
		//Create Components
		controlPanel = new ControlPanel();
		//TODO: change to custom panel;
		customPanel = new CustomPanel(controlPanel);
		
		//sets the layout manager
		GridPane pane = new GridPane();
		Scene scene = new Scene(pane, 800, 800);
		//TODO: Add components
		pane.add(controlPanel, 0, 0);
		pane.add(customPanel, 1, 0);
		
		//setup frame
		stage.setScene(scene);
		stage.setResizable(false);
	}
}
