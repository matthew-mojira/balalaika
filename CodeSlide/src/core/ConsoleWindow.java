package core;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;

public class ConsoleWindow extends JScrollPane {
	private JTextArea consoleWindow;
	
	public ConsoleWindow() {
		
		consoleWindow = new JTextArea();
		consoleWindow.setEditable(false);
		consoleWindow.setFont(Slide.MONOSPACE_FONT);
		setViewportView(consoleWindow);
	}
	
	public void clearConsole() {
		consoleWindow.setText(null);
	}

}
