package core;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import java.util.*;

public class EditorWindow extends JTabbedPane {

	Map<String, JTextArea> tabs = new HashMap<>();
	
	
	/**
	 * @return true if new tab created, false otherwise
	 */
	public boolean importTab(String title, String contents) {
		if (tabs.containsKey(title)) {	// already exists. modify tab
			tabs.get(title).setText(contents);
			
			return false;
		} else { // does not already exist. create new tab
			
			/* make new tab, and make new text area to put inside */
			JScrollPane newTab = new JScrollPane();
			JTextArea textArea = new JTextArea(contents);
			textArea.setEditable(false);
			
			/* put the new text area inside the new tab */
			newTab.add(textArea);
			
			/* put the title/text area into the map */
			tabs.put(title, textArea);
			
			/* add the new tab to the tabbed pane (this object) */
			this.addTab(title, newTab);
			
			return true;
		}
	}
	
	public String exportTab(String title) {
		return tabs.get(title).getText();
	}
	
	public void importFiles(FolderManager files) {
		Iterator<String> iterator = files.iterator();
		
		while (iterator.hasNext()) {
			importTab(iterator.next(), iterator.next());	// wow. this is shit
		}
		
		this.repaint();
	}
}
