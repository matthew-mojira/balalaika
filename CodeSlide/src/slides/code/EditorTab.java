package slides.code;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.undo.UndoManager;

import slides.Slide;

public class EditorTab extends JScrollPane {
	
	private JTextArea textArea;
	private String title;
	private UndoManager undoManager;
	
	protected EditorTab(String title, String contents) {
		
		this.title = title; // do we need this? we should 
		
		textArea = new JTextArea();
		textArea.setFont(Slide.MONOSPACE_FONT);
		textArea.setTabSize(CodeExampleSlide.TAB_SIZE);
		textArea.setText(contents);
		textArea.setLineWrap(false);
		textArea.setEditable(true);
		textArea.setCaretPosition(0);
		
		/* put the new text area inside the new tab */
		this.setViewportView(textArea);

		/* make and put the line numbers */
		TextLineNumber lineSide = new TextLineNumber(textArea);
		this.setRowHeaderView(lineSide);

		/* undo manager */
		undoManager = new UndoManager();
		textArea.getDocument().addUndoableEditListener(undoManager);
	}
	
	protected boolean undo() {
		if (undoManager.canUndo()) {
			undoManager.undo();
			return true;
		} else {
			return false;
		}
	}
	
	protected boolean redo() {
		if (undoManager.canRedo()) {
			undoManager.redo();
			return true;
		} else {
			return false;
		}
	}
	
	protected void setContents(String contents) {
		textArea.setText(contents);
	}
	
	protected String getContents() {
		return textArea.getText();
	}
	
	protected String getTitle() {
		return title;
	}
}
