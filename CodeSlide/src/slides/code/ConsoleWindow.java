package slides.code;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import slides.Slide;

import java.awt.Font;

public class ConsoleWindow extends JScrollPane {
	private JTextArea consoleWindow;
	
	public ConsoleWindow() {
		
		consoleWindow = new JTextArea();
		consoleWindow.setEditable(false);
		consoleWindow.setFont(Slide.MONOSPACE_FONT);
		consoleWindow.setTabSize(CodeExampleSlide.TAB_SIZE);
		setViewportView(consoleWindow);
	}
	
	public void clearConsole() {
		consoleWindow.setText(null);
	}

}
