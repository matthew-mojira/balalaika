import java.awt.EventQueue;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AppWindow {

	private JFrame frmDemoPresentation;
	
	private SlideDeck slideDeck;
	
	private int slideNumber;
	private JLabel slideNumberLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppWindow window = new AppWindow();
					window.frmDemoPresentation.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDemoPresentation = new JFrame();
		frmDemoPresentation.setMinimumSize(new Dimension(800, 600));
		frmDemoPresentation.setTitle("Demo Presentation");
		frmDemoPresentation.setBounds(100, 100, 450, 300);
		frmDemoPresentation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDemoPresentation.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel slidePanel = new JPanel();
		frmDemoPresentation.getContentPane().add(slidePanel, BorderLayout.CENTER);
		slidePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// constroller stuff begins here
		
		slideDeck = new SlideDeck();	// default slides stuff put here
		slidePanel.add(slideDeck.getSlide(1));
		slideNumber = 1;
		
		// controller stuff done here

		JPanel hotBar = new JPanel();
		frmDemoPresentation.getContentPane().add(hotBar, BorderLayout.SOUTH);
		hotBar.setLayout(new BorderLayout(0, 0));

		JButton prevSlideButton = new JButton("Previous");
		prevSlideButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(slideNumber == 1)) {
					slideNumber--;
					
					/* this needs to be made less redundant */
					slideNumberLabel.setText(String.format("Slide %d of %d", slideNumber, slideDeck.getNumSlides()));
					slidePanel.removeAll();	// why this does not have to be instance variable???? design !?!?!?!?
					slidePanel.add(slideDeck.getSlide(slideNumber));
					slidePanel.repaint();
				}
			}
		});
		hotBar.add(prevSlideButton, BorderLayout.WEST);

		JButton nextSlideButton = new JButton("Next");
		nextSlideButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(slideNumber == slideDeck.getNumSlides())) {
					slideNumber++;
					
					/* this needs to be made less redundant */
					slideNumberLabel.setText(String.format("Slide %d of %d", slideNumber, slideDeck.getNumSlides()));
					slidePanel.removeAll();	// why this does not have to be instance variable???? design !?!?!?!?
					slidePanel.add(slideDeck.getSlide(slideNumber));
					slidePanel.repaint();
				}
			}
		});
		hotBar.add(nextSlideButton, BorderLayout.EAST);

		slideNumberLabel = new JLabel("Slide X of Y");	// WHAT!?
		slideNumberLabel.setText(String.format("Slide %d of %d", slideNumber, slideDeck.getNumSlides()));
		slideNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		hotBar.add(slideNumberLabel, BorderLayout.CENTER);
	}

	private class SlideDeck {

		List<Slide> slides = new ArrayList<>();

		private SlideDeck() {
			slides.add(new TextSlide(1));
			slides.add(new CodeSlide(2, "Here is some code:"));
			// the numbering may not be necessary
		}
		
		private Slide getSlide(int slideNumber) {
			return slides.get(slideNumber - 1);
		}

		private int getNumSlides() {
			return slides.size();
		}
	}

}
