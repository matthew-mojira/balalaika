package slides;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public abstract class Slide extends JPanel {
	
	public static final Font HEADING_FONT = new Font("Tahoma", Font.BOLD, 42); 
	public static final Font MONOSPACE_FONT = new Font("Monospaced", Font.PLAIN, 11);
	public static final Color BACKGROUND_COLOR = new Color(30, 144, 255);
	public static final Dimension DEFAULT_SIZE = new Dimension(1024, 768);
	
//	String title;
	private JLabel titleLabel;
	
	/**
	 * Create the panel.
	 */
	public Slide(String title) {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setBackground(BACKGROUND_COLOR);
		setPreferredSize(DEFAULT_SIZE);
		setMinimumSize(DEFAULT_SIZE);
		
//		this.title = title;	
		setLayout(new BorderLayout(0, 0));
		
		titleLabel = new JLabel(title);
		titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(HEADING_FONT);
		add(titleLabel, BorderLayout.NORTH);
		

	}
	
}
