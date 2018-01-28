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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controls all DC motors and encoders for the forklift assembly
 */
public class ForkLift extends Subsystem {
	
	public static final double ENCODER_PULSES_PER_REVOLUTION = 1343;
	public static final double WHEEL_DIAMETER = 4.0; // in inches
	
	// sets the left and right forklift motors to be together...
	private WPI_TalonSRX _liftMotors = new TalonSRXGroup(RobotMap.LIFT_MOTOR_LEFT, RobotMap.LIFT_MOTOR_RIGHT);

	private Encoder _liftEncoder = new Encoder(RobotMap.LIFT_ENCODER[0], RobotMap.LIFT_ENCODER[1], true,
			EncodingType.k4X);
	
	public ForkLift() {
		double distancePerPulse; // in feet
		distancePerPulse = (WHEEL_DIAMETER/* in */ * Math.PI) / (ENCODER_PULSES_PER_REVOLUTION * 12.0/* in/ft */);

		_liftEncoder.setDistancePerPulse(distancePerPulse);
	}

	public void setStartPositions() {
		_liftEncoder.reset();
	}
	public double getLiftHeight() {
		return _liftEncoder.getDistance();
	}
	
	
	/**
	 * 
	 * @param power
	 * 		desired motor power
	 */
	public void setLiftPower(double power) {
		_liftMotors.set(power);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
