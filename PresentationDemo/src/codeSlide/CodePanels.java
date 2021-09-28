package codeSlide;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import java.awt.Font;
import java.io.File;
import java.util.*;
import static codeSlide.CodeSlide.*;

public class CodePanels extends JTabbedPane {
	
	Set<JTextArea> pages = new HashSet<>();
	
	public CodePanels(String directory) {
		super(JTabbedPane.TOP);
		
		FileReaders files = new FileReaders();
		
		for (File f : files) {			
			JTextArea fileContents = new JTextArea(FileReaders.readFileContents(f));
			fileContents.setFont(MONOSPACE_FONT);
			fileContents.setTabSize(TAB_SIZE);

			pages.add(fileContents);
			
			this.addTab(f.getName(), null, new JScrollPane(fileContents), null);	// do better...
		}
	}

}
