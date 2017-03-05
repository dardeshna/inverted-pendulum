import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class balances an inverted pendulum with a feedback control loop and displays it in a frame
 * 
 * @author dardeshna
 */
public class Controller extends JFrame {

	private static final long serialVersionUID = 1L;

	private Pendulum pendulum;
	
	public static final int CONVERSION_FACTOR = 100; //Conversion factor from meters to pixels
	public static final int WIDTH = CONVERSION_FACTOR * 8;  //Width of frame
	public static final int HEIGHT = CONVERSION_FACTOR * 4;  //Height of frame
	
	/**
	 * Constructs a new <code>Controller</code> and opens the window
	 */
	public Controller() {
		
		super("invertedPendulum");
		
		pendulum = new Pendulum();
		
		JPanel panel = new JPanel(){
			
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics graphics) {
				graphics.setColor(Color.BLACK);
				pendulum.draw(graphics);
			}
			
			@Override
			public Dimension getPreferredSize() {
				return new Dimension(Controller.WIDTH, Controller.HEIGHT);
			}
			
		};
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		setBackground(Color.WHITE);
		c.add(panel, BorderLayout.CENTER);
		
		pack();
		
	}
	
	/**
	 * Runs the program, balances the inverted pendulum using feedback control loops, and repaints it
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {
		Controller c = new Controller();
		PD angle = new PD(175, 15);  //PD loop to control the angle of the pendulum
		PD position = new PD(1.25, 2);  //PD loop to control the position of the cart
		while(true) {
			Thread.sleep(1000*1/120);
			c.pendulum.update(angle.getValue(c.pendulum.pPos, c.pendulum.pVel)-position.getValue(c.pendulum.cPos, c.pendulum.cVel));  //Updates the pendulum with a force equaling the sum of the outputs of the angle and position PD loops
			c.repaint();
		}
	}
	
}
