import java.util.TreeSet;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

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
	
	protected Button calcAveButton;
	
	private TextArea textArea;
	
	private ControlPanel controlPanel;

	public CustomPanel(ControlPanel controlPanel) {
		this.controlPanel = controlPanel;
		createComponents();
		
		this.add(fourAveButton, 0, 0);
		this.add(oneAveButton, 1, 0);
		
		CustomPanel.setColumnSpan(shownStaAveBox, 2);
		this.add(shownStaAveBox, 0, 1);		
		textArea = controlPanel.getTextArea();
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
		
		calcAveButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				calcAveValue(shownStaAveBox.isSelected());
			}
			
		});
	}
	
	private void createComponents() {
		radioGroup = new ToggleGroup();
		fourAveButton = new RadioButton();
		fourAveButton.setText("4-Letter Average");
		oneAveButton = new RadioButton();
		oneAveButton.setText("1-Letter Average");
		
		radioGroup.getToggles().add(fourAveButton);
		radioGroup.getToggles().add(oneAveButton);
		radioGroup.selectToggle(fourAveButton);
		
		shownStaAveBox = new CheckBox();
		shownStaAveBox.setText("Use Shown Stations Only");
		shownStaAveBox.setDisable(true);
		
		asciiAveField = new TextField();
		asciiAveField.setEditable(false);
		
		calcAveButton = new Button();
		calcAveButton.setText("Calculate Average");
	}
	
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
