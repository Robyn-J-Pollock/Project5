import java.util.TreeSet;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/*
 * Custom panel for Project 5
 * @author Robyn Pollock
 * @version 12/1/2019
 */
public class CustomPanel extends GridPane {
	
	
	protected ToggleGroup radioGroup;
	
	protected RadioButton fourAveButton, oneAveButton;
	
	protected CheckBox shownStaAveBox;
	
	protected TextField asciiAveField;
	
	protected Button calcAveButton, pressButton;
	
	protected ProgressBar buttonBar;
	
	protected Label loadLabel;
	
	protected TabPane tabPane;
	
	protected Tab tab1, tab2;
	
	protected Accordion accordion;
	
	private TextArea textArea;
	
	private ControlPanel controlPanel;
	
	private float buttonPressed;

	/*
	 * Creates custom panel and adds components and listeners
	 */
	public CustomPanel(ControlPanel controlPanel) {
		this.controlPanel = controlPanel;
		//Moved to method for readability
		createComponents();
		
		//Used for progress bar
		buttonPressed = 0;
		
		this.add(fourAveButton, 0, 0);
		this.add(oneAveButton, 1, 0);
		
		CustomPanel.setColumnSpan(shownStaAveBox, 2);
		this.add(shownStaAveBox, 0, 1);		
		textArea = controlPanel.getTextArea();
		//Makes showStaAveBox enabled or disabled depending on if there is 
		//input in the textArea
		textArea.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				// TODO Auto-generated method stub
				if (textArea.getText().length() == 0) {
					shownStaAveBox.setSelected(false);
					shownStaAveBox.setDisable(true);
				}
				else
				{
					shownStaAveBox.setDisable(false);
				}
			}
		});
		
		this.add(asciiAveField, 0, 2);
		this.add(calcAveButton, 1, 2);
		
		//Runs calcAveValue on button press
		calcAveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				calcAveValue(shownStaAveBox.isSelected());
			}
			
		});
		
		CustomPanel.setColumnSpan(tabPane, 3);
		CustomPanel.setVgrow(tabPane, Priority.ALWAYS);
		this.add(tabPane, 0, 6);
		
		GridPane tab1Pane = new GridPane();
		
		tab1Pane.add(buttonBar, 0, 1);
		tab1Pane.add(loadLabel, 0, 2);
		tab1Pane.add(pressButton, 0, 0);
		
		tab1.setContent(tab1Pane);
		
		//Adds 5% to the progress bar for each button press
		pressButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				buttonPressed++;
				if (buttonPressed > 20) {
					buttonPressed = 0;
					loadLabel.setVisible(false);
				}
				buttonBar.setProgress(buttonPressed*0.05);
				if (buttonPressed == 20) {
					loadLabel.setVisible(true);
				}
			}
			
		});
		
		tab2.setContent(accordion);
	}
	
	/*
	 * Creates and customizes components for panel
	 */
	private void createComponents() {
		radioGroup = new ToggleGroup();
		fourAveButton = new RadioButton();
		fourAveButton.setText("4-Letter Average");
		oneAveButton = new RadioButton();
		oneAveButton.setText("1-Letter Average");
		
		radioGroup.getToggles().add(fourAveButton);
		radioGroup.getToggles().add(oneAveButton);
		radioGroup.selectToggle(fourAveButton);
		
		pressButton = new Button();
		pressButton.setText("Press Me");
		
		buttonBar = new ProgressBar();
		
		loadLabel = new Label();
		loadLabel.setText("Loading Complete!");
		loadLabel.setVisible(false);
		
		shownStaAveBox = new CheckBox();
		shownStaAveBox.setText("Use Shown Stations Only");
		shownStaAveBox.setDisable(true);
		
		asciiAveField = new TextField();
		asciiAveField.setEditable(false);
		
		calcAveButton = new Button();
		calcAveButton.setText("Calculate Average");
		
		tabPane = new TabPane();
		tab1 = new Tab();
		tab1.setText("Tab 1");
		tab2 = new Tab();
		tab2.setText("Tab 2");
		tabPane.getTabs().addAll(tab1, tab2);
		
		accordion = new Accordion();
		TitledPane pane1 = new TitledPane("Never Gonna", new Label("Give You Up!"));
		TitledPane pane2 = new TitledPane("Never Gonna", new Label("Let You Down!"));
		TitledPane pane3 = new TitledPane();
		pane3.setText("Never Gonna");
		GridPane pane3Group = new GridPane();
		pane3Group.add(new Label("Run Around"), 0, 1);
		pane3Group.add(new Label("And Desert You!"), 0, 2);
		pane3.setContent(pane3Group);
		accordion.getPanes().addAll(pane1, pane2, pane3);		
	}
	
	/*
	 * useTextArea determines if the text area will be used or the full list
	 * This method goes through the designated list and determines the average letter
	 * value of that list, either that of a single letter or 4 letter value dependent on
	 * the users selection.
	 */
	private void calcAveValue(boolean useTextArea) {
		TreeSet<String> staList = new TreeSet<String>();
		int[] placeValue = new int[] { 0, 0, 0, 0 };
		
		if (useTextArea) {
			String[] tempArray = textArea.getText(0, textArea.getLength() - 1).split("\n");
			for (String string : tempArray) 
				staList.add(string);
		}
		else {
			staList = controlPanel.getFullList();
		}
		
		for (String item : staList) {
			char[] itemArray = item.toCharArray();
			for (int x = 0; x < 4; x++) {
				placeValue[x] += (int)itemArray[x];
			}
		}
		
		for (int x = 0; x < 4; x++) {
			placeValue[x] = placeValue[x]/staList.size();
		}
		
		if (fourAveButton.isSelected()) {
			String tempString = "";
			for (int x = 0; x < 4; x++)
				tempString += (char)placeValue[x];
			asciiAveField.setText(tempString);
		}
		
		else {
			int fullAve = 0;
			for (int x = 0; x < 4; x++) 
				fullAve += placeValue[x];
			fullAve = fullAve/4;
			asciiAveField.setText(String.valueOf((char)fullAve));
		}
	}
}
