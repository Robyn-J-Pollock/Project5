import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
	
	protected JTextField[] textDistArray;
	
	protected JPanel bottomPanel;
	
	
	public ControlPanel() {		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		hamDistSlider = new JSlider();
		showStationButton = new JButton();
		calcHDButton = new JButton();
		addStationButton = new JButton();
		showStationArea = new JTextArea();
		compareWithBox = new JComboBox<String>();
		textDistArray = new JTextField[7];
		for (int x = 0; x < 7; x++)
			textDistArray[x] = new JTextField();
		bottomPanel = new JPanel();
		
		this.add(textDistArray[0]);
		this.add(hamDistSlider);
		this.add(showStationButton);
		this.add(showStationArea);
		this.add(compareWithBox);
		this.add(calcHDButton);
		for (int x = 1; x < 6; x++)
			this.add(textDistArray[x]);
		
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		bottomPanel.add(addStationButton);
		bottomPanel.add(textDistArray[6]);
		this.add(bottomPanel);
	}
}
