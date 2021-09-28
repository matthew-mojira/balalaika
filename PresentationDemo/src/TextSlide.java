import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;

public class TextSlide extends Slide {
	private JTextPane bodyPane;
	private JLabel titleLabel;
	
	private static final String DEFAULT_TITLE = "Sample slide title";
	private static final String DEFAULT_BODY = "Hello friend,\nHere is some text for you to read!\n\n"
			+ "In other news, we should figure out how to format text (from the editor side and in here).\n\n"
			+ "The printf() method in Java allows the user to print text out to the console, "
			+ "formatting the text in the style of C using format specifiers. Example on the next slide.";
	

	/**
	 * Create the panel.
	 * @wbp.parser.constructor
	 */
	public TextSlide(int slideNumber, String slideTitle, String slideBody) {
		super(slideNumber);
		setLayout(new BorderLayout(0, 0));
		
		titleLabel = new JLabel("New label");
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 42));	/* needs to be constant */
		titleLabel.setText(slideTitle);
		add(titleLabel, BorderLayout.NORTH);
				
		bodyPane = new JTextPane();
		bodyPane.setFont(new Font("Tahoma", Font.PLAIN, 24));
		bodyPane.setEditable(false);
		bodyPane.setText(slideBody);
		add(bodyPane, BorderLayout.CENTER);
	}
	
	public TextSlide(int slideNumber) {
		this(slideNumber, DEFAULT_TITLE, DEFAULT_BODY);
	}

}
