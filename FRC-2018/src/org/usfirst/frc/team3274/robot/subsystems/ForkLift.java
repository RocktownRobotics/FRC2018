/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3274.robot.subsystems;

import org.usfirst.frc.team3274.robot.Robot;
import org.usfirst.frc.team3274.robot.RobotMap;
import org.usfirst.frc.team3274.robot.util.TalonSRXGroup;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 */
public class ForkLift extends Subsystem {

	private WPI_TalonSRX leftClaw = new WPI_TalonSRX(RobotMap.CLAW_MOTOR_LEFT);
	private WPI_TalonSRX rightClaw = new WPI_TalonSRX(RobotMap.CLAW_MOTOR_RIGHT);

	// sets the left and right forklift motors to be together...
	private WPI_TalonSRX _liftMotors = new TalonSRXGroup(RobotMap.LIFT_MOTOR_LEFT, RobotMap.LIFT_MOTOR_RIGHT);

	private Encoder _liftEncoder = new Encoder(RobotMap.LIFT_ENCODER[0], RobotMap.LIFT_ENCODER[1], true,
			EncodingType.k4X);

	/**
	 * @param ejectSpeed
	 *            the motor power at which the cube should be ejected. Between 0 and 1, the negatives, if any, will
	 *            be handled in the eject code.
	 * 
	 * @param thrustTime
	 *            the time to run the ejection motors
	 */
	public void eject(double ejectSpeed, double thrustTime) {

		this.leftClaw.set(ejectSpeed);
		this.rightClaw.set(ejectSpeed);
		Timer.delay(thrustTime);
		this.leftClaw.set(0);
		this.rightClaw.set(0);

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
