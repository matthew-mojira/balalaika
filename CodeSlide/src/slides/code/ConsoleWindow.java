package slides.code;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import slides.Slide;

import java.awt.Font;
import java.io.IOException;
import java.io.OutputStream;

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
	
	private class ConsoleText extends OutputStream {

		@Override
		public void write(int b) throws IOException {
			// TODO Auto-generated method stub
			
		}
		
	}

}
