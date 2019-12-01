import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;


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

	public CustomPanel() {
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
		
		this.add(fourAveButton);
		this.add(oneAveButton);
		
		this.add(shownStaAveBox);
	}
}
