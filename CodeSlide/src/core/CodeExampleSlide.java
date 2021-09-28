package core;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class CodeExampleSlide extends Slide {
	
	public static final int JAVA = 131;
	
	private JSplitPane splitPane;
	private EditorWindow editorPanel;
	private ConsoleWindow consolePanel;
	
	private FolderManager fileFolderSystem;
	
	public CodeExampleSlide(String title, String examplesFolder) {
		this(title, examplesFolder, JAVA);		
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public CodeExampleSlide(String title, String examplesFolder, int lang) {
		super(title);
		
//		this.fileFolderSystem = new FolderManager(examplesFolder);
		this.fileFolderSystem = new FolderManager();
		
		splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(splitPane, BorderLayout.CENTER);
		
		editorPanel = new EditorWindow();
		editorPanel.importFiles(fileFolderSystem);
		splitPane.setLeftComponent(editorPanel);
		
		consolePanel = new ConsoleWindow();
		splitPane.setRightComponent(consolePanel);
		
	}

}
