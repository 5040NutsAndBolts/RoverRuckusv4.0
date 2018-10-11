package org.firstinspires.ftc.teamcode;

/**
 *  PID
 *  @author Jackson Daumeyer
 *
 *  A basic implemtation of a PID control loop. Intended to be reuseable by 
 *  changing the gains through the constructor
 *
 *  <p> 
 *  A basic PID control loop intended to be versatile and used in any application
 *  . Intended for use on the robot drive train for the 18-19 FTC Rover Ruckus 
 *  Season. However the class is able to be used in any implementation with 
 *  different gains provided in the constructor.
 *  </p>
 *
 */
public class PID {

	// Gains for the PId control loop
	private double KP;
   private double KI;
   private double KD;

	// Reusable Last Error variable used for the derivative  
   private double lastError = 0;

	/**
	 *  No-Args Constructor
	 *  Please Don't ever use this
	 */
	public PID() {
		this.KP = 1;
		this.KI = 1;
		this.KD = 1;
	}

	/**
	 *  Constructor
	 *
	 *  @param kp -- the proportional gain
	 *  @param ki -- the integral gain
	 *  @param kd -- the derivative gain 
	 */
   public PID(double kp, double ki, double kd) {
      this.KP = kp;
      this.KI = ki;
      this.KD = kd;
   }

	/**
	 *  Proportional Method
	 *
	 *  @param  error -- the current error of the system
	 *  @return the value with which to correct the system
	 *
	 */
   private double proportional (double error) {
      return KP * error;
   }

	/**
	 *  Integral Method
	 *
	 *  @param  error -- the current error in the system
	 *  @return the value with which to correct the system
	 */
   private double integral (double error) {
      return 0;
   }

	/**
	 *  Derivative Method
	 *
	 *  @param  error -- the current error in the system
	 *  
	 *  @return the value with which to correct the system
	 */
	private double derivative (double error) {
      // return (KD / (2 * Math.PI)) * (error - lastError);
      return 0;
   }

	/**
	 *  Application of all the PID Control Methods
	 *
	 *  @param  setpoint -- the point at which the system is going to
	 *  @param  value    -- the current value at which the system is currently is
	 *
	 *  @return the value to change the system by
	 */
   public double evaluate (double setpoint, double value) {
      double error = Math.abs(setpoint - value);
      double output = proportional(error) + integral(error) + derivative(error);
      lastError = error;
      return output;
   }
}
