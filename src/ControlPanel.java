import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/*
 * @author Robyn Pollock
 * @version 12/1/2019
 * 
 * Control panel for the frame.
 */


public class ControlPanel extends GridPane {
	
	protected Slider hamDistSlider;
	
	protected Button showStationButton, calcHDButton, addStationButton;
	
	protected TextArea showStationArea;
	
	protected ComboBox<String> compareWithBox;
	
	protected TextField hamDistField, addStationField;
	
	protected TextField[] textDistFields;
	
	protected Label hamDistLabel, compareWithLabel;
	
	protected Label[] textDistLabels;
	
	private TreeSet<String> compareList;
	
	/*
	 * Creates and controls required panel
	 */
	public ControlPanel() throws IOException {	
		
		//Method to create components
		createComponents();
		
		this.add(hamDistLabel, 0, 0);
		
		this.add(hamDistField, 1, 0);
		
		ControlPanel.setColumnSpan(hamDistSlider, 2);
		this.add(hamDistSlider, 0, 1);
		
		this.add(showStationButton, 0, 2);
		
		ControlPanel.setColumnSpan(showStationArea, 2);
		this.add(showStationArea, 0, 3);
		
		this.add(compareWithLabel, 0, 4);
		
		this.add(compareWithBox, 1, 4);
		
		ControlPanel.setColumnSpan(calcHDButton, 2);
		this.add(calcHDButton, 0, 5);
		
		for (int y = 6; y < 11; y++)
		{
			for (int x = 0; x < 2; x++)
			{
				if (x == 0)
					this.add(textDistLabels[y - 6], x, y);
				else if (x == 1)
					this.add(textDistFields[y - 6], x, y);
			}
		}
		
		this.add(addStationButton, 0, 11);
		
		this.add(addStationField, 1, 11);
		
		//Adjusts text field on slider change
		hamDistSlider.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				hamDistField.setText("" + (int)hamDistSlider.getValue());
			}
			
		});
		
		//Runs showStations method on button push
		showStationButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				showStations(Integer.parseInt(hamDistField.getText()));
			}
			
		});
		
		//Runs calcHamDist function on button press;
		calcHDButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				calcHamDist((String)compareWithBox.getValue());
			}
			
		});
		
		addStationButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				addStation(addStationField.getText().toUpperCase());
			}
			
		});
	}
	
	public TextArea getTextArea() {
		return showStationArea;
	}
	
	public TreeSet<String> getFullList() {
		return compareList;
	}
	
	/*
	 * Method used to create components
	 * Seperated to make editing and readability easier
	 */
	private void createComponents() throws IOException {
		hamDistSlider = new Slider(1, 4, 2);
		hamDistSlider.setMajorTickUnit(1);
		hamDistSlider.setMinorTickCount(0);
		hamDistSlider.setSnapToTicks(true);
		hamDistSlider.setShowTickMarks(true);
		hamDistSlider.setShowTickLabels(true);
		
		hamDistLabel = new Label("Enter Hamming Dist: ");
		hamDistLabel.setAlignment(Pos.CENTER_RIGHT);
		
		hamDistField = new TextField();
		hamDistField.setEditable(false);
		hamDistField.setText("" + (int)hamDistSlider.getValue());
		
		showStationButton = new Button("Show Station");
		calcHDButton = new Button("Calculate HD");
		addStationButton = new Button("Add Station");
		
		showStationArea = new TextArea();
		showStationArea.setEditable(false);
		showStationArea.setMaxWidth(350);
				
		compareWithBox = new ComboBox<String>();
		compareWithBox.setEditable(false);
		//Generates base compare with drop down list
		generateCompareWith();
		compareWithBox.getSelectionModel().select(0);
		compareWithLabel = new Label("Compare with: ");
		compareWithLabel.setAlignment(Pos.CENTER_RIGHT);
		
		textDistFields = new TextField[5];
		for (int x = 0; x < 5; x++) {
			textDistFields[x] = new TextField();
			textDistFields[x].setEditable(false);
		}
		
		textDistLabels = new Label[5];
		for (int x = 0; x < 5; x++) {
			textDistLabels[x] = new Label("Distance " + x);
			textDistLabels[x].setAlignment(Pos.CENTER_RIGHT);
		}
		
		addStationField = new TextField();
	}
	
	/*
	 * Reads Mesonet.txt and adds the list to the drop down box
	 */
	private void generateCompareWith() throws IOException {
		compareList = new TreeSet<String>();
		
		BufferedReader br = new BufferedReader(new FileReader("Mesonet.txt"));
		
		String line = br.readLine();
		while (line != null) {
			line = line.trim().toUpperCase();
			if (line.length() == 4)
				compareList.add(line);
			line = br.readLine();
		}
		
		compareWithBox.getItems().addAll(compareList);
	}
	
	/*
	 * Shows station with the same hamming distance,
	 */
	private void showStations(int hamDist) {
		showStationArea.setText("");
		for (String thing : compareList)
		{
			if (hamDistComparer((String)compareWithBox.getValue(), thing) == hamDist)
			{
				showStationArea.setText(showStationArea.getText() + thing + "\n");
			}
		}
	}
	
	/*
	 * Compares the hamming distance between two stations
	 */
	private int hamDistComparer(String firstStation, String secondStation) {
		char[] firstChar = firstStation.toCharArray();
		char[] secondChar = secondStation.toCharArray();
		
		int dist = 0;
		for (int x = 0; x < 4; x++) 
		{
			if (firstChar[x] != secondChar[x])
				dist++;
		}
		return dist;
	}
	
	/*
	 * Changes Distance 0 - 4 for the number of stations that distance away
	 */
	
	private void calcHamDist(String station) {
		int[] distances = new int[5];
		
		for (String compStation : compareList) 
		{
			int hamDist = hamDistComparer(station, compStation);
			switch (hamDist) {
			case 0:
				distances[0]++;
				break;
			case 1:
				distances[1]++;
				break;
			case 2:
				distances[2]++;
				break;
			case 3:
				distances[3]++;
				break;
			case 4:
				distances[4]++;
				break;
			default:
				break;
			}
		}
		
		for (int x = 0; x < 5; x++)
			textDistFields[x].setText(String.valueOf(distances[x]));
	}
	
	/*
	 * Adds input station to compareList dropdown
	 */
	private void addStation(String station) {
		int selected = compareWithBox.getSelectionModel().getSelectedIndex();
		String selectString = compareWithBox.getSelectionModel().getSelectedItem();
		station = station.trim();
		if (station.length() == 4)
		{
			if (compareList.add(station))
			{
				if(selectString.compareTo(station) > 0)
					selected++;
				compareWithBox.getItems().clear();
				compareWithBox.getItems().addAll(compareList);
				compareWithBox.getSelectionModel().select(selected);
			}
		}
	}
}
