/**
 * This class implements a PD feedback control loop for force given a position and velocity
 * 
 * @author dardeshna
 */
public class PD {

	private double p;  //Proportional constant
	private double d;  //Derivative constant
	
	private double max = 1e4;  //Maximum output
	private double min = -1e4;  //Minimum output

	/**
	 * Constructs a new PD loop with constants
	 * @param p the proportional constant
	 * @param d the derivative constant
	 */
	public PD(double p, double d){
		this.p = p;
		this.d = d;
	}

	/**
	 * Returns the output of the PD loop
	 * @param pos the current position
	 * @param vel the current velocity
	 * @return the force to be applied
	 */
	public double getValue(double pos, double vel)  {
		return Math.min(Math.max(-pos*p - vel*d, min), max);
	}
	
	
}
