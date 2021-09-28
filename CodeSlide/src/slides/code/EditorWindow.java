package slides.code;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.undo.UndoManager;

import slides.Slide;

import java.util.*;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditorWindow extends JPanel {

	Map<String, EditorTab> tabs = new HashMap<>();

	private JTabbedPane fileViewers;

	public EditorWindow() {
		setLayout(new BorderLayout(0, 0));

		fileViewers = new JTabbedPane(JTabbedPane.TOP);
		add(fileViewers, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);

		JButton undo = new JButton("Undo");
		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((EditorTab)fileViewers.getSelectedComponent()).undo();
			}
		});
		panel.add(undo);

		JButton redo = new JButton("Redo");
		redo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((EditorTab)fileViewers.getSelectedComponent()).redo();
			}
		});
		panel.add(redo);
	}

	/**
	 * @return true if new tab created, false otherwise
	 */
	public boolean importTab(String title, String contents) {
		if (tabs.containsKey(title)) { // already exists. modify tab
			tabs.get(title).setContents(contents);

			return false;
		} else { // does not already exist. create new tab
			EditorTab newTab = new EditorTab(title, contents);
			
			/* put the title/text area into the map */
			tabs.put(title, newTab);

			/* add the new tab to the tabbed pane (this object) */
			fileViewers.addTab(title, newTab);

			return true;
		}
	}

	public String exportTab(String title) {
		return tabs.get(title).getContents();
	}

	public void importFiles(FolderManager files) {
		Iterator<String> iterator = files.iterator();

		while (iterator.hasNext()) {
			importTab(iterator.next(), iterator.next()); // wow. this is shit
		}

		this.repaint();
	}

}
