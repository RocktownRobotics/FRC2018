package org.usfirst.frc.team3274.robot.util;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * A group of TalonSRX motors.
 * 
 * @author AJ Snarr
 */
public class TalonSRXGroup extends WPI_TalonSRX {

	private WPI_TalonSRX[] motors;

	/**
	 * Creates a group with the given motors.
	 * 
	 * <p>
	 * Example usage:
	 * </p>
	 * <p>
	 * TalonSRXGroup group = new TalonSRXGroup(0, 1, 2, 3);
	 * </p>
	 * 
	 * @param portNums
	 *            the port numbers of all motors to add
	 */
	public TalonSRXGroup(Integer... portNums) {
		// initialize the first motor given
		super(portNums[0]);

		// initialize all other motors given
		this.motors = new WPI_TalonSRX[portNums.length - 1];
		for (int i = 1; i < portNums.length; i++) {
			motors[i - 1] = new WPI_TalonSRX(portNums[i]);
		}

		// make every motor become a slave to the first motor
		for (WPI_TalonSRX mot : this.motors) {
			mot.follow(this);
		}
	}
}
