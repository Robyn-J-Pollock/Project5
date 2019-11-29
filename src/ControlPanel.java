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
	
	protected JPanel actionPanel;
	
	protected JSlider hamDistSlider;
	
	protected JButton showStationButton, calcHDButton, addStationButton;
	
	protected JTextArea showStationArea;
	
	protected JComboBox<String> compareWithBox;
	
	protected JTextField[] textDistArray;
	
	
	
	public ControlPanel() {
		
	}
}
