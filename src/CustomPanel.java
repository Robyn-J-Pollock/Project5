import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
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
	
	private Document textArea;

	public CustomPanel(ControlPanel controlPanel) {
		createComponents();
		
		this.add(fourAveButton);
		this.add(oneAveButton);
		
		this.add(shownStaAveBox);
		
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
	}
}
