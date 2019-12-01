import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeSet;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/*
 * @author Robyn Pollock
 * @version 11/28/2019
 * 
 * Control panel for the frame.
 */


public class ControlPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected JSlider hamDistSlider;
	
	protected JButton showStationButton, calcHDButton, addStationButton;
	
	protected JTextArea showStationArea;
	
	protected JComboBox<String> compareWithBox;
	
	protected JTextField hamDistField, addStationField;
	
	protected JTextField[] textDistFields;
	
	protected JLabel hamDistLabel, compareWithLabel;
	
	protected JLabel[] textDistLabels;
	
	private TreeSet<String> compareList;
	
	/*
	 * Creates and controls required panel
	 */
	public ControlPanel() {	
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbConstraints = new GridBagConstraints();
		
		//Method to create components
		createComponents();
		gbConstraints.fill = GridBagConstraints.BOTH;
		gbConstraints.weightx = 1;
		gbConstraints.insets = new Insets(10, 10, 10, 10);
		
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 0;
		this.add(hamDistLabel, gbConstraints);
		
		
		gbConstraints.gridx = 1;
		this.add(hamDistField, gbConstraints);
		
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 1;
		gbConstraints.gridwidth = 2;
		this.add(hamDistSlider, gbConstraints);
		
		gbConstraints.gridy = 2;
		this.add(showStationButton, gbConstraints);
		
		gbConstraints.gridy = 3;
		gbConstraints.weighty = 1;
		this.add(showStationArea, gbConstraints);
		
		gbConstraints.gridwidth = 1;
		gbConstraints.gridy = 4;
		gbConstraints.weighty = 0;
		this.add(compareWithLabel, gbConstraints);
		
		gbConstraints.gridx = 1;
		this.add(compareWithBox, gbConstraints);
		
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 5;
		gbConstraints.gridwidth = 2;
		this.add(calcHDButton, gbConstraints);
		
		gbConstraints.gridwidth = 1;
		for (int y = 6; y < 11; y++)
		{
			gbConstraints.gridy = y;
			for (int x = 0; x < 2; x++)
			{
				gbConstraints.gridx = x;
				if (x == 0)
					this.add(textDistLabels[y - 6], gbConstraints);
				else if (x == 1)
					this.add(textDistFields[y - 6], gbConstraints);
			}
		}
		
		gbConstraints.anchor = GridBagConstraints.PAGE_END;
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 11;
		this.add(addStationButton, gbConstraints);
		
		gbConstraints.gridx = 1;
		this.add(addStationField, gbConstraints);
		
		//Adjusts text field on slider change
		hamDistSlider.addChangeListener(new ChangeListener() 
				{

					@Override
					public void stateChanged(ChangeEvent arg0) {
						// TODO Auto-generated method stub
						hamDistField.setText("" + hamDistSlider.getValue());
					}
				});
		
		//Runs showStations method on button push
		showStationButton.addActionListener(new ActionListener() 
				{

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						showStations(Integer.parseInt(hamDistField.getText()));
					}
				});
		
		//Runs calcHamDist function on button press;
		calcHDButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						calcHamDist((String)compareWithBox.getSelectedItem());
					}
					
				});
		
		addStationButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						addStation(addStationField.getText().toUpperCase());
					}
			
				});
	}
	
	/*
	 * Method used to create components
	 * Seperated to make editing and readability easier
	 */
	private void createComponents() {
		hamDistSlider = new JSlider(1, 4);
		hamDistSlider.setMajorTickSpacing(1);
		hamDistSlider.setPaintTicks(true);
		hamDistSlider.setPaintLabels(true);
		
		hamDistLabel = new JLabel("Enter Hamming Dist: ");
		hamDistLabel.setHorizontalAlignment(JLabel.RIGHT);
		
		hamDistField = new JTextField();
		hamDistField.setEditable(false);
		hamDistField.setText("" + hamDistSlider.getValue());
		
		showStationButton = new JButton("Show Station");
		calcHDButton = new JButton("Calculate HD");
		addStationButton = new JButton("Add Station");
		
		showStationArea = new JTextArea();
		showStationArea.setEditable(false);
				
		compareWithBox = new JComboBox<String>();
		//Generates base compare with drop down list
		generateCompareWith();
		compareWithLabel = new JLabel("Compare with: ");
		compareWithLabel.setHorizontalAlignment(JLabel.RIGHT);
		
		textDistFields = new JTextField[5];
		for (int x = 0; x < 5; x++) {
			textDistFields[x] = new JTextField();
			textDistFields[x].setEditable(false);
		}
		
		textDistLabels = new JLabel[5];
		for (int x = 0; x < 5; x++) {
			textDistLabels[x] = new JLabel("Distance " + x);
			textDistLabels[x].setHorizontalAlignment(JLabel.RIGHT);
		}
		
		addStationField = new JTextField();
	}
	
	/*
	 * Reads Mesonet.txt and adds the list to the drop down box
	 */
	private void generateCompareWith() {
		//TODO: Implement Function
		compareList = new TreeSet<String>();
		compareList.add("WHO");
		compareList.add("APPLE");
		compareList.add("EHHO");
		
		for (String thing : compareList)
			compareWithBox.addItem(thing);
	}
	
	/*
	 * Shows station with the same hamming distance,
	 */
	private void showStations(int hamDist) {
		//TODO: Implement function
		showStationArea.setText(showStationArea.getText() + 1);
	}
	
	/*
	 * Changes Distance 0 - 4 for the number of stations that distance away
	 */
	
	private void calcHamDist(String station) {
		//TODO: Implement function
		textDistFields[0].setText(textDistFields[0].getText() + 0);
		textDistFields[1].setText(textDistFields[1].getText() + 1);
		textDistFields[2].setText(textDistFields[2].getText() + 2);
		textDistFields[3].setText(textDistFields[3].getText() + 3);
		textDistFields[4].setText(textDistFields[4].getText() + 4);
	}
	
	private void addStation(String station) {
		//TODO: create safeguards
		if (compareList.add(station))
		{
			compareWithBox.removeAllItems();
			for (String thing : compareList)
				compareWithBox.addItem(thing);
		}
	}
}
