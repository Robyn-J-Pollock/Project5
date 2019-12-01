import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JFrame;

/*
 * @author Robyn Pollock
 * @version 12/1/2019
 * 
 * Project 5: For CS-2334
 * Creates a gui that allows the user to select a hamming distance.
 * 	Finds the stations that fall within that hamming distance.
 */
public class DrawFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected ControlPanel controlPanel;
	//TODO: change to custom panel;
	protected CustomPanel customPanel;

	/*
	 * Creates frame and panel.
	 */
	public DrawFrame() throws IOException {
		//sets title
		super("Hamming Distance");
		
		//Create Components
		controlPanel = new ControlPanel();
		//TODO: change to custom panel;
		customPanel = new CustomPanel(controlPanel);
		
		//sets the layout manager
		this.setLayout(new GridLayout(1, 2));
		//TODO: Add components
		this.add(controlPanel);
		this.add(customPanel);
		
		//setup frame
		this.setSize(800, 800);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	/*
	 * Main method
	 */
	public static void main(String[] args) throws IOException {
		DrawFrame frame = new DrawFrame();
		frame.isActive();
	}
}
