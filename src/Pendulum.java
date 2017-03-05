import java.awt.Graphics;

/**
 * This class represents an inverted pendulum and its motion given a horizontal force applied to the cart
 * 
 * @author dardeshna
 */
public class Pendulum {

	public double cPos;
	public double cVel;
	public double cAccel;
	public double pPos;
	public double pVel;
	public double pAccel;
	
	public static final double G = 9.8;  //Acceleration due to gravity
	public static final double L = 1;  //Length of pendulum
	public static final double M_C = 10;  //Mass of cart
	public static final double M_P = 2;  //Mass of pendulum
	
	public Pendulum() {
		cPos = 0;  //Cart starts at position 0
		cVel = Math.random()*2-1;  //Cart starts at a random velocity
		pPos = Math.random()-.5;  //Pendulum starts a random angle from position 0
		pVel = Math.random()*.4-.2;  //Pendulum starts at a random velocity
	}
	
	/**
	 * Updates the acceleration, velocity and position of the cart and pendulum based on the horizontal force applied to the cart
	 * @param force the horizontal force applied to the cart
	 */
	public void update(double force) {
		
		if (!hasHitGround()) {
			double cAccel_new = (force+L*M_P*pAccel*Math.cos(pPos)-L*M_P*pVel*pVel*Math.sin(pPos))/(M_C+M_P);
			double pAccel_new = (cAccel*Math.cos(pPos)+G*Math.sin(pPos))/L;
			
			cAccel = cAccel_new;
			pAccel = pAccel_new;
			
			pVel += pAccel/120;
			pPos += pVel/120;
			
			cVel += cAccel/120;
			cPos += cVel/120;
		}
		
	}
	
	/**
	 * Returns whether the pendulum has fallen over
	 * @return whether the pendulum has fallen over
	 */
	private boolean hasHitGround() {
		return (pPos > Math.PI/2 || pPos < -Math.PI/2);
	}
	
	/**
	 * Draws the pendulum and number line
	 * @param g the specified Graphics window
	 */
	public void draw(Graphics g) {
		
		//Draw pendulum
		g.fillRect(toPixels(3.90), toPixels(1.85), toPixels(.20), toPixels(.1));
		g.fillOval(toPixels(3.90), toPixels(1.95), toPixels(.05), toPixels(.05));
		g.fillOval(toPixels(4.05), toPixels(1.95), toPixels(.05), toPixels(.05));
		g.fillOval(toPixels(4-Math.sin(pPos)*L-.05), toPixels(1.9-Math.cos(pPos)*L-.05), toPixels(.1), toPixels(.1));
		g.drawLine(toPixels(4), toPixels(1.9), toPixels(4-Math.sin(pPos)*L), toPixels(1.9-Math.cos(pPos)*L));
		
		//Draw number line
		g.drawLine(toPixels(0), toPixels(2), toPixels(8), toPixels(2));
		for (int i = 0; i<=8; i++) {
			g.drawString(Integer.toString((int)Math.round(cPos)-4+i), toPixels((int)Math.round(cPos)+i-cPos), toPixels(2.3));
		}
		
	}
	
	/**
	 * Converts meters to pixels based on the <code>CONVERSION_FACTOR</code>
	 * @param meters
	 * @return pixels
	 */
	private int toPixels(double meters) {
		return (int) (meters*Controller.CONVERSION_FACTOR);
	}
	
}
