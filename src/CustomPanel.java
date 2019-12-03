import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeSet;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;


/*
 * Custom panel for Project 5
 * @author Robyn Pollock
 * @version 12/1/2019
 */
public class CustomPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected ButtonGroup radioGroup;
	
	protected JRadioButton fourAveButton, oneAveButton;
	
	protected JCheckBox shownStaAveBox;
	
	protected JTextField asciiAveField;
	
	protected JButton calcAveButton;
	
	private Document textArea;
	
	private ControlPanel controlPanel;

	public CustomPanel(ControlPanel controlPanel) {
		this.controlPanel = controlPanel;
		createComponents();
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbCon = new GridBagConstraints();
		
		gbCon.fill = GridBagConstraints.BOTH;
		gbCon.gridx = 0;
		gbCon.gridy = 0;
		this.add(fourAveButton, gbCon);
		gbCon.gridx = 1;
		this.add(oneAveButton,gbCon);
		
		gbCon.gridx = 0;
		gbCon.gridy = 1;
		this.add(shownStaAveBox,gbCon);		
		textArea = controlPanel.getTextAreaDoc();
		textArea.addDocumentListener(new DocumentListener() {
			
			boolean selected = false;

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				shownStaAveBox.setSelected(selected);
				shownStaAveBox.setEnabled(true);
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				selected = shownStaAveBox.isSelected();
				shownStaAveBox.setSelected(false);
				shownStaAveBox.setEnabled(false);
			}
			
		});
		
		gbCon.gridx = 0;
		gbCon.gridy = 2;
		this.add(asciiAveField, gbCon);
		gbCon.gridx = 1;
		this.add(calcAveButton, gbCon);
		calcAveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					calcAveValue(shownStaAveBox.isSelected());
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
	}
	
	private void createComponents() {
		radioGroup = new ButtonGroup();
		fourAveButton = new JRadioButton();
		fourAveButton.setText("4-Letter Average");
		oneAveButton = new JRadioButton();
		oneAveButton.setText("1-Letter Average");
		
		radioGroup.add(fourAveButton);
		radioGroup.add(oneAveButton);
		radioGroup.setSelected(fourAveButton.getModel(), true);
		
		shownStaAveBox = new JCheckBox();
		shownStaAveBox.setText("Use Shown Stations Only");
		shownStaAveBox.setEnabled(false);
		
		asciiAveField = new JTextField();
		asciiAveField.setEditable(false);
		
		calcAveButton = new JButton();
		calcAveButton.setText("Calculate Average");
	}
	
	private void calcAveValue(boolean useTextArea) throws BadLocationException {
		TreeSet<String> staList = new TreeSet<String>();
		int[] placeValue = new int[] { 0, 0, 0, 0 };
		
		if (useTextArea) {
			String[] tempArray = textArea.getText(0, textArea.getLength() - 1).split("\n");
			for (String string : tempArray) {
				System.out.print(string);
				staList.add(string);
			}
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
