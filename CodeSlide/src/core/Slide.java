package core;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;

public abstract class Slide extends JPanel {
	
	protected static final Font HEADING_FONT = new Font("Tahoma", Font.BOLD, 42); 
	protected static final Font MONOSPACE_FONT = new Font("Monospaced", Font.PLAIN, 11);
	
//	String title;
	private JLabel titleLabel;
	
	/**
	 * Create the panel.
	 */
	public Slide(String title) {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setBackground(new Color(30, 144, 255));
		setPreferredSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(800, 600));
		
//		this.title = title;	
		setLayout(new BorderLayout(0, 0));
		
		titleLabel = new JLabel(title);
		titleLabel.setFont(HEADING_FONT);
		add(titleLabel, BorderLayout.NORTH);
		

	}
	
}
