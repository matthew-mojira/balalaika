package application;

import javax.swing.JPanel;

import java.util.Random;

import javax.swing.JLabel;

public class RNGPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public RNGPanel() {
		
		JLabel numberLabel = new JLabel("...");
		add(numberLabel);
		
		numberLabel.setText(String.valueOf(Math.random()));

	}

}
