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
	 * Main method
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/*
	 * Generates frame and panels
	 */
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		//sets title
		Stage stage = new Stage();
		stage.setTitle("Hamming Distance");
		
		//Create Components
		controlPanel = new ControlPanel();
		customPanel = new CustomPanel(controlPanel);
		
		//sets the layout manager
		GridPane pane = new GridPane();
		Scene scene = new Scene(pane, 700, 600);
		//TODO: Add components
		pane.add(controlPanel, 0, 0);
		pane.add(customPanel, 1, 0);
		
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	}
}
