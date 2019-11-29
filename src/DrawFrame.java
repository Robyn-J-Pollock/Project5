import java.awt.BorderLayout;

import javax.swing.JFrame;

/*
 * @author Robyn Pollock
 * @version 11/28/2019
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

	public DrawFrame() {
		//sets title
		super("Hamming Distance");
		
		//Create Components
		controlPanel = new ControlPanel();
		
		//sets the layout manager
		this.setLayout(new BorderLayout());
		//TODO: Add components
		this.add(controlPanel, BorderLayout.WEST);
		
		//setup frame
		this.setSize(800, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		DrawFrame frame = new DrawFrame();
		frame.isActive();
	}
}
