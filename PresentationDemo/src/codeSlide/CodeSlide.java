package codeSlide;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import old.Slide;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;

public class CodeSlide extends Slide {

	private static final String DEFAULT_CODE_LOL_TEMPORARY_THE_JAVA_COMPILER_IS_ACTUALLY_NOT_CONNECTED_TO_THE_WRITTEN_CODE_AT_ALL = 
			"public class HelloWorld {\n\t\n\tpublic static void main(String[] args) {\n\t\t\n\t\tSystem.out.printf(\"Lol! This is hardcoded!\");\n\t\t\n\t}\n\t\n}";
	private static final Dimension PREFERRED_SIZE = new Dimension(89, 23);
	
	protected static final Font MONOSPACE_FONT = new Font("Monospaced", Font.PLAIN, 16); 
	protected static final int TAB_SIZE = 4;

	private JLabel titleLabel;
	private JTextArea consoleTextArea;
	private JTextArea codeTextDefaultArea;
	private JScrollPane codeDefaultPane;
	private JTabbedPane codePanes;

	/**
	 * Create the panel.
	 */
	public CodeSlide(String slideTitle, String directory) {
		super();
		setLayout(new BorderLayout(0, 0));

		titleLabel = new JLabel("New label");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 42)); /* needs to be constant */
		/* in fact, the whole JLabel should be a constant*/
		titleLabel.setText(slideTitle);
		add(titleLabel, BorderLayout.NORTH);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);	/* this is dumb */
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(splitPane, BorderLayout.CENTER);

		JScrollPane consolePane = new JScrollPane();
		splitPane.setRightComponent(consolePane);

		consoleTextArea = new JTextArea();
		consoleTextArea.setFont(MONOSPACE_FONT);
		consoleTextArea.setEditable(false);
		consoleTextArea.setLineWrap(true);
		consoleTextArea.setTabSize(TAB_SIZE);
		consolePane.setViewportView(consoleTextArea);
		
		JPanel buttonsPanel = new JPanel();
		consolePane.setColumnHeaderView(buttonsPanel);
		buttonsPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel leftBPanel = new JPanel();
		buttonsPanel.add(leftBPanel, BorderLayout.WEST);
		
		JButton runButton = new JButton("Run");
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consoleTextArea.setText("Lol! This is hardcoded!");
			}
		});
		runButton.setToolTipText("Runs the code in the console window.");
		runButton.setPreferredSize(new Dimension(89, 23));
		leftBPanel.add(runButton);
		
		JButton clearButton = new JButton("Compile");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Compiled!");
			}
		});
		clearButton.setToolTipText("Does nothing!!!");
		clearButton.setPreferredSize(new Dimension(89, 23));
		leftBPanel.add(clearButton);
		
		JPanel rightBPanel = new JPanel();
		buttonsPanel.add(rightBPanel, BorderLayout.EAST);
		
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a = 1 / 0;
			}
		});
		saveButton.setToolTipText("(Does nothing!) Saves the code in the editor window to the presentation file.");
		saveButton.setPreferredSize(new Dimension(89, 23));
		rightBPanel.add(saveButton);
		
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				codeTextDefaultArea.setText(DEFAULT_CODE_LOL_TEMPORARY_THE_JAVA_COMPILER_IS_ACTUALLY_NOT_CONNECTED_TO_THE_WRITTEN_CODE_AT_ALL);
			}
		});
		resetButton.setToolTipText("Resets the code editor to the code that is currently saved.");
		resetButton.setPreferredSize(new Dimension(89, 23));
		rightBPanel.add(resetButton);
		
		codePanes = new CodePanels(null);
		splitPane.setLeftComponent(codePanes);
		
		
		
//		codeDefaultPane = new JScrollPane();
//		codePanes.addTab("HelloWorld.class", null, codeDefaultPane, null);
		/* In a perfect world, these class files should be read from where they are stored on the disk,
		 * then the file name goes up here and the contents are then loaded into the window.
		 * 
		 * We can only hope...
		 */
		
//		codeTextDefaultArea = new JTextArea(DEFAULT_CODE_LOL_TEMPORARY_THE_JAVA_COMPILER_IS_ACTUALLY_NOT_CONNECTED_TO_THE_WRITTEN_CODE_AT_ALL);
//		codeTextDefaultArea.setFont(MONOSPACE_FONT);
//		codeDefaultPane.setViewportView(codeTextDefaultArea);
		
		experimentalRedirectOutputStream();
	}
	
	/*
	 * Before we actually do anything big, we must do a big job of separating the compiler/language away from the slide itself.
	 * In short, don't hardcode it towards Java stuff. We need it to support other languages, too.
	 */
	
	// experimental Code
	private void experimentalRedirectOutputStream() {
        System.setOut(new PrintStream(new StreamCapturer("STDOUT", consoleTextArea, System.out)));
        System.setErr(new PrintStream(new StreamCapturer("STDERR", consoleTextArea, System.err)));
        
        System.out.printf("This worked!!! %5.4f", Math.random());
	}
	
	// stolen from https://stackoverflow.com/questions/12945537/how-to-set-output-stream-to-textarea
	public class StreamCapturer extends OutputStream {

        private StringBuilder buffer;
        private String prefix;
        private JTextArea consumer;
        private PrintStream old;

        public StreamCapturer(String prefix, JTextArea consumer, PrintStream old) {
            this.prefix = prefix;
            buffer = new StringBuilder(128);
//            buffer.append("[").append(prefix).append("] ");
            this.old = old;
            this.consumer = consumer;
        }

        @Override
        public void write(int b) throws IOException {
            char c = (char) b;
            String value = Character.toString(c);
            buffer.append(value);
            if (value.equals("\n")) {
                consumer.append(buffer.toString());
                buffer.delete(0, buffer.length());
//                buffer.append("[").append(prefix).append("] ");
            }
            old.print(c);
        }        
    }

}
