import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.border.EmptyBorder;

public abstract class Slide extends JPanel {
	
	protected int slideNumber;

	/**
	 * Create the panel.
	 */
	public Slide(int slideNumber) {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setBackground(new Color(30, 144, 255));
		setPreferredSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(800, 600));
		
		/* The abstract class should also preferably be handling the title of the slide,
		 * since it is being used across multiple subclasses.
		 */
		
		this.slideNumber = slideNumber;

	}
	
	public int getSlideNumber() {
		return slideNumber;
	}

}
