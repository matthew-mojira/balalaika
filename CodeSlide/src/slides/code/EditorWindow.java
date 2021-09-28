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

	Map<String, JTextArea> tabs = new HashMap<>();
	Map<JTextArea, UndoManager> lol = new HashMap<>();

	// TODO: WRAP THE JTEXTAREAS WITH AN INNER CLASS

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

				JTextArea currentlyViewedTextArea = (JTextArea) ((JScrollPane) fileViewers.getSelectedComponent())
						.getViewport().getView();
				// WOW! so bad...
				if (lol.get(currentlyViewedTextArea).canUndo()) {
					lol.get(currentlyViewedTextArea).undo();
				}

			}
		});
		panel.add(undo);

		JButton redo = new JButton("Redo");
		redo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JTextArea currentlyViewedTextArea = (JTextArea) ((JScrollPane) fileViewers.getSelectedComponent())
						.getViewport().getView();
				// WOW! so bad...
				if (lol.get(currentlyViewedTextArea).canRedo()) {
					lol.get(currentlyViewedTextArea).redo();
				}

			}
		});
		panel.add(redo);
	}

	/**
	 * @return true if new tab created, false otherwise
	 */
	public boolean importTab(String title, String contents) {
		if (tabs.containsKey(title)) { // already exists. modify tab
			tabs.get(title).setText(contents);

			return false;
		} else { // does not already exist. create new tab
//			System.out.printf("Tab %s not working. %s", title, contents);
			/* make new tab, and make new text area to put inside */
			JScrollPane newTab = new JScrollPane();
			JTextArea textArea = new JTextArea();
			textArea.setFont(Slide.MONOSPACE_FONT);
			textArea.setTabSize(CodeExampleSlide.TAB_SIZE);
			textArea.setText(contents);
			textArea.setLineWrap(false);
			textArea.setEditable(true);

			/* put the new text area inside the new tab */
			newTab.setViewportView(textArea);

			/* make and put the line numbers */
			TextLineNumber lineSide = new TextLineNumber(textArea);
			newTab.setRowHeaderView(lineSide);

			/* undo manager */
			UndoManager undoManager = new UndoManager();
			textArea.getDocument().addUndoableEditListener(undoManager);

			lol.put(textArea, undoManager);

			/* put the title/text area into the map */
			tabs.put(title, textArea);

			/* add the new tab to the tabbed pane (this object) */
			fileViewers.addTab(title, newTab);

			return true;
		}
	}

	public String exportTab(String title) {
		return tabs.get(title).getText();
	}

	public void importFiles(FolderManager files) {
		Iterator<String> iterator = files.iterator();

		while (iterator.hasNext()) {
			importTab(iterator.next(), iterator.next()); // wow. this is shit
		}

		this.repaint();
	}
	
	private class Tab extends JScrollPane {
		
		JTextArea textArea;
		String title;
		UndoManager undoManager;
		
		private Tab(String title, String contents) {
			
			this.title = title; // do we need this? we should 
			
			textArea = new JTextArea();
			textArea.setFont(Slide.MONOSPACE_FONT);
			textArea.setTabSize(CodeExampleSlide.TAB_SIZE);
			textArea.setText(contents);
			textArea.setLineWrap(false);
			textArea.setEditable(true);
			
			/* put the new text area inside the new tab */
			this.setViewportView(textArea);

			/* make and put the line numbers */
			TextLineNumber lineSide = new TextLineNumber(textArea);
			this.setRowHeaderView(lineSide);

			/* undo manager */
			undoManager = new UndoManager();
			textArea.getDocument().addUndoableEditListener(undoManager);
		}
		
		private boolean undo() {
			if (undoManager.canUndo()) {
				undoManager.undo();
				return true;
			} else {
				return false;
			}
		}
		
		private boolean redo() {
			if (undoManager.canRedo()) {
				undoManager.redo();
				return true;
			} else {
				return false;
			}
		}
	}
}
