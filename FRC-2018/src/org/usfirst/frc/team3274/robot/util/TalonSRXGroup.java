package org.usfirst.frc.team3274.robot.util;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * A group of CAN motors.
 * 
 * @author AJ Snarr
 */
public class TalonSRXGroup extends TalonSRX {

	private TalonSRX[] motors;

	/**
	 * Creates a group with the given motors.
	 * 
	 * @param motors
	 */
	public TalonSRXGroup(Integer... portNums) {
		// initialize the first motor given
		super(portNums[0]);

		// initialize all other motors given
		this.motors = new TalonSRX[portNums.length - 1];
		for (int i = 1; i < portNums.length; i++) {
			motors[i - 1] = new TalonSRX(portNums[i]);
		}

		// make every motor become a slave to the first motor
		for (TalonSRX mot : this.motors) {
			mot.follow(this);
		}
	}
}
